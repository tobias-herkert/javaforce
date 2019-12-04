/** Client API
 *
 * @author pquiring
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.zip.*;

import javaforce.*;

public class Client extends Thread {
  private Socket s;
  private InputStream is;
  private OutputStream os;
  public void run() {
    //connects to Server
//    Config.current.authFailed = false;
//    Config.save();
    while (Status.active) {
      try {
        s = new Socket(Config.current.server_host, 33200);
        is = s.getInputStream();
        os = s.getOutputStream();
        if (!version()) throw new Exception("version not accepted");
        if (!authenticate()) throw new Exception("wrong password");
        sendHost();
        while (Status.active) {
          int length = readLength();
          if (length == 0) {
            writeLength(0);  //pong
            continue;
          }
          byte cmd[] = read(length);
          switch (new String(cmd)) {
            case "listvolumes":  //list volumes
              listvolumes();
              break;
            case "mount":  //mount volume
              mount();
              break;
            case "unmount":  //unmount volume
              unmount();
              break;
            case "listfolder":  //list folder
              listfolder();
              break;
            case "readfile":  //read file
              readfile();
              break;
          }
        }
      } catch (Exception e) {
        //JFLog.log(e);
        JF.sleep(3000);  //try again in 3 seconds
      }
    }
  }
  public boolean test() {
    Socket s;
    InputStream is;
    OutputStream os;
    try {
      s = new Socket(Config.current.server_host, 33200);
      is = s.getInputStream();
      os = s.getOutputStream();
      if (!version()) throw new Exception("version not accepted");
      if (!authenticate()) throw new Exception("wrong password");
    } catch (Exception e) {
      return false;
    }
    return true;
  }
  public void cancel() {
    try {s.close();} catch (Exception e) {}
  }
  private byte[] read(int size) throws Exception {
    byte[] data = new byte[size];
    int left = size;
    int pos = 0;
    while (left > 0) {
      int read = is.read(data, pos, left);
      if (read == -1) throw new Exception("bad read");
      if (read > 0) {
        pos += read;
        left -= read;
      }
    }
    return data;
  }
  private boolean version() throws Exception {
    //send client version
    os.write(Config.APIVersion.getBytes());
    //read server version
    read(4);
    return true;
  }
  private boolean authenticate() throws Exception {
    byte key[] = read(16);
    MD5 md5 = new MD5();
    String pwd_key = Config.current.password + new String(key);
    md5.add(pwd_key);
    String md5reply = new String(md5.done());
    //send password reply
    os.write(md5reply.getBytes());
    //read reply
    byte[] data = read(4);
    String reply = new String(data);
    if (reply.equals("OKAY")) return true;
//    Config.current.authFailed = true;
//    Config.save();
    return false;
  }
  private void sendHost() throws Exception {
    writeLength(Config.current.this_host.length());
    os.write(Config.current.this_host.getBytes());
  }
  private void writeLength(int len) throws Exception {
    byte[] data = new byte[4];
    LE.setuint32(data, 0, len);
    os.write(data);
  }
  private void writeLength64(long len) throws Exception {
    byte[] data = new byte[8];
    LE.setuint64(data, 0, len);
    os.write(data);
  }
  private int readLength() throws Exception {
    byte[] data = read(4);
    return LE.getuint32(data, 0);
  }
  private long readLength64() throws Exception {
    byte[] data = read(8);
    return LE.getuint64(data, 0);
  }
  private void listvolumes() throws Exception {
    String vols[] = VSS.listVolumes();
    StringBuilder list = new StringBuilder();
    for(int a=0;a<vols.length;a++) {
      list.append(vols[a]);
      list.append("\r\n");
    }
    writeLength(list.length());
    os.write(list.toString().getBytes());
  }
  private void mount() throws Exception {
    //read arg
    int arglen = readLength();
    byte[] arg = read(arglen);
    String vol = new String(arg);
    //remove old mount if exists
    if (new File(Paths.vssPath).exists()) {
      VSS.unmountShadow(Paths.vssPath);
    }
    //delete all old shadow copies
    String shadows[] = VSS.listShadows();
    for(String shadow : shadows) {
      VSS.deleteShadow(shadow.substring(0, 2));
    }
    if (!VSS.createShadow(vol)) {
      writeLength(4);
      os.write("FAIL".getBytes());
      return;
    }
    shadows = VSS.listShadows();
    for(String shadow : shadows) {
      if (shadow.startsWith(vol)) {
        if (VSS.mountShadow(Paths.vssPath, shadow.substring(3))) {
          writeLength(4);
          os.write("OKAY".getBytes());
        } else {
          writeLength(4);
          os.write("FAIL".getBytes());
        }
        return;
      }
    }
    writeLength(4);
    os.write("FAIL".getBytes());
  }
  private void unmount() throws Exception {
    //read arg
    int arglen = readLength();
    byte[] arg = read(arglen);
    String vol = new String(arg);
    VSS.unmountShadow(Paths.vssPath);
    if (!VSS.deleteShadow(vol)) {
      writeLength(4);
      os.write("FAIL".getBytes());
    } else {
      writeLength(4);
      os.write("OKAY".getBytes());
    }
  }
  private void listfolder() throws Exception {
    //read arg
    int arglen = readLength();
    byte[] arg = read(arglen);
    String folder = Paths.vssPath + new String(arg);
    File files[] = new File(folder).listFiles();
    StringBuilder list = new StringBuilder();
    for(File file : files) {
      String name = file.getName();
      if (name.equals(".") || name.equals("..")) continue;
      if (name.length() == 0) continue;
      if (file.isDirectory()) {
        list.append("\\");
        list.append(name);
      } else {
        if (file.length() == 0) continue;  //omit empty files
        list.append(name);
        list.append("|");
        list.append(file.length());
      }
      list.append("\r\n");
    }
    writeLength(list.length());
    os.write(list.toString().getBytes());
  }
  private final static int blocksize = 64 * 1024;
  private void readfile() throws Exception {
    //read arg
    int arglen = readLength();
    byte[] arg = read(arglen);
    //compress file and then send it
    String vssFilename = Paths.vssPath + new String(arg);
    File vssFile = new File(vssFilename);
    if (!vssFile.exists()) {
      //should not happen
      JFLog.log(1, "Error:file not found:" + vssFilename);
      writeLength64(-1);
      return;
    }
    long uncompressed = vssFile.length();
    FileInputStream fis = new FileInputStream(vssFile);
    File tempFile = new File(Paths.tempPath + "\\compressed.dat");
    FileOutputStream fos = new FileOutputStream(tempFile);
    long compressed = Compression.compress(fis, fos, uncompressed);
    fis.close();
    fos.close();
    //send compressed file
    writeLength64(uncompressed);
    writeLength64(compressed);
    fis = new FileInputStream(tempFile);
    long left = compressed;
    byte[] buf = new byte[blocksize];
    while (left > 0) {
      int read = fis.read(buf);
      if (read > 0) {
        os.write(buf, 0, read);
        left -= read;
      }
    }
    fis.close();
    tempFile.delete();
  }
}
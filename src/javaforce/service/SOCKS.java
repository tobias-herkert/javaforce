package javaforce.service;

/** Socks 4/4a/5 Server
 *
 * Default Port 1080
 *
 * https://en.wikipedia.org/wiki/SOCKS
 *
 * @author pquiring
 */

import java.io.*;
import java.net.*;
import java.util.*;

import javaforce.*;
import javaforce.jbus.*;

public class SOCKS extends Thread {
  public final static String busPack = "net.sf.jfsocks";

  public static String getConfigFile() {
    return JF.getConfigPath() + "/jfsocks.cfg";
  }

  public static String getLogFile() {
    return JF.getLogPath() + "/jfsocks.log";
  }

  public static int getBusPort() {
    if (JF.isWindows()) {
      return 33008;
    } else {
      return 777;
    }
  }

  private ServerSocket ss;
  private volatile boolean active;
  private static ArrayList<SocksWorker> socks_workers = new ArrayList<SocksWorker>();
  private static ArrayList<ForwardWorker> forward_workers = new ArrayList<ForwardWorker>();
  private static Object lock = new Object();
  private static boolean socks4 = true, socks5 = false;
  private IP4Port bind = new IP4Port();
  private boolean secure = false;
  private static ArrayList<String> user_pass_list;
  private static ArrayList<Subnet> subnet_list;
  private static ArrayList<Forward> forward_list;

  public SOCKS() {
  }

  public SOCKS(int port, boolean secure) {
    bind.port = port;
    this.secure = secure;
  }

  private static class IP4 {
    public short[] ip = new short[4];
    public boolean setIP(String str) {
      String[] ips = str.split("[.]");
      if (ips.length != 4) {
        JFLog.log("invalid ip:" + str);
        return false;
      }
      for(int a=0;a<4;a++) {
        ip[a] = Short.valueOf(ips[a]);
      }
      return true;
    }
    public InetAddress toInetAddress() {
      try {
        return InetAddress.getByName(toIP4String());
      } catch (Exception e) {
        JFLog.log("Error:IP4.toInetAddress() failed:" + toIP4String());
        return null;
      }
    }
    private String toIP4String() {
      return String.format("%d.%d.%d.%d", ip[0] & 0xff, ip[1] & 0xff, ip[2] & 0xff, ip[3] & 0xff);
    }
    public String toString() {
      return toIP4String();
    }
  }

  private static class IP4Port extends IP4 {
    public int port;
    public boolean setPort(String str) {
      port = JF.atoi(str);
      if (port < 1 || port > 65535) {
        JFLog.log("invalid port:" + port);
        port = -1;
        return false;
      }
      return true;
    }
    public InetSocketAddress toInetSocketAddress() {
      return new InetSocketAddress(toInetAddress(), port);
    }
    public String toString() {
      return super.toString() + ":" + port;
    }
  }

  private static class Subnet {
    public IP4 ip = new IP4(), mask = new IP4();
    public boolean matches(IP4 in) {
      IP4 copy = new IP4();
      for(int a=0;a<4;a++) {
        copy.ip[a] = in.ip[a];
      }
      for(int a=0;a<4;a++) {
        copy.ip[a] &= mask.ip[a];
      }
      for(int a=0;a<4;a++) {
        if (copy.ip[a] != ip.ip[a]) return false;
      }
      return true;
    }
    public void maskIP() {
      for(int a=0;a<4;a++) {
        ip.ip[a] &= mask.ip[a];
      }
    }
    public String toString() {
      return ip.toString() + "/" + mask.toString();
    }
  }

  private static class Forward {
    public IP4Port from = new IP4Port();
    public IP4Port to = new IP4Port();
    public boolean set_from(String ip_port) {
      int idx = ip_port.indexOf(':');
      if (idx == -1) return false;
      String ip = ip_port.substring(0, idx);
      String port = ip_port.substring(idx+1);
      if (!from.setIP(ip)) return false;
      if (!from.setPort(port)) return false;
      return true;
    }
    public boolean set_to(String ip_port) {
      int idx = ip_port.indexOf(':');
      if (idx == -1) return false;
      String ip = ip_port.substring(0, idx);
      String port = ip_port.substring(idx+1);
      if (!to.setIP(ip)) return false;
      if (!to.setPort(port)) return false;
      return true;
    }
    public String toString() {
      return from.toString() + " -> " + to.toString();
    }
  }

  public void addUserPass(String user, String pass) {
    String user_pass = user + ":" + pass;
    user_pass_list.add(user_pass);
  }

  public static void addSession(SocksWorker sess) {
    synchronized(lock) {
      socks_workers.add(sess);
    }
  }

  public static void removeSession(SocksWorker sess) {
    synchronized(lock) {
      socks_workers.remove(sess);
    }
  }

  public static String getKeyFile() {
    return JF.getConfigPath() + "/jfsocks.key";
  }

  public void run() {
    JFLog.append(JF.getLogPath() + "/jfsocks.log", true);
    JFLog.setRetention(30);
    JFLog.log("jfSocks starting...");
    try {
      loadConfig();
      busClient = new JBusClient(busPack, new JBusMethods());
      busClient.setPort(getBusPort());
      busClient.start();
      if (secure) {
        JFLog.log("CreateServerSocketSSL");
        KeyMgmt keys = new KeyMgmt();
        if (new File(getKeyFile()).exists()) {
          FileInputStream fis = new FileInputStream(getKeyFile());
          keys.open(fis, "password".toCharArray());
          fis.close();
        } else {
          JFLog.log("Warning:Server SSL Keys not generated!");
        }
        ss = JF.createServerSocketSSL(keys);
      } else {
        ss = new ServerSocket();
      }
      ss.bind(bind.toInetSocketAddress());
      active = true;
      for(Forward f : forward_list) {
        ForwardWorker fw = new ForwardWorker(f);
        fw.start();
        forward_workers.add(fw);
      }
      while (active) {
        Socket s = ss.accept();
        SocksWorker sess = new SocksWorker(s);
        addSession(sess);
        sess.start();
      }
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  public void close() {
    active = false;
    try { ss.close(); } catch (Exception e) {}
    synchronized(lock) {
      SocksWorker[] socks = socks_workers.toArray(new SocksWorker[0]);
      for(SocksWorker s : socks) {
        s.close();
      }
      socks_workers.clear();

      ForwardWorker[] forwards = forward_workers.toArray(new ForwardWorker[0]);
      for(ForwardWorker f : forwards) {
        f.close();
      }
      forward_workers.clear();
    }
  }

  enum Section {None, Global};

  private final static String defaultConfig
    = "[global]\n"
    + "port=1080\n"
    + "#bind=192.168.100.2\n"
    + "secure=false\n"
    + "socks4=true\n"
    + "socks5=false\n"
    + "#auth=user:pass\n"
    + "#ipnet=192.168.0.0/255.255.255.0\n"
    + "#ip=192.168.5.6\n"
    + "#forward=192.168.100.2:80,192.168.200.2:80\n";

  private void loadConfig() {
    JFLog.log("loadConfig");
    user_pass_list = new ArrayList<String>();
    subnet_list = new ArrayList<Subnet>();
    forward_list = new ArrayList<Forward>();
    Section section = Section.None;
    bind.setIP("0.0.0.0");  //bind to all interfaces
    bind.port = 1080;
    try {
      BufferedReader br = new BufferedReader(new FileReader(getConfigFile()));
      StringBuilder cfg = new StringBuilder();
      while (true) {
        String ln = br.readLine();
        if (ln == null) break;
        cfg.append(ln);
        cfg.append("\n");
        ln = ln.trim();
        if (ln.startsWith("#")) continue;
        if (ln.length() == 0) continue;
        if (ln.equals("[global]")) {
          section = Section.Global;
          continue;
        }
        int idx = ln.indexOf("=");
        if (idx == -1) continue;
        String key = ln.substring(0, idx);
        String value = ln.substring(idx + 1);
        switch (section) {
          case None:
          case Global:
            switch (key) {
              case "port":
                bind.port = Integer.valueOf(ln.substring(5));
                break;
              case "bind":
                if (!bind.setIP(value)) {
                  JFLog.log("SOCKS:bind:Invalid IP:" + value);
                  break;
                }
                break;
              case "secure":
                secure = value.equals("true");
                break;
              case "socks4":
                socks4 = value.equals("true");
                break;
              case "socks5":
                socks5 = value.equals("true");
                break;
              case "auth":
                user_pass_list.add(value);
                break;
              case "ipnet": {
                Subnet subnet = new Subnet();
                idx = value.indexOf('/');
                if (idx == -1) {
                  JFLog.log("SOCKS:Invalid IP Subnet:" + value);
                  break;
                }
                String ip = value.substring(0, idx);
                String mask = value.substring(idx + 1);
                if (!subnet.ip.setIP(ip)) {
                  JFLog.log("SOCKS:Invalid IP:" + ip);
                  break;
                }
                if (!subnet.mask.setIP(mask)) {
                  JFLog.log("SOCKS:Invalid netmask:" + mask);
                  break;
                }
                JFLog.log("Allow IP Network=" + subnet.toString());
                subnet.maskIP();
                subnet_list.add(subnet);
                break;
              }
              case "ip": {
                Subnet subnet = new Subnet();
                if (!subnet.ip.setIP(value)) {
                  JFLog.log("SOCKS:Invalid IP:" + value);
                  break;
                }
                subnet.mask.setIP("255.255.255.255");
                JFLog.log("Allow IP Address=" + subnet.toString());
                subnet.maskIP();
                subnet_list.add(subnet);
                break;
              }
              case "forward": {
                String[] p = value.split(",");
                if (p.length != 2) {
                  JFLog.log("forward:Invalid option:" + value);
                  break;
                }
                Forward forward = new Forward();
                if (!forward.set_from(p[0])) {
                  JFLog.log("forward:Invalid from:" + p[0]);
                  break;
                }
                if (!forward.set_to(p[1])) {
                  JFLog.log("forward:Invalid to:" + p[1]);
                  break;
                }
                forward_list.add(forward);
                break;
              }
            }
            break;
        }
      }
      br.close();
      config = cfg.toString();
    } catch (FileNotFoundException e) {
      //create default config
      JFLog.log("config not found, creating defaults.");
      try {
        FileOutputStream fos = new FileOutputStream(getConfigFile());
        fos.write(defaultConfig.getBytes());
        fos.close();
        config = defaultConfig;
      } catch (Exception e2) {
        JFLog.log(e2);
      }
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  private static boolean ip_allowed(String ip4) {
    if (subnet_list.size() == 0) return true;
    IP4 target = new IP4();
    if (!target.setIP(ip4)) return false;
    for(Subnet net : subnet_list) {
      if (net.matches(target)) {
        return true;
      }
    }
    return false;
  }

  public static class SocksWorker extends Thread {
    private Socket c;
    private Socket o;
    private ProxyData pd1, pd2;
    private boolean connected = false;
    private InputStream cis = null;
    private OutputStream cos = null;
    private byte[] req = new byte[1500];
    private int reqSize = 0;
    public SocksWorker(Socket s) {
      c = s;
    }
    public void close() {
      if (c != null) {
        try { c.close(); } catch (Exception e) {}
      }
      if (o != null) {
        try { o.close(); } catch (Exception e) {}
      }
      if (pd1 != null) {
        pd1.close();
      }
      if (pd2 != null) {
        pd2.close();
      }
    }
    public void run() {
      //request = 0x04 0x01 port16 ip32 user_id_null [domain_name_null]
      //reply   = 0x00 0x5a reserved[6]   //0x5b = failed
      try {
        JFLog.log("Session start");
        cis = c.getInputStream();
        cos = c.getOutputStream();
        //read request
        while (c.isConnected()) {
          int read = cis.read(req, reqSize, 1500 - reqSize);
          if (read < 0) break;
          reqSize += read;
          if (reqSize == 0) continue;
          if (req[0] == 0x04) {
            if (reqSize < 8) continue;
            String ip3 = String.format("%d.%d.%d", req[4] & 0xff, req[5] & 0xff, req[6] & 0xff);
            if (ip3.equals("0.0.0")) {
              //domain request
              //look for user_id_null and domain_null
              int null_count = 0;
              for(int a=8;a<reqSize;a++) {
                if (req[a] == 0) null_count++;
              }
              if (null_count == 2) break;
              if (null_count > 2) throw new Exception("SOCKS4:bad request:too many nulls:expect=2");
            } else {
              //ip4 request
              //look for user_id_null
              int null_count = 0;
              for(int a=8;a<reqSize;a++) {
                if (req[a] == 0) null_count++;
              }
              if (null_count == 1) break;
              if (null_count > 1) throw new Exception("SOCKS4:bad request:too many nulls:expect=1");
            }
          } else if (req[0] == 0x05) {
            if (reqSize < 3) continue;
            int nauth = req[1] & 0xff;
            if (reqSize == nauth + 2) break;
          } else {
            throw new Exception("bad request:not SOCKS4/5 request");
          }
        }
        switch (req[0]) {
          case 0x04: socks4(); break;
          case 0x05: socks5(); break;
          default: throw new Exception("bad request:not SOCKS4/5 request");
        }
      } catch (Exception e) {
        if (!(e instanceof SocketException)) JFLog.log(e);
        if (!connected) {
          byte[] reply = new byte[8];
          reply[0] = 0x00;
          reply[1] = 0x5b;  //failed
          try {cos.write(reply);} catch (Exception e2) {}
        }
      }
      close();
      removeSession(this);
    }

    private void socks4() throws Exception {
      JFLog.log("socks4 connection started");
      if (req[1] != 0x01) throw new Exception("SOCKS4:bad request:not open socket request");
      if (!socks4) throw new Exception("SOCKS4:not enabled");
      int port = BE.getuint16(req, 2);
      String user_id;  //ignored
      String ip3 = String.format("%d.%d.%d", req[4] & 0xff, req[5] & 0xff, req[6] & 0xff);
      String dest;
      if (ip3.equals("0.0.0")) {
        int user_null = -1;
        int domain_null = -1;
        for(int a=8;a<reqSize;a++) {
          if (req[a] == 0) {
            if (user_null == -1) {
              user_null = a;
            } else {
              domain_null = a;
            }
          }
        }
        user_id = new String(req, 8, user_null - 8);
        dest = new String(req, 8, domain_null - 8);
        dest = InetAddress.getByName(dest).getHostAddress();
      } else {
        dest = String.format("%d.%d.%d.%d", req[4] & 0xff, req[5] & 0xff, req[6] & 0xff, req[7] & 0xff);
      }
      if (!ip_allowed(dest)) throw new Exception("SOCKS:Target IP outside of allowed IP Subnets:" + dest);
      o = new Socket(dest, port);
      connected = true;
      byte[] reply = new byte[8];
      reply[0] = 0x00;
      reply[1] = 0x5a;  //success
      cos.write(reply);
      //now just proxy data back and forth
      pd1 = new ProxyData(c,o,"s4-1");
      pd1.start();
      pd2 = new ProxyData(o,c,"s4-2");
      pd2.start();
      pd1.join();
      pd2.join();
      JFLog.log("SOCKS4:Session end");
    }

    private void socks5() throws Exception {
      JFLog.log("socks5 connection started");
      if (!socks5) throw new Exception("SOCKS5:not enabled");
      //req = 0x05 nauth auth_types[]
      int nauth = req[1] & 0xff;
      boolean auth_type_2 = false;
      for(int a=0;a<nauth;a++) {
        if (req[a+2] == 0x02) {
          auth_type_2 = true;
          break;
        }
      }
      if (!auth_type_2) throw new Exception("SOCKS5:auth not supported");
      byte[] reply = new byte[2];
      reply[0] = 0x05;
      reply[1] = 0x02;
      cos.write(reply);
      //read username/password
      reqSize = 0;
      while (c.isConnected()) {
        int read = cis.read(req, reqSize, 1500 - reqSize);
        if (read < 0) break;
        reqSize += read;
        if (reqSize < 5) continue;
        if (req[0] != 0x01) throw new Exception("SOCKS5:invalid auth request");
        int user_len = req[1] & 0xff;
        if (reqSize < 3 + user_len) continue;
        int pass_len = req[2 + user_len] & 0xff;
        if (reqSize < 3 + user_len + pass_len) continue;
        break;
      }
      int user_len = req[1] & 0xff;
      int pass_len = req[2 + user_len] & 0xff;
      String user = new String(req, 2, user_len);
      String pass = new String(req, 3 + user_len, pass_len);
      String user_pass = user + ":" + pass;
      if (!user_pass_list.contains(user_pass)) {
        throw new Exception("SOCKS5:user/pass not authorized");
      }
      reply = new byte[2];
      reply[0] = 0x01;  //version 1
      reply[1] = 0x00;  //authorized
      cos.write(reply);
      //read connect request
      reqSize = 0;
      int toRead = 10;
      while (c.isConnected()) {
        int read = cis.read(req, reqSize, toRead - reqSize);
        if (read < 0) break;
        reqSize += read;
        if (reqSize < 10) continue;
        int dest_type = req[3] & 0xff;
        if (dest_type == 0x01) {
          //ip4
          if (reqSize == toRead) break;
        } else if (dest_type == 0x03) {
          //domain name
          int domain_len = req[4] & 0xff;
          toRead = 5 + domain_len + 2;
          if (reqSize == toRead) break;
        } else if (dest_type == 0x04) {
          throw new Exception("SOCKS5:IP6 not supported");
        } else {
          throw new Exception("SOCKS5:dest_type not supported:" + dest_type);
        }
      }
      if (req[0] != 0x05) throw new Exception("SOCKS5:bad connection request:version != 0x05");
      if (req[1] != 0x01) throw new Exception("SOCKS5:bad connection request:cmd not supported:" + req[1]);
      //req[2] = reserved
      String dest = null;
      int port = BE.getuint16(req, reqSize - 2);
      switch (req[3]) {
        case 0x01:
          dest = String.format("%d.%d.%d.%d", req[4] & 0xff, req[5] & 0xff, req[6] & 0xff, req[7] & 0xff);
          break;
        case 0x03:
          dest = new String(req, 5, req[4] & 0xff);
          dest = InetAddress.getByName(dest).getHostAddress();
          break;
        default:
          throw new Exception("SOCKS5:bad connection request:addr type not supported:" + req[3]);
      }
      if (!ip_allowed(dest)) throw new Exception("SOCKS:Target IP outside of allowed IP Subnets:" + dest);
      reply = new byte[reqSize];
      System.arraycopy(req, 0, reply, 0, reqSize);
      reply[1] = 0x00;  //success
      cos.write(reply);
      JFLog.log("SOCKS5:Connect:" + dest + ":" + port);
      o = new Socket(dest, port);
      connected = true;
      //now just proxy data back and forth
      pd1 = new ProxyData(c,o,"s5-1");
      pd1.start();
      pd2 = new ProxyData(o,c,"s5-2");
      pd2.start();
      pd1.join();
      pd2.join();
      JFLog.log("SOCKS5:Session end");
    }
  }

  public class ForwardWorker extends Thread {
    private Forward forward;
    private ServerSocket ss;
    public ForwardWorker(Forward forward) {
      this.forward = forward;
    }
    public void run() {
      try {
        ss = new ServerSocket();
        ss.bind(forward.from.toInetSocketAddress());
        while (active) {
          Socket from = ss.accept();
          try {
            Socket to = new Socket(forward.to.toInetAddress(), forward.to.port);
            ProxyData pd1 = new ProxyData(from, to, "f-1");
            pd1.start();
            ProxyData pd2 = new ProxyData(to, from, "f-2");
            pd2.start();
          } catch (Exception e) {
            if (!(e instanceof SocketException)) JFLog.log(e);
          }
        }
      } catch (Exception e) {
        if (!(e instanceof SocketException)) JFLog.log(e);
      }
    }
    public void close() {
      try { ss.close(); } catch (Exception e) {}
    }
  }

  public static class ProxyData extends Thread {
    private Socket sRead;
    private Socket sWrite;
    private volatile boolean active;
    private String name;
    public ProxyData(Socket sRead, Socket sWrite, String name) {
      this.sRead = sRead;
      this.sWrite = sWrite;
      this.name = name;
    }
    public void run() {
      try {
        InputStream is = sRead.getInputStream();
        OutputStream os = sWrite.getOutputStream();
        byte[] buf = new byte[1500];
        active = true;
        while (active) {
          int read = is.read(buf);
          if (read < 0) break;
          if (read > 0) {
            os.write(buf, 0, read);
          }
        }
      } catch (Exception e) {
        try {sRead.close();} catch (Exception e2) {}
        try {sWrite.close();} catch (Exception e2) {}
        if (!(e instanceof SocketException)) JFLog.log(e);
      }
    }
    public void close() {
      active = false;
    }
  }

  private static SOCKS socks;

  public static void serviceStart(String[] args) {
    if (JF.isWindows()) {
      busServer = new JBusServer(getBusPort());
      busServer.start();
      while (!busServer.ready) {
        JF.sleep(10);
      }
    }
    socks = new SOCKS();
    socks.start();
  }

  public static void serviceStop() {
    socks.close();
  }

  public static void main(String[] args) {
    serviceStart(args);
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        serviceStop();
      }
    });
  }

  private static JBusServer busServer;
  private JBusClient busClient;
  private String config;

  public static class JBusMethods {
    public void getConfig(String pack) {
      socks.busClient.call(pack, "getConfig", socks.busClient.quote(socks.busClient.encodeString(socks.config)));
    }
    public void setConfig(String cfg) {
      //write new file
      JFLog.log("setConfig");
      try {
        FileOutputStream fos = new FileOutputStream(getConfigFile());
        fos.write(JBusClient.decodeString(cfg).getBytes());
        fos.close();
      } catch (Exception e) {
        JFLog.log(e);
      }
    }
    public void restart() {
      JFLog.log("restart");
      socks.close();
      socks = new SOCKS();
      socks.start();
    }

    public void genKeys(String pack) {
      if (KeyMgmt.keytool(new String[] {
        "-genkey", "-debug", "-alias", "jfsocks", "-keypass", "password", "-storepass", "password",
        "-keystore", getKeyFile(), "-validity", "3650", "-dname", "CN=jfsocks.sourceforge.net, OU=user, O=server, C=CA",
        "-keyalg" , "RSA", "-keysize", "2048"
      })) {
        JFLog.log("Generated Keys");
        socks.busClient.call(pack, "getKeys", socks.busClient.quote("OK"));
      } else {
        socks.busClient.call(pack, "getKeys", socks.busClient.quote("ERROR"));
      }
    }
  }
}

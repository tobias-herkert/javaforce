/** SOCKS Client
 *
 *  Simple client that redirects local port thru SOCKS server to remote host.
 *
 * @author pquiring
 */

import java.io.*;
import java.net.*;
import javax.net.ssl.*;

import java.awt.*;
import java.awt.event.*;

import javaforce.*;

public class Main extends javax.swing.JFrame implements ActionListener {

  /**
   * Creates new form SOCKSClient
   */
  public Main() {
    initComponents();
    JFAWT.centerWindow(this);
    genkeys.setVisible(false);  //SSL keys not needed on client side
    JFImage appicon = new JFImage();
    appicon.loadPNG(this.getClass().getClassLoader().getResourceAsStream("jfsocks.png"));
    setIconImage(appicon.getImage());
    trayicon = new JFImage();
    trayicon.loadPNG(this.getClass().getClassLoader().getResourceAsStream("jfsocks_tray.png"));
    addTrayIcon();
    try {
      loadConfig();
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel7 = new javax.swing.JLabel();
    buttonGroup1 = new javax.swing.ButtonGroup();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    local_port = new javax.swing.JTextField();
    remote_host = new javax.swing.JTextField();
    secure = new javax.swing.JCheckBox();
    connect = new javax.swing.JButton();
    remote_port = new javax.swing.JTextField();
    jLabel5 = new javax.swing.JLabel();
    socks_server = new javax.swing.JTextField();
    jLabel6 = new javax.swing.JLabel();
    socks4 = new javax.swing.JRadioButton();
    socks5 = new javax.swing.JRadioButton();
    jPanel1 = new javax.swing.JPanel();
    jLabel8 = new javax.swing.JLabel();
    user = new javax.swing.JTextField();
    jLabel9 = new javax.swing.JLabel();
    pass = new javax.swing.JTextField();
    jLabel10 = new javax.swing.JLabel();
    socks_port = new javax.swing.JTextField();
    genkeys = new javax.swing.JButton();
    jLabel11 = new javax.swing.JLabel();
    jLabel12 = new javax.swing.JLabel();
    jLabel13 = new javax.swing.JLabel();
    profiles = new javax.swing.JComboBox<>();
    save = new javax.swing.JButton();
    jButton1 = new javax.swing.JButton();

    jLabel7.setText("jLabel7");

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("SOCKS Client");

    jLabel1.setText("Redirects local port thru SOCKS Server to remote ip/port");

    jLabel2.setText("Local Port");

    jLabel3.setText("Remote Port");

    jLabel4.setText("Remote Host/IP");

    secure.setText("Server is Secure (SSL)");

    connect.setText("Start");
    connect.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        connectActionPerformed(evt);
      }
    });

    jLabel5.setText("SOCKS Server");

    jLabel6.setText("SOCKS Type");

    buttonGroup1.add(socks4);
    socks4.setSelected(true);
    socks4.setText("SOCKS4");

    buttonGroup1.add(socks5);
    socks5.setText("SOCKS5");

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("SOCKS5 Authentication"));

    jLabel8.setText("User");

    jLabel9.setText("Pass");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel8)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(user))
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel9)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(pass)))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel8)
          .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel9)
          .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jLabel10.setText("SOCKS Port");

    genkeys.setText("Generate SSL Keys");
    genkeys.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        genkeysActionPerformed(evt);
      }
    });

    jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ArrowDown.png"))); // NOI18N

    jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ArrowDown.png"))); // NOI18N

    jLabel13.setText("Profile");

    profiles.setEditable(true);
    profiles.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        profilesItemStateChanged(evt);
      }
    });

    save.setText("Save");
    save.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        saveActionPerformed(evt);
      }
    });

    jButton1.setText("Delete");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(genkeys)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(connect))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel4)
              .addComponent(jLabel3))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(remote_port)
              .addComponent(remote_host)))
          .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel2)
            .addGap(17, 17, 17)
            .addComponent(local_port))
          .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel5)
              .addComponent(jLabel10))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(socks_port)
              .addComponent(socks_server)))
          .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel13)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(profiles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(save)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton1))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel1)
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(socks4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socks5))
              .addComponent(secure))
            .addGap(0, 58, Short.MAX_VALUE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel13)
          .addComponent(profiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(save)
          .addComponent(jButton1))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(local_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel11)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel5)
          .addComponent(socks_server, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel10)
          .addComponent(socks_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel6)
          .addComponent(socks4)
          .addComponent(socks5))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(secure)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel12)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4)
          .addComponent(remote_host, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(remote_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(connect)
          .addComponent(genkeys))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectActionPerformed
    connect();
  }//GEN-LAST:event_connectActionPerformed

  private void genkeysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genkeysActionPerformed
    genKeys();
  }//GEN-LAST:event_genkeysActionPerformed

  private void profilesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_profilesItemStateChanged
    getProfile();
    loadProfile();
  }//GEN-LAST:event_profilesItemStateChanged

  private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
    saveProfile();
  }//GEN-LAST:event_saveActionPerformed

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    deleteProfile();
  }//GEN-LAST:event_jButton1ActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Main().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.ButtonGroup buttonGroup1;
  private javax.swing.JButton connect;
  private javax.swing.JButton genkeys;
  private javax.swing.JButton jButton1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel12;
  private javax.swing.JLabel jLabel13;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JTextField local_port;
  private javax.swing.JTextField pass;
  private javax.swing.JComboBox<String> profiles;
  private javax.swing.JTextField remote_host;
  private javax.swing.JTextField remote_port;
  private javax.swing.JButton save;
  private javax.swing.JCheckBox secure;
  private javax.swing.JRadioButton socks4;
  private javax.swing.JRadioButton socks5;
  private javax.swing.JTextField socks_port;
  private javax.swing.JTextField socks_server;
  private javax.swing.JTextField user;
  // End of variables declaration//GEN-END:variables

  public Server server;

  public static class Profile implements Serializable {
    public String name;
    public String socks_server;
    public int socks_port;
    public boolean secure;
    public boolean socks4, socks5;
    public String user, pass;
    public int local_port;
    public String remote_host;
    public int remote_port;

    public Profile() {
      name = "new";
      socks_server = "";
      socks_port = 1080;
      socks4 = true;
      socks5 = false;
      user = "";
      pass = "";
      local_port = 1080;
      remote_host = "";
      remote_port = 0;
    }
  }

  public static class Config implements Serializable {
    public Profile[] profiles;
    public int profile;

    public Config() {
      profiles = new Profile[1];
      profiles[0] = new Profile();
      profile = 0;
    }
  }

  private static Profile profile = new Profile();
  private static Config config = new Config();

  public void connect() {
    String str;
    int num;
    try {
      if (server != null) {
        server.close();
        server = null;
        connect.setText("Start");
        setState(true);
        return;
      }

      str = SQL.numbers(local_port.getText());
      if (str.length() == 0) throw new Exception("Invalid local port");
      num = Integer.valueOf(str);
      if (num < 1 || num > 65535) throw new Exception("Invalid local port");
      profile.local_port = num;

      profile.socks_server = socks_server.getText();
      str = SQL.numbers(socks_port.getText());
      if (str.length() == 0) throw new Exception("Invalid socks port");
      num = Integer.valueOf(str);
      if (num < 1 || num > 65535) throw new Exception("Invalid socks port");
      profile.socks_port = num;

      profile.remote_host = remote_host.getText();
      is_remote_ip4 = getIP(new byte[4], 0, profile.remote_host);
      str = SQL.numbers(remote_port.getText());
      if (str.length() == 0) throw new Exception("Invalid remote port");
      num = Integer.valueOf(str);
      if (num < 1 || num > 65535) throw new Exception("Invalid remote port");
      profile.remote_port = num;

      profile.socks4 = socks4.isSelected();
      profile.socks5 = socks5.isSelected();
      profile.secure = secure.isSelected();

      profile.user = user.getText();
      profile.pass = pass.getText();

      server = new Server();
      server.ss = new ServerSocket(profile.local_port);
      server.start();
      connect.setText("Cancel");
      setState(false);
      saveConfig();
    } catch (Exception e) {
      e.printStackTrace();
      JFAWT.showError("Error", e.toString());
    }
  }

  public void setState(boolean state) {
    socks_server.setEditable(state);
    socks_port.setEditable(state);
    socks4.setEnabled(state);
    socks5.setEnabled(state);
    secure.setEnabled(state);
    user.setEditable(state);
    pass.setEnabled(state);
    local_port.setEnabled(state);
    remote_host.setEnabled(state);
    remote_port.setEnabled(state);
  }

  public static class Server extends Thread {
    public ServerSocket ss;
    public boolean active;
    public void run() {
      active = true;
      while (active) {
        try {
          Socket s = ss.accept();
          Client client = new Client();
          client.c = s;
          client.start();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    public void close() {
      active = false;
      try {
        ss.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static boolean is_remote_ip4;

  public static boolean getIP(byte[] packet, int offset, String ip) {
    String[] ip4 = ip.split("[.]");
    if (ip4.length != 4) return false;
    for(int a=0;a<4;a++) {
      int value = Integer.valueOf(ip4[a]);
      if (value < 0 || value > 255) return false;
      packet[offset+a] = (byte)value;
    }
    return true;
  }

  public static void printArray(byte[] array, String msg) {
    StringBuilder sb = new StringBuilder();
    sb.append(msg);
    sb.append("=[");
    for(int a=0;a<array.length;a++) {
      if (a > 0) sb.append(",");
      sb.append(String.format("%02x", array[a] & 0xff));
    }
    sb.append("]");
    JFLog.log(sb.toString());
  }

  public static class Client extends Thread {
    public Socket c, s;
    //public InputStream cis;
    //public OutputStream cos;
    public InputStream sis;
    public OutputStream sos;
    private ProxyData pd1, pd2;
    public void run() {
      byte[] req;
      byte[] reply;
      try {
        //cis = c.getInputStream();
        //cos = c.getOutputStream();
        //connect to SOCKS server
        JFLog.log("Connecting to:" + profile.socks_server + ":" + profile.socks_port + ":secure=" + profile.secure);
        if (profile.secure) {
          KeyMgmt keys = new KeyMgmt();
          if (new File(getKeyFile()).exists()) {
            FileInputStream fis = new FileInputStream(getKeyFile());
            keys.open(fis, "password".toCharArray());
            fis.close();
          } else {
            JFLog.log("Warning:Client SSL keys not generated!");
          }
          s = JF.connectSSL(profile.socks_server, profile.socks_port/*, keys*/);
        } else {
          s = new Socket(profile.socks_server, profile.socks_port);
        }
        if (s == null) throw new Exception("Connection to SOCKS server failed");
        sis = s.getInputStream();
        sos = s.getOutputStream();
        JFLog.log("Connected to SOCKS server");
        //init connection
        if (profile.socks4) {
          if (is_remote_ip4) {
            //ip4
            req = new byte[9];
            req[0] = 0x04;  //SOCKS ver 4
            req[1] = 0x01;  //connect
            BE.setuint16(req, 2, profile.remote_port);
            getIP(req, 4, profile.remote_host);
            sos.write(req);
            reply = new byte[8];
            if (!JF.readAll(sis, reply, 0, reply.length)) {
              throw new Exception("SOCKS4:read failed");
            }
            if (reply[1] != 0x5a) throw new Exception("SOCKS4 connection failed");
          } else {
            //domain
            byte[] domain = profile.remote_host.getBytes();
            req = new byte[9 + domain.length + 1];
            req[0] = 0x04;  //SOCKS ver 4
            req[1] = 0x01;  //connect
            BE.setuint16(req, 2, profile.remote_port);
            getIP(req, 4, "0.0.0.1");
            req[8] = 0x00;  //user-id null
            System.arraycopy(domain, 0, req, 9, domain.length);
            sos.write(req);
            reply = new byte[8];
            if (!JF.readAll(sis, reply, 0, reply.length)) {
              throw new Exception("SOCKS4:read failed");
            }
            if (reply[1] != 0x5a) throw new Exception("SOCKS4 connection failed");
          }
        } else {
          req = new byte[3];
          //send auth type
          req[0] = 0x05;  //SOCKS ver 5
          req[1] = 1;  //nauth
          req[2] = 0x02;  //user/pass auth type
          sos.write(req);
          reply = new byte[2];
          if (!JF.readAll(sis, reply, 0, reply.length)) {
            throw new Exception("SOCKS5:read failed");
          }
          if (reply[1] != 0x02) throw new Exception("SOCKS5 auth type not supported");
          //send user/pass
          byte[] user = profile.user.getBytes();
          byte[] pass = profile.pass.getBytes();
          req = new byte[3 + user.length + pass.length];
          req[0] = 0x01;  //auth type ver
          req[1] = (byte)user.length;
          System.arraycopy(user, 0, req, 2, user.length);
          req[2 + user.length] = (byte)pass.length;
          System.arraycopy(pass, 0, req, 3 + user.length, pass.length);
          sos.write(req);
          reply = new byte[2];
          if (!JF.readAll(sis, reply, 0, reply.length)) {
            throw new Exception("SOCKS5:read failed");
          }
          if (reply[1] != 0x00) throw new Exception("SOCKS5 auth failed");
          if (is_remote_ip4) {
            req = new byte[10];
            req[0] = 0x05;  //SOCKS ver 5
            req[1] = 0x01;  //connect command
            //req[2] = reserved
            req[3] = 0x01;  //IP4 address
            getIP(req, 4, profile.remote_host);
            BE.setuint16(req, 8, profile.remote_port);
            sos.write(req);
            reply = new byte[10];
            if (!JF.readAll(sis, reply, 0, reply.length)) {
              throw new Exception("SOCKS5:read failed");
            }
            if (reply[1] != 0x00) throw new Exception("SOCKS5 connect failed");
          } else {
            byte[] domain = profile.remote_host.getBytes();
            req = new byte[5 + domain.length + 2];
            req[0] = 0x05;  //SOCKS ver 5
            req[1] = 0x01;  //connect command
            //req[2] = reserved
            req[3] = 0x03;  //domain name
            req[4] = (byte)domain.length;
            System.arraycopy(domain, 0, req, 5, domain.length);
            BE.setuint16(req, 5 + domain.length, profile.remote_port);
            sos.write(req);
            reply = new byte[req.length];
            if (!JF.readAll(sis, reply, 0, reply.length)) {
              throw new Exception("SOCKS5:read failed");
            }
            if (reply[1] != 0x00) throw new Exception("SOCKS5 connect failed");
          }
        }
        JFLog.log("Connection complete");
        //relay data between sockets
        pd1 = new ProxyData(c,s,"1");
        pd1.start();
        pd2 = new ProxyData(s,c,"2");
        pd2.start();
        pd1.join();
        pd2.join();
      } catch (Exception e) {
        e.printStackTrace();
      }
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
          if (read < 0) throw new Exception("bad read:pd" + name);
          if (read > 0) {
            os.write(buf, 0, read);
          }
        }
      } catch (Exception e) {
        try {sRead.close();} catch (Exception e2) {}
        try {sWrite.close();} catch (Exception e2) {}
      }
    }
    public void close() {
      active = false;
    }
  }

  public void loadConfig() {
    config = (Config)JF.readObject(JF.getUserPath() + "/.jfsocks-profiles.cfg");
    if (config == null) config = new Config();
    for(Profile p : config.profiles) {
      profiles.addItem(p.name);
    }
    profile = config.profiles[config.profile];
    loadProfile();
    profiles.setSelectedIndex(config.profile);
  }

  public void saveConfig() {
    JF.writeObject(config, JF.getUserPath() + "/.jfsocks-profiles.cfg");
  }

  public static String getKeyFile() {
    return JF.getUserPath() + "/.jfsocks.key";
  }

  public void genKeys() {
    if (KeyMgmt.keytool(new String[] {
      "-genkey", "-debug", "-alias", "jfsocks", "-keypass", "password", "-storepass", "password",
      "-keystore", getKeyFile(), "-validity", "3650", "-dname", "CN=jfsocks.sourceforge.net, OU=user, O=client, C=CA",
      "-keyalg" , "RSA", "-keysize", "2048"
    })) {
      JFAWT.showMessage("GenKeys", "OK");
    } else {
      JFAWT.showMessage("GenKeys", "Error");
    }
  }

  public void actionPerformed(ActionEvent e) {
    Object o = e.getSource();
    if (o == exit) {
      System.exit(0);
    }
    if (o == show) {
      setVisible(true);
    }
  }

  private void addTrayIcon() {
    //create tray icon
    PopupMenu popup = new PopupMenu();
    show = new MenuItem("Show");
    show.addActionListener(this);
    popup.add(show);
    popup.addSeparator();
    exit = new MenuItem("Exit");
    exit.addActionListener(this);
    popup.add(exit);
    icon = new JFIcon();
    icon.create(trayicon, popup, this, "Socks");
  }

  private JFImage trayicon;
  private JFIcon icon;
  private MenuItem exit, show;

  private void getProfile() {
    int idx = profiles.getSelectedIndex();
    if (idx == -1) return;
    if (idx >= config.profiles.length) return;
    config.profile = idx;
    profile = config.profiles[idx];
    loadProfile();
  }

  private void loadProfile() {
    //apply to fields
    local_port.setText(Integer.toString(profile.local_port));
    if (profile.socks_server != null) {
      socks_server.setText(profile.socks_server);
    }
    socks_port.setText(Integer.toString(profile.socks_port));
    socks4.setSelected(profile.socks4);
    socks5.setSelected(profile.socks5);
    secure.setSelected(profile.secure);
    if (profile.user != null) {
      user.setText(profile.user);
    }
    if (profile.pass != null) {
      pass.setText(profile.pass);
    }
    if (profile.remote_host != null) {
      remote_host.setText(profile.remote_host);
    }
    remote_port.setText(Integer.toString(profile.remote_port));
  }

  private void saveProfile() {
    String name = (String)profiles.getSelectedItem();
    if (name.length() == 0) return;
    for(Profile p : config.profiles) {
      if (p.name.equals(name)) {
        saveProfile(p);
        profile = p;
        saveConfig();
        return;
      }
    }
    //create new profile
    Profile p = new Profile();
    p.name = name;
    config.profiles = java.util.Arrays.copyOf(config.profiles, config.profiles.length + 1);
    config.profiles[config.profiles.length - 1] = p;
    saveProfile(p);
    profile = p;
    saveConfig();
    profiles.addItem(name);
  }

  private void saveProfile(Profile p) {
    p.local_port = Integer.valueOf(local_port.getText());
    p.socks_server = socks_server.getText();
    p.socks_port = Integer.valueOf(socks_port.getText());
    p.socks4 = socks4.isSelected();
    p.socks5 = socks5.isSelected();
    p.secure = secure.isSelected();
    p.user = user.getText();
    p.pass = pass.getText();
    p.remote_host = remote_host.getText();
    p.remote_port = Integer.valueOf(remote_port.getText());
  }

  private void deleteProfile() {
    int idx = profiles.getSelectedIndex();
    if (idx == -1) return;
    if (idx >= config.profiles.length) return;
    config.profiles = (Profile[])JF.copyOfExcluding(config.profiles, idx);
    profiles.removeItem((String)profiles.getSelectedItem());
    if (config.profiles.length == 0) {
      config.profiles = new Profile[1];
      config.profiles[0] = new Profile();
      profiles.addItem(config.profiles[0].name);
    }
  }
}

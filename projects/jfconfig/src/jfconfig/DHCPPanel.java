package jfconfig;

/**
 * Created : Mar 17, 2012
 *
 * @author pquiring
 */

import java.io.*;
import java.util.*;
import javax.swing.*;

import javaforce.*;
import javaforce.awt.*;
import javaforce.linux.*;

public class DHCPPanel extends javax.swing.JPanel {

  /**
   * Creates new form DHCPPanel
   */
  public DHCPPanel() {
    initComponents();
    loadPools();
    updatePools();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jToolBar1 = new javax.swing.JToolBar();
    back = new javax.swing.JButton();
    addPool = new javax.swing.JButton();
    editPool = new javax.swing.JButton();
    delPool = new javax.swing.JButton();
    save = new javax.swing.JButton();
    apply = new javax.swing.JButton();
    restart = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    pools = new javax.swing.JList();

    jToolBar1.setFloatable(false);
    jToolBar1.setRollover(true);

    back.setText("<Back");
    back.setFocusable(false);
    back.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    back.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    back.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        backActionPerformed(evt);
      }
    });
    jToolBar1.add(back);

    addPool.setText("Add Pool");
    addPool.setFocusable(false);
    addPool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    addPool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    addPool.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addPoolActionPerformed(evt);
      }
    });
    jToolBar1.add(addPool);

    editPool.setText("Edit Pool");
    editPool.setFocusable(false);
    editPool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    editPool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    editPool.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        editPoolActionPerformed(evt);
      }
    });
    jToolBar1.add(editPool);

    delPool.setText("Del Pool");
    delPool.setFocusable(false);
    delPool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    delPool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    delPool.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        delPoolActionPerformed(evt);
      }
    });
    jToolBar1.add(delPool);

    save.setText("Save");
    save.setFocusable(false);
    save.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    save.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    save.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        saveActionPerformed(evt);
      }
    });
    jToolBar1.add(save);

    apply.setText("Apply");
    apply.setFocusable(false);
    apply.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    apply.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    apply.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        applyActionPerformed(evt);
      }
    });
    jToolBar1.add(apply);

    restart.setText("Restart Server");
    restart.setFocusable(false);
    restart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    restart.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    restart.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        restartActionPerformed(evt);
      }
    });
    jToolBar1.add(restart);

    pools.setModel(poolsModel);
    pools.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jScrollPane1.setViewportView(pools);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
      .addComponent(jScrollPane1)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

  private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
    ConfigApp.This.setPanel(new ServersPanel());
  }//GEN-LAST:event_backActionPerformed

  private void addPoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPoolActionPerformed
    addPool();
  }//GEN-LAST:event_addPoolActionPerformed

  private void editPoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPoolActionPerformed
    int idx = pools.getSelectedIndex();
    if (idx == -1) return;
    editPool(idx);
  }//GEN-LAST:event_editPoolActionPerformed

  private void delPoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delPoolActionPerformed
    int idx = pools.getSelectedIndex();
    if (idx == -1) return;
    delPool(idx);
  }//GEN-LAST:event_delPoolActionPerformed

  private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
    savePools();
  }//GEN-LAST:event_saveActionPerformed

  private void applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyActionPerformed
    apply();
  }//GEN-LAST:event_applyActionPerformed

  private void restartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartActionPerformed
    restart();
  }//GEN-LAST:event_restartActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton addPool;
  private javax.swing.JButton apply;
  private javax.swing.JButton back;
  private javax.swing.JButton delPool;
  private javax.swing.JButton editPool;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JToolBar jToolBar1;
  private javax.swing.JList pools;
  private javax.swing.JButton restart;
  private javax.swing.JButton save;
  // End of variables declaration//GEN-END:variables

/*
 * /etc/dhcp/dhcpd.conf
 *
 * man dhcpd.conf
 * man dhcp-options
 *
 * subnet <ip> netmask <mask> {
 *   option routers <ip>;
 *   option domain-name <domain>;
 *   option domain-name-servers <ip1>,<ip2>,<ip3>;
 *   option netbios-name-servers <ip1>,...;
 *   max-lease-time <sec>;   //600
 *   default-lease-time <sec>;  //7200
 *
 *   range <ipfirst> <iplast>;
 * }
 *
 */

  private DefaultListModel poolsModel = new DefaultListModel();

  public static class Pool {
    public boolean enabled;
    public String ip, mask;
    public String ipfirst, iplast;  //range
    public String router;
    public String domain;
    public String dns;
    public String wins;
    public String extra;  //extra options
  }

  public static class Config {
    public Pool pool[];
  }

  private Config config;
  private String configFolder = "/etc/jfconfig.d/";
  private String configFile = "dhcp.xml";

  private void loadPools() {
    config = new Config();
    config.pool = new Pool[0];
    try {
      XML xml = new XML();
      FileInputStream fis = new FileInputStream(configFolder + configFile);
      xml.read(fis);
      xml.writeClass(config);
    } catch (FileNotFoundException e1) {
      defaultConfig();
    } catch (Exception e2) {
      JFLog.log(e2);
      defaultConfig();
    }
  }

  private void defaultConfig() {
    config = new Config();
    config.pool = new Pool[0];
  }

  private void savePools() {
    try {
      XML xml = new XML();
      File tmpFile = File.createTempFile("dhcp", ".xml");
      FileOutputStream fos = new FileOutputStream(tmpFile);
      xml.readClass("dhcp", config);
      xml.write(fos);
      fos.close();
      Linux.mkdir(configFolder);
      if (!Linux.copyFile(tmpFile.getAbsolutePath(), configFolder + configFile)) {
        tmpFile.delete();
        throw new Exception("file io error");
      }
      tmpFile.delete();
    } catch (Exception e) {
      JFLog.log(e);
    }
  }

  private String getPoolString(Pool pool) {
    return pool.ip + " " + pool.mask;
  }

  private void updatePools() {
    poolsModel.clear();
    for(int a=0;a<config.pool.length;a++) {
      poolsModel.addElement(getPoolString(config.pool[a]));
    }
  }

  private void addPool() {
    DHCPPoolDialog dialog = new DHCPPoolDialog(ConfigApp.This, true, null);
    dialog.setVisible(true);
    if (!dialog.accepted) return;
    Pool newPool = new Pool();
    newPool.enabled = dialog.getEnabled();
    newPool.ip = dialog.getIP();
    newPool.mask = dialog.getMask();
    newPool.ipfirst = dialog.getIPFirst();
    newPool.iplast = dialog.getIPLast();
    newPool.router = dialog.getRouter();
    newPool.domain = dialog.getDomain();
    newPool.dns = dialog.getDNS();
    newPool.wins = dialog.getWINS();
    newPool.extra = dialog.getExtra();
    config.pool = Arrays.copyOf(config.pool, config.pool.length + 1);
    config.pool[config.pool.length-1] = newPool;
    updatePools();
  }

  private void editPool(int idx) {
    Pool pool = config.pool[idx];
    DHCPPoolDialog dialog = new DHCPPoolDialog(ConfigApp.This, true, pool);
    dialog.setVisible(true);
    if (!dialog.accepted) return;
    pool.enabled = dialog.getEnabled();
    pool.ip = dialog.getIP();
    pool.mask = dialog.getMask();
    pool.ipfirst = dialog.getIPFirst();
    pool.iplast = dialog.getIPLast();
    pool.router = dialog.getRouter();
    pool.domain = dialog.getDomain();
    pool.dns = dialog.getDNS();
    pool.wins = dialog.getWINS();
    pool.extra = dialog.getExtra();
    updatePools();
  }

  private void delPool(int idx) {
    Pool pool = config.pool[idx];
    if (!JFAWT.showConfirm("Confirm", "Are you sure you want to delete '" + pool.ip + "'?")) return;
    int len = config.pool.length;
    Pool newList[] = new Pool[len-1];
    System.arraycopy(config.pool, 0, newList, 0, idx);
    System.arraycopy(config.pool, idx+1, newList, idx, len - idx - 1);
    config.pool = newList;
    updatePools();
  }

  private void restart() {
    if (Linux.restartService("isc-dhcp-server"))
      JFAWT.showMessage("Notice", "DHCP Service Restarted");
    else
      JFAWT.showError("Error", "Failed to Restart DHCP Service");
  }

 /*subnet <ip> netmask <mask> {
 *   option routers <ip>;
 *   option domain-name <domain>;
 *   option domain-name-servers <ip1>,<ip2>,<ip3>;
 *   option netbios-name-servers <ip1>,...;
 *   max-lease-time <sec>;   //600
 *   default-lease-time <sec>;  //7200
 *
 *   range <ipfirst> <iplast>;
 * }
 */

  private void applyPool(Pool pool, OutputStream os) throws Exception {
    if (!pool.enabled) return;
    StringBuilder str = new StringBuilder();
    str.append("subnet " + pool.ip + " netmask " + pool.mask + " {\n");
    if (pool.domain.length() > 0) str.append("  option domain-name " + pool.domain + ";\n");
    if (pool.router.length() > 0) str.append("  option routers " + pool.router + ";\n");
    if (pool.dns.length() > 0) str.append("  option domain-name-servers " + pool.dns + ";\n");
    if (pool.wins.length() > 0) str.append("  option netbios-name-servers " + pool.wins + ";\n");
//    str.append("  max-lease-time 28800;\n");  //8 hrs
//    str.append("  default-lease-time 432000;\n");  //12 hrs
    if (pool.extra.length() > 0) str.append(pool.extra + "\n");
    str.append("  range " + pool.ipfirst + " " + pool.iplast + ";\n");
    str.append("}\n");
    os.write(str.toString().getBytes());
  }

  private void apply() {
    //save config to /etc/dhcp/dhcpd.conf
    try {
      File tmpFile = File.createTempFile("dhcp", ".xml");
      FileOutputStream fos = new FileOutputStream(tmpFile);
      for(int a=0;a<config.pool.length;a++) applyPool(config.pool[a], fos);
      fos.close();
      //copy tmpFile to /etc/dhcp/dhcpd.conf
      if (!Linux.copyFile(tmpFile.getAbsolutePath(), "/etc/dhcp/dhcpd.conf"))
        throw new Exception("file copy error");
      JFAWT.showMessage("Notice", "Settings have been applied, please restart server.");
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to apply settings.");
    }
  }
}

package jfconfig;

/**
 * Created : Mar 14, 2012
 *
 * @author pquiring
 */

import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import javaforce.*;
import javaforce.linux.*;

public class RoutingPanel extends javax.swing.JPanel {

  /**
   * Creates new form RoutingPanel
   */
  public RoutingPanel() {
    initComponents();
    loadConfig();
    showUserRoutes();
    showActiveRoutes();
    ConfigApp.This.setTitle("Config : Routing");
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
    addRoute = new javax.swing.JButton();
    edit = new javax.swing.JButton();
    delRoute = new javax.swing.JButton();
    save = new javax.swing.JButton();
    apply = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jScrollPane3 = new javax.swing.JScrollPane();
    userTable = new javax.swing.JTable();
    jScrollPane4 = new javax.swing.JScrollPane();
    routesTable = new javax.swing.JTable();
    delCurrentRoute = new javax.swing.JButton();

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

    addRoute.setText("Add Route");
    addRoute.setFocusable(false);
    addRoute.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    addRoute.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    addRoute.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addRouteActionPerformed(evt);
      }
    });
    jToolBar1.add(addRoute);

    edit.setText("Edit Route");
    edit.setFocusable(false);
    edit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    edit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    edit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        editActionPerformed(evt);
      }
    });
    jToolBar1.add(edit);

    delRoute.setText("Delete Route");
    delRoute.setFocusable(false);
    delRoute.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    delRoute.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    delRoute.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        delRouteActionPerformed(evt);
      }
    });
    jToolBar1.add(delRoute);

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

    jLabel1.setText("User Routes:");

    jLabel2.setText("Current Active Routes:");

    userTable.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Destination", "Netmask", "Flags", "Metric", "Gateway", "Interface"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    userTable.getTableHeader().setReorderingAllowed(false);
    jScrollPane3.setViewportView(userTable);
    userTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

    routesTable.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Destination", "Netmask", "Flags", "Metric", "Gateway", "Interface"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    routesTable.getTableHeader().setReorderingAllowed(false);
    jScrollPane4.setViewportView(routesTable);
    routesTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

    delCurrentRoute.setText("Delete Route");
    delCurrentRoute.setFocusable(false);
    delCurrentRoute.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    delCurrentRoute.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    delCurrentRoute.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        delCurrentRouteActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
      .addComponent(jScrollPane4)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(delCurrentRoute)
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel2)
          .addComponent(delCurrentRoute, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

  private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
    ConfigApp.setPanel(new NetworkPanel());
  }//GEN-LAST:event_backActionPerformed

  private void addRouteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRouteActionPerformed
    addRoute();
  }//GEN-LAST:event_addRouteActionPerformed

  private void delRouteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delRouteActionPerformed
    int idx = userTable.getSelectedRow();
    if (idx == -1) return;
    delRoute(idx);
  }//GEN-LAST:event_delRouteActionPerformed

  private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
    saveConfig();
  }//GEN-LAST:event_saveActionPerformed

  private void applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyActionPerformed
    apply();
    showActiveRoutes();
  }//GEN-LAST:event_applyActionPerformed

  private void delCurrentRouteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delCurrentRouteActionPerformed
    int idx = routesTable.getSelectedRow();
    if (idx == -1) return;
    delActiveRoute(idx);
  }//GEN-LAST:event_delCurrentRouteActionPerformed

  private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
    int idx = userTable.getSelectedRow();
    if (idx == -1) return;
    editRoute(idx);
  }//GEN-LAST:event_editActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton addRoute;
  private javax.swing.JButton apply;
  private javax.swing.JButton back;
  private javax.swing.JButton delCurrentRoute;
  private javax.swing.JButton delRoute;
  private javax.swing.JButton edit;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JScrollPane jScrollPane3;
  private javax.swing.JScrollPane jScrollPane4;
  private javax.swing.JToolBar jToolBar1;
  private javax.swing.JTable routesTable;
  private javax.swing.JButton save;
  private javax.swing.JTable userTable;
  // End of variables declaration//GEN-END:variables

  public static class Route {
    public String dest, mask, via;  //via is "gw x.x.x." or "dev x"
    public int metric;
  }

  public static class Config {
    public Route route[];
  }

  private Config config;
  private String configFolder = "/etc/jconfig.d/";
  private String configFile = "routing.xml";
//  private boolean dirty = false;

  private void loadConfig() {
    defaultConfig();
    try {
      XML xml = new XML();
      FileInputStream fis = new FileInputStream(configFolder + configFile);
      xml.read(fis);
      fis.close();
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
    config.route = new Route[0];
  }

  private void saveConfig() {
    try {
      XML xml = new XML();
      File tmpFile = File.createTempFile("routing", ".xml");
      FileOutputStream fos = new FileOutputStream(tmpFile);
      xml.readClass("routing", config);
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

  private void showUserRoutes() {
    DefaultTableModel tm = (DefaultTableModel)userTable.getModel();
    while (tm.getRowCount() > 0) {
      tm.removeRow(0);
    }
    for(int a=0;a<config.route.length;a++) {
      String via = config.route[a].via;
      String gw = "", iface = "";
      if (via.startsWith("gw ")) gw = via.substring(3);
      if (via.startsWith("dev ")) iface = via.substring(4);
      tm.addRow(new Object[] {config.route[a].dest, config.route[a].mask, "",
        "" + config.route[a].metric, gw, iface});
    }
  }

  private void showActiveRoutes() {
    //exec "route -n" and show in routes list
    DefaultTableModel tm = (DefaultTableModel)routesTable.getModel();
    while (tm.getRowCount() > 0) {
      tm.removeRow(0);
    }
    try {
      ShellProcess sp = new ShellProcess();
      ArrayList<String> cmd = new ArrayList<String>();
      cmd.add("route");
      cmd.add("-n");
      String output = sp.run(cmd, false);
      if (output == null) throw new Exception("failed to exec route");
      String lns[] = output.split("\n");
/*
      String headers = lns[1];
      int destIdx = headers.indexOf("Dest");
      int gwIdx = headers.indexOf("Gateway");
      int maskIdx = headers.indexOf("Genmask");
      int flagsIdx = headers.indexOf("Flags");
      int metrixIdx = headers.indexOf("Metric");
      //ignore Ref
      //ignore Use
      int ifIdx = headers.indexOf("Iface");
*/
      for(int a=2;a<lns.length;a++) {
        String f[] = lns[a].split(" +");  //space w/ greedy modifier
        tm.addRow(new Object[] {f[0], f[2], f[3], f[4], f[1], f[7]});
      }
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to exec 'route'");
    }
  }

  private void addRoute() {
    RoutingRouteDialog dialog = new RoutingRouteDialog(ConfigApp.This, true, null);
    dialog.setVisible(true);
    if (!dialog.accepted) return;
    Route route = new Route();
    route.dest = dialog.getDest();
    route.mask = dialog.getMask();
    if (dialog.getUseDev())
      route.via = "dev " + dialog.getDev();
    else
      route.via = "gw " + dialog.getGw();
    route.metric = dialog.getMetric();
    config.route = Arrays.copyOf(config.route, config.route.length + 1);
    config.route[config.route.length-1] = route;
    showUserRoutes();
  }

  private void editRoute(int idx) {
    Route route = config.route[idx];
    RoutingRouteDialog dialog = new RoutingRouteDialog(ConfigApp.This, true, route);
    dialog.setVisible(true);
    if (!dialog.accepted) return;
    route.dest = dialog.getDest();
    route.mask = dialog.getMask();
    if (dialog.getUseDev())
      route.via = "dev " + dialog.getDev();
    else
      route.via = "gw " + dialog.getGw();
    route.metric = dialog.getMetric();
    showUserRoutes();
  }

  private void delRoute(int idx) {
    if (!JFAWT.showConfirm("Confirm", "Are you sure you want to delete?")) return;
    int len = config.route.length;
    Route newList[] = new Route[len-1];
    System.arraycopy(config.route, 0, newList, 0, idx);
    System.arraycopy(config.route, idx+1, newList, idx, len - idx - 1);
    config.route = newList;
    showUserRoutes();
  }

  private void delActiveRoute(int idx) {
    DefaultTableModel tm = (DefaultTableModel)routesTable.getModel();
    try {
      ShellProcess sp = new ShellProcess();
      ArrayList<String> cmd = new ArrayList<String>();
      cmd.add("sudo");
      cmd.add("route");
      cmd.add("del");
      cmd.add("-net");
      cmd.add((String)tm.getValueAt(idx, 0));
      cmd.add("netmask");
      cmd.add((String)tm.getValueAt(idx, 1));
      sp.run(cmd, false);
      if (sp.getErrorLevel() != 0) throw new Exception("failed to exec route");
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to exec 'route'");
    }
    showActiveRoutes();
  }

  private String applyRoute(Route route) {
    StringBuilder cmd = new StringBuilder();
    cmd.append("route add -net ");
    cmd.append(route.dest);
    cmd.append(" netmask ");
    cmd.append(route.mask);
    cmd.append(" ");
    int idx = route.via.indexOf(" ");
    cmd.append(route.via.substring(0, idx));
    cmd.append(" ");
    cmd.append(route.via.substring(idx+1));
    if (route.metric > 0) {
      cmd.append(" metric ");
      cmd.append(route.metric);
    }
    cmd.append("\n");
    return cmd.toString();
  }

  private void apply() {
    saveConfig();
    //save to /etc/jconfig.d/routing.sh
    try {
      File tmpFile = File.createTempFile("routing", ".sh");
      FileOutputStream fos = new FileOutputStream(tmpFile);
      fos.write("#!/bin/bash\n".getBytes());
      for(int a=0;a<config.route.length;a++) {
        fos.write(applyRoute(config.route[a]).getBytes());
      }
      fos.close();
      if (!Linux.copyFile(tmpFile.getAbsolutePath(), "/etc/jconfig.d/routing.sh")) {
        tmpFile.delete();
        throw new Exception("file io error");
      }
      tmpFile.delete();
      if (!Linux.setExec("/etc/jconfig.d/routing.sh")) {
        throw new Exception("file io error");
      }
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to save rules to /etc/jconfig.d/routing.sh");
      return;
    }
    //exec /etc/jconfig.d/routing.sh to apply now
    try {
      ShellProcess sp = new ShellProcess();
      sp.run(new String[] {"sudo", "/etc/jconfig.d/routing.sh"}, false);
      if (sp.getErrorLevel() != 0) throw new Exception("routing failed");
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to exec /etc/jconfig.d/routing.sh");
      return;
    }
    //add /etc/jconfig.d/routing.sh to /etc/rc.local for next reboot
    try {
      FileInputStream fis = new FileInputStream("/etc/rc.local");
      byte data[] = JF.readAll(fis);
      String str = new String(data);
      String lns[] = str.split("\n");
      int exitIdx = -1;
      for(int a=0;a<lns.length;a++) {
        if (lns[a].startsWith("exit ")) exitIdx = a;
        if (lns[a].indexOf("/etc/jconfig.d/routing.sh") != -1) {
          //already done
          JFAWT.showMessage("Notice", "Routing table updated!");
          return;
        }
      }
      File tmpFile = File.createTempFile("rc_local", ".tmp");
      FileOutputStream fos = new FileOutputStream(tmpFile);
      for(int a=0;a<lns.length;a++) {
        if (a == exitIdx) {
          fos.write("/etc/jconfig.d/routing.sh\n".getBytes());
        }
        fos.write(lns[a].getBytes());
        fos.write("\n".getBytes());
      }
      if (exitIdx == -1) {
        fos.write("/etc/jconfig.d/routing.sh\n".getBytes());
      }
      fos.close();
      if (!Linux.copyFile(tmpFile.getAbsolutePath(), "/etc/rc.local")) {
        tmpFile.delete();
        throw new Exception("file io error");
      }
      if (!Linux.setExec("/etc/rc.local")) {
        tmpFile.delete();
        throw new Exception("file io error");
      }
      tmpFile.delete();
    } catch (Exception e) {
      JFLog.log(e);
      JFAWT.showError("Error", "Failed to add routing.sh to /etc/rc.local");
      return;
    }
    JFAWT.showMessage("Notice", "Routing table updated!");
  }
}

package jfconfig;

/**
 * Created : Mar 15, 2012
 *
 * @author pquiring
 */

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javaforce.*;

public class FirewallRuleDialog extends javax.swing.JDialog implements KeyListener {

  /**
   * Creates new form FirewallRuleDialog
   */
  public FirewallRuleDialog(java.awt.Frame parent, boolean modal, FirewallPanel.Rule rule, String ruleName) {
    super(parent, modal);
    initComponents();
    setPosition();
    listInterfaces();
    tabs.removeAll();
    if (rule == null) {
      tabs.add(localhost);
      name.setText(ruleName);
      setTitle("Add Rule");
    } else {
      //load rule
      name.setText(rule.name);
      type.setEnabled(false);
      type.setSelectedIndex(rule.type);
      typeItemStateChanged(null);
      enabled.setSelected(rule.enabled);
      loadOpts(rule.opts, rule.type);
      setTitle("Edit Rule");
    }
    basicPort.addKeyListener(this);
    portForwardingPort.addKeyListener(this);
    portForwardingLength.addKeyListener(this);
    portForwardingDestPort.addKeyListener(this);
    portForwardingIP.addKeyListener(this);
    DMZPublicIP.addKeyListener(this);
    DMZPrivateIP.addKeyListener(this);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    buttonGroup1 = new javax.swing.ButtonGroup();
    jLabel1 = new javax.swing.JLabel();
    name = new javax.swing.JTextField();
    cancel = new javax.swing.JButton();
    accept = new javax.swing.JButton();
    type = new javax.swing.JComboBox();
    jLabel2 = new javax.swing.JLabel();
    tabs = new javax.swing.JTabbedPane();
    localhost = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    basicProtocol = new javax.swing.JComboBox();
    basicPort = new javax.swing.JTextField();
    basicAccept = new javax.swing.JRadioButton();
    jLabel5 = new javax.swing.JLabel();
    basicBlock = new javax.swing.JRadioButton();
    nat = new javax.swing.JPanel();
    jLabel6 = new javax.swing.JLabel();
    wan = new javax.swing.JComboBox();
    jLabel7 = new javax.swing.JLabel();
    lan = new javax.swing.JComboBox();
    portForwarding = new javax.swing.JPanel();
    jLabel8 = new javax.swing.JLabel();
    portForwardingIF = new javax.swing.JComboBox();
    jLabel9 = new javax.swing.JLabel();
    portForwardingProtocol = new javax.swing.JComboBox();
    jLabel10 = new javax.swing.JLabel();
    portForwardingIP = new javax.swing.JTextField();
    jLabel13 = new javax.swing.JLabel();
    portForwardingPort = new javax.swing.JTextField();
    jLabel14 = new javax.swing.JLabel();
    portForwardingLength = new javax.swing.JTextField();
    jLabel15 = new javax.swing.JLabel();
    portForwardingDestPort = new javax.swing.JTextField();
    dmz = new javax.swing.JPanel();
    jLabel11 = new javax.swing.JLabel();
    DMZIF = new javax.swing.JComboBox();
    jLabel12 = new javax.swing.JLabel();
    DMZPrivateIP = new javax.swing.JTextField();
    jLabel16 = new javax.swing.JLabel();
    DMZPublicIP = new javax.swing.JTextField();
    jLabel17 = new javax.swing.JLabel();
    enabled = new javax.swing.JCheckBox();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    jLabel1.setText("Name:");

    cancel.setText("Cancel");
    cancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cancelActionPerformed(evt);
      }
    });

    accept.setText("Accept");
    accept.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        acceptActionPerformed(evt);
      }
    });

    type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Localhost Exception", "NAT Routing", "Port Forwarding", "DMZ" }));
    type.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        typeItemStateChanged(evt);
      }
    });

    jLabel2.setText("Type:");

    tabs.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

    jLabel3.setText("Port");

    jLabel4.setText("Protocol");

    basicProtocol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TCP", "UDP", "ICMP" }));

    buttonGroup1.add(basicAccept);
    basicAccept.setSelected(true);
    basicAccept.setText("Accept");

    jLabel5.setText("Action");

    buttonGroup1.add(basicBlock);
    basicBlock.setText("Block");

    javax.swing.GroupLayout localhostLayout = new javax.swing.GroupLayout(localhost);
    localhost.setLayout(localhostLayout);
    localhostLayout.setHorizontalGroup(
      localhostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(localhostLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(localhostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel4)
          .addComponent(jLabel3)
          .addComponent(jLabel5))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(localhostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(localhostLayout.createSequentialGroup()
            .addComponent(basicAccept)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(basicBlock))
          .addGroup(localhostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
            .addComponent(basicProtocol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(basicPort)))
        .addContainerGap(219, Short.MAX_VALUE))
    );
    localhostLayout.setVerticalGroup(
      localhostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(localhostLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(localhostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4)
          .addComponent(basicProtocol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(localhostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel3)
          .addComponent(basicPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(localhostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(basicAccept)
          .addComponent(jLabel5)
          .addComponent(basicBlock))
        .addContainerGap(125, Short.MAX_VALUE))
    );

    tabs.addTab("Exception", localhost);

    jLabel6.setText("WAN Interface");

    jLabel7.setText("LAN Interface");

    javax.swing.GroupLayout natLayout = new javax.swing.GroupLayout(nat);
    nat.setLayout(natLayout);
    natLayout.setHorizontalGroup(
      natLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(natLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(natLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(natLayout.createSequentialGroup()
            .addComponent(jLabel6)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(wan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(natLayout.createSequentialGroup()
            .addComponent(jLabel7)
            .addGap(18, 18, 18)
            .addComponent(lan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(279, Short.MAX_VALUE))
    );
    natLayout.setVerticalGroup(
      natLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(natLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(natLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel6)
          .addComponent(wan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(natLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel7)
          .addComponent(lan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(145, Short.MAX_VALUE))
    );

    tabs.addTab("NAT Routing", nat);

    jLabel8.setText("WAN Interface");

    jLabel9.setText("Protocol");

    portForwardingProtocol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TCP", "UDP", "ICMP" }));

    jLabel10.setText("Destination IP");

    jLabel13.setText("Port");

    jLabel14.setText("Length");

    jLabel15.setText("Dest Port");

    javax.swing.GroupLayout portForwardingLayout = new javax.swing.GroupLayout(portForwarding);
    portForwarding.setLayout(portForwardingLayout);
    portForwardingLayout.setHorizontalGroup(
      portForwardingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(portForwardingLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(portForwardingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(portForwardingLayout.createSequentialGroup()
            .addComponent(jLabel10)
            .addGap(18, 18, 18)
            .addComponent(portForwardingIP))
          .addGroup(portForwardingLayout.createSequentialGroup()
            .addGroup(portForwardingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel8)
              .addComponent(jLabel9)
              .addComponent(jLabel13)
              .addComponent(jLabel15))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(portForwardingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
              .addComponent(portForwardingDestPort)
              .addComponent(portForwardingIF, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(portForwardingProtocol, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(portForwardingPort, javax.swing.GroupLayout.Alignment.LEADING))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel14)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(portForwardingLength, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)))
        .addContainerGap())
    );
    portForwardingLayout.setVerticalGroup(
      portForwardingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(portForwardingLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(portForwardingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel8)
          .addComponent(portForwardingIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(portForwardingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel9)
          .addComponent(portForwardingProtocol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(portForwardingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(portForwardingPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel14)
          .addComponent(portForwardingLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel13))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(portForwardingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel15)
          .addComponent(portForwardingDestPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(portForwardingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel10)
          .addComponent(portForwardingIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(70, Short.MAX_VALUE))
    );

    tabs.addTab("Port Forwarding", portForwarding);

    jLabel11.setText("WAN Interface");

    jLabel12.setText("Private IP");

    jLabel16.setText("Public IP (optional)");

    jLabel17.setText("NOTE : Using Public IP field creates a 1-to-1 NAT");

    javax.swing.GroupLayout dmzLayout = new javax.swing.GroupLayout(dmz);
    dmz.setLayout(dmzLayout);
    dmzLayout.setHorizontalGroup(
      dmzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(dmzLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(dmzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(dmzLayout.createSequentialGroup()
            .addComponent(jLabel12)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(DMZPrivateIP))
          .addGroup(dmzLayout.createSequentialGroup()
            .addComponent(jLabel16)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(DMZPublicIP))
          .addGroup(dmzLayout.createSequentialGroup()
            .addGroup(dmzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(dmzLayout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DMZIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addComponent(jLabel17))
            .addGap(0, 77, Short.MAX_VALUE)))
        .addContainerGap())
    );
    dmzLayout.setVerticalGroup(
      dmzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(dmzLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(dmzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel11)
          .addComponent(DMZIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(dmzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel16)
          .addComponent(DMZPublicIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(dmzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel12)
          .addComponent(DMZPrivateIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel17)
        .addContainerGap(104, Short.MAX_VALUE))
    );

    tabs.addTab("DMZ", dmz);

    enabled.setSelected(true);
    enabled.setText("Enabled");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(tabs)
            .addContainerGap())
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addComponent(accept)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancel))
              .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel1)
                  .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(name)
                  .addComponent(type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGap(12, 12, 12))
          .addGroup(layout.createSequentialGroup()
            .addComponent(enabled)
            .addGap(0, 0, Short.MAX_VALUE))))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(enabled)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel2))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tabs)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(cancel)
          .addComponent(accept))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void typeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_typeItemStateChanged
    int idx = type.getSelectedIndex();
    tabs.removeAll();
    switch (idx) {
      case 0: tabs.add(localhost); break;
      case 1: tabs.add(nat); break;
      case 2: tabs.add(portForwarding); break;
      case 3: tabs.add(dmz); break;
    }
  }//GEN-LAST:event_typeItemStateChanged

  private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
    dispose();
  }//GEN-LAST:event_cancelActionPerformed

  private void acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptActionPerformed
    if (!valid()) return;
    accepted = true;
    opts = buildOpts();
    dispose();
  }//GEN-LAST:event_acceptActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox DMZIF;
  private javax.swing.JTextField DMZPrivateIP;
  private javax.swing.JTextField DMZPublicIP;
  private javax.swing.JButton accept;
  private javax.swing.JRadioButton basicAccept;
  private javax.swing.JRadioButton basicBlock;
  private javax.swing.JTextField basicPort;
  private javax.swing.JComboBox basicProtocol;
  private javax.swing.ButtonGroup buttonGroup1;
  private javax.swing.JButton cancel;
  private javax.swing.JPanel dmz;
  private javax.swing.JCheckBox enabled;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel12;
  private javax.swing.JLabel jLabel13;
  private javax.swing.JLabel jLabel14;
  private javax.swing.JLabel jLabel15;
  private javax.swing.JLabel jLabel16;
  private javax.swing.JLabel jLabel17;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JComboBox lan;
  private javax.swing.JPanel localhost;
  private javax.swing.JTextField name;
  private javax.swing.JPanel nat;
  private javax.swing.JPanel portForwarding;
  private javax.swing.JTextField portForwardingDestPort;
  private javax.swing.JComboBox portForwardingIF;
  private javax.swing.JTextField portForwardingIP;
  private javax.swing.JTextField portForwardingLength;
  private javax.swing.JTextField portForwardingPort;
  private javax.swing.JComboBox portForwardingProtocol;
  private javax.swing.JTabbedPane tabs;
  private javax.swing.JComboBox type;
  private javax.swing.JComboBox wan;
  // End of variables declaration//GEN-END:variables

  public boolean accepted = false;
  private ArrayList<String> ifNames = new ArrayList<String>();
  private String opts;

  private void listInterfaces() {
    //exec "ifconfig"
    ShellProcess sp = new ShellProcess();
    ArrayList<String> cmd = new ArrayList<String>();
    cmd.add("ifconfig");
    String output = sp.run(cmd, false);
    if (output == null) {
      JFAWT.showError("Error", "Failed to exec ifconfig.");
      return;
    }
    String lns[] = output.split("\n");
    for(int a=0;a<lns.length;a++) {
      if (lns[a].length() == 0) continue;
      if (lns[a].charAt(0) == ' ') continue;
      int idx = lns[a].indexOf(' ');
      String intface = lns[a].substring(0, idx);
//      if (intface.equals("lo")) continue;  //useful?
      ifNames.add(intface);
      wan.addItem(intface);
      lan.addItem(intface);
      portForwardingIF.addItem(intface);
      DMZIF.addItem(intface);
    }
  }

  private String buildOpts() {
    switch (type.getSelectedIndex()) {
      case 0: return "protocol=" + basicProtocol.getSelectedItem()
        + ";port=" + basicPort.getText()
        + ";action=" + (basicAccept.isSelected() ? "ACCEPT" : "DROP");
      case 1: return "wan=" + wan.getSelectedItem()
        + ";lan=" + lan.getSelectedItem();
      case 2: return "wan=" + portForwardingIF.getSelectedItem()
        + ";protocol=" + portForwardingProtocol.getSelectedItem()
        + ";port=" + portForwardingPort.getText()
        + ";length=" + portForwardingLength.getText()
        + ";dport=" + portForwardingDestPort.getText()
        + ";ip=" + portForwardingIP.getText();
      case 3: return "wan=" + DMZIF.getSelectedItem()
        + ";privateip=" + DMZPrivateIP.getText()
        + ";publicip=" + DMZPublicIP.getText();
    }
    return null;
  }

  private void loadOpts(String opts, int type) {
    String lns[] = opts.split(";");
    for(int a=0;a<lns.length;a++) {
      String f[] = lns[a].split("=");  //sides of equation
      switch (type) {
        case 0:
          if (f[0].equals("protocol")) {basicProtocol.setSelectedIndex(getProtocolIdx(f[1])); continue;}
          if (f[0].equals("port")) {basicPort.setText(f[1]); continue;}
          if (f[0].equals("action")) {basicAccept.setSelected(f[1].equals("ACCEPT")); continue;}
        case 1:
          if (f[0].equals("wan")) {wan.setSelectedIndex(getIFidx(f[1])); continue;}
          if (f[0].equals("lan")) {lan.setSelectedIndex(getIFidx(f[1])); continue;}
        case 2:
          if (f[0].equals("wan")) {portForwardingIF.setSelectedIndex(getIFidx(f[1])); continue;}
          if (f[0].equals("protocol")) {portForwardingProtocol.setSelectedIndex(getProtocolIdx(f[1])); continue;}
          if (f[0].equals("port")) {portForwardingPort.setText(f[1]); continue;}
          if (f[0].equals("length")) {portForwardingLength.setText(f[1]); continue;}
          if (f[0].equals("dport")) {portForwardingDestPort.setText(f[1]); continue;}
          if (f[0].equals("ip")) {portForwardingIP.setText(f[1]); continue;}
        case 3:
          if (f[0].equals("wan")) {DMZIF.setSelectedIndex(getIFidx(f[1])); continue;}
          if (f[0].equals("privateip")) {DMZPrivateIP.setText(f[1]); continue;}
          if (f[0].equals("publicip")) {DMZPublicIP.setText(f[1]); continue;}
      }
    }
  }

  private int getIFidx(String ifName) {
    for(int a=0;a<ifNames.size();a++) {
      if (ifNames.get(a).equalsIgnoreCase(ifName)) return a;
    }
    return -1;
  }

  private int getProtocolIdx(String name) {
    if (name.equalsIgnoreCase("TCP")) return 0;
    if (name.equalsIgnoreCase("UDP")) return 1;
    if (name.equalsIgnoreCase("ICMP")) return 2;
    return -1;
  }

  private boolean isPortNumberValid(JTextField tf) {
    String str = tf.getText();
    tf.setBackground(new Color(0xff0000));
    if (str.length() == 0) return false;
    try {
      int test = Integer.valueOf(str);
      if ((test < 0) || (test > 65535)) return false;
    } catch (Exception e) {
      return false;
    }
    tf.setBackground(new Color(0xffffff));
    return true;
  }

  private boolean isIP4Valid(JTextField tf, boolean nullAllowed) {
    String str = tf.getText();
    tf.setBackground(new Color(0xff0000));
    if (str.length() == 0) return nullAllowed;
    String octs[] = str.split("[.]", -1);
    if (octs.length != 4) {JFLog.log("octs.length=" + octs.length); return false;}
    try {
      for(int a=0;a<4;a++) {
        int test = Integer.valueOf(octs[a]);
        System.out.println("test=" + test);
        if ((test < 0) || (test > 255)) return false;
      }
    } catch (Exception e) {
      JFLog.log(e);
      return false;
    }
    tf.setBackground(new Color(0xffffff));
    return true;
  }

  private boolean isStringValid(JTextField tf, int minlength, int maxlength) {
    String str = tf.getText();
    tf.setBackground(new Color(0xff0000));
    int len = str.length();
    if ((len < minlength) || (len > maxlength)) return false;
    tf.setBackground(new Color(0xffffff));
    return true;
  }

  private boolean valid() {
    boolean valid = true;
    if (!isStringValid(name, 1, 32)) valid = false;
    switch (type.getSelectedIndex()) {
      case 0:
        if (!isPortNumberValid(basicPort)) valid = false;
        break;
      case 1:
        break;
      case 2:
        if (!isPortNumberValid(portForwardingPort)) valid = false;
        if (!isPortNumberValid(portForwardingLength)) valid = false;
        if (!isPortNumberValid(portForwardingDestPort)) valid = false;
        if (!isIP4Valid(portForwardingIP, false)) valid = false;
        break;
      case 3:
        if (!isIP4Valid(DMZPublicIP, true)) valid = false;
        if (!isIP4Valid(DMZPrivateIP, false)) valid = false;
        break;
    }
    return valid;
  }

  public String getOpts() {
    return opts;
  }

  public String getName() {
    return name.getText();
  }

  public int getRuleType() {
    return type.getSelectedIndex();
  }

  public boolean getEnabled() {
    return enabled.isSelected();
  }

  public void keyPressed(KeyEvent e) {}
  public void keyReleased(KeyEvent e) {
    valid();
  }
  public void keyTyped(KeyEvent e) {
  }

  private void setPosition() {
    Rectangle s = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    Dimension d = getSize();
    setLocation(s.width/2 - d.width/2, s.height/2 - (d.height/2));
  }
}

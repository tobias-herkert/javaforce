package jfconfig;

/**
 * Created : Mar 7, 2012
 *
 * @author pquiring
 */

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.FileInputStream;
import javaforce.*;

public class UserAddDialog extends javax.swing.JDialog {

  /**
   * Creates new form AddUserDialog
   */
  public UserAddDialog(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
    setPosition();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    fullName = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    userName = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    passwd1 = new javax.swing.JPasswordField();
    jLabel4 = new javax.swing.JLabel();
    passwd2 = new javax.swing.JPasswordField();
    admin = new javax.swing.JCheckBox();
    create = new javax.swing.JButton();
    cancel = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    jLabel1.setText("Full Name");

    jLabel2.setText("User Name");

    jLabel3.setText("Password");

    jLabel4.setText("Confirm");

    admin.setText("Administrator");

    create.setText("Create");
    create.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        createActionPerformed(evt);
      }
    });

    cancel.setText("Cancel");
    cancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cancelActionPerformed(evt);
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
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel2)
              .addComponent(jLabel1)
              .addComponent(jLabel3)
              .addComponent(jLabel4))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(passwd1)
              .addComponent(fullName)
              .addComponent(userName)
              .addComponent(passwd2)))
          .addComponent(admin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(create)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cancel)
            .addGap(0, 207, Short.MAX_VALUE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(fullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(passwd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4)
          .addComponent(passwd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(admin)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(create)
          .addComponent(cancel))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
    dispose();
  }//GEN-LAST:event_cancelActionPerformed

  private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
    if (!valid()) return;
    accepted = true;
    dispose();
  }//GEN-LAST:event_createActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JCheckBox admin;
  private javax.swing.JButton cancel;
  private javax.swing.JButton create;
  private javax.swing.JTextField fullName;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JPasswordField passwd1;
  private javax.swing.JPasswordField passwd2;
  private javax.swing.JTextField userName;
  // End of variables declaration//GEN-END:variables

  public boolean accepted = false;

  public String getFullName() {return fullName.getText();}
  public String getUserName() {return userName.getText();}
  public String getPassWord() {return new String(passwd1.getPassword());}
  public String getPassWord2() {return new String(passwd2.getPassword());}
  public boolean getAdmin() {return admin.isSelected();}

  private String allChars(String str) {
    //allows all chars EXCEPT double-quotes
    return str.replaceAll("\"", "");
  }

  private String loginChars(String str) {
    String out = "";
    for(int a=0;a<str.length();a++) {
      char ch = str.charAt(a);
      if ((ch >= 'a') || (ch <= 'z')) {out += ch; continue;}
      if ((ch >= 'A') || (ch <= 'Z')) {out += ch; continue;}
      if ((ch >= '0') || (ch <= '9')) {out += ch; continue;}
    }
    return out;
  }

  private boolean reserved(String login) {
    try {
      FileInputStream fis = new FileInputStream("/etc/passwd");
      int len = fis.available();
      byte buf[] = new byte[len];
      fis.read(buf);
      fis.close();
      String lns[] = new String(buf).split("\n");
      for(int a=0;a<lns.length;a++) {
        String f[] = lns[a].split(":");
        if (login.equalsIgnoreCase(f[0])) return true;
      }
      return false;
    } catch (Exception e) {
      JFLog.log(e);
      return true;
    }
  }

  public boolean valid() {
    fullName.setText(allChars(fullName.getText()));
    userName.setText(loginChars(userName.getText()));
    if (fullName.getText().length() < 3) {JFAWT.showError("Error", "Full name too short"); return false;}
    if (userName.getText().length() < 3) {JFAWT.showError("Error", "Username too short"); return false;}
    if (reserved(userName.getText())) {JFAWT.showError("Error", "That login name is reserved"); return false;}
    if (new String(passwd1.getPassword()).length() < 5) {JFAWT.showError("Error", "Password too short"); return false;}
    if (!(new String(passwd1.getPassword())).equals(new String(passwd2.getPassword()))) {JFAWT.showError("Error", "Passwords do not match"); return false;}
    return true;
  }

  private void setPosition() {
    Rectangle s = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    Dimension d = getSize();
    setLocation(s.width/2 - d.width/2, s.height/2 - (d.height/2));
  }
}

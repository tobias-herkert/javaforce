/*
 * FindDialog.java
 *
 * Created on July 29, 2007, 4:04 PM
 */
package javaforce.awt;

import javaforce.awt.FindEvent;
import java.awt.event.KeyEvent;

/**
 * Opens a find text dialog which uses FindEvent for callback handling.
 *
 * @author Peter Quiring
 */
public class FindDialog extends javax.swing.JDialog {

  /**
   * Creates new form FindDialog
   */
  private FindDialog(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
    setTitle("Find");
    setComponentOrientation(((parent == null) ? javax.swing.JOptionPane.getRootFrame() : parent).getComponentOrientation());
    if (parent != null) {
      setLocationRelativeTo(parent);
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
  private void initComponents() {
    jLabel1 = new javax.swing.JLabel();
    text = new javax.swing.JTextField();
    cbWhole = new javax.swing.JCheckBox();
    cbCase = new javax.swing.JCheckBox();
    find = new javax.swing.JButton();
    cancel = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setResizable(false);
    addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        anykey(evt);
      }
    });

    jLabel1.setText("Find What:");

    text.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        anykey(evt);
      }
    });

    cbWhole.setMnemonic('w');
    cbWhole.setText("Match whole word only");
    cbWhole.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    cbWhole.setMargin(new java.awt.Insets(0, 0, 0, 0));
    cbWhole.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        anykey(evt);
      }
    });

    cbCase.setMnemonic('c');
    cbCase.setText("Match case sensitive");
    cbCase.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    cbCase.setMargin(new java.awt.Insets(0, 0, 0, 0));
    cbCase.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        anykey(evt);
      }
    });

    find.setText("Find");
    find.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        findActionPerformed(evt);
      }
    });
    find.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        anykey(evt);
      }
    });

    cancel.setText("Cancel");
    cancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cancelActionPerformed(evt);
      }
    });
    cancel.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        anykey(evt);
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
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
          .addComponent(cbWhole)
          .addComponent(cbCase)
          .addGroup(layout.createSequentialGroup()
            .addComponent(find)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cancel)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cbWhole)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cbCase)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(find)
          .addComponent(cancel))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void anykey(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anykey
      int keyCode = evt.getKeyCode();
      int mods = evt.getModifiers();
      if (keyCode == KeyEvent.VK_ESCAPE && mods == 0) {
        cancelActionPerformed(null);
      }
      if (keyCode == KeyEvent.VK_ENTER && mods == 0) {
        findActionPerformed(null);
      }
    }//GEN-LAST:event_anykey

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
      this.dispose();
    }//GEN-LAST:event_cancelActionPerformed

    private void findActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findActionPerformed
      fe.findEvent(this);
      this.dispose();
    }//GEN-LAST:event_findActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton cancel;
  private javax.swing.JCheckBox cbCase;
  private javax.swing.JCheckBox cbWhole;
  private javax.swing.JButton find;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JTextField text;
  // End of variables declaration//GEN-END:variables
  private FindEvent fe;

  public String getText() {
    return text.getText();
  }

  public boolean getWhole() {
    return cbWhole.isSelected();
  }

  public boolean getCase() {
    return cbCase.isSelected();
  }

  public static void showFindDialog(java.awt.Frame parent, boolean modular, String text, boolean bWhole, boolean bCase, FindEvent fe) {
    FindDialog dialog = new FindDialog(parent, modular);
    dialog.fe = fe;
    dialog.text.setText(text);
    dialog.cbWhole.setSelected(bWhole);
    dialog.cbCase.setSelected(bCase);
    dialog.text.selectAll();
    dialog.setVisible(true);
  }
}

/** Battery Details
 *
 * @author pquiring
 *
 * Created : Feb 24, 2014
 */

// TODO : create graphical display of battery.

import java.awt.*;
import javax.swing.*;

import javaforce.*;
import javaforce.awt.*;

public class BatteryDialog extends javax.swing.JDialog {

  /**
   * Creates new form BatteryDialog
   */
  public BatteryDialog(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
    setPosition();
    list.setModel(model);
    updateInfo();
    timer = new java.util.Timer();
    timer.schedule(new java.util.TimerTask() {
      public void run() {
        java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
            updateInfo();
          }
        });
      }
    }, 1000, 5000);
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form
   * Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    close = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    list = new javax.swing.JList();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Battery Info");
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });

    close.setText("Close");
    close.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        closeActionPerformed(evt);
      }
    });

    list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    list.setEnabled(false);
    jScrollPane1.setViewportView(list);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(0, 286, Short.MAX_VALUE)
            .addComponent(close))
          .addComponent(jScrollPane1))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(close)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
    dispose();
  }//GEN-LAST:event_closeActionPerformed

  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    timer.cancel();
  }//GEN-LAST:event_formWindowClosing

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton close;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JList list;
  // End of variables declaration//GEN-END:variables


  private DefaultListModel model = new DefaultListModel();
  private java.util.Timer timer;

  private void updateInfo() {
    model.setSize(0);
    ShellProcess sp = new ShellProcess();
    String output = sp.run(new String[] {"acpi", "-b", "-a"}, false);
    String lns[] = output.split("\n");
    for(int a=0;a<lns.length;a++) {
      model.addElement(lns[a]);
    }
    repaint();
  }

  private void setPosition() {
    Rectangle s = JFAWT.getMaximumBounds();
    Dimension d = getPreferredSize();
    setSize(d.width, d.height);
    setLocation(s.width/2 - d.width/2, s.height/2 - (d.height/2));
  }
}

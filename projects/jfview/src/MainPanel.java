/**
 * Created : Mar 26, 2012
 *
 * @author pquiring
 */

import java.io.*;

import javaforce.*;
import javaforce.awt.*;

public class MainPanel extends javax.swing.JPanel {

  /**
   * Creates new form MainPanel
   */
  public MainPanel() {
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolbar = new javax.swing.JToolBar();
        prev = new javax.swing.JButton();
        next = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        edit = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        noscale = new javax.swing.JToggleButton();
        scroll = new javax.swing.JScrollPane() {
            public void doLayout() {
                super.doLayout();
                if (filename != null) setImage();
            }
        };
        image = new javax.swing.JLabel();

        toolbar.setFloatable(false);
        toolbar.setRollover(true);

        prev.setText("<");
        prev.setFocusable(false);
        prev.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        prev.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevActionPerformed(evt);
            }
        });
        toolbar.add(prev);

        next.setText(">");
        next.setFocusable(false);
        next.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        next.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });
        toolbar.add(next);
        toolbar.add(jSeparator1);

        edit.setText("Edit");
        edit.setFocusable(false);
        edit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        edit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        toolbar.add(edit);
        toolbar.add(jSeparator2);

        noscale.setText("1:1");
        noscale.setFocusable(false);
        noscale.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        noscale.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        noscale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noscaleActionPerformed(evt);
            }
        });
        toolbar.add(noscale);

        image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/404.png"))); // NOI18N
        scroll.setViewportView(image);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
            .addComponent(scroll)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

// Note : this is some custom code to init scroll to override the doLayout() so I know when it get's resized.

  private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
    try {
      String cmd[] = {"jfpaint", filename};
      Runtime.getRuntime().exec(cmd);
    } catch (Exception e) {
      JFAWT.showError("Error", "Failed to execute jPaint");
    }
  }//GEN-LAST:event_editActionPerformed

  private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
    next();
  }//GEN-LAST:event_nextActionPerformed

  private void prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevActionPerformed
    prev();
  }//GEN-LAST:event_prevActionPerformed

  private void noscaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noscaleActionPerformed
    setImage();
  }//GEN-LAST:event_noscaleActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton edit;
    private javax.swing.JLabel image;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JButton next;
    private javax.swing.JToggleButton noscale;
    private javax.swing.JButton prev;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables

  public JFImage jfimage = new JFImage();
  public String filename;

  private void setImage() {
    //assigns image and scales if needed
    int sx = scroll.getViewport().getWidth();
    int sy = scroll.getViewport().getHeight();
    int ix = jfimage.getWidth();
    int iy = jfimage.getHeight();
    if (((ix > sx) || (iy > sy)) && (!noscale.isSelected())) {
      JFImage scaled = new JFImage();
      scaled.setImageSize(sx, sy);
      scaled.getGraphics().drawImage(jfimage.getImage(), 0,0, sx-1,sy-1, 0,0, ix-1,iy-1, null);
      image.setIcon(scaled);
    } else {
      image.setIcon(jfimage);
    }
  }

  public void showImage(String filename) {
    this.filename = filename;
    ViewApp.This.setTitle("jView - " + filename);
    try {
      if (!jfimage.load(new FileInputStream(filename))) throw new Exception("404");
      setImage();
    } catch (Exception e) {
      jfimage.load(this.getClass().getClassLoader().getResourceAsStream("404.png"));
      setImage();
    }
    repaint();
  }

  private void next() {
    File file = new File(filename);
    String path = file.getAbsolutePath();
    int idx = path.lastIndexOf("/");
    if (idx == -1) return;
    path = path.substring(0, idx);
    File pathfile = new File(path);
    File files[] = pathfile.listFiles(new FileFilter() {
      public boolean accept(File file) {
        String filename = file.getName().toLowerCase();
        if (filename.endsWith(".png")) return true;
        if (filename.endsWith(".gif")) return true;
        if (filename.endsWith(".jpg")) return true;
        if (filename.endsWith(".bmp")) return true;
        return false;
      }
    });
    String name = file.getName();
    for(int a=0;a<files.length;a++) {
      if (files[a].getName().equals(name)) {
        if (a == files.length-1) {
          //use first image
          showImage(files[0].getAbsolutePath());
          return;
        } else {
          //use next image
          showImage(files[a+1].getAbsolutePath());
          return;
        }
      }
    }
    showImage(files[0].getAbsolutePath());  //show 1st image
  }

  private void prev() {
    File file = new File(filename);
    String path = file.getAbsolutePath();
    int idx = path.lastIndexOf("/");
    if (idx == -1) return;
    path = path.substring(0, idx);
    File pathfile = new File(path);
    File files[] = pathfile.listFiles(new FileFilter() {
      public boolean accept(File file) {
        String filename = file.getName().toLowerCase();
        if (filename.endsWith(".png")) return true;
        if (filename.endsWith(".gif")) return true;
        if (filename.endsWith(".jpg")) return true;
        if (filename.endsWith(".bmp")) return true;
        return false;
      }
    });
    String name = file.getName();
    for(int a=0;a<files.length;a++) {
      if (files[a].getName().equals(name)) {
        if (a == 0) {
          //use last image
          showImage(files[files.length-1].getAbsolutePath());
          return;
        } else {
          //use prev image
          showImage(files[a-1].getAbsolutePath());
          return;
        }
      }
    }
    showImage(files[files.length-1].getAbsolutePath());  //show 1st image
  }
}

/**
 * Created : Mar 28, 2012
 *
 * @author pquiring
 */

import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

import javaforce.*;
import javaforce.awt.*;

public class MainPanel extends javax.swing.JPanel {

  /**
   * Creates new form MainPanel
   */
  public MainPanel(String archive) {
    initComponents();
    xml.root.setName("Archive");
    tree.setModel(xml.getTreeModel());
    if (archive != null) openArchive(archive);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();
        jToolBar1 = new javax.swing.JToolBar();
        createArchive = new javax.swing.JButton();
        openArchive = new javax.swing.JButton();
        extract = new javax.swing.JButton();
        addFiles = new javax.swing.JButton();
        stop = new javax.swing.JButton();

        jScrollPane1.setViewportView(tree);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        createArchive.setText("Create Archive");
        createArchive.setFocusable(false);
        createArchive.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createArchive.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        createArchive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createArchiveActionPerformed(evt);
            }
        });
        jToolBar1.add(createArchive);

        openArchive.setText("Open Archive");
        openArchive.setFocusable(false);
        openArchive.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openArchive.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openArchive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openArchiveActionPerformed(evt);
            }
        });
        jToolBar1.add(openArchive);

        extract.setText("Extract");
        extract.setEnabled(false);
        extract.setFocusable(false);
        extract.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        extract.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        extract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extractActionPerformed(evt);
            }
        });
        jToolBar1.add(extract);

        addFiles.setText("Add Files/Folders");
        addFiles.setEnabled(false);
        addFiles.setFocusable(false);
        addFiles.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addFiles.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFilesActionPerformed(evt);
            }
        });
        jToolBar1.add(addFiles);

        stop.setText("Stop");
        stop.setEnabled(false);
        stop.setFocusable(false);
        stop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopActionPerformed(evt);
            }
        });
        jToolBar1.add(stop);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

  private void openArchiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openArchiveActionPerformed
    openArchive();
  }//GEN-LAST:event_openArchiveActionPerformed

  private void extractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extractActionPerformed
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    chooser.setMultiSelectionEnabled(false);
    File path = new File(JF.getUserPath());
    chooser.setCurrentDirectory(path);
    if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
    File dest = chooser.getSelectedFile();
    ExtractDialog dialog = new ExtractDialog(null, true, tree.getSelectionCount() > 0);
    dialog.setVisible(true);
    if (!dialog.accepted) return;
    extractFiles(dest, dialog.getSelectFiles(), dialog.getCreateFolders(),
      dialog.getOverwriteFiles(), dialog.getNewerOnly());
  }//GEN-LAST:event_extractActionPerformed

  private void addFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFilesActionPerformed
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    chooser.setMultiSelectionEnabled(true);
    File path = new File(JF.getUserPath());
    chooser.setCurrentDirectory(path);
    if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;
    File files[] = chooser.getSelectedFiles();
    addFiles(files);
  }//GEN-LAST:event_addFilesActionPerformed

  private void createArchiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createArchiveActionPerformed
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    chooser.setMultiSelectionEnabled(false);
    File path = new File(JF.getUserPath());
    chooser.setCurrentDirectory(path);
    javax.swing.filechooser.FileFilter ff_zip = new javax.swing.filechooser.FileFilter() {
      public boolean accept(File file) {
        return (file.getName().endsWith(".zip"));
      }
      public String getDescription() {
        return "ZIP Files (*.zip)";
      }
    };
    chooser.addChoosableFileFilter(ff_zip);
    javax.swing.filechooser.FileFilter ff_tar = new javax.swing.filechooser.FileFilter() {
      public boolean accept(File file) {
        if (file.getName().endsWith(".tar.gz")) return true;
        if (file.getName().endsWith(".tgz")) return true;
        if (file.getName().endsWith(".tar.bz2")) return true;
        if (file.getName().endsWith(".tbz2")) return true;
        return false;
      }
      public String getDescription() {
        return "TAR Files (*.tar*)";
      }
    };
    chooser.addChoosableFileFilter(ff_tar);
    if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
    String filename = chooser.getSelectedFile().getName();
    if (filename.endsWith(".zip")) {
      type = "zip";
    } else {
      boolean ok = false;
      if (filename.endsWith(".tar.gz")) {
        tarType = 'z';
        ok = true;
      } else if (filename.endsWith(".tgz")) {
        tarType = 'z';
        ok = true;
      } else if (filename.endsWith(".tar.bz2")) {
        tarType = 'j';
        ok = true;
      } else if (filename.endsWith(".tbz2")) {
        tarType = 'j';
        ok = true;
      }
      if (!ok) {JFAWT.showError("Error", "Unknown archive type"); return;}
      type = "tar";
    }
    archive = chooser.getSelectedFile();
    xml.deleteAll();
    xml.root.setName("Archive");
    extract.setEnabled(true);
    addFiles.setEnabled(true);
  }//GEN-LAST:event_createArchiveActionPerformed

  private void stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopActionPerformed
    abort = true;
  }//GEN-LAST:event_stopActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFiles;
    private javax.swing.JButton createArchive;
    private javax.swing.JButton extract;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton openArchive;
    private javax.swing.JButton stop;
    private javax.swing.JTree tree;
    // End of variables declaration//GEN-END:variables

  private XML xml = new XML();
  private File archive;
  private String type;  //zip or tar
  private char tarType;  //z=gzip j=bzip2
  private volatile boolean abort;
  private Thread thread;

  private void openArchive(String filename) {
    xml.deleteAll();
    xml.root.setName("Archive");
    extract.setEnabled(false);
    addFiles.setEnabled(false);
    archive = new File(filename);
    boolean typeNotSupported = true;
    int idx = filename.lastIndexOf('.');
    if (idx != -1) {
      String ext = filename.substring(idx+1).toLowerCase();
      if (ext.equals("zip")) {
        typeNotSupported = false;
        type = ext;
        tarType = '-';
      } else if (ext.equals("tar")) {
        typeNotSupported = false;
        type = "tar";
        tarType = '-';
      } else if (ext.equals("tgz")) {
        typeNotSupported = false;
        type = "tar";
        tarType = 'z';
      } else if (ext.equals("tbz2")) {
        typeNotSupported = false;
        type = "tar";
        tarType = 'j';
      } else if (ext.equals("gz")) {
        if (filename.toLowerCase().endsWith(".tar.gz")) {
          typeNotSupported = false;
          type = "tar";
          tarType = 'z';
        }
      } else if (ext.equals("bz2")) {
        if (filename.toLowerCase().endsWith(".tar.bz2")) {
          typeNotSupported = false;
          type = "tar";
          tarType = 'j';
        }
      }
    }
    if (!archive.exists() || typeNotSupported) {
      JFAWT.showError("Error", "That file doesn't exist");
      archive = null;
      return;
    }
    listArchive();
  }

  private void openArchive() {
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    chooser.setMultiSelectionEnabled(false);
    File path = new File(JF.getUserPath());
    chooser.setCurrentDirectory(path);
    javax.swing.filechooser.FileFilter ff_zip = new javax.swing.filechooser.FileFilter() {
      public boolean accept(File file) {
        return (file.getName().endsWith(".zip"));
      }
      public String getDescription() {
        return "ZIP Files (*.zip)";
      }
    };
    chooser.addChoosableFileFilter(ff_zip);
    javax.swing.filechooser.FileFilter ff_tar = new javax.swing.filechooser.FileFilter() {
      public boolean accept(File file) {
        if (file.getName().endsWith(".tar.gz")) return true;
        if (file.getName().endsWith(".tgz")) return true;
        if (file.getName().endsWith(".tar.bz2")) return true;
        if (file.getName().endsWith(".tbz2")) return true;
        return false;
      }
      public String getDescription() {
        return "TAR Files (*.tar*)";
      }
    };
    chooser.addChoosableFileFilter(ff_tar);
    if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
    String filename = chooser.getSelectedFile().getAbsolutePath();
    openArchive(filename);
  }

  private void listArchive() {
    if (archive == null) return;
    ShellProcess sp = new ShellProcess();
    String cmd[];
    if (type.equals("zip")) {
      cmd = new String[] {"unzip", "-lqq" + tarType, archive.getAbsolutePath()};
    } else {
      cmd = new String[] {"tar", "tf" + tarType, archive.getAbsolutePath()};
    }
    String output = sp.run(cmd, false);
    String lns[] = output.split("\n");
    if (type.equals("zip")) {
      //unzip format : size data time filename
      //   105706  2012-03-28 10:03   .xsession-errors
      for(int a=0;a<lns.length;a++) {
        lns[a] = lns[a].substring(30);
      }
    }
    XML.XMLTag tag;
    File file;
    for(int a=0;a<lns.length;a++) {
      String f[] = lns[a].split("/");
      String path = "";
      tag = xml.root;
      for(int b=0;b<f.length-1;b++) {
        tag = xml.addSetTag(tag, f[b], "", "");
      }
      xml.addSetTag(tag, f[f.length-1], "", lns[a]);
    }
    //make root files visible
    int cnt = xml.root.getChildCount();
    for(int a=0;a<cnt;a++) {
      tree.makeVisible(new TreePath(xml.root.getChildAt(a).getPath()));
    }
    //enable buttons
    extract.setEnabled(true);
    addFiles.setEnabled(true);
  }

  private File files[];

  private void addFiles(File files[]) {
    this.files = files;
    createArchive.setEnabled(false);
    openArchive.setEnabled(false);
    extract.setEnabled(false);
    addFiles.setEnabled(false);
    stop.setEnabled(true);
    new Thread() {
      public void run() {
        addFiles();
        listArchive();
        createArchive.setEnabled(true);
        openArchive.setEnabled(true);
        extract.setEnabled(true);
        addFiles.setEnabled(true);
        stop.setEnabled(false);
      }
    }.start();
  }

  private void addFiles() {
    //tar : tar c?f archive files
    //zip : zip -r archive files
    String path = files[0].getAbsolutePath();
    int idx = path.lastIndexOf('/');
    path = path.substring(0, idx+1);
    ArrayList<String> cmd = new ArrayList<String>();
    if (type.equals("zip")) {
      cmd.add("zip");
      cmd.add("-r");
    } else {
      cmd.add("tar");
      cmd.add("c" + tarType + "f");
    }
    cmd.add(archive.getAbsolutePath());
    for(int a=0;a<files.length;a++) {
      cmd.add(files[a].getName());
    }
    ShellProcess sp = new ShellProcess();
    sp.setFolder(new File(path));
    String output = sp.run(cmd, false);
    //TODO : handle errors
    listArchive();
  }

  private File dest;
  private boolean selectFiles, createFolders, overwriteFiles, newerOnly;

  private void extractFiles(File dest, boolean selectFiles, boolean createFolders,
    boolean overwriteFiles, boolean newerOnly)
  {
    createArchive.setEnabled(false);
    openArchive.setEnabled(false);
    extract.setEnabled(false);
    addFiles.setEnabled(false);
    stop.setEnabled(true);
    this.dest = dest;
    this.selectFiles = selectFiles;
    this.createFolders = createFolders;
    this.overwriteFiles = overwriteFiles;
    this.newerOnly = newerOnly;
    new Thread() {
      public void run() {
        extractFiles();
        listArchive();
        createArchive.setEnabled(true);
        openArchive.setEnabled(true);
        extract.setEnabled(true);
        addFiles.setEnabled(true);
        stop.setEnabled(false);
      }
    }.start();
  }

  private void extractFiles() {
    //tar : tar x?f archive [files] -C dest
    //zip : unzip archive [files] -d dest [-u(newerOnly)] [-n(!overwriteFiles)] [-P password]
    ArrayList<String> cmd = new ArrayList<String>();
    if (type.equals("zip")) {
      cmd.add("unzip");
      cmd.add(archive.getAbsolutePath());
      if (selectFiles) {
        TreePath paths[] = tree.getSelectionPaths();
        XML.XMLTag tag;
        for(int a=0;a<paths.length;a++) {
          tag = xml.getTag(paths[a]);
          cmd.add(tag.content);
        }
      }
      cmd.add("-d");
      cmd.add(dest.getAbsolutePath());
      //TODO : createFolders ???
      if (!overwriteFiles) cmd.add("-n");
      if (newerOnly) cmd.add("-u");
    } else {
      cmd.add("tar");
      cmd.add("x" + tarType + "f");
      cmd.add(archive.getAbsolutePath());
      if (selectFiles) {
        TreePath paths[] = tree.getSelectionPaths();
        XML.XMLTag tag;
        for(int a=0;a<paths.length;a++) {
          tag = xml.getTag(paths[a]);
          cmd.add(tag.content);
        }
      }
      cmd.add("-C");
      cmd.add(dest.getAbsolutePath());
      //TODO : createFolders ???
      if (overwriteFiles) cmd.add("--overwrite");
      if (newerOnly) cmd.add("--keep-newer-files");
    }
    ShellProcess sp = new ShellProcess();
    String output = sp.run(cmd, false);
    //TODO : handle errors
  }
}

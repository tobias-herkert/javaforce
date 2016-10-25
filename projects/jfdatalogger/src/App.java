/** jfDataLogger
 *
 * @author pquiring
 */

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import javaforce.*;
import javaforce.controls.*;
import javaforce.controls.ni.DAQmx;

public class App extends javax.swing.JFrame {

  public static String version = "0.4";

  public static int delays[] = new int[] {
    25, 50, 100, 500, 1000, 3000, 5000, 10000, 30000, 60000, 300000
  };

  /**
   * Creates new form App
   */
  public App() {
    app = this;
    initComponents();
    JFImage icon = new JFImage();
    icon.loadPNG(this.getClass().getClassLoader().getResourceAsStream("jfdatalogger.png"));
    setIconImage(icon.getImage());
    for(int a=0;a<delays.length;a++) {
      speed.addItem(Integer.toString(delays[a]) + "ms");
    }
    table.setModel(tableModel);
    list.setModel(listModel);
    newProject();
    setTitle("jfDataLogger/" + version);
    JF.centerWindow(this);
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jToolBar1 = new javax.swing.JToolBar();
    newProject = new javax.swing.JButton();
    jSeparator1 = new javax.swing.JToolBar.Separator();
    load = new javax.swing.JButton();
    save = new javax.swing.JButton();
    jSeparator2 = new javax.swing.JToolBar.Separator();
    run = new javax.swing.JToggleButton();
    clear = new javax.swing.JButton();
    jSeparator3 = new javax.swing.JToolBar.Separator();
    logBtn = new javax.swing.JButton();
    jSeparator4 = new javax.swing.JToolBar.Separator();
    jLabel1 = new javax.swing.JLabel();
    speed = new javax.swing.JComboBox<>();
    jSplitPane1 = new javax.swing.JSplitPane();
    jPanel1 = new javax.swing.JPanel();
    img = new javax.swing.JLabel() {
      public void paint(Graphics g) {
        if (logImage == null) return;
        drawImage(g);
      }
    };
    jScrollPane2 = new javax.swing.JScrollPane();
    table = new javax.swing.JTable();
    jPanel2 = new javax.swing.JPanel();
    jToolBar2 = new javax.swing.JToolBar();
    add = new javax.swing.JButton();
    edit = new javax.swing.JButton();
    delete = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    list = new javax.swing.JList<>();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("jfDataLogger");
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });

    jToolBar1.setFloatable(false);
    jToolBar1.setRollover(true);

    newProject.setText("New");
    newProject.setFocusable(false);
    newProject.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    newProject.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    newProject.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        newProjectActionPerformed(evt);
      }
    });
    jToolBar1.add(newProject);
    jToolBar1.add(jSeparator1);

    load.setText("Load");
    load.setFocusable(false);
    load.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    load.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    load.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        loadActionPerformed(evt);
      }
    });
    jToolBar1.add(load);

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
    jToolBar1.add(jSeparator2);

    run.setText("Run");
    run.setFocusable(false);
    run.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    run.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    run.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        runActionPerformed(evt);
      }
    });
    jToolBar1.add(run);

    clear.setText("Clear");
    clear.setFocusable(false);
    clear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    clear.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    clear.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        clearActionPerformed(evt);
      }
    });
    jToolBar1.add(clear);
    jToolBar1.add(jSeparator3);

    logBtn.setText("LogFile");
    logBtn.setFocusable(false);
    logBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    logBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    logBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        logBtnActionPerformed(evt);
      }
    });
    jToolBar1.add(logBtn);
    jToolBar1.add(jSeparator4);

    jLabel1.setText("Speed");
    jToolBar1.add(jLabel1);

    speed.setMaximumSize(new java.awt.Dimension(100, 32767));
    jToolBar1.add(speed);

    jSplitPane1.setDividerLocation(250);

    img.setMaximumSize(new java.awt.Dimension(32768, 100));
    img.setMinimumSize(new java.awt.Dimension(0, 100));

    table.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
      },
      new String [] {
        "Title 1", "Title 2", "Title 3", "Title 4"
      }
    ));
    jScrollPane2.setViewportView(table);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    jSplitPane1.setRightComponent(jPanel1);

    jToolBar2.setFloatable(false);
    jToolBar2.setRollover(true);

    add.setText("Add");
    add.setFocusable(false);
    add.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    add.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    add.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addActionPerformed(evt);
      }
    });
    jToolBar2.add(add);

    edit.setText("Edit");
    edit.setFocusable(false);
    edit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    edit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    edit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        editActionPerformed(evt);
      }
    });
    jToolBar2.add(edit);

    delete.setText("Delete");
    delete.setFocusable(false);
    delete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    delete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    delete.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        deleteActionPerformed(evt);
      }
    });
    jToolBar2.add(delete);

    jScrollPane1.setViewportView(list);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE))
    );

    jSplitPane1.setLeftComponent(jPanel2);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jSplitPane1))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
    add();
  }//GEN-LAST:event_addActionPerformed

  private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
    delete();
  }//GEN-LAST:event_deleteActionPerformed

  private void runActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runActionPerformed
    run();
  }//GEN-LAST:event_runActionPerformed

  private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
    clear();
  }//GEN-LAST:event_clearActionPerformed

  private void loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadActionPerformed
    load();
  }//GEN-LAST:event_loadActionPerformed

  private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
    save();
  }//GEN-LAST:event_saveActionPerformed

  private void newProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProjectActionPerformed
    newProject();
  }//GEN-LAST:event_newProjectActionPerformed

  private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
    edit();
  }//GEN-LAST:event_editActionPerformed

  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    if (worker == null) {
      System.exit(0);
    }
  }//GEN-LAST:event_formWindowClosing

  private void logBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logBtnActionPerformed
    logFile();
  }//GEN-LAST:event_logBtnActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new App().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton add;
  private javax.swing.JButton clear;
  private javax.swing.JButton delete;
  private javax.swing.JButton edit;
  private javax.swing.JLabel img;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JToolBar.Separator jSeparator1;
  private javax.swing.JToolBar.Separator jSeparator2;
  private javax.swing.JToolBar.Separator jSeparator3;
  private javax.swing.JToolBar.Separator jSeparator4;
  private javax.swing.JSplitPane jSplitPane1;
  private javax.swing.JToolBar jToolBar1;
  private javax.swing.JToolBar jToolBar2;
  private javax.swing.JList<String> list;
  private javax.swing.JButton load;
  private javax.swing.JButton logBtn;
  private javax.swing.JButton newProject;
  private javax.swing.JToggleButton run;
  private javax.swing.JButton save;
  private javax.swing.JComboBox<String> speed;
  private javax.swing.JTable table;
  // End of variables declaration//GEN-END:variables

  public static App app;
  public static ArrayList<Tag> tags = new ArrayList<Tag>();
  public static DefaultTableModel tableModel = new DefaultTableModel();
  public static DefaultListModel listModel = new DefaultListModel();
  public static JFImage logImage = new JFImage(1, 510);
  public static Worker worker;
  public static Task task;
  public static int delay;
  public static boolean first;
  public static FileOutputStream logger;
  public String projectFile;
  public String logFile;
  public static boolean active;

  public void newProject() {
    tags.clear();
    listModel.clear();
    list.removeAll();
    clear();
    projectFile = null;
  }

  public void clear() {
    while (tableModel.getRowCount() > 0) {
      tableModel.removeRow(0);
    }
    logImage.clear();
  }

  public static String dl_filters[][] = new String[][] { {"Data Logger Files (*.dl)", "dl"} };
  public static String csv_filters[][] = new String[][] { {"CSV Files (*.csv)", "csv"} };

  public void load() {
    String filename = JF.getOpenFile(JF.getUserPath(), dl_filters);
    if (filename == null) return;
    newProject();
    projectFile = filename;
    XML xml = new XML();
    xml.read(filename);
    int cnt = xml.root.getChildCount();
    for(int a=0;a<cnt;a++) {
      XML.XMLTag xmltag = xml.root.getChildAt(a);
      Tag tag = new Tag();
      tag.load(xmltag.content);
      tags.add(tag);
      listModel.addElement(tag.toString());
    }
  }

  public void save() {
    String filename;
    if (projectFile != null) {
      filename = JF.getSaveFile(projectFile, dl_filters);
    } else {
      filename = JF.getSaveAsFile(JF.getUserPath(), dl_filters);
    }
    if (filename == null) return;
    if (!filename.toLowerCase().endsWith(".dl")) {
      filename += ".dl";
    }
    XML xml = new XML();
    xml.setRoot("jfDataLogger", "", "");
    int cnt = tags.size();
    for(int a=0;a<cnt;a++) {
      Tag tag = tags.get(a);
      xml.addTag(xml.root, "tag", "", tag.save());
    }
    xml.write(filename);
    projectFile = filename;
  }

  public void logFile() {
    if (logFile == null) {
      logFile = JF.getSaveAsFile(JF.getUserPath(), csv_filters);
      if (logFile == null) return;
      if (!logFile.toLowerCase().endsWith(".csv")) {
        logFile += ".csv";
      }
      logBtn.setText("LogFile*");
    } else {
      logFile = null;
      JF.showMessage("Notice", "Log file turned off");
    }
  }

  public void add() {
    TagDialog dialog = new TagDialog(null, true);
    dialog.setVisible(true);
    if (dialog.accepted()) {
      Tag tag = new Tag();
      dialog.save(tag);
      tags.add(tag);
      listModel.addElement(tag.toString());
    }
    clear();
  }

  public void edit() {
    int idx = list.getSelectedIndex();
    if (idx == -1) return;
    Tag tag = tags.get(idx);
    TagDialog dialog = new TagDialog(null, true);
    dialog.load(tag);
    dialog.setVisible(true);
    if (dialog.accepted()) {
      dialog.save(tag);
    }
    listModel.setElementAt(tags.get(idx).toString(), idx);
    clear();
  }

  public void delete() {
    int idx = list.getSelectedIndex();
    if (idx == -1) return;
    tags.remove(idx);
    listModel.remove(idx);
    clear();
  }

  public void run() {
    if (worker == null) {
      if (tags.size() == 0) {
        JF.showError("Error", "No tags defined");
        return;
      }
      setState(true);
      delay = delays[speed.getSelectedIndex()];
      clear();
      if (logFile != null) {
        try {
          logger = new FileOutputStream(logFile);
        } catch (Exception e) {
          e.printStackTrace();
          logger = null;
        }
      } else {
        logger = null;
      }
      run.setText("Stop");
      worker = new Worker();
      worker.start();
    } else {
      worker.cancel();
    }
  }

  private void setState(boolean running) {
    newProject.setEnabled(!running);
    load.setEnabled(!running);
    save.setEnabled(!running);
    add.setEnabled(!running);
    edit.setEnabled(!running);
    delete.setEnabled(!running);
    logBtn.setEnabled(!running);
    speed.setEnabled(!running);
  }

  public void drawImage(Graphics g) {
    if (logImage.getWidth() != img.getWidth()) {
      int ow = logImage.getWidth();
      int nw = img.getWidth();
      int diff = nw - ow;
      if (diff > 0) {
        //expanding image
        int px[] = logImage.getPixels();
        logImage.setSize(img.getWidth(), 510);
        logImage.putPixels(px, diff, 0, ow, 510, 0);
      } else {
        //shrinking image
        diff *= -1;
        int px[] = logImage.getPixels(diff, 0, nw, 510);
        logImage.setSize(img.getWidth(), 510);
        logImage.putPixels(px, 0, 0, nw, 510, 0);
      }
    }
    g.drawImage(logImage.getImage(), 0, 0, null);
  }

  public static void gui(Runnable task) {
    try {
      java.awt.EventQueue.invokeAndWait(task);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static ArrayList<Controller> cs;
  public static ArrayList<String> urls;
  public static java.util.Timer timer;
  public static boolean running;

  public static class Worker extends Thread {
    public void run() {
      JFLog.log("connecting to controllers...");
      running = false;
      cs = new ArrayList<Controller>();
      urls = new ArrayList<String>();
      //create controllers and find fastest timer
      tableModel.setColumnCount(0);
      tableModel.addColumn("timestamp");
      Controller.rate = 1000 / delay;
      active = true;
      System.out.println("rate=" + Controller.rate);
      System.gc();  //ensure all prev connections are closed
      int cnt = tags.size();
      for(int a=0;a<cnt;a++) {
        Tag tag = tags.get(a);
        tag.children.clear();
        tag.child = false;
        String url = tag.getURL();
        boolean have = false;
        Tag parent = null;
        for(int b=0;b<urls.size();b++) {
          if (urls.get(b).equals(url)) {
            have = true;
            tag.c = cs.get(b);
            parent = tags.get(b);
            break;
          }
        }
        if (!have) {
          if (tag.type == Tag.types.NI) {
            if (!DAQmx.loaded) {
              System.out.println("Warning:DAQmx not available for tag:" + tag.host);
              continue;
            }
          }
          Controller c = new Controller();
          JFLog.log("connect:" + url);
          if (!c.connect(url)) {
            JFLog.log("Connection failed:" + url);
            if (url.startsWith("NI:")) {
              DAQmx.printError();
            }
            tag.connected = false;
          } else {
            tag.connected = true;
          }
          cs.add(c);
          urls.add(url);
          tag.c = c;
        } else {
          if (tag.type == Tag.types.S7) {
            parent.children.add(tag);
            tag.child = true;
            tag.parent = parent;
          }
        }
        tableModel.addColumn(tag.toString());
      }
      //init
      first = true;
      for(int a=0;a<cnt;a++) {
        Tag tag = tags.get(a);
        if (tag.children.size() > 0) {
          int ccnt = tag.children.size();
          tag.tags = new String[ccnt+1];
          tag.tags[0] = tag.tag;
          for(int b=0;b<ccnt;b++) {
            tag.tags[b+1] = tag.children.get(b).tag;
          }
        }
      }
      //start tag timers
      for(int a=0;a<cnt;a++) {
        Tag tag = tags.get(a);
        if (tag.child) continue;
        tag.start(delay);
      }
      //start timer
      timer = new java.util.Timer();
      task = new Task();
      timer.scheduleAtFixedRate(task, delay, delay);
      JFLog.log("running...");
      running = true;
    }
    public void cancel() {
      if (!running) return;
      active = false;
      timer.cancel();
      timer = null;
      worker = null;
      task = null;
      if (logger != null) {
        try { logger.close(); } catch (Exception e) {}
        logger = null;
      }
      int cnt = tags.size();
      for(int a=0;a<cnt;a++) {
        Tag tag = tags.get(a);
        if (tag.child) continue;
        if (tag.c != null) {
          tag.c.disconnect();
        }
        tag.stop();
      }
      app.run.setText("Run");
      app.setState(false);
      running = false;
    }
  }
  public static class Task extends TimerTask {
    public String[] row;
    public int idx;
    public int delaycount = 0;
    public String ln;
    public long start = -1;
    public void run() {
      try {
        int cnt = tags.size();
        row = new String[cnt+1];
        if (start == -1) {
          start = System.nanoTime();
        }
        int timestamp = (int)((System.nanoTime() - start) / 1000000L);
        String now = Long.toString(timestamp);
        row[0] = now;
        ln = now;
        idx = 1;
        for(int a=0;a<cnt;a++) {
          Tag tag = tags.get(a);
          if ((tag.parent != null && !tag.parent.connected) || (tag.parent == null && !tag.connected)) {
            row[idx] = "N/C";
          } else {
            if (tag.children.size() > 0) {
              //read multiple tags
              byte datas[][] = tag.reads();
              int ccnt = tag.children.size();
              for(int b=0;b<ccnt;b++) {
                tag.children.get(b).data = datas[b+1];
              }
              byte data[] = datas[0];
              switch (tag.size) {
                case bit: log(tag, data[0] == 0 ? 0 : 1); break;
                case int8: log(tag, data[0] & 0xff); break;
                case int16: log(tag, BE.getuint16(data, 0)); break;
                case int32: log(tag, BE.getuint32(data, 0)); break;
                case float32: log(tag, Float.intBitsToFloat(BE.getuint32(data, 0))); break;
              }
            } else {
              //read one tag
              byte data[] = null;
              if (tag.child) {
                data = tag.data;
              } else {
                data = tag.read();
              }
              if (data == null) data = new byte[8];
              switch (tag.size) {
                case bit: log(tag, data[0] == 0 ? 0 : 1); break;
                case int8: log(tag, data[0] & 0xff); break;
                case int16: log(tag, BE.getuint16(data, 0)); break;
                case int32: log(tag, BE.getuint32(data, 0)); break;
                case float32: log(tag, Float.intBitsToFloat(BE.getuint32(data, 0))); break;
                case float64: log(tag, Double.longBitsToDouble(BE.getuint64(data, 0))); break;
              }
            }
          }
          ln += ",";
          ln += row[idx];
          idx++;
          if (first) {
            tag.lastScaledValue = tag.scaledValue;
          }
        }
        ln += "\r\n";
        if (logger != null) {
          logger.write(ln.getBytes());
        }
        gui(() -> {
          tableModel.addRow(row);
          if (tableModel.getRowCount() > 10) {
            tableModel.removeRow(0);
          }
        });
        updateImage();
        delaycount += delay;
        if (delaycount >= 100) {
          gui( () -> {
            app.img.repaint();
          });
          delaycount = 0;
        }
        if (first) {
          first = false;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    public int scale(Tag tag, int value) {
      if (value < tag.min) return 0;
      if (value > tag.max) return 100;
      float delta = tag.max - tag.min;
      return (int)((value - tag.min) / delta * 100.0);
    }
    public int scale(Tag tag, float value) {
      if (value < tag.fmin) return 0;
      if (value > tag.fmax) return 100;
      float delta = tag.fmax - tag.fmin;
      float fval = value;
      float fmin = tag.min;
      return (int)((fval - fmin) / delta * 100.0);
    }
    public int scale(Tag tag, double value) {
      if (value < tag.fmin) return 0;
      if (value > tag.fmax) return 100;
      double delta = tag.fmax - tag.fmin;
      double fval = value;
      double fmin = tag.min;
      return (int)((fval - fmin) / delta * 100.0);
    }
    public void log(Tag tag, int value) {
      tag.scaledValue = scale(tag, value);
      row[idx] = Integer.toString(value);
    }
    public void log(Tag tag, float value) {
      tag.scaledValue = scale(tag, value);
      row[idx] = Float.toString(value);
    }
    public void log(Tag tag, double value) {
      tag.scaledValue = scale(tag, value);
      row[idx] = Double.toString(value);
    }
    public void updateImage() {
      int x2 = logImage.getWidth() - 1;
      int px[] = logImage.getPixels(1, 0, x2, 510);
      logImage.putPixels(px, 0, 0, x2, 510, 0);
      logImage.line(x2, 0, x2, 509, 0);
      int cnt = tags.size();
      for(int a=0;a<cnt;a++) {
        Tag tag = tags.get(a);
        int y = 5 + 500 - (tag.scaledValue * 5);
        int ly = 5 + 500 - (tag.lastScaledValue * 5);
        logImage.line(x2-1, ly, x2, y, tag.color);
        tag.lastScaledValue = tag.scaledValue;
      }
    }
  }
}

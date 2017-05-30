package explorer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.*;

/**
 *
 * @author cuonglvrepvn@gmail.com
 */
public class Explorer extends javax.swing.JFrame {

    // Model cho Table Detail
    DefaultTableModel modelTable;
    private final FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    // Khai báo đối tượng treeModel
    DefaultTreeModel treeModel;
    // 2 ngăn xếp xử dụng cho back và forward hành động di chuyển trong Explore
    Stack<File> back = new Stack<File>();
    Stack<File> forward = new Stack<File>();
    // Đối tượng xử lý trên File và Directory
    ExplorerOperation exp = new ExplorerOperation();
    String original = "";
    boolean isCut = false;

    // Hàm dựng Explorer
    public Explorer() {
        initComponents();
        this.setLocationRelativeTo(null);
        setTable();
        setTree();
        jProgressBar1.setVisible(false);
        CheckBackForward();
    }

    // Thiết lập File Tree của hệ thống
    private void setTree() {
        // Khởi tạo đối tượng Node root
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        // Khởi tạo đối tượng treeModel với tham số root
        treeModel = new DefaultTreeModel(root);
        File fileSystemRoot = fileSystemView.getHomeDirectory();
        // Khởi tạo đối tượng node với tham số mặc định fileSystemRoot
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
        // thêm node vào root
        root.add(node);
        // Thiết lập node cho các file bên trong node
        File[] files = fileSystemView.getFiles(fileSystemRoot, true);
        for (File file : files) {
            if (file.isDirectory()) {
                node.add(new DefaultMutableTreeNode(file));
            }
        }
        getDetailRight(fileSystemRoot);
        pushBack(fileSystemRoot);
        jTree1.setModel(treeModel);
        jTree1.setCellRenderer(new FileTreeCellRenderer());
        jTree1.setRootVisible(false);
        jTree1.expandRow(0);
        jTree1.setSelectionRow(0);
        jTree1.requestFocus();
    }

    // Hàm gọi các phân vùng trong hệ thống
    private void getDriveName() {
        DefaultMutableTreeNode myComputer = new DefaultMutableTreeNode("My Computer");
        File[] drivers = File.listRoots();
        // Thiết đặt các node là phân vùng
        for (File d : drivers) {
            String dName = FileSystemView.getFileSystemView().getSystemDisplayName(d);
            if ("".equals(dName)) {
                dName = "CD Drive";
            }
            myComputer.add(new DefaultMutableTreeNode(dName));
        }
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(myComputer));
    }

    // Thiết lập Table hiển thị danh sách file, directory
    private void setTable() {

        // Khởi tạo modelTable như là đối tượng của MyModel()
        modelTable = new MyModel();
        jTable1.setModel(modelTable);
        jTable1.setDefaultRenderer(JLabel.class, new Renderer());
        //Icon column
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(30);
        //Size column
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(120);
        jTable1.getColumnModel().getColumn(2).setMinWidth(50);
        //Date Modified column
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(200);
        jTable1.getColumnModel().getColumn(3).setMinWidth(50);

        //Column path
        jTable1.removeColumn(jTable1.getColumnModel().getColumn(4));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        puOpen = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        puCut = new javax.swing.JMenuItem();
        puCopy = new javax.swing.JMenuItem();
        puPaste = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        puDelete = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        puDeleteTree = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        btnForward = new javax.swing.JButton();
        txtAddress = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnAbout = new javax.swing.JMenuItem();

        jPopupMenu1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jPopupMenu1PopupMenuWillBecomeVisible(evt);
            }
        });

        puOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, 0));
        puOpen.setText("Open");
        puOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puOpenActionPerformed(evt);
            }
        });
        jPopupMenu1.add(puOpen);
        jPopupMenu1.add(jSeparator1);

        puCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        puCut.setText("Cut");
        puCut.setActionCommand("Cut");
        puCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puCutActionPerformed(evt);
            }
        });
        jPopupMenu1.add(puCut);

        puCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        puCopy.setText("Copy");
        puCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puCopyActionPerformed(evt);
            }
        });
        jPopupMenu1.add(puCopy);

        puPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        puPaste.setText("Paste");
        puPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puPasteActionPerformed(evt);
            }
        });
        jPopupMenu1.add(puPaste);
        jPopupMenu1.add(jSeparator2);

        puDelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        puDelete.setText("Delete");
        puDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puDeleteActionPerformed(evt);
            }
        });
        jPopupMenu1.add(puDelete);

        puDeleteTree.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        puDeleteTree.setText("Delete");
        puDeleteTree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puDeleteTreeActionPerformed(evt);
            }
        });
        jPopupMenu2.add(puDeleteTree);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("\"Explorer Simulator\"");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImages(null);

        jSplitPane2.setDividerLocation(200);
        jSplitPane2.setDividerSize(2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setComponentPopupMenu(jPopupMenu2);
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jTree1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTree1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane2.setLeftComponent(jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jTable1.setRowHeight(20);
        jTable1.setShowVerticalLines(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
        );

        jSplitPane2.setRightComponent(jPanel4);

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/explorer/1496157029_resultset_first.png"))); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnForward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/explorer/1496157037_resultset_last.png"))); // NOI18N
        btnForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForwardActionPerformed(evt);
            }
        });

        txtAddress.setEnabled(false);

        jTextField1.setForeground(new java.awt.Color(153, 153, 153));
        jTextField1.setText("Search ...");
        jTextField1.setEnabled(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnForward)
                .addGap(2, 2, 2)
                .addComponent(txtAddress)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(btnBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnForward)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jMenu1.setText("File");

        mnExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        mnExit.setText("Exit");
        mnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnExitActionPerformed(evt);
            }
        });
        jMenu1.add(mnExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        mnAbout.setText("About");
        mnAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAboutActionPerformed(evt);
            }
        });
        jMenu2.add(mnAbout);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Xủ lý sự kiện khi 1 node trên file tree được chọn
    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged

        new Thread(new ProgressLoadStart(jProgressBar1)).start();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) evt.getPath().getLastPathComponent();
        File f = (File) node.getUserObject();
        pushBack(f);
        getChildNode(f);
        getDetailRight(f);
        //jTree1.expandPath(evt.getPath());
        new Thread(new ProgressLoadStop(jProgressBar1)).start();
    }//GEN-LAST:event_jTree1ValueChanged

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        File f = null;
        try {
            forward.push(back.pop());
            f = back.peek();
        } catch (Exception e) {
        }
        if (f != null) {
            getDetailRight(f);
        }
        CheckBackForward();
    }//GEN-LAST:event_btnBackActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        if (evt.getClickCount() == 2) {
            exp.OpenInTable();
        }
    }//GEN-LAST:event_jTable1MousePressed

    // Hàm thêm vào ngăn xếp Back
    private void pushBack(File f) {
        back.push(f);
        forward.clear();
        CheckBackForward();
    }

    // Thiết lập khả năng sử dụng btnBack và btnForward
    private void CheckBackForward() {
        btnBack.setEnabled(back.size() >= 2);
        btnForward.setEnabled(forward.size() > 0);
    }

    private void btnForwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForwardActionPerformed
        File f = null;
        try {
            f = forward.pop();
            back.push(f);
        } catch (Exception e) {
        }
        if (f != null) {
            getDetailRight(f);
        }
        CheckBackForward();
    }//GEN-LAST:event_btnForwardActionPerformed

    private void puOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puOpenActionPerformed
        exp.OpenInTable();
    }//GEN-LAST:event_puOpenActionPerformed

    private void puDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puDeleteActionPerformed
        exp.DeleteInTable();
    }//GEN-LAST:event_puDeleteActionPerformed

    private void puCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puCopyActionPerformed
        exp.CopyInTable();
    }//GEN-LAST:event_puCopyActionPerformed

    private void puPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puPasteActionPerformed
        exp.PasteInTable();
    }//GEN-LAST:event_puPasteActionPerformed

    private void jPopupMenu1PopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jPopupMenu1PopupMenuWillBecomeVisible
        exp.LoadMenuPopup();
    }//GEN-LAST:event_jPopupMenu1PopupMenuWillBecomeVisible

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (puOpen.isEnabled()) {
                exp.OpenInTable();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            if (puDelete.isEnabled()) {
                exp.DeleteInTable();
            }
        } else if ((evt.getKeyCode() == KeyEvent.VK_C) && ((evt.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            if (puCopy.isEnabled()) {
                exp.CopyInTable();
            }
        } else if ((evt.getKeyCode() == KeyEvent.VK_V) && ((evt.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            if (puPaste.isEnabled()) {
                exp.PasteInTable();
            }
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void mnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAboutActionPerformed
        JOptionPane.showMessageDialog(this,
                "<html>"
                + "<table>"
                + "<tr><td>Crearte by </td><td>Lê Văn Cường</td></tr>"
                + "<tr><td><label>Email </label></td>"
                + "<td><a href='mailto:cuonglvrepvn@gmail.com'>cuonglvrepvn@gmail.com</a></td></tr>"
                + "<tr><td><label>Mobile </label></td>"
                + "<td><a href='callto:+84905891431'>+84-905-891-431</a></td></tr>"
                + "<tr><td><label>Facebook </label></td>"
                + "<td><a href='https://www.facebook.com/Kokamis.Allah'>www.facebook.com/Kokamis.Allah</td></tr>"
                + "</table></html>");
    }//GEN-LAST:event_mnAboutActionPerformed

    private void mnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnExitActionPerformed

    private void puDeleteTreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puDeleteTreeActionPerformed
        exp.DeleteInTree();
    }//GEN-LAST:event_puDeleteTreeActionPerformed

    private void jTree1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTree1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            exp.DeleteInTree();
        }
    }//GEN-LAST:event_jTree1KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void puCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puCutActionPerformed
        exp.CutInTable();
    }//GEN-LAST:event_puCutActionPerformed

    // Lấy ra các node con
    void getChildNode(File f) {
        try {
            DefaultMutableTreeNode top = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
            if (top == null) //Nothing is selected.     
            {
                return;
            }
            if (f.isDirectory()) {
                File[] files = f.listFiles();
                if (top.isLeaf()) {
                    for (File fc : files) {
                        if (fc.isDirectory()) {
                            //System.out.println(fileSystemView.getSystemDisplayName(fc));
                            DefaultMutableTreeNode child = new DefaultMutableTreeNode(fc);
                            top.add(child);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Lấy ra chi tiết của từng File
    private void getDetailRight(File f) {
        String address = f.getPath();
        if (address.contains("::{")) {
            address = fileSystemView.getSystemDisplayName(f);
        }
        txtAddress.setText(address);
        for (int i = 0; i < modelTable.getRowCount();) {
            modelTable.removeRow(0);
        }
        File[] listFile = f.listFiles();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        for (File fn : listFile) {
            if (fn.isDirectory()) {
                String Name = fileSystemView.getSystemDisplayName(fn);
                Icon icon = fileSystemView.getSystemIcon(fn);
                Object[] row = {new JLabel(icon), Name, "", sdf.format(fn.lastModified()), fn.getPath()};
                modelTable.insertRow(modelTable.getRowCount(), row);
            }
        }
        for (File fn : listFile) {
            if (fn.isFile()) {
                String Name = fileSystemView.getSystemDisplayName(fn);
                Icon icon = fileSystemView.getSystemIcon(fn);
                Object[] row = {new JLabel(icon), Name, fn.length() / 1024 + " KB", sdf.format(fn.lastModified()), fn.getPath()};
                modelTable.insertRow(modelTable.getRowCount(), row);
            }
        }
    }

    public static void main(String args[]) {
        /* Thiết lập  look and feel */
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e);
        }

        /**
         * Khởi tạo và hiển thị form Sử dụng EventQueue.invokeLater() xử lý sau
         * khi tất cả các sự kiện trước đó được xử lý
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Explorer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnForward;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTree jTree1;
    private javax.swing.JMenuItem mnAbout;
    private javax.swing.JMenuItem mnExit;
    private javax.swing.JMenuItem puCopy;
    private javax.swing.JMenuItem puCut;
    private javax.swing.JMenuItem puDelete;
    private javax.swing.JMenuItem puDeleteTree;
    private javax.swing.JMenuItem puOpen;
    private javax.swing.JMenuItem puPaste;
    private javax.swing.JTextField txtAddress;
    // End of variables declaration//GEN-END:variables

    class ExplorerOperation {

        // Hiển thị MenuPopup để Cut, Copy, Paste, Delete
        public void LoadMenuPopup() {
            int row = jTable1.getSelectedRow();
            puOpen.setEnabled(row != -1);
            puDelete.setEnabled(row != -1);
            puCut.setEnabled(row != -1);
            puCopy.setEnabled(row != -1);
            puPaste.setEnabled(row != -1);
            try {
                String path = jTable1.getModel().getValueAt(row, 4).toString();
                File f = new File(path);
                puPaste.setEnabled(!"".equals(original) && f.isDirectory());
            } catch (Exception e) {
            }
        }

        //Xóa file trong Table Detail
        public void DeleteInTable() {
            int row = jTable1.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Select a folder or file!",
                        null, JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Lấy ra đường dẫn đến file và xóa
            String path = jTable1.getModel().getValueAt(row, 4).toString();
            if (FileOperation.Delete(path)) {
                // Xóa row đó khỏi Table Detail
                modelTable.removeRow(row);
            }
        }

        //Xóa file trong File Tree
        public void DeleteInTree() {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
            if (node == null) {
                JOptionPane.showMessageDialog(null, "Select a folder to delete!",
                        null, JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Lấy ra node cần xóa, gán vào file, lấy địa chỉ file và xóa 
            File f = (File) node.getUserObject();
            if (f != null) {
                String path = f.getPath();
                if (FileOperation.Delete(path)) {
                    // Xóa node khỏi File Tree
                    treeModel.removeNodeFromParent(node);
                }
            }
        }

        // Copy file trong Table Detail
        public void CopyInTable() {
            int row = jTable1.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Select a folder or file!", null, JOptionPane.WARNING_MESSAGE);
                original = "";
                return;
            }
            original = jTable1.getModel().getValueAt(row, 4).toString();
        }

        // Cut file trong Table Detail
        public void CutInTable() {
            int row = jTable1.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Select a folder or file!", null, JOptionPane.WARNING_MESSAGE);
                original = "";
                return;
            }
            original = jTable1.getModel().getValueAt(row, 4).toString();
            isCut = true;
        }

        // Dán file trong Table Detail
        public void PasteInTable() {
            int row = jTable1.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Select a folder or file!", null, JOptionPane.WARNING_MESSAGE);
                return;
            }
            String destination = jTable1.getModel().getValueAt(row, 4).toString();
            int t = original.lastIndexOf("\\");
            destination += original.substring(t);
            if (!FileOperation.Paste(original, destination)) {
                JOptionPane.showMessageDialog(null, "Have an error. Try agian!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            // Nếu là Cut thì xóa file gốc đi            
            if (isCut) {
                if (FileOperation.Delete(original)) {
                    // Xóa row đó khỏi Table Detail
                    modelTable.removeRow(row);
                }
                isCut = false;
            }
            original = "";
        }

        // Mở file trong Table Detail
        public void OpenInTable() throws HeadlessException {
            int row = jTable1.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Select a folder or file!", null, JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Lấy đường dẫn đến file
            String path = jTable1.getModel().getValueAt(row, 4).toString();
            // Khởi tạo biến File với tham số path
            File f = new File(path);
            // Nếu là thư mục hiển thị ra Table Detail
            if (f.isDirectory()) {
                new Thread(new ProgressLoadStart(jProgressBar1)).start();
                pushBack(f);
                getDetailRight(f);
                new Thread(new ProgressLoadStop(jProgressBar1)).start();
            } else if (f.isFile()) {
                try {
                    Desktop.getDesktop().open(f);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        }
    }
}

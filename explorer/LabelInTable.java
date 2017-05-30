package explorer;

import javax.swing.JLabel;
import java.awt.Component;
import java.io.File;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author cuonglvrepvn@gmail.com 
 * Xử lý nhãn trong Panel hiển thị
 */
public class LabelInTable {

}

class Renderer extends DefaultTableCellRenderer {

    public void fillColor(JTable t, JLabel l, boolean isSelected) {
        //setting the background and foreground when JLabel is selected
        if (isSelected) {
            l.setBackground(t.getSelectionBackground());
            l.setForeground(t.getSelectionForeground());
        } else {
            l.setBackground(t.getBackground());
            l.setForeground(t.getForeground());
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        if (value instanceof JLabel) {
            JLabel label = (JLabel) value;
            //label.setOpaque(true);
            fillColor(table, label, isSelected);
            return label;
        } else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
}

class MyModel extends javax.swing.table.DefaultTableModel {

    Object[][] row = {};

    Object[] col = {"", "Name", "Size", "Date modified", "Path"};

    public MyModel() {

        //Adding columns
        for (Object c : col) {
            this.addColumn(c);
        }

        //Adding rows
        for (Object[] r : row) {
            addRow(r);
        }

    }

    @Override

    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return getValueAt(0, columnIndex).getClass();
        } else {
            return super.getColumnClass(columnIndex);
        }

    }

    // Có được phép chỉnh sửa hay không?
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}

// Hàm thiết đặt thể hiện của cây file, thư mục
class FileTreeCellRenderer extends DefaultTreeCellRenderer {

    private FileSystemView fileSystemView;

    private JLabel label;

    FileTreeCellRenderer() {
        label = new JLabel();
        label.setOpaque(true);
        fileSystemView = FileSystemView.getFileSystemView();
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean selected, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {
        try {
            // Khởi tạo node từ đối tượng value
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            // Khởi tạo đối tượng file bằng cách get đối tượng từ node
            File file = (File) node.getUserObject();
            // Thiết lập nhãn
            label.setIcon(fileSystemView.getSystemIcon(file));
            label.setText(fileSystemView.getSystemDisplayName(file));
            label.setToolTipText(file.getPath());

        } catch (Exception e) {
            //label.setText("My Computer");
            System.out.println(e);
        }

        if (selected) {
            label.setBackground(backgroundSelectionColor);
            label.setForeground(textSelectionColor);
        } else {
            label.setBackground(backgroundNonSelectionColor);
            label.setForeground(textNonSelectionColor);
        }
        return label;
    }
}

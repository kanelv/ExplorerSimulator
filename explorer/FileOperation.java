package explorer;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import javax.swing.JOptionPane;

/**
 *
 * @author cuonglvrepvn@gmail.com Các xử lý toán tử dùng trên file, directory
 */
public class FileOperation {

    // Xóa đệ quy file or thư mục
    static void recursiveDelete(File file) {
        //Kiểm tra file tồn tại
        if (!file.exists()) {
            return;
        }
        //Kiểm tra thư mục cần xóa và xóa từ trong ra ngoài
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                recursiveDelete(f);
            }
        }
        file.delete();
    }

    public static boolean Delete(String path) {
        //Xuất hộp thoại thông báo về việc xóa
        int res = JOptionPane.showConfirmDialog(null, "Are you sure want to move"
                + " to Recycle Bin?", "Delete", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (res == JOptionPane.NO_OPTION) {
            return false;
        }
        File file = new File(path);
        recursiveDelete(file);
        if (!file.exists()) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "Cannot delete file! Try agian!",
                "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public static boolean Paste(String original, String destination) {
        try {
            final Path sourceDir = Paths.get(original);
            final Path targetDir = Paths.get(destination);
            Files.walkFileTree(sourceDir, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE,
                    new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path target = targetDir.resolve(sourceDir.relativize(dir));
                    try {
                        Files.copy(dir, target);
                    } catch (FileAlreadyExistsException e) {
                        if (!Files.isDirectory(target)) {
                            throw e;
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.copy(file, targetDir.resolve(sourceDir.relativize(file)));
                    return FileVisitResult.CONTINUE;
                }
            });
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

}

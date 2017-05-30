package explorer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

/**
 *
 * @author cuonglvrepvn@gmail.com
 */
/**
 * Hiển thị giao diện progress khi load vào 1 directory hay file
 */
public class ProgressLoad {
    
    public static void main(String[] args) {
        
        Logger logger = Logger.getLogger(ProgressLoad.class.getName());
        logger.log(Level.SEVERE, "fighting");
    }
}

// Bắt đầu quá trình load
class ProgressLoadStart implements Runnable {

    JProgressBar myProgressBar;

    public ProgressLoadStart(JProgressBar myProgressBar) {
        this.myProgressBar = myProgressBar;
    }

    @Override
    public void run() {
        myProgressBar.setIndeterminate(true);
        myProgressBar.setVisible(true);
    }
}

// Kết thúc load
class ProgressLoadStop implements Runnable {

    JProgressBar myProgressBar;

    public ProgressLoadStop(JProgressBar myProgressBar) {
        this.myProgressBar = myProgressBar;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProgressLoadStop.class.getName()).log(Level.SEVERE, null, ex);
        }
        myProgressBar.setVisible(false);
    }
}
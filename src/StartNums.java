import javax.swing.*;
import java.awt.*;
public class StartNums extends JPanel {
    JTextField adults;
    JTextField children;
    boolean listen;
    public StartNums() {
        super(new GridLayout(2,2))
        listen = true;
    }


    public boolean hasAdults() {
        try {
            int x = getAdults();
            if (x >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (IllegalAccessError e) {
            return false;
        }
    }
    public boolean hasChildren() {
        try {
            int x = getChildren();
            if (x >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (IllegalAccessError e) {
            return false;
        }
    }

    public int getAdults() {
        return StartOptions.getNumFromField(adults);
    }
    public int getChildren() {
        return StartOptions.getNumFromField(children);
    }
}

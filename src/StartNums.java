import javax.swing.*;
import java.awt.*;
public class StartNums extends JPanel {
    JTextField adults;
    JTextField children;
    JTextField factor;
    boolean listen;
    public StartNums(int adultsI, int childrenI, int factorI) {
        super(new GridLayout(2,3));
        listen = true;
        add(new JLabel("Starting Adults"));
        add(new JLabel("Starting Children"));
        add(new JLabel("Children Per Couple"));
        adults = new JTextField(Integer.toString(adultsI));
        children = new JTextField(Integer.toString(childrenI));
        factor = new JTextField(Integer.toString(factorI));
        add(adults);
        add(children);
        add(factor);
    }
    public StartNums() {
        this(0,0,1);
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
    public boolean hasFactor() {
        try {
            int x = getFactor();
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
    public int getFactor() {
        return StartOptions.getNumFromField(factor);
    }
}

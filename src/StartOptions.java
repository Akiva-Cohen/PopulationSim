import javax.swing.*;
import java.awt.*;
public class StartOptions extends JPanel {
    JTextField total;
    JTextField adult;
    JTextField child;
    public StartOptions() {
        super(new GridLayout(2,3));
        add(new JLabel("Total lifespan"));
        add(new JLabel("Adulthood Length"));
        add(new JLabel("Childhood length"));
        total = new JTextField("0");
        adult = new JTextField("0");
        child = new JTextField("0");
        add(total);
        add(adult);
        add(child);
    }

    public int getTotal(){
        return getNumFromField(total);
    }
    public int getAdult() {
        return getNumFromField(adult);
    }
    public int getChild() {
        return getNumFromField(child);
    }
    public int getNumFromField(JTextField field) {
        int x;
        try {
            x = Integer.parseInt(field.getText());
        } catch (NumberFormatException e) {
            throw new IllegalAccessError("No number");
        }
        return x;
    }
}

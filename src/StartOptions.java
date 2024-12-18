import javax.swing.*;
import javax.swing.event.DocumentListener;

import java.awt.*;
public class StartOptions extends JPanel {
    JTextField total;
    JTextField adult;
    JTextField child;
    boolean listen;
    public StartOptions() {
        super(new GridLayout(2,3));
        listen = true;
        add(new JLabel("Total lifespan"));
        add(new JLabel("Adulthood Length"));
        add(new JLabel("Childhood length"));
        total = new JTextField("0");
        adult = new JTextField("0");
        child = new JTextField("0");
        add(total);
        add(adult);
        add(child);
        total.getDocument().addDocumentListener(new DoDoc() {
            public void updater() {
                if (listen) {
                    listen = false;
                    //update text
                    listen = true;
                }
            }
        });
        adult.getDocument().addDocumentListener(new DoDoc() {
            public void updater() {
                if (listen) {
                    listen = false;
                    //update text
                    listen =true;
                }
            }
        });
        child.getDocument().addDocumentListener(new DoDoc() {
            public void updater() {
                if (listen) {
                    listen = false;
                    //update text
                    listen = true;
                }
            }
        });
    }

    public boolean inCheck() {
        if (hasAdult() && hasChild() && hasTotal() && getTotal() == getChild() + getAdult()) {
            return true;
        }
        return false;
    }
    public int getTotal(){
        return getNumFromField(total);
    }
    public boolean hasTotal() {
        try {
            int x = getTotal();
            if (x >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (IllegalAccessError e) {
            return false;
        }
    }
    public int getAdult() {
        return getNumFromField(adult);
    }
    public boolean hasAdult() {
        try {
            int x = getAdult();
            if (x >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (IllegalAccessError e) {
            return false;
        }
    }
    public int getChild() {
        return getNumFromField(child);
    }
    public boolean hasChild() {
        try {
            int x = getChild();
            if (x >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (IllegalAccessError e) {
            return false;
        }
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

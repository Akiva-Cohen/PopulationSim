import javax.swing.*;
import javax.swing.event.DocumentListener;

import java.awt.*;
public class StartOptions extends JPanel {
    JTextField total;
    JTextField adult;
    JTextField child;
    boolean listen;
    public static final int TOTAL_BEFORE = 1;
    public static final int ADULT_BEFORE = 2;
    public static final int CHILD_BEFORE = 3;
    int previousEdit = 0;
    int previousPrevious = 0;
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
                    //setFromTotal();
                    updatePrevious(TOTAL_BEFORE);
                    listen = true;
                }
            }
        });
        adult.getDocument().addDocumentListener(new DoDoc() {
            public void updater() {
                if (listen) {
                    listen = false;
                    //update text
                    updatePrevious(ADULT_BEFORE);
                    listen =true;
                }
            }
        });
        child.getDocument().addDocumentListener(new DoDoc() {
            public void updater() {
                if (listen) {
                    listen = false;
                    //update text
                    updatePrevious(CHILD_BEFORE);
                    listen = true;
                }
            }
        });
    }

    public void updateFromTotal() {
        if (hasTotal()) {
            int use = getUsePrevious(TOTAL_BEFORE);
            
        }
    }
    public void updateFromChild() {
        if (hasChild()) {
            int previous = getChild();
            int use = getUsePrevious(CHILD_BEFORE);
        }
    }
    public void updateFromAdult() {
        if (hasAdult()) {
            int use = getUsePrevious(ADULT_BEFORE);
        }
    }

    public int getUsePrevious(int id) {
        if (previousEdit == id) {
            return previousPrevious;
        } else {
            return previousEdit;
        }
    }

    public void updatePrevious(int id) {
        if (id != previousEdit) {
            previousPrevious = previousEdit;
            previousEdit = id;
        }
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

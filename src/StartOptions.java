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
                    updateFromTotal();
                    updatePrevious(TOTAL_BEFORE);
                    listen = true;
                }
            }
        });
        adult.getDocument().addDocumentListener(new DoDoc() {
            public void updater() {
                if (listen) {
                    listen = false;
                    updateFromAdult();
                    updatePrevious(ADULT_BEFORE);
                    listen =true;
                }
            }
        });
        child.getDocument().addDocumentListener(new DoDoc() {
            public void updater() {
                if (listen) {
                    listen = false;
                    updateFromChild();
                    updatePrevious(CHILD_BEFORE);
                    listen = true;
                }
            }
        });
    }

    public void updateFromTotal() {
        if (hasTotal()) {
            int use = getUsePrevious(TOTAL_BEFORE);
            replaceWithTwo(TOTAL_BEFORE, use, false);
        }
    }
    public void updateFromChild() {
        if (hasChild()) {
            int use = getUsePrevious(CHILD_BEFORE);
            replaceWithTwo(CHILD_BEFORE, use, false);
        }
    }
    public void updateFromAdult() {
        if (hasAdult()) {
            int use = getUsePrevious(ADULT_BEFORE);
            replaceWithTwo(ADULT_BEFORE, use, false);
        }
    }

    public void replaceWithTwo(int mine, int use, boolean repeat) {
        if (mine != TOTAL_BEFORE && use != TOTAL_BEFORE) {
            if (!replaceTotal()) {
                if (!repeat) {
                    replaceWithTwo(mine, TOTAL_BEFORE, true);
                }
            }
        } else if (mine != ADULT_BEFORE && use != ADULT_BEFORE) {
            if (!replaceAdult()) {
                if (!repeat) {
                    replaceWithTwo(mine, ADULT_BEFORE, true);
                }
            }
        } else if (mine != CHILD_BEFORE && use != CHILD_BEFORE) {
            if (!replaceChild()) {
                if (!repeat) {
                    replaceWithTwo(mine, CHILD_BEFORE, true);
                }
            }
        }
    }

    public boolean replaceTotal() {
        if (hasAdult() && hasChild()) {
            int newTotal = getAdult() + getChild();
            total.setText(Integer.toString(newTotal));
            return true;
        } else {
            return false;
        }
    }
    public boolean replaceAdult() {
        if (hasTotal() && hasChild() && getTotal() >= getChild()) {
            int newAdult = getTotal() - getChild();
            adult.setText(Integer.toString(newAdult));
            return true;
        } else {
            return false;
        }

    }
    public boolean replaceChild() {
        if (hasTotal() && hasAdult() && getTotal() >= getAdult()) {
            int newChild = getTotal() - getAdult();
            child.setText(Integer.toString(newChild));
            return true;
        } else {
            return false;
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
    public static int getNumFromField(JTextField field) {
        int x;
        try {
            x = Integer.parseInt(field.getText());
        } catch (NumberFormatException e) {
            throw new IllegalAccessError("No number");
        }
        return x;
    }
}

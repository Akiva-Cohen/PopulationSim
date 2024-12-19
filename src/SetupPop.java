import java.awt.GridLayout;

import javax.swing.*;
public class SetupPop extends JOptionPane{
    JPanel panel;
    StartOptions spans;
    StartNums counts;
    public SetupPop() {
        super();
        panel = new JPanel(new GridLayout(2,1));
        spans = new StartOptions();
        counts = new StartNums();
        panel.add(spans);
        panel.add(counts);
    }
    public void start() {
        showConfirmDialog(null, panel,"Choose Population Atributes",JOptionPane.OK_CANCEL_OPTION);
    }
}

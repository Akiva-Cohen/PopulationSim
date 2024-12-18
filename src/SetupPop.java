import javax.swing.*;
public class SetupPop extends JOptionPane{
    public SetupPop() {
        super();
        //add(new StartOptions());
    }
    public void start() {
        showConfirmDialog(null, new StartOptions());
    }
}

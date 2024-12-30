import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SetupPop hi = new SetupPop();
        hi.start();
    }
}
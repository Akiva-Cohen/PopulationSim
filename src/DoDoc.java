import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
public interface DoDoc extends DocumentListener{
    default void changedUpdate(DocumentEvent e) {
        updater();
    }
    default void insertUpdate(DocumentEvent e) {
        updater();
    }
    default void removeUpdate(DocumentEvent e) {
        updater();
    }

    void updater();
}

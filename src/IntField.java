import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.*;
public class IntField extends JFormattedTextField {
    public IntField(int start, int min) {
        super();
        NumberFormatter inter = new NumberFormatter();
        inter.setValueClass(Integer.class);
        inter.setAllowsInvalid(false);
        inter.setMinimum(min);
        setFormatter(inter);
        setText(Integer.toString(start));
    }
    public int getNumber() {
        try {
            commitEdit();
        } catch (ParseException e) {}
        return (int)getValue();
    }
}

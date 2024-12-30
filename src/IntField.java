import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.*;
public class IntField extends JFormattedTextField {
    public IntField(int start, int min) {
        super();
        NumberFormatter inter = new NumberFormatter(NumberFormat.getIntegerInstance());
        inter.setValueClass(Integer.class);
        inter.setAllowsInvalid(false);
        inter.setMinimum(min);

        AbstractFormatterFactory factory = new DefaultFormatterFactory(inter);
        setFormatterFactory(factory);
        setValue(start);
    }
    public int getNumber() {
        try {
            commitEdit();
        } catch (ParseException e) {
            return 0;
        }
        return (int)getValue();
    }
}

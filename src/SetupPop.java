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
    public SetupPop(int adultSpan, int childSpan, int adults, int children, int factor) {
        super();
        panel = new JPanel(new GridLayout(2,1));
        spans = new StartOptions(adultSpan + childSpan, adultSpan, childSpan);
        counts = new StartNums(adults,children,factor);
        panel.add(spans);
        panel.add(counts);
    }
    public void start() {
        int op = showConfirmDialog(null, panel,"Choose Population Atributes",JOptionPane.OK_CANCEL_OPTION);
        if (op == JOptionPane.OK_OPTION) {
            int adultSpan = 0;
            int childSpan = 0;
            int adults = 0;
            int children = 0;
            int factor = 0;
            if (spans.hasAdult() && spans.hasChild() && counts.hasAdults() && counts.hasChildren() && counts.hasFactor()) {
                adultSpan = spans.getAdult();
                childSpan = spans.getChild();
                adults = counts.getAdults();
                children = counts.getChildren();
                factor = counts.getFactor();
                Run run = new Run(adultSpan,childSpan,adults,children,factor);
                run.run();
            } else {
                if (spans.hasAdult()) {
                    adultSpan = spans.getAdult();
                }
                if (spans.hasChild()) {
                    childSpan = spans.getChild();
                }
                if (counts.hasAdults()) {
                    adults = counts.getAdults();
                }
                if (counts.hasChildren()) {
                    children = counts.getChildren();
                }
                if (counts.hasFactor()) {
                    factor = counts.getFactor();
                }
                SetupPop redo = new SetupPop(adultSpan,childSpan,adults,children,factor);
                redo.start();
            }
            
        }
    }
}

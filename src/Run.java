import java.awt.GridLayout;

import javax.swing.*;


public class Run {
    int factor;
    int[] adultArr;
    int[] childArr;
    JTextField adults;
    JTextField children;
    JButton next;
    JTextField turnF;
    int turns;
    public Run(int adultSpan, int childSpan, int adults, int children, int factor) {
        adultArr = new int[adultSpan];
        childArr = new int[childSpan];
        adultArr[0] = adults;
        childArr[0] = children;
        this.factor = factor;
        this.adults = new JTextField(Integer.toString(adults));
        this.adults.setEditable(false);
        this.children = new JTextField(Integer.toString(children));
        this.children.setEditable(false);
        next = new JButton("Simulate One turn");
        turns = 0;
        turnF = new JTextField("0");
        turnF.setEditable(false);
    }
    public void run() {
        JFrame main = new JFrame("Population sim");
        JPanel panel = new JPanel(new GridLayout(3,1));
        JPanel top = new JPanel(new GridLayout(1,2));
        JPanel topL = new JPanel(new GridLayout(2,1));
        topL.add(new JLabel("Blåhajar"));
        topL.add(new JLabel(new ImageIcon("blahaj.png")));
        top.add(topL);
        JPanel topR = new JPanel(new GridLayout(2,1));
        topR.add(new JLabel("Smolhåjar"));
        topR.add(new JLabel(new ImageIcon("smolhaj.png")));
        top.add(topR);
        panel.add(top);
        JPanel middle = new JPanel(new GridLayout(1, 2));
        middle.add(adults);
        middle.add(children);
        panel.add(middle);
        JPanel bottom = new JPanel(new GridLayout(1,2));
        bottom.add(next);
        bottom.add(turnF);
        panel.add(bottom);
        main.add(panel);
        main.setVisible(true);
    }
    public int[][] increment(int[] children, int[] adults) {
        int[] outChildren = new int[children.length];
        int[] outAdults = new int[adults.length];
        outAdults[0] = children[children.length - 1];
        outChildren[0] = sumArray(adults) / 2 * factor;
        for (int i = 0; i < children.length - 1; i++) {
            outChildren[i+1] = children[i];
        }
        for (int i = 0; i < adults.length - 1; i++) {
            outAdults[i+1] = adults[i];
        }
        int[][] out = {outChildren,outAdults};
        return out;
    }
    public int sumArray(int[] arr) {
        int out = 0;
        for (int i : arr) {
            out += i;
        }
        return out;
    }
}
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.*;

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
        int mainAdult = adults / adultSpan;
        int remainAdult = adults % adultSpan;
        for(int i = 0; i < adultArr.length; i++) {
            int set = mainAdult;
            if (i < remainAdult) {
                set++;
            }
            adultArr[i] = set;
        }
        int mainChild = children / childSpan;
        int remainChild = children % childSpan;
        for (int i = 0; i < childArr.length; i++) {
            int set = mainChild;
            if (i < remainChild) {
                set++;
            }
            childArr[i] = set;
        }
        this.factor = factor;
        this.adults = new JTextField(Integer.toString(adults) + " Blåhajar");
        this.adults.setEditable(false);
        this.children = new JTextField(Integer.toString(children) + " Smolhåjar");
        this.children.setEditable(false);
        next = new JButton("Simulate One turn");
        turns = 0;
        turnF = new JTextField("0 Turns");
        turnF.setEditable(false);
    }
    public void run() {
        JFrame main = new JFrame("Population sim");
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(2,1));
        JPanel top = new JPanel(new GridLayout(1,2));
        top.add(new JLabel(new ImageIcon(new ImageIcon("/app/blahaj.png").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH))));
        top.add(new JLabel(new ImageIcon(new ImageIcon("/app/smolhaj.png").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH))));
        panel.add(top);
        JPanel middle = new JPanel(new GridLayout(2, 2));
        middle.add(adults);
        middle.add(children);
        middle.add(next);
        middle.add(turnF);
        panel.add(middle);
        main.add(panel);
        main.pack();
        main.setVisible(true);
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                increment();
            }
        });
    }
    public void increment() {
        int[] outChildren = new int[childArr.length];
        int[] outAdults = new int[adultArr.length];
        outAdults[0] = childArr[childArr.length - 1];
        outChildren[0] = sumArray(adultArr) / 2 * factor;
        for (int i = 0; i < childArr.length - 1; i++) {
            outChildren[i+1] = childArr[i];
        }
        for (int i = 0; i < adultArr.length - 1; i++) {
            outAdults[i+1] = adultArr[i];
        }
        adultArr = outAdults;
        childArr = outChildren;
        adults.setText(Integer.toString(sumArray(adultArr)) + " Blåhajar");
        children.setText(Integer.toString(sumArray(childArr)) + " Smolhåjar");
        turns++;
        turnF.setText(Integer.toString(turns) + " Turns");
    }
    public int sumArray(int[] arr) {
        int out = 0;
        for (int i : arr) {
            out += i;
        }
        return out;
    }
}
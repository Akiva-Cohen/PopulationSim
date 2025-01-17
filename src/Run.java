import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Run {
    int factor;
    int[] adultArr;
    int[] childArr;
    JLabel adults;
    JLabel children;
    JButton next;
    JLabel turnF;
    int turns;
    boolean popWarn;
    IntField times;
    IntField delay;
    JPanel inP;
    JButton start;
    boolean running;
    Timer runer;
    JFrame main;
    public Run(int adultSpan, int childSpan, int adults, int children, int factor) {
        popWarn = true;
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
        this.adults = new JLabel(Integer.toString(adults) + " Blåhajar");
        this.children = new JLabel(Integer.toString(children) + " Smolhajar");
        next = new JButton();
        inP = new JPanel();
        times = new IntField(1, 1);
        inP.add(new JLabel("Simulate"));
        inP.add(times);
        inP.add(new JLabel(" Turns"));
        inP.setOpaque(false);
        next.setLayout(new GridBagLayout());
        next.add(inP);
        turns = 0;
        turnF = new JLabel("0 Turns");
        delay = new IntField(10,1);
        runer = new Timer(10, null);
        runer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ping();
            }
        });

    }
    public void run() {
        running = false;
        main = new JFrame("Population sim");
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(2,1));
        JPanel top = new JPanel(new GridLayout(1,2));
        top.add(new JLabel(new ImageIcon(new ImageIcon("/app/blahaj.png").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH))));
        top.add(new JLabel(new ImageIcon(new ImageIcon("/app/smolhaj.png").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH))));
        panel.add(top);
        JPanel middle = new JPanel(new GridLayout(3, 2));
        middle.add(adults);
        middle.add(children);
        middle.add(next);
        middle.add(turnF);
        JPanel delayP = new JPanel(new GridBagLayout());
        delayP.add(new JLabel("Delay:  "));
        delayP.add(delay);
        delayP.add(new JLabel("ms"));
        middle.add(delayP);
        start = new JButton("Start/Stop");
        middle.add(start);
        panel.add(middle);
        main.add(panel);
        main.pack();
        main.setVisible(true);
        delay.getDocument().addDocumentListener(new DoDoc() {
            public void updater() {
                delayP.validate();
            }
        });
        times.getDocument().addDocumentListener(new DoDoc() {
            public void updater() {
                next.validate();
            }
        });
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start.setEnabled(false);
                pressResult();
            }
        });
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                starter();
            }
        });

    }

    public void starter() {
        running = !running;
        runer.setDelay(delay.getNumber());
        if (running) {
            next.setEnabled(false);
            ping();
            runer.start();
        } else {
            runer.stop();
            next.setEnabled(true);
            
        }
    }
    public void pressResult() {
        int delayTime = delay.getNumber();
        ping();
        Timer timer = new Timer(delayTime, null);
        timer.addActionListener(new ActionListener() {
            int count = times.getNumber() - 1;
            public void actionPerformed(ActionEvent e) {
                if (count <= 0) {
                    timer.stop();
                    start.setEnabled(true);
                } else {
                    if (ping()) {
                        timer.stop();
                        start.setEnabled(true);
                    }
                }
                count--;
            }
        });
        timer.start();
    }
    public boolean ping() {
        if (popWarn) {
            if (incrementAndCheck()) {
                if (SetupPop.stableMode(main)) {
                    main.dispose();
                    return true;
                } else {
                    popWarn = false;
                }
            }
        } else {
            turns++;
            turnF.setText(Integer.toString(turns) + " Turns");
        }
        return false;
    }
    public boolean incrementAndCheck() {
        int[] previousChildren = childArr;
        int[] previousAdults = adultArr;
        increment();
        if (checkIntArrs(previousChildren, childArr) && checkIntArrs(previousAdults, adultArr)) {
            return true;
        } else {
            return false;
        }
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

    public boolean checkIntArrs(int[] one, int[] two) {
        if (one.length != two.length) {
            return false;
        }
        for (int i = 0; i < one.length; i++) {
            if (one[i] != two[i]) {
                return false;
            }
        }
        return true;
    }
    
    public int sumArray(int[] arr) {
        int out = 0;
        for (int i : arr) {
            out += i;
        }
        return out;
    }
}
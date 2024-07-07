package gui.panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Holds the area where you can create a report
 */
public class ReportCreationPanel extends JPanel {

    private JPanel parentPanel;
    private ArrayList<DetailPanel> listOfItems;
    public ReportCreationPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    public void setup() {
        setBackground(Color.yellow);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));



    }

    public void hmm() {
        DetailPanel test = new DetailPanel(this);
        test.setup();
        add(test);

        DetailPanel test2 = new DetailPanel(this);
        test2.setup();
        test2.setBackground(Color.white);
        add(test2);


    }

}

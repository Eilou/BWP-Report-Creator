package gui.panels;

import enums.ReportState;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Superclass for the details for each individual item in a given report
 * To inherit from to specify the details
 */
public class DetailPanel extends JPanel {

    private JPanel parentPanel;
    private TitledBorder border;


    public DetailPanel(JPanel parentPanel, ReportState reportState, int count) {
        this.parentPanel = parentPanel;
        setPreferredSize(new Dimension(parentPanel.getWidth(), 200));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        border = new TitledBorder("");
    }
    public void setup() {
        setBackground(Color.red);

        setBorder(new TitledBorder("Door 1"));
        setLayout(new GridBagLayout());
    }
}

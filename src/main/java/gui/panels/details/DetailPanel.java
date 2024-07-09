package gui.panels.details;

import enums.ReportState;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Superclass for the details for each individual item in a given report
 * To inherit from to specify the details
 */
public class DetailPanel extends JPanel {

    private JPanel parentPanel;
    private TitledBorder border;
   private int count;

    public DetailPanel(JPanel parentPanel, ReportState reportState, int count) {
        this.parentPanel = parentPanel;
        this.count = count;
        setPreferredSize(new Dimension(0, 200));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        border = new TitledBorder(reportState.toString().charAt(0) + String.valueOf(count));
    }
    public void setup() {
        setBackground(Color.red);
        setBorder(border);
        setLayout(new GridBagLayout());
    }
}

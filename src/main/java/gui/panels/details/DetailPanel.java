package gui.panels.details;

import enums.ReportState;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.*;
import java.awt.*;

/**
 * Superclass for the details for each individual item in a given report
 * To inherit from to specify the details
 */
public class DetailPanel extends JPanel {

    private JPanel parentPanel;
    private Border border;
    private int count;
    private String detailTitle;

    public DetailPanel(JPanel parentPanel, ReportState reportState, int count) {
        this.parentPanel = parentPanel;
        this.count = count;
        setPreferredSize(new Dimension(0, 200));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        this.detailTitle = reportState.toString().charAt(0) + String.valueOf(count);
//        border = new LineBorder(Color.black, 5);
        int margin = 5;
        border = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(margin,margin,margin,
                        margin),
                BorderFactory.createLineBorder(Color.black, 5));

    }
    public void setup() {
        setBackground(Color.red);
        setBorder(border);
        setLayout(new GridBagLayout());
    }
}

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
    private int count;
    private Border border;
    private String detailTitle;
    private JTextField titleField;
    private JPanel dataPanel;

    public DetailPanel(JPanel parentPanel, ReportState reportState, int count) {
        this.parentPanel = parentPanel;
        this.count = count;
        setPreferredSize(new Dimension(0, 200));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));

        this.detailTitle = reportState.toString().charAt(0) + String.valueOf(count);
        titleField = new JTextField(detailTitle);

        int margin = 5;
        border = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(margin,margin,margin,
                        margin),
                BorderFactory.createLineBorder(Color.black, 5));

    }

    /**
     * Sets up the layout for each detail panel
     */
    public void setup(JPanel dataPanel) {
        setBackground(Color.red);
        setBorder(border);

        JPanel titleAndExitPanel = new JPanel();
        titleAndExitPanel.setLayout(new BoxLayout(titleAndExitPanel, BoxLayout.LINE_AXIS));
        titleAndExitPanel.add(titleField);
        add(titleAndExitPanel);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.dataPanel = dataPanel;
//        this.dataPanel.setLayout(new GridBagLayout());
        add(this.dataPanel);
//        setPreferredSize(new Dimension(0,dataPanel.getHeight()+20));
    }

    ////////////////////////////////////
    // getters and setters
    ////////////////////////////////////

    public JPanel getDataPanel() {
        return dataPanel;
    }
}

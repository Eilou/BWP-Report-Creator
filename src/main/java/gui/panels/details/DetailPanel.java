package gui.panels.details;

import enums.ReportState;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
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
    private JButton closePanelButton;
    private JPanel dataPanel;

    public DetailPanel(JPanel parentPanel, ReportState reportState, int count) {
        this.parentPanel = parentPanel;
        this.count = count;
        setPreferredSize(new Dimension(0, 300));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));

        this.detailTitle = reportState.toString().charAt(0) + String.valueOf(count);
        titleField = new JTextField(detailTitle);

        closePanelButton = new JButton("CLOSE");

        int margin = 5;
        border = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(margin/4, 0, margin/4, 0, Color.black),
                    BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(2*margin, margin,2*margin, margin),
                    BorderFactory.createLineBorder(Color.black, 5)));

    }

    /**
     * Sets up the layout for each detail panel
     */
    public void setup(JPanel dataPanel) {
        setBackground(Color.red);
        setBorder(border);

        JPanel titleAndExitPanel = new JPanel();
        titleAndExitPanel.setLayout(new BoxLayout(titleAndExitPanel, BoxLayout.LINE_AXIS));
//        titleField.setColumns(20);

//        titleField.setAlignmentX(Component.LEFT_ALIGNMENT);
//        closePanelButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        titleAndExitPanel.add(titleField);
        titleAndExitPanel.add(closePanelButton);
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

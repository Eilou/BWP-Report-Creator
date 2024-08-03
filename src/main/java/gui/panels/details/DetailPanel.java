package gui.panels.details;

import enums.ReportState;
import gui.handlers.CloseDetailButtonHandler;
import gui.panels.ReportCreationPanel;
import items.Item;

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
    private ReportState reportState;
    private int count;
    private Border border;
    private String detailTitle;
    private JTextField titleField;
    private JButton closePanelButton;
    private JPanel dataPanel;

    public DetailPanel(JPanel parentPanel, ReportState reportState, int count) {
        this.parentPanel = parentPanel;
        this.reportState = reportState;
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

        titleAndExitPanel.add(titleField);
        titleAndExitPanel.add(closePanelButton);
        add(titleAndExitPanel);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.dataPanel = dataPanel;
        add(this.dataPanel);
        attachHandlers();
    }

    /**
     * Gives the buttons functionality
     */
    public void attachHandlers() {
        closePanelButton.addActionListener(new CloseDetailButtonHandler((ReportCreationPanel) parentPanel, reportState, this));
    }

    ////////////////////////////////////
    // getters and setters
    ////////////////////////////////////

    public JPanel getDataPanel() {
        return dataPanel;
    }

    /**
     * Gets the item from the detail panel relative to the state of the report
     * @return the current item attached to this detail panel
     */
    public Item getItem() {
        return switch(reportState) {
            case DOOR -> ((DoorDetailsPanel) dataPanel).getDoor();
            default -> null;
        };
    }

}

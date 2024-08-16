package gui.panels.details;

import enums.ReportState;
import gui.handlers.CloseDetailButtonHandler;
import gui.panels.ReportCreationPanel;
import items.Item;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

/**
 * Superclass for the details for each individual item in a given report
 * To inherit from to specify the details
 */
public class DetailPanel extends JPanel {

    private JPanel parentPanel;
    private ReportState reportState;
    private Border border;
    private JTextField titleField;
    private JButton closePanelButton;
    private JPanel dataPanel;

    /**
     * Sets the size of the detail panel to hold the specific data panel
     * Spent ages trying to get the swing layout to work in my favour to show the correct sizing
     * of each data panel, but it just doesn't want to work with me
     *
     * @param parentPanel ReportCreationPanel
     * @param reportState The type of report being created
     * @param count       the number of this door
     */
    public DetailPanel(JPanel parentPanel, ReportState reportState, int count) {
        this.parentPanel = parentPanel;
        this.reportState = reportState;
//        setPreferredSize(new Dimension(parentPanel.getWidth(), (int) (parentPanel.getHeight()*0.65)));
        setMinimumSize(new Dimension(0, (int) (parentPanel.getHeight() * 0.65)));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) (parentPanel.getHeight() * 0.65)));
//        setMaximumSize(getPreferredSize());

        titleField = new JTextField(20);

        closePanelButton = new JButton("DELETE");

        int margin = 5;
        border = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(margin / 4, 0, margin / 4, 0, Color.black),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(2 * margin, margin, 2 * margin, margin),
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
        closePanelButton.setBackground(Color.red);
        closePanelButton.setForeground(Color.white);

        titleAndExitPanel.add(titleField);
        titleAndExitPanel.add(closePanelButton);
        add(titleAndExitPanel);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.dataPanel = dataPanel;
        add(this.dataPanel);

        titleField.setText(reportState.toString().charAt(0) + String.valueOf(getCount()));

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
     *
     * @return the current item attached to this detail panel
     */
    public Item getItem() {
        return switch (reportState) {
            case DOOR -> ((DoorDetailsPanel) dataPanel).getDoor();
            default -> null;
        };
    }

    /**
     * Bubbles the count directly from the door object so there aren't random primitives that
     * don't get updates as they are by value not by reference
     *
     * @return the count of their item
     */
    public int getCount() {
        return ((SpecificDetailInterface) dataPanel).getCount();
    }

    /**
     * Bubbles the count variable's new value down to the item through the specific data panel
     */
    public void setCount(int newCount) {
        ((SpecificDetailInterface) dataPanel).setCount(newCount);
    }

}

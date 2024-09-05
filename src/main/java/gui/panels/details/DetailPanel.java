package gui.panels.details;

import enums.ReportState;
import gui.Styling;
import gui.handlers.CloseDetailButtonHandler;
import gui.handlers.EditMinimiseButtonHandler;
import gui.panels.ReportCreationPanel;
import items.Item;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

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
    private JButton editMinimiseButton;
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

        titleField = new JTextField();

        closePanelButton = new JButton("DELETE");
        editMinimiseButton = new JButton("Minimise");

        int margin = 5;
        border = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(margin / 4, 0, margin / 4, 0, Color.black),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(2 * margin, margin, 2 * margin, margin),
                        BorderFactory.createLineBorder(Styling.BORDER, 5)));

    }

    /**
     * Sets up the layout for each detail panel
     */
    public void setup(JPanel dataPanel) {
        setBackground(Styling.BORDER);
        setForeground(Styling.FOREGROUND);

        setBorder(border);

        JPanel titleAndExitPanel = new JPanel();
        titleAndExitPanel.setLayout(new BoxLayout(titleAndExitPanel, BoxLayout.LINE_AXIS));

        editMinimiseButton.setBackground(Color.green);
        editMinimiseButton.setForeground(Color.black);

        closePanelButton.setBackground(Color.red);
        closePanelButton.setForeground(Color.white);

        titleAndExitPanel.add(titleField);
        titleAndExitPanel.add(editMinimiseButton);
        titleAndExitPanel.add(closePanelButton);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(titleAndExitPanel);
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
        editMinimiseButton.addActionListener(new EditMinimiseButtonHandler(this, dataPanel));
    }

    /**
     * Populates a given combobox with the contents of the array passed to it, default value selected being ""
     *
     * @param comboBox     the combobox to add items to
     * @param optionsToAdd the options to add
     * @param <T>          the type parameter ensuring that equal data types are held in the array list as
     *                     are used in the combobox
     */
    public static <T> void populateGivenComboBox(JComboBox<T> comboBox, T[] optionsToAdd) {
        populateGivenComboBox(comboBox, optionsToAdd, "");
    }

    /**
     * Populates a given combobox with the contents of the array passed to it, selecting a value as well
     *
     * @param comboBox     the combobox to add items to
     * @param optionsToAdd the options to add
     * @param selected     the value to set as selected in the combobox
     * @param <T>          the type parameter ensuring that equal data types are held in the array list as
     *                     are used in the combobox
     */
    public static <T> void populateGivenComboBox(JComboBox<T> comboBox, T[] optionsToAdd, String selected) {
        DefaultComboBoxModel<T> model =
                (DefaultComboBoxModel<T>) comboBox.getModel();
        model.removeAllElements();
//        Arrays.sort(optionsToAdd);
        model.addAll(List.of(optionsToAdd));
        model.setSelectedItem(selected);
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

    public JButton getEditMinimiseButton() {
        return editMinimiseButton;
    }

    public void setEditMinimiseButton(JButton editMinimiseButton) {
        this.editMinimiseButton = editMinimiseButton;
    }

    public void setParentPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    public JTextField getTitleField() {
        return titleField;
    }
}

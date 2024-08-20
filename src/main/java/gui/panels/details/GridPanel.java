package gui.panels.details;

import gui.handlers.CustomOptionHandler;
import items.doors.Door;

import javax.swing.*;
import java.awt.*;

/**
 * Means we can access a custom text box to add or remove later
 * Having it as a class means that we always have a pointer readily available to read from when outputting to a PDF
 *
 * @param <T> Indicates which type of parameter dealing with in the combobox if panel contains one
 */
public class GridPanel<T> extends JPanel {
    private JLabel gridLabel;
    private JPanel gridLabelPanel;

    private JComboBox<T> panelComboBox;
    private JTextField panelTextField;
    private JPanel secondPanel;

    private Boolean customUsed;
    private JTextField customTextBox;

    /**
     * Constructor for a panel within the grid
     */
    public GridPanel() {
        super();
        customUsed = false;
        customTextBox = new JTextField("Custom option here");
    }

    /**
     * Sets up the labels in the grid panels
     *
     * @param labelText the label for each grid section
     */
    public void setupLabel(String labelText) {
        this.gridLabel = new JLabel(labelText);
        this.gridLabelPanel = new JPanel();
        gridLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        gridLabelPanel.add(gridLabel);
        add(gridLabelPanel);
    }

    /**
     * Polymorphic function to setup each different grip panel to help position them
     *
     * @param labelText The label text of each grid section
     * @param comboBox  the combobox within this grid panel
     */
    public void setup(String labelText, JComboBox<T> comboBox) {
        setupLabel(labelText);
        this.panelComboBox = comboBox;
        add(panelComboBox);
    }

    /**
     * Polymorphic function to setup each different grip panel to help position them
     *
     * @param labelText The label text of each grid section
     * @param textField the textField within this grid panel
     */
    public void setup(String labelText, JTextField textField) {
        setupLabel(labelText);
        this.panelTextField = textField;
        add(panelTextField);
    }

    public void setup(String labelText, JPanel premadePanel) {
        setupLabel(labelText);
        this.secondPanel = premadePanel;
        add(secondPanel);
    }

    /**
     * Called when the custom option is selected in a combobox to add a section to add what you want to
     */
    public void addCustomOption() {
        add(customTextBox);
        customUsed = true;
        // handle the custom text box updates when decided what to do with JTextField handler, as it will be the same
        revalidate();
        repaint();
    }

    /**
     * Remove the custom input section when the custom option not selected from the combobox
     */
    public void removeCustomOption() {
        remove(customTextBox);
        customUsed = false;
        revalidate();
        repaint();
    }

    ////////////////////
    // getters and setters
    ////////////////////

    public JTextField getCustomTextBox() {
        return customTextBox;
    }

    public void setCustomTextBox(JTextField customTextBox) {
        this.customTextBox = customTextBox;
    }

    public JComboBox<T> getPanelComboBox() {
        return panelComboBox;
    }

    public void setPanelComboBox(JComboBox<T> panelComboBox) {
        this.panelComboBox = panelComboBox;
    }

    public JTextField getPanelTextField() {
        return panelTextField;
    }

    public void setPanelTextField(JTextField panelTextField) {
        this.panelTextField = panelTextField;
    }
}

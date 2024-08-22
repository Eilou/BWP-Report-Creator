package gui.panels.details;

import gui.handlers.AttributeComboBoxHandler;
import gui.handlers.AttributeTextFieldHandler;
import gui.handlers.CustomOptionHandler;
import gui.handlers.Setter;
import items.Item;

import javax.swing.*;
import java.awt.*;

/**
 * Means we can access a custom text box to add or remove later
 * Having it as a class means that we always have a pointer readily available to read from when outputting to a PDF
 * one
 */
public class GridPanel extends JPanel {
    private Item item;

    private JLabel gridLabel;
    private JPanel gridLabelPanel;

    private JComboBox<?> panelComboBox;
    private JTextField panelTextField;
    private JPanel secondPanel;

    private Boolean customUsed;
    private JTextField customTextBox;

    /**
     * Constructor for a panel within the grid
     */
    public GridPanel(Item item) {
        super();

        this.item = item;

        customUsed = false;
        customTextBox = new JTextField("Custom option here");

        panelComboBox = null;
        panelTextField = null;
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
    public void setup(String labelText, JComboBox<?> comboBox) {
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

    /**
     * Polymorphic function to setup each different grip panel to help position them
     *
     * @param labelText    the label text of each grid section
     * @param premadePanel the second panel to be added below the label
     */
    public void setup(String labelText, JPanel premadePanel) {
        setupLabel(labelText);
        this.secondPanel = premadePanel;
        add(secondPanel);
    }

    /**
     * case 1: there is a combo box but not a text field and so add custom option handler and then also deal with comb box
     * @param setter the method reference to set a given attribute
     */
    public void attachCBAttributeHandler(Setter<?> setter) {
        // case 1: there is a combo box but not a text field
        panelComboBox.addActionListener(new CustomOptionHandler<>(panelComboBox, this));
        panelComboBox.addActionListener(new AttributeComboBoxHandler<>(setter));

        // case 3: there is a second panel (needs to be handled specifically)
    }

    /**
     * case 2: there is a text field but not a combo box
     *
     * @param setter different setter as we know it is only ever to be dealing with strings
     */
    public void attachTFAttributeHandler(Setter<String> setter) {
        panelTextField.getDocument().addDocumentListener(new AttributeTextFieldHandler(panelTextField, setter));
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

    public JComboBox<?> getPanelComboBox() {
        return panelComboBox;
    }

    public void setPanelComboBox(JComboBox<?> panelComboBox) {
        this.panelComboBox = panelComboBox;
    }

    public JTextField getPanelTextField() {
        return panelTextField;
    }

    public void setPanelTextField(JTextField panelTextField) {
        this.panelTextField = panelTextField;
    }
}

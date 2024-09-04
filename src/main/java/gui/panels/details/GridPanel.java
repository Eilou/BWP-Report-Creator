package gui.panels.details;

import gui.handlers.*;
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

    private JComboBox<String> panelComboBox;
    private JTextField panelTextField;
    private JPanel secondPanel;

    private JTextField customTextField;

    /**
     * Constructor for a panel within the grid
     */
    public GridPanel(Item item) {
        super();

        this.item = item;

        customTextField = new JTextField("Custom option here");

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
        gridLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        gridLabelPanel.add(gridLabel);
        add(gridLabelPanel);
    }

    /**
     * More generic version of setupLabel meaning it can be used to add specific labels to other (inner) panels
     * todo make the above work using this method but its not crucial
     *
     * @param panel     the panel to add the label to
     * @param labelText the text of the label
     */
    public static void setupSpecificLabel(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        labelPanel.add(label);
        panel.add(labelPanel);
    }

    /**
     * Polymorphic function to setup each different grip panel to help position them
     *
     * @param labelText The label text of each grid section
     * @param comboBox  the combobox within this grid panel
     */
    public void setup(String labelText, JComboBox<String> comboBox) {
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
     *
     * @param setter the method reference to set a given attribute
     */
    public void attachCBAttributeHandler(Setter<String> setter) {
        // case 1: there is a combo box but not a text field
        customTextField.getDocument().addDocumentListener(new AttributeTextFieldHandler(customTextField, setter));

        panelComboBox.addActionListener(new CustomOptionHandler<>(panelComboBox, this));
        panelComboBox.addItemListener(new AttributeComboBoxHandler(panelComboBox, setter));
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
     * Treats an inner combo box in a second panel as if it is the same as a grid panel with just a combo box
     * case 3: there is a second panel (needs to be handled specifically)
     *
     * @param setter        the attribute to set
     * @param innerPanel    the panel to add the custom text box to
     * @param innerComboBox the combo box to read from
     */
    public void attachSpecificAttributeHandler(Setter<String> setter, JPanel innerPanel, JComboBox<String> innerComboBox) {
        JTextField innerCustomTextField = new JTextField("Custom option here"); // cannot use class's custom text field as this one object variant will need multiple custom options
        innerCustomTextField.getDocument().addDocumentListener(new AttributeTextFieldHandler(innerCustomTextField, setter));

        innerComboBox.addActionListener(new InnerCustomOptionHandler<>(innerComboBox, innerPanel, innerCustomTextField));
        innerComboBox.addItemListener(new AttributeComboBoxHandler(innerComboBox, setter));
    }

    /**
     * Treats an inner text field in a second panel as if it is the same as a grid panel with just a text field
     *
     * @param setter         the attribute to set
     * @param innerTextField the text field to read from
     */
    public void attachSpecificAttributeHandler(Setter<String> setter, JTextField innerTextField) {
        innerTextField.getDocument().addDocumentListener(new AttributeTextFieldHandler(innerTextField, setter));
    }

    /**
     * Called when the custom option is selected in a combobox to add a section to add what you want to
     */
    public void addCustomOption() {
        add(customTextField);
//        customTextField.addActionListener(new AttributeTextFieldHandler(customTextField, ));
        // handle the custom text box updates when decided what to do with JTextField handler, as it will be the same
        revalidate();
        repaint();
    }

    /**
     * Remove the custom input section when the custom option not selected from the combobox
     */
    public void removeCustomOption() {
        remove(customTextField);
        revalidate();
        repaint();
    }

    ////////////////////
    // getters and setters
    ////////////////////

    public JTextField getCustomTextField() {
        return customTextField;
    }

    public void setCustomTextField(JTextField customTextField) {
        this.customTextField = customTextField;
    }

    public JComboBox<?> getPanelComboBox() {
        return panelComboBox;
    }

    public void setPanelComboBox(JComboBox<String> panelComboBox) {
        this.panelComboBox = panelComboBox;
    }

    public JTextField getPanelTextField() {
        return panelTextField;
    }

    public void setPanelTextField(JTextField panelTextField) {
        this.panelTextField = panelTextField;
    }
}

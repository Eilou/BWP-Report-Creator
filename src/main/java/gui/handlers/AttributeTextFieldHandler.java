package gui.handlers;

import items.Item;

import javax.swing.*;
import javax.swing.event.*;

public class AttributeTextFieldHandler implements DocumentListener {

    private JTextField textField;
    private Item item;
    private String attribute;

    /**
     * Sets up the handler. Could get the text field from the source of the event but that means
     * going through the SwingUtilities library which may be shorter in code but is more complex
     *
     * @param textField the textField to read from
     * @param item      item to update
     * @param attribute the item attribute to update
     */
    public AttributeTextFieldHandler(JTextField textField, Item item, String attribute) {
        this.textField = textField;
        this.item = item;
        this.attribute = attribute;
    }

    /**
     * Event generated when the document is written to
     *
     * @param e the document event
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        updateItem();
    }

    /**
     * Event generated when the document is deleted from
     *
     * @param e the document event
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        updateItem();
    }

    /**
     * Event generated when the document properties are changed
     *
     * @param e the document event
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        updateItem();
    }

    /**
     * updates the item with the specified value
     */
    public void updateItem() {
        item.setAttributeSwitcher(attribute, textField.getText());
    }
}

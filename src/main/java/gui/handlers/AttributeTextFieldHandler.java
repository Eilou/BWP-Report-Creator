package gui.handlers;

import javax.swing.*;
import javax.swing.event.*;

public class AttributeTextFieldHandler implements DocumentListener {

    private  JTextField textField;
    private Setter<String> setter;

    /**
     * Sets up the handler. Could get the text field from the source of the event but that means
     * going through the SwingUtilities library which may be shorter in code but is more complex
     */
    public AttributeTextFieldHandler(JTextField textField, Setter<String> setter) {
        this.textField = textField;
        this.setter = setter;
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
        setter.apply(textField.getText());
    }
}

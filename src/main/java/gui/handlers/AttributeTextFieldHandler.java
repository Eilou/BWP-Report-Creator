package gui.handlers;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AttributeTextFieldHandler implements DocumentListener {

    /**
     * Event generated when the document is written to
     * @param e the document event
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        updateItem();
    }

    /**
     * Event generated when the document is deleted from
     * @param e the document event
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        updateItem();
    }

    /**
     * Event generated when the document properties are changed
     * @param e the document event
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        updateItem();
    }

    public void updateItem() {
        System.out.println("Hello world");
    }
}

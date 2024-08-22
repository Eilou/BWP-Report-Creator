package gui.handlers;

import javax.swing.*;
import java.awt.event.*;

/**
 * handles the events generated by comboboxes for the item attributes
 * @param <T> Type value of the attribute to set
 */
public class AttributeComboBoxHandler<T> implements ActionListener {

    private Setter<T> setter;

    public AttributeComboBoxHandler(Setter<T> setter) {
        this.setter = setter;
    }

    /**
     * Handles the combo box events
     * Cleverly, and conveniently if I am honest, if the custom option is selected, then the textField event handler will
     * overwrite this, as that updates whenever the text box is typed in
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<T> source = (JComboBox<T>) e.getSource();
        setter.apply((T) source.getSelectedItem()); // itll work, trust, literally everything is an object (i think)

    }
}
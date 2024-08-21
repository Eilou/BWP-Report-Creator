package gui.handlers;

import gui.panels.details.GridPanel;
import items.Item;

import javax.swing.*;
import java.awt.event.*;

/**
 * handles the events generated by comboboxes for the item attributes
 */
public class AttributeComboBoxHandler implements ActionListener {

    private Item item;
    private String attribute;

    public AttributeComboBoxHandler(Item item, String attribute) {
        this.item = item;
        this.attribute = attribute;
    }

    /**
     * Handles the combo box events
     * Cleverly, and conveniently if I am honest, if the custom option is selected, then the textField event handler will
     * overwrite this, as that updates whenever the text box is typed in
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox source = (JComboBox)e.getSource(); // unparameterised because there are multiple different combo boxes
        item.setAttributeSwitcher(attribute, source.getSelectedItem());

    }
}

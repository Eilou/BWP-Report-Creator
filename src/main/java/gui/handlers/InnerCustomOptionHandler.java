package gui.handlers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handler to add a custom text box when the "Custom" option is selected on each different combobox in a nested panel in a Grid Panel
 *
 * @param <T> Often will be type string
 */
public class InnerCustomOptionHandler<T> implements ActionListener {

    private JComboBox<T> comboBox;
    private JPanel innerPanel;
    private JTextField innerCustomTextField;

    /**
     * Sets up the handler to add a custom text box when needed
     *
     * @param comboBox         check here to see if its selected
     * @param innerPanel the panel to update
     */
    public InnerCustomOptionHandler(JComboBox<T> comboBox, JPanel innerPanel, JTextField innerCustomTextField) {
        this.comboBox = comboBox;
        this.innerPanel = innerPanel;
        this.innerCustomTextField = innerCustomTextField;
    }

    /**
     * Handles the need to add a custom option for an inner panel by doing what
     * GridPanel.addCustomOption() and GridPanel.removeCustomOption() do on smaller scope
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (comboBox.getSelectedItem() != null && comboBox.getSelectedItem().toString().equals(
                "Custom")) {
            innerPanel.add(innerCustomTextField);

        } else {
            innerPanel.remove(innerCustomTextField);

        }
        innerPanel.revalidate();
        innerPanel.repaint();

    }
}

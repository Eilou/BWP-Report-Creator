package gui.handlers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handler to add a custom text box when the "Custom" option is selected on each different combobox
 */
public class CustomOptionHandler implements ActionListener {

    private JComboBox<String> comboBox;
    private JPanel gridSectionPanel;
    private JTextField customTextField;

    /**
     * Sets up the handler to add a custom text box when needed
     *
     * @param comboBox         check here to see if its selected
     * @param gridSectionPanel the panel to update
     */
    public CustomOptionHandler(JComboBox<String> comboBox, JPanel gridSectionPanel) {
        this.comboBox = comboBox;
        this.gridSectionPanel = gridSectionPanel;
        this.customTextField = new JTextField("HELP ME");
    }

    /**
     * Handles the event
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (comboBox.getSelectedItem() != null && comboBox.getSelectedItem().equals("Custom")) {
            gridSectionPanel.add(this.customTextField);
        } else {
            gridSectionPanel.remove(customTextField);
        }
        gridSectionPanel.repaint();
        gridSectionPanel.revalidate();
    }
}

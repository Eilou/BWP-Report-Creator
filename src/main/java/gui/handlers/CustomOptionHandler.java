package gui.handlers;

import gui.panels.details.GridPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handler to add a custom text box when the "Custom" option is selected on each different combobox
 * @param <T> Means can work it with both String combo boxes and YesNoOption ones
 */
public class CustomOptionHandler <T> implements ActionListener {

    private JComboBox<T> comboBox;
    private GridPanel gridSectionPanel;

    /**
     * Sets up the handler to add a custom text box when needed
     *
     * @param comboBox         check here to see if its selected
     * @param gridSectionPanel the panel to update
     *
     */
    public CustomOptionHandler (JComboBox<T> comboBox, GridPanel gridSectionPanel) {
        this.comboBox = comboBox;
        this.gridSectionPanel = gridSectionPanel;
    }

    /**
     * Handles the event
     *
     * @param e the event to be processed
     *          TODO MAYEB I NEED TO REVALIDATE AND REPAINT THE REPORT CREATION PANEL AND THE
     *          DETAIL PANEL COS Something IS AFOOT
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (comboBox.getSelectedItem() != null && comboBox.getSelectedItem().toString().equals(
                "Custom")) {
            gridSectionPanel.addCustomOption();
        } else {
            gridSectionPanel.removeCustomOption();
        }

    }
}

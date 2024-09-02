package gui.handlers;

import gui.panels.details.DetailPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Shrinks down or expands a detail panel so it is more readable
 */
public class EditMinimiseButtonHandler implements ActionListener {

    private DetailPanel detailPanel;
    private JPanel dataPanel;

    public EditMinimiseButtonHandler(DetailPanel detailPanel, JPanel dataPanel) {
        this.detailPanel = detailPanel;
        this.dataPanel = dataPanel;
    }

    /**
     * Changes the appearance of the button and adds/removes the data panel to expand or shrink
     * the detail panel
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = detailPanel.getEditMinimiseButton();
        if (button.getText().equals("Minimise")) {
            button.setText("Edit");
            button.setBackground(Color.orange);
            button.setForeground(Color.black);
            detailPanel.remove(dataPanel);
        } else {
            button.setText("Minimise");
            button.setBackground(Color.green);
            button.setForeground(Color.black);
            detailPanel.add(dataPanel);
        }
        button.repaint();
        button.revalidate();
    }
}

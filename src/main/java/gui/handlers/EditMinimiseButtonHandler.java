package gui.handlers;

import gui.panels.details.DetailPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditMinimiseButtonHandler implements ActionListener {

    private DetailPanel detailPanel;
    private JPanel dataPanel;

    public EditMinimiseButtonHandler(DetailPanel detailPanel, JPanel dataPanel) {
        this.detailPanel = detailPanel;
        this.dataPanel = dataPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = detailPanel.getEditMinimiseButton();
        if (button.getText().equals("Minimise")) {
            button.setText("Edit");
            button.setBackground(Color.orange);
            button.setForeground(Color.black);
            detailPanel.remove(dataPanel);
        }
        else {
            button.setText("Minimise");
            button.setBackground(Color.green);
            button.setForeground(Color.black);
            detailPanel.add(dataPanel);
        }
        button.repaint();
        button.revalidate();
    }
}

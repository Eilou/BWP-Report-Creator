package gui.panels.details;

import enums.ReportState;
import items.doors.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Subclass to be used when creating a door report
 */
public class DoorDetailsPanel extends DetailPanel{
    private Door door;

    public DoorDetailsPanel(JPanel parentPanel, ReportState reportState, int count) {
        super(parentPanel, reportState, count);
        this.door = new Door(count);
    }

    /**
     * Sets up the overall panel and the inner panel divisions ready for the different input forms
     */
    public void setup() {
        super.setup();

        GridBagConstraints gbc = new GridBagConstraints();

        int rows = 5;
        int columns = 5;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5,5,5,5);

        JPanel[][] gridPanels = new JPanel[rows][columns];
        for (int row = 0; row < gridPanels.length; row++) {
            for (int column = 0; column < gridPanels[0].length; column++) {
                gridPanels[row][column] = new JPanel();
                gridPanels[row][column].setPreferredSize(new Dimension(50,50));
                gridPanels[row][column].setBorder(new LineBorder(Color.BLACK));
                gridPanels[row][column].setBackground(new Color(255,0,255));

                gbc.fill = GridBagConstraints.BOTH; // stretch both horizontally and vertically
                gbc.weightx = 1.0; // expand in both directions at equal rates
                gbc.weighty = 1.0;

                gbc.gridx = column;
                gbc.gridy = row;

                add(gridPanels[row][column], gbc);
            }
        }

        addForms();
    }

    public void addForms() {

    }

}

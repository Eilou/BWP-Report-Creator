package gui.panels.details;

import enums.ReportState;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Superclass for the details for each individual item in a given report
 * To inherit from to specify the details
 */
public class DetailPanel extends JPanel {

    private JPanel parentPanel;
    private TitledBorder border;
   private int count;

    public DetailPanel(JPanel parentPanel, ReportState reportState, int count) {
        this.parentPanel = parentPanel;
        this.count = count;
        setPreferredSize(new Dimension(0, 200));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        border = new TitledBorder(reportState.toString().charAt(0) + String.valueOf(count));
    }
    public void setup() {
        setBackground(Color.red);
        setBorder(border);
        setLayout(new GridBagLayout());
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

    }
}

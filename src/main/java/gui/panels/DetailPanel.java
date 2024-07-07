package gui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Superclass for the details for each individual item in a given report
 * To inherit from to specify the details
 */
public class DetailPanel extends JPanel {

    private JPanel parentPanel;

    public DetailPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;
        setPreferredSize(new Dimension(parentPanel.getWidth(), 50));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        System.out.println(parentPanel.getHeight());
    }
    public void setup() {
        setBackground(Color.red);
        setLayout(new GridBagLayout());
    }
}

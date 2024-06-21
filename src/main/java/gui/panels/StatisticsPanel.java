package gui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Holds general information about the
 */
public class StatisticsPanel extends JPanel {

    private JPanel parentPanel;


    public StatisticsPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;
        setSize(new Dimension(parentPanel.getWidth(), 200));
    }

    public void setup() {
        setBackground(Color.blue);
    }

}

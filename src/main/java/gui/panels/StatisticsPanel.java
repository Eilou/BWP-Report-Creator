package gui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Holds general information about the
 */
public class StatisticsPanel extends JPanel {

    private JPanel parentPanel;
    private JLabel text;

    public StatisticsPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;
        setPreferredSize(new Dimension(parentPanel.getWidth(), 50));

        text = new JLabel();
        text.setHorizontalAlignment(JLabel.CENTER);
    }

    public void setup() {
        setBackground(Color.blue);

        text.setText("Statistics Filler Text");
        text.setHorizontalAlignment(JLabel.CENTER);
        add(text);
    }

}

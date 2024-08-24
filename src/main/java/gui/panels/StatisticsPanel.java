package gui.panels;

import gui.Styling;

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
    }

    /**
     * adds in the text to the statistics panel
     */
    public void setup() {
        setBackground(Styling.FOREGROUND);
        setForeground(Styling.TEXT);

        setLayout(new GridLayout(1, 0));

        text.setText("Statistics Filler Text");
        text.setForeground(Styling.TEXT);
        text.setHorizontalAlignment(JLabel.CENTER);
        add(text);
    }

}

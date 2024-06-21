package gui;

import gui.panels.StatisticsPanel;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;

/**
 * Frame to hold the main window for the GUI
 */
public class GUIFrame extends JFrame {

    public Toolkit toolkit = Toolkit.getDefaultToolkit();
    public GUIFrame() {
        setTitle("BWP Report Creator");
        setSize((int) (toolkit.getScreenSize().width * 0.75),
                (int) (toolkit.getScreenSize().height * 0.75));
    }

    /**
     * Define the panels and the hierarchy
     */
    public void setup() {

        // set up panels
        Container contentPanel = getContentPane();
        contentPanel.setBackground(Color.red);

        StatisticsPanel statisticsPanel = new StatisticsPanel((JPanel) contentPanel);
        statisticsPanel.setup();
        contentPanel.add(statisticsPanel);

        // show on the screen
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

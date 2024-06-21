package gui;

import gui.panels.ReportCreationPanel;
import gui.panels.StatisticsPanel;
import gui.panels.ToolbarPanel;

import javax.swing.*;
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
        contentPanel.setLayout(new BorderLayout());

        StatisticsPanel statisticsPanel = new StatisticsPanel((JPanel) contentPanel);
        statisticsPanel.setup();
        contentPanel.add(statisticsPanel, BorderLayout.NORTH);

        ToolbarPanel toolbarPanel = new ToolbarPanel((JPanel) contentPanel);
        toolbarPanel.setup();
        contentPanel.add(toolbarPanel, BorderLayout.WEST);

        ReportCreationPanel reportCreationPanel = new ReportCreationPanel((JPanel) contentPanel);
        reportCreationPanel.setup();
        contentPanel.add(reportCreationPanel, BorderLayout.CENTER);

        // show on the screen
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

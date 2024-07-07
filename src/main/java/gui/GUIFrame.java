package gui;

import enums.ReportState;
import gui.panels.*;

import javax.swing.*;
import java.awt.*;

/**
 * Frame to hold the main window for the GUI
 */
public class GUIFrame extends JFrame {

    public Toolkit toolkit = Toolkit.getDefaultToolkit();

    private ReportState reportState;
    private Container contentPane;
    private StatisticsPanel statisticsPanel;
    private ToolbarPanel toolbarPanel;
    private ReportCreationPanel reportCreationPanel;

    public GUIFrame() {
        reportState = ReportState.DOOR;
        setTitle("BWP Report Creator");
        setSize((int) (toolkit.getScreenSize().width * 0.75),
                (int) (toolkit.getScreenSize().height * 0.75));

        this.contentPane = getContentPane();
        statisticsPanel = new StatisticsPanel((JPanel) contentPane);
        toolbarPanel = new ToolbarPanel((JPanel) contentPane);
        reportCreationPanel = new ReportCreationPanel((JPanel) contentPane, reportState);

    }

    /**
     * Define the panels and the hierarchy
     */
    public void setup() {

        // set up panels
        contentPane.setBackground(Color.red);
        contentPane.setLayout(new BorderLayout());

        statisticsPanel.setup();
        contentPane.add(statisticsPanel, BorderLayout.NORTH);

        toolbarPanel.setup();
        contentPane.add(toolbarPanel, BorderLayout.WEST);

        reportCreationPanel.setup();
        contentPane.add(reportCreationPanel, BorderLayout.CENTER);

        // show on the screen
        setVisible(true);
//        reportCreationPanel.createDefaultItems();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

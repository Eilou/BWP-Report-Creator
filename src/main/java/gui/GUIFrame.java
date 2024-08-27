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
    private ProjectDetailsPanel projectDetailsPanel;
    private ToolbarPanel toolbarPanel;
    private ReportCreationPanel reportCreationPanel;

    public GUIFrame() {
        reportState = ReportState.DOOR;
        setTitle("BWP Report Creator");
        setSize((int) (toolkit.getScreenSize().width * 0.75),
                (int) (toolkit.getScreenSize().height * 0.75));

        this.contentPane = getContentPane();
        reportCreationPanel = new ReportCreationPanel((JPanel) contentPane, reportState);
        projectDetailsPanel = new ProjectDetailsPanel((JPanel) contentPane, reportState);
        toolbarPanel = new ToolbarPanel((JPanel) contentPane, reportState, reportCreationPanel, projectDetailsPanel);
    }

    /**
     * Define the panels and the hierarchy
     */
    public void setup() {

        // set up panels
        contentPane.setBackground(Color.red);
        contentPane.setLayout(new BorderLayout());

        projectDetailsPanel.setup();
        contentPane.add(projectDetailsPanel, BorderLayout.NORTH);

        toolbarPanel.setup();
        contentPane.add(toolbarPanel, BorderLayout.WEST);

        reportCreationPanel.setup();
        contentPane.add(reportCreationPanel, BorderLayout.CENTER);

        // show on the screen
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

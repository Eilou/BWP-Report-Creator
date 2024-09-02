package gui;

import enums.ReportState;
import gui.panels.*;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.io.*;

/**
 * Frame to hold the main window for the GUI
 */
public class GUIFrame extends JFrame {

    public Toolkit toolkit = Toolkit.getDefaultToolkit();

    private ReportState reportState;
    private ProjectDetailsPanel projectDetailsPanel;
    private ToolbarPanel toolbarPanel;
    private ReportCreationPanel reportCreationPanel;


    public GUIFrame() {
        reportState = ReportState.DOOR;
        setTitle("BWP Report Creator");
        setSize((int) (toolkit.getScreenSize().width * 0.75),
                (int) (toolkit.getScreenSize().height * 0.75));

        reportCreationPanel = new ReportCreationPanel(reportState);
        projectDetailsPanel = new ProjectDetailsPanel(reportState);
        toolbarPanel = new ToolbarPanel(reportState, reportCreationPanel, projectDetailsPanel);
    }

    /**
     * Define the panels and the hierarchy
     */
    public void setup() {

        // set up panels
//        contentPane.setBackground(Color.red);
        Container contentPane = getContentPane();

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

package gui.panels;

import enums.ReportState;
import exporting.DoorReportBuilder;
import gui.GUIFrame;
import gui.Styling;
import gui.handlers.AddDetailButtonHandler;
import gui.handlers.GenerateReportHandler;
import gui.handlers.RemoveLastDetailButtonHandler;
import gui.panels.details.DoorDetailsPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

/**
 * Provides the toolbar on the left hand side of the application
 */
public class ToolbarPanel extends JPanel {

    private ReportState reportState;
    private ReportCreationPanel reportCreationPanel;
    private ProjectDetailsPanel projectDetailsPanel;

    private JButton saveButton;
    private JButton openButton;

    private JButton addDetailButton;
    private JCheckBox backfillCheckbox;
    private JButton removeDetailButton;
    private JButton generateReportButton;

    private JButton summaryButton;

    public ToolbarPanel(ReportState reportState, ReportCreationPanel reportCreationPanel, ProjectDetailsPanel projectDetailsPanel) {
        this.reportState = reportState;
        this.reportCreationPanel = reportCreationPanel;
        this.projectDetailsPanel = projectDetailsPanel;

        saveButton = new JButton("Save");
        openButton = new JButton("Open");

        addDetailButton = new JButton();
        addDetailButton.setIcon(new ImageIcon("src/main/resources/buttonIcons/addItemIcon.png"));
        backfillCheckbox = new JCheckBox();
        backfillCheckbox.setIcon(new ImageIcon("src/main/resources/buttonIcons/backfillIcon-Disabled.png"));
        removeDetailButton = new JButton();
        removeDetailButton.setIcon(new ImageIcon("src/main/resources/buttonIcons/deleteLastIcon.png"));
        generateReportButton = new JButton("");
        generateReportButton.setIcon(new ImageIcon("src/main/resources/buttonIcons/generateIcon.png"));

        summaryButton = new JButton("Summary");
    }

    /**
     * Add the buttons onto the GUI and locate them as appropriate
     */
    public void setup() {

        setBackground(Styling.FOREGROUND);
        setForeground(Styling.TEXT);
        setLayout(new GridLayout(0, 1));

        attachHandlers();

        add(saveButton);
        add(openButton);

        JPanel addDetailPanel = new JPanel();
        addDetailPanel.setLayout(new BoxLayout(addDetailPanel, BoxLayout.LINE_AXIS));
        addDetailPanel.add(addDetailButton);
        addDetailPanel.add(backfillCheckbox);
        add(addDetailPanel);
        add(removeDetailButton);
        add(generateReportButton);
        add(summaryButton);
    }

    /**
     * Attach the handlers to the buttons to give them functionality
     */
    public void attachHandlers() {

        saveButton.addActionListener(lambda -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "BWP Report Creator Files", "bwparchirc");

            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(null); // null parent so appears in the middle
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("User chose to save file: " + chooser.getSelectedFile().getName());
                try {
                    reportCreationPanel.save(chooser.getSelectedFile());
                } catch (IOException e) {
                    System.out.println("User has chosen to write to a file which doesn't yet exist");
                    e.printStackTrace();
                }
            }
        });

        openButton.addActionListener(lambda -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "BWP Report Creator Files", "bwparchirc");

            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null); // null parent so appears in the middle
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("User chose to open file: " + chooser.getSelectedFile().getName());
                try {
                    reportCreationPanel.load(chooser.getSelectedFile());
                } catch (IOException e) {
                    System.out.println("User has chosen to read from a file which doesn't yet exist");
                    e.printStackTrace();
                }
            }
        });

        addDetailButton.addActionListener(new AddDetailButtonHandler(reportState, reportCreationPanel, backfillCheckbox));
        backfillCheckbox.addActionListener(e -> {
            if (backfillCheckbox.isSelected())
                backfillCheckbox.setIcon(new ImageIcon("src/main/resources/buttonIcons/backfillIcon-Enabled.png"));
            else backfillCheckbox.setIcon(new ImageIcon("src/main/resources/buttonIcons/backfillIcon-Disabled.png"));
        });

        removeDetailButton.addActionListener(new RemoveLastDetailButtonHandler(reportState, reportCreationPanel));
        generateReportButton.addActionListener(new GenerateReportHandler(new DoorReportBuilder(reportCreationPanel, projectDetailsPanel)));
    }

    /**
     * Updates the Generate report button to match whatever state the report creator is currently in by changing which
     * type of report builder the GenerateReportHandler is using
     */
    public void reloadGenerateReportButtonHandlers() {

        ((GenerateReportHandler) generateReportButton.getActionListeners()[0]).setReportBuilder(
                switch (reportState) {
                    case DOOR -> new DoorReportBuilder(reportCreationPanel, projectDetailsPanel);
                    default -> null;
                }
        );

    }

}

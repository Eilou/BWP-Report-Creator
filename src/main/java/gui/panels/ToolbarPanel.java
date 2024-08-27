package gui.panels;

import enums.ReportState;
import exporting.DoorReportBuilder;
import gui.Styling;
import gui.handlers.AddDetailButtonHandler;
import gui.handlers.GenerateReportHandler;
import gui.handlers.RemoveLastDetailButtonHandler;
import gui.panels.details.DoorDetailsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Provides the toolbar on the left hand side of the application
 */
public class ToolbarPanel extends JPanel {

    private JPanel parentPanel;
    private ReportState reportState;
    private ReportCreationPanel reportCreationPanel;
    private ProjectDetailsPanel projectDetailsPanel;

    private JButton addDetailButton;
    private JCheckBox backfillCheckbox;
    private JButton removeDetailButton;
    private JButton generateReportButton;

    public ToolbarPanel(JPanel parentPanel, ReportState reportState, ReportCreationPanel reportCreationPanel, ProjectDetailsPanel projectDetailsPanel) {
        this.parentPanel = parentPanel;
        this.reportState = reportState;
        this.reportCreationPanel = reportCreationPanel;
        this.projectDetailsPanel = projectDetailsPanel;
//        setPreferredSize(new Dimension(100, parentPanel.getHeight()));

        addDetailButton = new JButton();
        addDetailButton.setIcon(new ImageIcon("src/main/resources/buttonIcons/addItemIcon.png"));
        backfillCheckbox = new JCheckBox();
        backfillCheckbox.setIcon(new ImageIcon("src/main/resources/buttonIcons/backfillIcon-Disabled.png"));
        removeDetailButton = new JButton();
        removeDetailButton.setIcon(new ImageIcon("src/main/resources/buttonIcons/deleteLastIcon.png"));
        generateReportButton = new JButton("");
        generateReportButton.setIcon(new ImageIcon("src/main/resources/buttonIcons/generateIcon.png"));

    }

    /**
     * Add the buttons onto the GUI and locate them as appropriate
     */
    public void setup() {
        setBackground(Styling.FOREGROUND);
        setForeground(Styling.TEXT);
        setLayout(new GridLayout(0,1));

        JLabel text = new JLabel();
        text.setText("Toolbar Filler Text");
        text.setForeground(Styling.TEXT);
        text.setHorizontalAlignment(JLabel.CENTER);
        add(text);

        attachHandlers();

        JPanel addDetailPanel = new JPanel();
        addDetailPanel.setLayout(new BoxLayout(addDetailPanel, BoxLayout.LINE_AXIS));
        addDetailPanel.add(addDetailButton);
        addDetailPanel.add(backfillCheckbox);
        add(addDetailPanel);
//        add(addDetailButton);
//        add(backfillCheckbox);
        add(removeDetailButton);
        add(generateReportButton);
    }

    /**
     * Attach the handlers to the buttons to give them functionality
     */
    public void attachHandlers() {
        addDetailButton.addActionListener(
                new AddDetailButtonHandler(reportState, reportCreationPanel, backfillCheckbox));
        backfillCheckbox.addActionListener(e -> {
            if (backfillCheckbox.isSelected()) backfillCheckbox.setIcon(new ImageIcon("src/main/resources/buttonIcons/backfillIcon-Enabled.png"));
            else backfillCheckbox.setIcon(new ImageIcon("src/main/resources/buttonIcons/backfillIcon-Disabled.png"));
        });
        removeDetailButton.addActionListener(
                new RemoveLastDetailButtonHandler(reportState, reportCreationPanel));
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

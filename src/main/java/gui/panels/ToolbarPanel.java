package gui.panels;

import enums.ReportState;
import exporting.DoorReportBuilder;
import gui.StyledButton;
import gui.Styling;
import gui.handlers.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


/**
 * Provides the toolbar on the left hand side of the application
 */
public class ToolbarPanel extends JPanel {

    private ReportState reportState;
    private ReportCreationPanel reportCreationPanel;
    private ProjectDetailsPanel projectDetailsPanel;

    private StyledButton newFileButton;
    private StyledButton saveButton;
    private StyledButton openButton;

    private StyledButton addDetailButton;
    private JCheckBox backfillCheckbox;
    private StyledButton removeDetailButton;
    private StyledButton generateReportButton;

    private StyledButton summaryButton;

    public ToolbarPanel(ReportState reportState, ReportCreationPanel reportCreationPanel, ProjectDetailsPanel projectDetailsPanel) {
        this.reportState = reportState;
        this.reportCreationPanel = reportCreationPanel;
        this.projectDetailsPanel = projectDetailsPanel;

        newFileButton = new StyledButton("New");
        saveButton = new StyledButton("Save");
        openButton = new StyledButton("Open");


        addDetailButton = new StyledButton("Add " + reportState);
        backfillCheckbox = new JCheckBox("Backfill");

        removeDetailButton = new StyledButton("Remove last "+ reportState);
        generateReportButton = new StyledButton("Generate " + reportState + " Report");

        summaryButton = new StyledButton("Summary");

    }

    /**
     * Add the buttons onto the GUI and locate them as appropriate
     */
    public void setup() {

        setBackground(Styling.FOREGROUND);
        setForeground(Styling.TEXT);
        setLayout(new GridLayout(0, 1));

        setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(Styling.TEXT),
                        new LineBorder(Styling.FOREGROUND, 5)));

        styleButtons();
        attachHandlers();

        add(newFileButton);
        add(saveButton);
        add(openButton);

        JPanel addDetailPanel = new JPanel();
        addDetailPanel.setLayout(new BoxLayout(addDetailPanel, BoxLayout.LINE_AXIS));
        addDetailPanel.setBackground(Styling.FOREGROUND);
        addDetailPanel.setForeground(Styling.TEXT);
        addDetailPanel.setBorder(new LineBorder(Styling.TEXT));
        addDetailPanel.add(addDetailButton);
        addDetailPanel.add(backfillCheckbox);

        add(addDetailPanel);
        add(removeDetailButton);
        add(generateReportButton);
        add(summaryButton);
    }

    /**
     * Style the button elements
     */
    public void styleButtons() {
        newFileButton.setup(UIManager.getIcon("FileView.fileIcon"));
        saveButton.setup(UIManager.getIcon("FileView.floppyDriveIcon"));
        openButton.setup(UIManager.getIcon("FileView.directoryIcon"));

        addDetailButton.setup(new ImageIcon("src/main/resources/buttonIcons/addItemIcon.png"));
        addDetailButton.setBorder(new EmptyBorder(0,0,0,0));
        backfillCheckbox.setIcon(new ImageIcon("src/main/resources/buttonIcons/backfillIcon-Disabled.png"));
        Styling.setComponentColours(backfillCheckbox, Styling.FOREGROUND, Styling.TEXT);

        removeDetailButton.setup(new ImageIcon("src/main/resources/buttonIcons/deleteLastIcon.png"));

        generateReportButton.setup(new ImageIcon("src/main/resources/buttonIcons/generateIcon.png"));
        summaryButton.setup();

    }

    /**
     * Attach the handlers to the buttons to give them functionality
     */
    public void attachHandlers() {

        newFileButton.addActionListener(new NewFileButtonHandler(reportCreationPanel, projectDetailsPanel));
        saveButton.addActionListener(new SaveButtonHandler(reportCreationPanel, projectDetailsPanel));
        openButton.addActionListener(new OpenButtonHandler(reportCreationPanel, projectDetailsPanel));

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

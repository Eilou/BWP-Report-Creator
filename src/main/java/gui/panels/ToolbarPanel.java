package gui.panels;

import enums.ReportState;
import exporting.DoorReportBuilder;
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
    private JButton addDetailButton;
    private JCheckBox backfillCheckbox;
    private JButton removeDetailButton;
    private JButton generateReportButton;

    public ToolbarPanel(JPanel parentPanel, ReportState reportState, ReportCreationPanel reportCreationPanel) {
        this.parentPanel = parentPanel;
        this.reportState = reportState;
        this.reportCreationPanel = reportCreationPanel;
        setPreferredSize(new Dimension(100, parentPanel.getHeight()));

        addDetailButton = new JButton("Add " + reportState);
        backfillCheckbox = new JCheckBox();
        removeDetailButton = new JButton("Remove " + reportState);
        generateReportButton = new JButton("Generate Report");
    }

    /**
     * Add the buttons onto the GUI and locate them as appropriate
     */
    public void setup() {
        setBackground(Color.green);
        setLayout(new GridLayout(0,1));

        JLabel text = new JLabel();
        text.setText("Toolbar Filler Text");
        text.setHorizontalAlignment(JLabel.CENTER);
        add(text);

        attachHandlers();

        add(addDetailButton);
        add(backfillCheckbox);
        add(removeDetailButton);
        add(generateReportButton);
    }

    /**
     * Attach the handlers to the buttons to give them functionality
     */
    public void attachHandlers() {
        addDetailButton.addActionListener(
                new AddDetailButtonHandler(reportState, reportCreationPanel, backfillCheckbox));
        removeDetailButton.addActionListener(
                new RemoveLastDetailButtonHandler(reportState, reportCreationPanel));
        generateReportButton.addActionListener(new GenerateReportHandler(new DoorReportBuilder(reportCreationPanel)));
    }

    /**
     * Updates the Generate report button to match whatever state the report creator is currently in
     */
    public void reloadGenerateReportButtonHandlers() {

        ((GenerateReportHandler) generateReportButton.getActionListeners()[0]).setReportBuilder(
                switch (reportState) {
                    case DOOR -> new DoorReportBuilder(reportCreationPanel);
                    default -> null;
                }
        );

    }

}

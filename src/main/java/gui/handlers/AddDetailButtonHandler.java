package gui.handlers;

import enums.ReportState;

import gui.panels.ReportCreationPanel;

import java.awt.event.*;

/**
 * Updates the report creation panel with an additional detail input panel, relative to the
 * current report state
 */
public class AddDetailButtonHandler implements ActionListener {
    private ReportState reportState;
    private ReportCreationPanel reportCreationPanel;
    public AddDetailButtonHandler(ReportState reportState, ReportCreationPanel reportCreationPanel) {
        this.reportState = reportState;
        this.reportCreationPanel = reportCreationPanel;
    }

    /**
     * Add an item detail panel to the report creation panel and get it to display
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        reportCreationPanel.addNewDetailPanel();

        System.out.println("Added " + reportState.toString().toLowerCase() + " panel");
    }
}

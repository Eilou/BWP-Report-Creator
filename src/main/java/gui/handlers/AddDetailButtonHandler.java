package gui.handlers;

import enums.ReportState;

import gui.panels.ReportCreationPanel;

import javax.swing.*;
import java.awt.event.*;

/**
 * Updates the report creation panel with an additional detail input panel, relative to the
 * current report state
 */
public class AddDetailButtonHandler implements ActionListener {
    private ReportState reportState;
    private ReportCreationPanel reportCreationPanel;
    private JCheckBox backfillCheckbox;
    public AddDetailButtonHandler(ReportState reportState,
                                  ReportCreationPanel reportCreationPanel,
                                  JCheckBox backfillCheckbox) {
        this.reportState = reportState;
        this.reportCreationPanel = reportCreationPanel;
        this.backfillCheckbox = backfillCheckbox;
    }

    /**
     * Add an item detail panel to the report creation panel and get it to display
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        reportCreationPanel.addNewDetailPanel(backfillCheckbox.isSelected());

        System.out.println("Added " + reportState.toString().toLowerCase() + " panel");
    }
}

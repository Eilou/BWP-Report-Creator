package gui.handlers;

import enums.ReportState;
import gui.panels.ReportCreationPanel;
import gui.panels.details.DetailPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles the removal of the detail panel which this handler's button is inside of
 */
public class CloseDetailButtonHandler implements ActionListener {

    private ReportCreationPanel reportCreationPanel;
    private ReportState reportState;
    private DetailPanel detailPanel;

    public CloseDetailButtonHandler(ReportCreationPanel reportCreationPanel,
                                    ReportState reportState, DetailPanel detailPanel) {
        this.reportCreationPanel = reportCreationPanel;
        this.reportState = reportState;
        this.detailPanel = detailPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        reportCreationPanel.removeDetailPanel(detailPanel);
        System.out.println("Removed panel associated with " + reportState + " " + detailPanel.getItem().getCount());
    }
}

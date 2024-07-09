package gui.handlers;

import enums.ReportState;
import gui.panels.ReportCreationPanel;
import gui.panels.details.DetailPanel;
import gui.panels.details.DoorDetailsPanel;

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
        DetailPanel itemPanelToAdd;
        switch (reportState) {
            case DOOR -> itemPanelToAdd = new DoorDetailsPanel(
                    reportCreationPanel,
                    reportState,
                    reportCreationPanel.getListOfDetails().size() + 1
            );
            default -> itemPanelToAdd = null;
        }
        assert itemPanelToAdd != null;
        itemPanelToAdd.setup();
        reportCreationPanel.getListOfDetails().add(itemPanelToAdd);
        reportCreationPanel.getDetailsContainer().add(itemPanelToAdd);
        reportCreationPanel.revalidate();
        System.out.println(reportCreationPanel.getListOfDetails().size());
    }
}

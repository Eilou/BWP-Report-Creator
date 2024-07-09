package gui.handlers;

import enums.ReportState;
import gui.panels.ReportCreationPanel;
import gui.panels.details.*;
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
                    reportCreationPanel.getListOfDetailsPanels().size() + 1
            );
            default -> itemPanelToAdd = null;
        }
        itemPanelToAdd.setup();

        reportCreationPanel.getListOfDetailsPanels().add(itemPanelToAdd);
        reportCreationPanel.getDetailsContainer().add(itemPanelToAdd);

        reportCreationPanel.getDetailsContainer().revalidate();
        System.out.println("Added " + reportState.toString().toLowerCase() + " panel");
    }
}

package gui.handlers;

import enums.ReportState;
import gui.panels.ReportCreationPanel;
import gui.panels.details.DetailPanel;

import java.awt.event.*;
import java.util.ArrayList;

/**
 * Updates the report creation panel by removing detail input panel, relative to the
 * current report state
 */
public class RemoveDetailsButtonHandler implements ActionListener {
    private ReportState reportState;
    private ReportCreationPanel reportCreationPanel;
    public RemoveDetailsButtonHandler(ReportState reportState, ReportCreationPanel reportCreationPanel) {
        this.reportState = reportState;
        this.reportCreationPanel = reportCreationPanel;
    }

    /**
     * Removes the most recently added detail panel whenever the remove button is pressed
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<DetailPanel> listOfDetailsPanels = reportCreationPanel.getListOfDetailsPanels();
        if (!listOfDetailsPanels.isEmpty()) {
            DetailPanel recentlyAddedPanel = listOfDetailsPanels.get(listOfDetailsPanels.size() - 1);
            reportCreationPanel.getDetailsContainer().remove(recentlyAddedPanel);
            listOfDetailsPanels.remove(recentlyAddedPanel);

            // remove logically and visually
            reportCreationPanel.revalidate();
            reportCreationPanel.repaint();
            System.out.println("Removed most recently added " + reportState.toString().toLowerCase() + " " +
                    "panel");
        }
        else {
            System.out.println("No remaining panels to remove");
        }
    }
}

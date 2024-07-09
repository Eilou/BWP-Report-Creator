package gui.panels;

import enums.ReportState;
import gui.panels.details.DetailPanel;
import gui.panels.details.DoorDetailsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Holds the area where you can create a report
 */
public class ReportCreationPanel extends JPanel {

    private JPanel parentPanel;
    private ArrayList<DetailPanel> listOfDetails;
    private ReportState reportState;

    private JPanel detailsContainer;
    private JScrollPane detailsScrollPane;
    public ReportCreationPanel(JPanel parentPanel, ReportState reportState) {
        this.parentPanel = parentPanel;
        this.reportState = reportState;
        listOfDetails = new ArrayList<>();

        detailsContainer = new JPanel();
        detailsScrollPane = new JScrollPane(detailsContainer);
    }

    public void setup() {
        setBackground(Color.green);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        detailsContainer.setLayout(new BoxLayout(detailsContainer, BoxLayout.PAGE_AXIS));
        detailsContainer.setBackground(Color.yellow);
        createDefaultItems();
        detailsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(detailsScrollPane);
    }

    /**
     * Generates a base amount of doors to fill out
     * Needs to be in a separate method, so it can be called after set visible is true
     */
    public void createDefaultItems() {
        for (int i = 0; i < 2; i++) {
            // will want to add an if statement here depending on the report state to determine
            // which subclass of detail panel to use

            if (reportState == ReportState.DOOR) {
                DetailPanel currentPanel = new DoorDetailsPanel(this, reportState, i + 1);
                currentPanel.setup();
                listOfDetails.add(currentPanel);
                detailsContainer.add(currentPanel);
            }
        }

    }

    ////////////////////////////////////
    // getters and setters
    ////////////////////////////////////

    public ArrayList<DetailPanel> getListOfDetails() {
        return listOfDetails;
    }

    public void setListOfDetails(ArrayList<DetailPanel> listOfDetails) {
        this.listOfDetails = listOfDetails;
    }

    public JPanel getDetailsContainer() {
        return detailsContainer;
    }

    public void setDetailsContainer(JPanel detailsContainer) {
        this.detailsContainer = detailsContainer;
    }
}

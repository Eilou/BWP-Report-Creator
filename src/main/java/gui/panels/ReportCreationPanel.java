package gui.panels;

import enums.ReportState;
import gui.panels.details.DetailPanel;
import gui.panels.details.DoorDetailsPanel;
import gui.panels.details.SpecificDetailInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Holds the area where you can create a report
 */
public class ReportCreationPanel extends JPanel {

    private JPanel parentPanel;
    private ArrayList<DetailPanel> listOfDetailsPanels;
    private ReportState reportState;

    private JPanel detailsContainer;
    private JScrollPane detailsScrollPane;
    public ReportCreationPanel(JPanel parentPanel, ReportState reportState) {
        this.parentPanel = parentPanel;
        this.reportState = reportState;
        listOfDetailsPanels = new ArrayList<>();

        detailsContainer = new JPanel();
        detailsScrollPane = new JScrollPane(detailsContainer);
    }

    public void setup() {
        setBackground(Color.green);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        detailsContainer.setLayout(new BoxLayout(detailsContainer, BoxLayout.PAGE_AXIS));
        detailsContainer.setBackground(Color.yellow);
        detailsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(detailsScrollPane);
    }

    /**
     * Adds a new detail panel to this panel
     */
    public void addNewDetailPanel() {

        DetailPanel itemPanelToAdd = new DetailPanel(this, reportState, getListOfDetailsPanels().size() + 1);

        SpecificDetailInterface dataPanelToAdd;
        switch (reportState) {
            case DOOR -> dataPanelToAdd = new DoorDetailsPanel(
                    this,
                    getListOfDetailsPanels().size() + 1
            );
            default -> dataPanelToAdd = null;
        }

        dataPanelToAdd.setup();
        itemPanelToAdd.setup((JPanel) dataPanelToAdd);

        getListOfDetailsPanels().add(itemPanelToAdd);
        getDetailsContainer().add(itemPanelToAdd);

        revalidate();
        repaint();
    }

    /**
     * Removes a given detail panel from the scroll pane
     * @param panelToRemove detail panel to be removed from this panel's scroll pane
     */
    public void removeDetailPanel(DetailPanel panelToRemove) {

        getDetailsContainer().remove(panelToRemove);
        listOfDetailsPanels.remove(panelToRemove);

        // remove logically and visually
        revalidate();
        repaint();

    }

    ////////////////////////////////////
    // getters and setters
    ////////////////////////////////////

    public ArrayList<DetailPanel> getListOfDetailsPanels() {
        return listOfDetailsPanels;
    }

    public void setListOfDetailsPanels(ArrayList<DetailPanel> listOfDetailsPanels) {
        this.listOfDetailsPanels = listOfDetailsPanels;
    }

    public JPanel getDetailsContainer() {
        return detailsContainer;
    }

    public void setDetailsContainer(JPanel detailsContainer) {
        this.detailsContainer = detailsContainer;
    }
}

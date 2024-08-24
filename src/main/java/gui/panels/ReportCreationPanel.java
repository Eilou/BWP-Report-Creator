package gui.panels;

import enums.ReportState;
import gui.Styling;
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
    private ArrayList<Integer> middleDeletedIndexes;
    private ReportState reportState;

    private JPanel detailsContainer;
    private JScrollPane detailsScrollPane;

    public ReportCreationPanel(JPanel parentPanel, ReportState reportState) {
        this.parentPanel = parentPanel;
        this.reportState = reportState;
        listOfDetailsPanels = new ArrayList<>();
        middleDeletedIndexes = new ArrayList<>();

        detailsContainer = new JPanel();
        detailsScrollPane = new JScrollPane(detailsContainer);
    }

    public void setup() {
        setBackground(Styling.BACKGROUND);
//        setForeground(Styling.FOREGROUND);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        detailsContainer.setLayout(new BoxLayout(detailsContainer, BoxLayout.PAGE_AXIS));
        detailsContainer.setBackground(Styling.BORDER);
//        detailsContainer.setForeground(Styling.FOREGROUND);
        detailsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(detailsScrollPane);
    }

    /**
     * Adds a new detail panel to this panel
     */
    public void addNewDetailPanel(Boolean backfill) {

        DetailPanel itemPanelToAdd = new DetailPanel(this, reportState, listOfDetailsPanels.size() + 1);


        int count = -1;

        // give the option to either backfill or add to the front
        if (backfill && !middleDeletedIndexes.isEmpty()) {
            int indexToAdd = middleDeletedIndexes.get(0);
            middleDeletedIndexes.remove(0);
            count = indexToAdd + 1;
            // need to change the count value for this door as well

            listOfDetailsPanels.add(indexToAdd, itemPanelToAdd);
            detailsContainer.add(itemPanelToAdd, indexToAdd);
        }
        else {
            if (listOfDetailsPanels.isEmpty()) {count = 1;}
            else {count = listOfDetailsPanels.get(listOfDetailsPanels.size()-1).getCount() + 1;}
            listOfDetailsPanels.add(itemPanelToAdd);
            detailsContainer.add(itemPanelToAdd);
        }

        SpecificDetailInterface dataPanelToAdd;
        switch (reportState) {
            case DOOR -> dataPanelToAdd = new DoorDetailsPanel(
                    this,
                    count
            );
            default -> dataPanelToAdd = null;
        }

        assert dataPanelToAdd != null;
        dataPanelToAdd.setup();
        itemPanelToAdd.setup((JPanel) dataPanelToAdd);

        revalidate();
        repaint();
    }

    /**
     * Removes a given detail panel from the scroll pane
     * @param panelToRemove detail panel to be removed from this panel's scroll pane
     */
    public void removeDetailPanel(DetailPanel panelToRemove) {

        // if not removing the end item, add it to the middle removed list
        if (!panelToRemove.equals(listOfDetailsPanels.get(listOfDetailsPanels.size()-1))) {
            middleDeletedIndexes.add(panelToRemove.getCount() - 1);
        }

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

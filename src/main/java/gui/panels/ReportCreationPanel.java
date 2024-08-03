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

        DetailPanel itemPanelToAdd = new DetailPanel(this, reportState, listOfDetailsPanels.size() + 1);



//        int indexToAdd = listOfDetailsPanels.size();
//        int count = listOfDetailsPanels.size() + 1;



        // if the last item's count - 1 does not equal the number in the list, then there is one
        // which has been deleted from the middle and should be backfilled in
            // could do through looking through the list finding the missing number as it is out
            // of sequence and then using this
        // or
            // keep track of those deleted if it's a middle one and then fill this


        int count = listOfDetailsPanels.size() + 1;
        if (listOfDetailsPanels.isEmpty() || listOfDetailsPanels.get(listOfDetailsPanels.size() -1).getCount() == listOfDetailsPanels.size()) {
            listOfDetailsPanels.add(itemPanelToAdd);
            detailsContainer.add(itemPanelToAdd);

        } else {
            int indexToAdd = middleDeletedIndexes.get(0);
            middleDeletedIndexes.remove(0);
            count = indexToAdd + 1;
            // need to change the count value for this door as well

            listOfDetailsPanels.add(indexToAdd, itemPanelToAdd);
            detailsContainer.add(itemPanelToAdd, indexToAdd);
        }


//        if (listOfDetailsPanels.isEmpty()) {
//            listOfDetailsPanels.add(itemPanelToAdd);
//            detailsContainer.add(itemPanelToAdd);
//        }
//        else {
//            DetailPanel lastPanel = listOfDetailsPanels.get(listOfDetailsPanels.size() -1);
//            // can do this because both count and size are 1 above the indices which are base 0
//            if (lastPanel.getCount() != listOfDetailsPanels.size()) {
//                int indexToAdd = middleDeletedIndexes.get(0);
//                middleDeletedIndexes.remove(0);
//                // need to change the count value for this door as well
//
//                listOfDetailsPanels.add(indexToAdd, itemPanelToAdd);
//                detailsContainer.add(itemPanelToAdd, indexToAdd);
//
//            }
//
//            else {
//                listOfDetailsPanels.add(itemPanelToAdd);
//                detailsContainer.add(itemPanelToAdd);
//            }
//        }

        SpecificDetailInterface dataPanelToAdd;
        switch (reportState) {
            case DOOR -> dataPanelToAdd = new DoorDetailsPanel(
                    this,
                    count
            );
            default -> dataPanelToAdd = null;
        }

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

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

    private ArrayList<DetailPanel> listOfDetailsPanels;
    private ArrayList<Integer> middleDeletedIndexes;
    private ReportState reportState;

    private JPanel detailsContainer;
    private JScrollPane detailsScrollPane;

    public ReportCreationPanel(ReportState reportState) {
        this.reportState = reportState;
        listOfDetailsPanels = new ArrayList<>();
        middleDeletedIndexes = new ArrayList<>();

        detailsContainer = new JPanel();
        detailsScrollPane = new JScrollPane(detailsContainer);
    }

    public void setup() {
        setBackground(Styling.BACKGROUND);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        detailsContainer.setLayout(new GridBagLayout());
        detailsContainer.setBackground(Styling.BORDER);
        detailsScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(detailsScrollPane);

    }

    /**
     * Adds a new detail panel to this panel
     */
    public void addNewDetailPanel(Boolean backfill) {

        DetailPanel itemPanelToAdd = new DetailPanel(this, reportState, listOfDetailsPanels.size() + 1);

        int count = -1;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 0, 0, 0);

        gbc.fill = GridBagConstraints.BOTH; // stretch both horizontally and vertically
        gbc.weightx = 1; // expand in the x direction but not in the y
        gbc.weighty = 0;

        gbc.gridx = 0;
        gbc.gridy = 0;

        // give the option to either backfill or add to the front
        if (backfill && !middleDeletedIndexes.isEmpty()) {
            int indexToAdd = middleDeletedIndexes.get(0);
            middleDeletedIndexes.remove(0);
            count = indexToAdd + 1;
            // need to change the count value for this door as well

            listOfDetailsPanels.add(indexToAdd, itemPanelToAdd);

            // don't need to -1 here as count is +1
            gbc.gridy = indexToAdd;

            detailsContainer.add(itemPanelToAdd, gbc);
        } else {
            if (listOfDetailsPanels.isEmpty()) {
                count = 1;
            } else {
                count = listOfDetailsPanels.get(listOfDetailsPanels.size() - 1).getCount() + 1;
            }
            listOfDetailsPanels.add(itemPanelToAdd);

            // -1 as I am basing the grid y off the count here which is one above the index value
            gbc.gridy = count - 1;
            detailsContainer.add(itemPanelToAdd, gbc);
        }

        SpecificDetailInterface dataPanelToAdd;
        switch (reportState) {
            case DOOR -> dataPanelToAdd = new DoorDetailsPanel(count);
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
     *
     * @param panelToRemove detail panel to be removed from this panel's scroll pane
     */
    public void removeDetailPanel(DetailPanel panelToRemove) {

        // if not removing the end item, add it to the middle removed list
        if (!panelToRemove.equals(listOfDetailsPanels.get(listOfDetailsPanels.size() - 1))) {
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

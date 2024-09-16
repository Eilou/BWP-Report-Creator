package gui.panels;

import enums.ReportState;
import gui.Styling;
import gui.panels.details.DetailPanel;
import gui.panels.details.DoorDataPanel;
import gui.panels.details.DataPanelInterface;
import items.doors.Door;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * Holds the area where you can create a report
 */
public class ReportCreationPanel extends JPanel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private ArrayList<DetailPanel> listOfDetailsPanels;
    private ArrayList<Integer> middleDeletedIndexes;
    private ReportState reportState;

    private transient JPanel detailsContainer;
    private transient JScrollPane detailsScrollPane;

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
        detailsScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

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

        DataPanelInterface dataPanelToAdd;
        switch (reportState) {
            case DOOR -> dataPanelToAdd = new DoorDataPanel(count);
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

    /**
     * Writes the current state of the app to a file to be loaded in later
     *
     * @param file the file path to save to
     * @throws IOException if the file path doesn't exist
     */
    public void save(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file, true);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(reportState);
        oos.writeObject(listOfDetailsPanels);
        oos.writeObject(middleDeletedIndexes);

        fos.close();
    }

    /**
     * Retrieves a saved version of the app in byte form, then updates the current one to match,
     * finally it runs setup() to ensure all handlers are appropriately assigned
     *
     * @param file file to read from
     * @throws IOException if the file does not exist
     */
    @SuppressWarnings("unchecked") // this is to counter the ois.readObject(), but I know they're correct so it's fine
    public void load(File file, long readSoFar) throws IOException {

        FileInputStream fis = new FileInputStream(file);

        try {
            // skip ahead to where read so far
            fis.getChannel().position(readSoFar);

            ObjectInputStream ois = new ObjectInputStream(fis);
            this.reportState = (ReportState) ois.readObject();

            this.listOfDetailsPanels = (ArrayList<DetailPanel>) ois.readObject();
            this.middleDeletedIndexes = (ArrayList<Integer>) ois.readObject();

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

            for (DetailPanel currentPanel : listOfDetailsPanels) {
                gbc.gridy = currentPanel.getItem().getCount() - 1; // the count value never changes with the door, so it can be used to insert at the correct point without needing to store null values in the array list (which would be embarrassing)
                currentPanel.setParentPanel(this); // reassign the event handlers
                currentPanel.attachHandlers();
                ((DataPanelInterface) currentPanel.getDataPanel()).attachHandlers();
                detailsContainer.add(currentPanel, gbc);
            }

            repaint();
            revalidate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally { // not optimal but more readable
            fis.close();
        }
    }

    /**
     * Clears out any existing details ready to add in new ones
     */
    public void reset() {
        this.listOfDetailsPanels = new ArrayList<>();
        this.middleDeletedIndexes = new ArrayList<>();
        this.detailsContainer.removeAll();
        repaint();
        revalidate();
    }

    /**
     * Generates a summary of all the current items
     *
     * @return the summary as a Hashmap(String, String)
     */
    public HashMap<String, Object> generateSummary() {
        HashMap<String, Object> summary = new HashMap<>();
        switch (reportState) {
            case DOOR -> {
                // ensures the dimensions are ordered so easier to read them
                TreeMap<String, Object> leafSizesSummary = new TreeMap<>((key1, key2) -> {
                    // convert the dimensions into integers
                    int key1Int = Integer.parseInt(key1.substring(0, key1.indexOf('x') - 1));
                    int key2Int = Integer.parseInt(key2.substring(0, key2.indexOf('x') - 1));

                    return Integer.compare(key1Int, key2Int);
                });

                for (DetailPanel detailPanel : listOfDetailsPanels) {
                    Door door = ((DoorDataPanel) detailPanel.getDataPanel()).getDoor();

                    String leafSizesKey = door.getLeafWidth() + " x " + door.getLeafHeight();

                    checkAbsentOrIncrement(leafSizesSummary, leafSizesKey, 1);
                    summary.put("Leaf Sizes", leafSizesSummary); // updates the inner map every time

                    String hingesKey = "Hinges";
                    // IF HINGE IS PER LEAF, THEN MULTIPLE ITS VALUE BY THE DOOR NUMBER
                    // ALSO if it says pair, then it means 2 hinges
                    HashMap<String, Object> hingesTypeMap = new HashMap<>();
                    switch (door.getIronmongery().getHinges()) {
                        case "" -> {
                        }
                        case "1/2 pair" -> {
                            checkAbsentOrIncrement(hingesTypeMap, "Total", 0.5);
                            checkAbsentOrIncrement(hingesTypeMap, "Pair", 0.5);//todo
                        }
//                        case "1/2 pair per leaf"

                    }
                    checkAbsentOrIncrement(summary, hingesKey, 1);

                    String handlesKey = "Handles";
                    checkAbsentOrIncrement(summary, handlesKey, 1);

                    String latchesKey = "Latches";
                    checkAbsentOrIncrement(summary, latchesKey, 1);

                    String locksKey = "Locks";
                    checkAbsentOrIncrement(summary, locksKey, 1);


                }
            }
            default -> {
            }
        }
        return summary;
    }

    /**
     * Private method to abstract the generate summary method()
     * Checks first if there is an instance of a key in the hashmap, if so it increments it to a certain value, else it
     * just adds the value onto the existing count
     *
     * @param hashMap the hashmap to check (is type Map so can be used on TreeMaps or HashMaps
     * @param key     the key to look at
     * @param value   the value to set or increment by
     */
    private void checkAbsentOrIncrement(Map<String, Object> hashMap, String key, double value) {
        if (hashMap.containsKey(key))
            hashMap.put(key, (int) hashMap.get(key) + value);
        else
            hashMap.putIfAbsent(key, value);
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

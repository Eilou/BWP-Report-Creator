package summarising;

import gui.panels.ReportCreationPanel;
import gui.panels.details.DetailPanel;
import gui.panels.details.DoorDataPanel;
import items.doors.Door;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Class to abstract the summary building process out from the report creation panel
 */
public class DoorSummaryBuilder extends SummaryBuilder{

    private ReportCreationPanel reportCreationPanel;
    private HashMap<String, Object> summary;
    private HashMap<String, Object> hingesQuantityCustomMap;
    private HashMap<String, Object> hingeCustomNumberMap;


    public DoorSummaryBuilder(ReportCreationPanel reportCreationPanel) {
        this.reportCreationPanel = reportCreationPanel;
        this.summary = new HashMap<>();
        hingesQuantityCustomMap = new HashMap<>();
        this.hingeCustomNumberMap = new HashMap<>();
    }

    @Override
    public HashMap<String, Object> generateSummary() {

        // ensures the dimensions are ordered so easier to read them
        TreeMap<String, Object> leafSizesSummary = new TreeMap<>((key1, key2) -> {
            // convert the dimensions into integers
            int key1Int = Integer.parseInt(key1.substring(0, key1.indexOf('x') - 1));
            int key2Int = Integer.parseInt(key2.substring(0, key2.indexOf('x') - 1));

            return Integer.compare(key1Int, key2Int);
        });

        // setting up for the hinges
        hingesQuantityCustomMap.put("Custom", hingeCustomNumberMap);
        summary.put("Hinges", hingesQuantityCustomMap);

        for (DetailPanel detailPanel : reportCreationPanel.getListOfDetailsPanels()) {
            Door door = ((DoorDataPanel) detailPanel.getDataPanel()).getDoor();

            String leafSizesKey = door.getLeafWidth() + " x " + door.getLeafHeight();

            checkAbsentOrIncrement(leafSizesSummary, leafSizesKey, 1);
            summary.put("Leaf Sizes", leafSizesSummary); // updates the inner map every time

            incrementHinges(door);

            String handlesKey = "Handles";
            checkAbsentOrIncrement(summary, handlesKey, 1);

            String latchesKey = "Latches";
            checkAbsentOrIncrement(summary, latchesKey, 1);

            String locksKey = "Locks";
            checkAbsentOrIncrement(summary, locksKey, 1);

        }

        return summary;
    }

    private int getHingeMultiplier(String leafNumber) {
        return switch (leafNumber) {
            case "Singular" -> 1;
            case "Double" -> 2;
            case "Triple" -> 3;
            case "Quadruple" -> 4;
            default -> {
                try {
                    yield Integer.parseInt(leafNumber.strip());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid custom door number so multiplier set to -1");
                    yield -1;
                }
            }
        };
    }

    private void incrementHinges(Door door) {
        // IF HINGE IS PER LEAF, THEN MULTIPLE ITS VALUE BY THE DOOR NUMBER
        // ALSO if it says pair, then it means 2 hinges
        String currentHinge = door.getIronmongery().getHinges();

        int multiplier = 1;
        if (currentHinge.contains("per leaf"))
            multiplier = getHingeMultiplier(door.getLeafNumber());

        // if door number is invalid but hinge is "per leaf"
        if (multiplier == -1)
            checkAbsentOrIncrement(hingeCustomNumberMap, currentHinge + " x " + door.getLeafNumber(), 1);
        else {
            switch (currentHinge) {

                case "1/2 pair" -> checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 1);
                case "1 pair" -> checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 2);
                case "1 1/2 pair" -> checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 3);
                case "2 pair" -> checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 4);
                case "2 1/2 pair" -> checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 5);

                case "1/2 pair per leaf" -> checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", multiplier);
                case "1 pair per leaf" -> checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 2 * multiplier);
                case "1 1/2 pair per leaf" -> checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 3 * multiplier);
                case "2 pair per leaf" -> checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 4 * multiplier);
                case "2 1/2 pair per leaf" -> checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 5 * multiplier);

                // if hinge number is custom and leaf number is valid (custom or not), independent of "per leaf"
                default -> {
                    if (!currentHinge.contains("per leaf"))
                        checkAbsentOrIncrement(hingeCustomNumberMap, currentHinge, 1); // if custom hinge number, but not "per leaf"
                    else
                        checkAbsentOrIncrement(hingeCustomNumberMap, currentHinge + " x " + door.getLeafNumber(), 1); // if custom hinge number, valid leaf number and "per leaf"
                }

            }
        }


    }


}

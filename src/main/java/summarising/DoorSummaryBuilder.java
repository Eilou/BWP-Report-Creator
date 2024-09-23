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
public class DoorSummaryBuilder extends SummaryBuilder {

    private ReportCreationPanel reportCreationPanel;
    private HashMap<String, Object> summary;
    private TreeMap<String, Object> leafSizesSummary;
    private HashMap<String, Object> leafSizesCustomNumberMap;
    private HashMap<String, Object> hingesQuantityCustomMap;
    private HashMap<String, Object> hingesCustomNumberMap;
    private HashMap<String, Object> handlesQuantityCustomMap;
    private HashMap<String, Object> handlesCustomNumberMap;
    private HashMap<String, Object> latchesQuantityCustomMap;
    private HashMap<String, Object> latchesCustomNumberMap;


    public DoorSummaryBuilder(ReportCreationPanel reportCreationPanel) {
        this.reportCreationPanel = reportCreationPanel;
        this.summary = new HashMap<>();

        this.leafSizesSummary = new TreeMap<>();
        this.leafSizesCustomNumberMap = new HashMap<>();
        this.hingesQuantityCustomMap = new HashMap<>();
        this.hingesCustomNumberMap = new HashMap<>();
        this.handlesQuantityCustomMap = new HashMap<>();
        this.handlesCustomNumberMap = new HashMap<>();
        this.latchesQuantityCustomMap = new HashMap<>();
        this.latchesCustomNumberMap = new HashMap<>();

    }

    /**
     * Generates the summary as a hashmap of key terms
     *
     * @return the hashmap summary
     */
    @Override
    public HashMap<String, Object> generateSummary() {

        // ensures the dimensions are ordered so easier to read them
        leafSizesSummary = new TreeMap<>((key1, key2) -> {
            // convert the dimensions into integers
            int key1Int = Integer.parseInt(key1.substring(0, key1.indexOf('x') - 1));
            int key2Int = Integer.parseInt(key2.substring(0, key2.indexOf('x') - 1));

            return Integer.compare(key1Int, key2Int);
        });

        // setting up for leaf sizes
        summary.put("Leaf Sizes", leafSizesSummary);
        summary.put("Leaf Sizes Custom", leafSizesCustomNumberMap);

        // setting up for the hinges
        hingesQuantityCustomMap.put("Custom", hingesCustomNumberMap);
        summary.put("Hinges", hingesQuantityCustomMap);

        // setting up for the handles
        handlesQuantityCustomMap.put("Custom", handlesCustomNumberMap);
        summary.put("Handles", handlesQuantityCustomMap);

        // setting up for the latches
        latchesQuantityCustomMap.put("Custom", latchesCustomNumberMap);
        summary.put("Latches", latchesQuantityCustomMap);

        for (DetailPanel detailPanel : reportCreationPanel.getListOfDetailsPanels()) {
            Door door = ((DoorDataPanel) detailPanel.getDataPanel()).getDoor();

            incrementLeafSizes(door);

            incrementHinges(door); // need to clarify number of pairs required for this

            incrementHandles(door);

            incrementLatches(door);

//            String latchesKey = "Latches";
//            checkAbsentOrIncrement(summary, latchesKey, 1);

            String locksKey = "Locks";
            checkAbsentOrIncrement(summary, locksKey, 1);

        }

        return summary;
    }

    /**
     * Gets the multiplier of certain elements relative to the leaf number
     * e.g. if the hinges are per leaf then that number should be multiplied by the leaf number
     *
     * @param leafNumber the leaf number of a particular
     * @return the multiplier for a door aspect
     */
    private int getLeafNumberMultiplier(String leafNumber) {
        return switch (leafNumber) {
            case "Single" -> 1;
            case "Double" -> 2;
            case "Triple" -> 3;
            case "Quad" -> 4;
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

    /**
     * Increments the leaf sizes and handles any custom sizes
     *
     * @param door the door to pull the leaf sizes
     */
    private void incrementLeafSizes(Door door) {
        String leafSizesKey = door.getLeafWidth() + " x " + door.getLeafHeight();

        try {
            Integer.parseInt(leafSizesKey.substring(0, leafSizesKey.indexOf('x') - 1));
            int multiplier = getLeafNumberMultiplier(door.getLeafNumber());
            // multiplies it by the leaf number, or if custom adds it to that

            if (multiplier == -1) {
                checkAbsentOrIncrement(leafSizesCustomNumberMap,
                        leafSizesKey + " x " + door.getLeafNumber(),
                        1);
            } else {
                checkAbsentOrIncrement(leafSizesSummary, leafSizesKey, multiplier);
            }

            // if the leaf size cannot be passed into the treemap because it is custom then put
            // it the custom map
        } catch (NumberFormatException e) {
            checkAbsentOrIncrement(leafSizesCustomNumberMap,
                    leafSizesKey + " x " + door.getLeafNumber(),
                    1);
        }
    }

    /**
     * Summarises the number of hinges in the system
     * If the hinge number is "per leaf", then the quantity is hinge number * leaf  number.
     * If the hinge number is custom and contains "per leaf", then a custom counter is incremented stating the custom
     * hinge number x leaf number : quantity.
     * If the hinge number is custom but doesn't contain "per leaf", then the custom counter is incremented to show just
     * hinge number : quantity.
     * If the hinge number is normal but the leaf size is custom and it contains "per leaf", then the custom counter shows
     * hinge number x leaf number : quantity.
     * If the hinge number is normal but the leaf size is custom and it doesn't contain per leaf,
     * then the regular quantity value is increased.
     * If hinge number is custom but can be converted to a double.
     * if the thing is per leaf, then it is multiplied by the number (if invalid then it adds to
     * the custom, but if valid...).
     * it multiplies the two and adds to a quantity.
     *
     * @param door the door currently being accessed in the summary
     */
    private void incrementHinges(Door door) {
        // IF HINGE IS PER LEAF, THEN MULTIPLE ITS VALUE BY THE DOOR NUMBER
        // ALSO if it says pair, then it means 2 hinges
        String currentHinge = door.getIronmongery().getHinges();

        int multiplier = 1;
        if (currentHinge.contains("per leaf"))
            multiplier = getLeafNumberMultiplier(door.getLeafNumber());

        // if door number is custom or invalid but hinge is "per leaf"
        if (multiplier == -1)
            checkAbsentOrIncrement(hingesCustomNumberMap, currentHinge + " x " + door.getLeafNumber(), 1);
        else {
            switch (currentHinge) {

                case "1/2 pair" -> checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 1);
                case "1 pair" -> checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 2);
                case "1 1/2 pair" -> checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 3);
                case "2 pair" -> checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 4);
                case "2 1/2 pair" -> checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 5);

                case "1/2 pair per leaf" ->
                        checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", multiplier);
                case "1 pair per leaf" ->
                        checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 2 * multiplier);
                case "1 1/2 pair per leaf" ->
                        checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 3 * multiplier);
                case "2 pair per leaf" ->
                        checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 4 * multiplier);
                case "2 1/2 pair per leaf" ->
                        checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity", 5 * multiplier);

                // if hinge number is custom and leaf number is valid (custom or not), independent of "per leaf"
                default -> {
                    if (!currentHinge.contains("per leaf")) {
                        try {
                            // first tries to convert a custom hinge number to a double, but if
                            // they cannot then add it to a custom one
                            checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity",
                                    Integer.parseInt(currentHinge.strip()));
                        } catch (NumberFormatException e) {
                            System.out.println("Custom hinge quantity could not be converted to an double so instead storing in custom");
                            checkAbsentOrIncrement(hingesCustomNumberMap, currentHinge, 1); // if custom hinge number, but not "per leaf"
                        }
                    } else {
                        try {
                            String value = currentHinge.substring(0, currentHinge.strip().indexOf("per leaf") - 1);
                            checkAbsentOrIncrement(hingesQuantityCustomMap, "Quantity",
                                    Integer.parseInt(value) * multiplier);
                        } catch (NumberFormatException e) {
                            checkAbsentOrIncrement(hingesCustomNumberMap, currentHinge + " x " + door.getLeafNumber(), 1); // if custom hinge number, valid leaf number and "per leaf"
                        }
                    }
                }

            }
        }


    }

    /**
     * Increments the number of handles or adds to a custom value
     *
     * @param door the door to decide whether to increment or not
     */
    private void incrementHandles(Door door) {
        String currentHandle = door.getIronmongery().getHandle();
        if (!currentHandle.isEmpty()) {
            if (currentHandle.equals("Yes"))
                checkAbsentOrIncrement(handlesQuantityCustomMap, "Quantity", 1);
            else
                checkAbsentOrIncrement(handlesCustomNumberMap, currentHandle, 1);
        }
    }

    /**
     * Used to increment the different number of latches and increment appropriately
     *
     * @param door
     */
    private void incrementLatches(Door door) {
        String currentLatch = door.getIronmongery().getLatch();
        if (!currentLatch.isEmpty()) {
            if (currentLatch.equals("Yes"))
                checkAbsentOrIncrement(latchesQuantityCustomMap, "Quantity", 1);
            else
                checkAbsentOrIncrement(latchesCustomNumberMap, currentLatch, 1);
        }
    }


}

package gui.panels.details;

import gui.Styling;
import items.doors.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Subclass to be used when creating a door report
 */
public class DoorDetailsPanel extends JPanel implements SpecificDetailInterface {

    private Door door;
    private GridPanel[][] gridPanels; // left as raw as it may be YesNoOption or String

    private final JComboBox<String> floorComboBox = new JComboBox<>();
    private final JComboBox<String> roomComboBox = new JComboBox<>();
    private final JComboBox<String> wallConstructionComboBox = new JComboBox<>();
    private final JTextField doorTypeTextField = new JTextField();
    private final JComboBox<String> internalExternalComboBox = new JComboBox<>();
    private final JComboBox<String> partMThresholdComboBox = new JComboBox<>();
    private final JComboBox<String> fireRatingComboBox = new JComboBox<>();
    private final JComboBox<String> glazedComboBox = new JComboBox<>();

    private final JPanel leafTypeInnerPanel = new JPanel();
    private final JPanel leafWidthInnerPanel = new JPanel();
    private final JPanel leafHeightInnerPanel = new JPanel();
    private final JPanel leafNumberInnerPanel = new JPanel();
    private final JComboBox<String> leafWidthComboBox = new JComboBox<>();
    private final JComboBox<String> leafHeightComboBox = new JComboBox<>();
    private final JComboBox<String> leafTypeComboBox = new JComboBox<>();
    // want a dropdown next to this with sizes: imperial, metric or bespoke
    // default should be imperial
    // this then influences the options available from the numbers
    private final JComboBox<String> leafNumberComboBox = new JComboBox<>();
    // this should be a dropdown box with options: single, double, triple, quad

    // todo lookup table realtive to leaf size
    // anything which is relative could ask whether want a dropdown which gets default filled with custom option, or just use a text field
    private final JComboBox<String> clearOpeningComboBox = new JComboBox<>();
    private final JComboBox<String> entranceLevelComboBox = new JComboBox<>();
    private final JComboBox<String> partMCompliantComboBox = new JComboBox<>();
    //todo:
    // this doesn't actually need to be a partM compliant box, but should be updates as a result
    // of an if elseif else statement (look at the Excel sheet for reference)

    private final JComboBox<String> additionalPlyLiningComboBox = new JComboBox<>();
    private final JComboBox<String> structuralOpeningComboBox = new JComboBox<>();
    // this should be a lookup table
    private final JTextField structuralOpeningDetailsTextField = new JTextField();

    private final JTextField frameDetailsTextField = new JTextField();
    private final JTextField sillDetailsTextField = new JTextField();
    private final JTextField architraveTypeTextField = new JTextField();

    private final JComboBox<String> hingesComboBox = new JComboBox<>();
    private final JComboBox<String> latchComboBox = new JComboBox<>();
    private final JComboBox<String> lockComboBox = new JComboBox<>();
    private final JComboBox<String> handleComboBox = new JComboBox<>();
    private final JTextField additionalNotesTextField = new JTextField();

    /**
     * Constructor called when the add item is called in door report state, instancing the
     * different inputs to then be added in later
     *
     * @param count the door number
     */
    public DoorDetailsPanel(int count) {

        this.door = new Door(count);
        int rows = 5;
        int columns = 5;
        gridPanels = new GridPanel[rows][columns];

    }

    /**
     * Sets up the overall panel and the inner panel divisions ready for the different input forms
     */
    public void setup() {

        setLayout(new GridBagLayout());
        setBackground(Styling.FOREGROUND);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(10, 10, 10, 10);

        for (int row = 0; row < gridPanels.length; row++) {
            for (int column = 0; column < gridPanels[0].length; column++) {
                gridPanels[row][column] = new GridPanel(this.door); //  the best part about GridPanel, is that it can be repeated using any type of Item, aren't I the best
                gridPanels[row][column].setLayout(new BoxLayout(gridPanels[row][column], BoxLayout.PAGE_AXIS));
                gridPanels[row][column].setPreferredSize(new Dimension(50, 100));
                gridPanels[row][column].setBorder(new LineBorder(Styling.BORDER));
                gridPanels[row][column].setBackground(Styling.BACKGROUND);

                gbc.fill = GridBagConstraints.BOTH; // stretch both horizontally and vertically
                gbc.weightx = 1; // expand in both directions at equal rates
                gbc.weighty = 1;

                gbc.gridx = column;
                gbc.gridy = row;

                if (row == 1 && column == 3) gbc.gridwidth = 2;
                else gbc.gridwidth = 1;

                add(gridPanels[row][column], gbc);
            }
        }

        populateComboBoxes();
        positionForms();
        attachHandlers();
        setFormDefaults();
    }

    /**
     * Position the inputs in the grid bag layout
     */
    public void positionForms() {

        // I am aware it talks about there being unchecked or unsafe operations, however, that's because
        // generics and arrays don't mix, I can always change it for ArrayLists in the future if need be
        gridPanels[0][0].setup("Floor", floorComboBox);
        gridPanels[0][1].setup("Room", roomComboBox);
        gridPanels[0][2].setup("Wall Construction", wallConstructionComboBox);
        gridPanels[0][3].setup("Door Type", doorTypeTextField);
        gridPanels[0][4].setup("Internal or External", internalExternalComboBox);

        gridPanels[1][0].setup("Part M Threshold", partMThresholdComboBox);
        gridPanels[1][1].setup("Fire Rating", fireRatingComboBox);
        gridPanels[1][2].setup("Glazed", glazedComboBox);

        JPanel leafSizeInputPanel = new JPanel();
        leafSizeInputPanel.setLayout(new GridLayout(1,4));

        leafTypeInnerPanel.setLayout(new BoxLayout(leafTypeInnerPanel, BoxLayout.PAGE_AXIS));
        leafTypeInnerPanel.add(leafTypeComboBox);

        leafWidthInnerPanel.setLayout(new BoxLayout(leafWidthInnerPanel, BoxLayout.PAGE_AXIS));
        leafWidthInnerPanel.add(leafWidthComboBox);

        leafHeightInnerPanel.setLayout(new BoxLayout(leafHeightInnerPanel, BoxLayout.PAGE_AXIS));
        leafHeightInnerPanel.add(leafHeightComboBox);

        leafNumberInnerPanel.setLayout(new BoxLayout(leafNumberInnerPanel, BoxLayout.PAGE_AXIS));
        leafNumberInnerPanel.add(leafNumberComboBox);

        leafSizeInputPanel.add(leafTypeInnerPanel);
        leafSizeInputPanel.add(leafWidthInnerPanel);
        leafSizeInputPanel.add(leafHeightInnerPanel);
        leafSizeInputPanel.add(leafNumberInnerPanel);
        gridPanels[1][3].setup("Leaf Size", leafSizeInputPanel);

        //        gridPanels[1][4].add();

        gridPanels[2][0].setup("Clear Opening", clearOpeningComboBox);
        gridPanels[2][1].setup("Entrance Level", entranceLevelComboBox);
        gridPanels[2][2].setup("Part M Compliant", partMCompliantComboBox);
        gridPanels[2][3].setup("Additional Ply Lining", additionalPlyLiningComboBox);

        //        gridPanels[2][4].add();

        gridPanels[3][0].setup("Structural Opening", structuralOpeningComboBox);
        gridPanels[3][1].setup("Structural Opening Details", structuralOpeningDetailsTextField);
        gridPanels[3][2].setup("Frame Details", frameDetailsTextField);
        gridPanels[3][3].setup("Sill Details", sillDetailsTextField);
        gridPanels[3][4].setup("Architrave Type", architraveTypeTextField);

        gridPanels[4][0].setup("Hinges", hingesComboBox);
        gridPanels[4][1].setup("Latch", latchComboBox);
        gridPanels[4][2].setup("Lock", lockComboBox);
        gridPanels[4][3].setup("Handle", handleComboBox);
        gridPanels[4][4].setup("Additional Notes", additionalNotesTextField);

    }

    public void setFormDefaults() {
        leafTypeComboBox.setSelectedItem("Imperial");
    }

    /**
     * Populates all the different comboboxes in the application
     */
    public void populateComboBoxes() {

        DetailPanel.populateGivenComboBox(floorComboBox, new String[]{"", "Ground Floor", "First" +
                " Floor",
                "Second Floor", "Third Floor", "Lower Ground Floor", "Upper Ground Floor", "Mezzanine", "Basement 1", "Basement 2", "Custom"});
        DetailPanel.populateGivenComboBox(roomComboBox, new String[]{"", "Custom"});
        DetailPanel.populateGivenComboBox(wallConstructionComboBox, new String[]{"", "Masonry " +
                "Cavity wall", "Timberframe", "SIPS panel", "100mm blockwork", "140mm blockwork", "215mm blockwork", "89mm partition", "100mm partition", "140mm partition", "Custom"});
        DetailPanel.populateGivenComboBox(internalExternalComboBox, new String[]{"", "Internal", "External [1]", "Custom"});
        DetailPanel.populateGivenComboBox(partMThresholdComboBox, new String[]{"", "Y [2]", "Custom"});
        DetailPanel.populateGivenComboBox(fireRatingComboBox, new String[]{"", "FD20 [3]", "FD30 [3]", "FD30-SC [3]", "FD60 [3]", "Custom"});
        DetailPanel.populateGivenComboBox(glazedComboBox, new String[]{"", "Y [4]", "Custom"});
        DetailPanel.populateGivenComboBox(leafTypeComboBox, new String[]{"", "Imperial", "Metric", "Bespoke"});
        DetailPanel.populateGivenComboBox(leafWidthComboBox, new String[]{"", "610", "686", "762", "838", "626", "726", "826", "926", "Custom"}); // these all get overwritten anyway
        DetailPanel.populateGivenComboBox(leafHeightComboBox, new String[]{"", "1981", "2040", "Custom"});
        DetailPanel.populateGivenComboBox(leafNumberComboBox, new String[]{"Single", "Double", "Triple", "Quad", "Custom"});

        //todo clear opening thing, want to be realtive to leaf size width, but then also part of a drop down to override

        DetailPanel.populateGivenComboBox(entranceLevelComboBox, new String[]{"", "Y", "Custom"});

        // todo DetailPanel.populateGivenComboBox(partMCompliantComboBox, new
        //  YesNoOptions[]{YesNoOptions.BLANK, }); // this should be the lookup table

        DetailPanel.populateGivenComboBox(additionalPlyLiningComboBox, new String[]{"", "Y",
                "Custom"});

        //todo structural opening populating, might need to make custom dimension class so can override the toString

        // needs turning into the unicode values
        DetailPanel.populateGivenComboBox(hingesComboBox, new String[]{"", "1 pair", "1 1/2 pair"
                , "2 pair", "Custom"});

        DetailPanel.populateGivenComboBox(latchComboBox, new String[]{"", "Y", "Custom"});
        DetailPanel.populateGivenComboBox(lockComboBox, new String[]{"", "Y", "Y [5]", "Custom"});
        DetailPanel.populateGivenComboBox(handleComboBox, new String[]{"Y", "Custom"});

    }

    /**
     * Add the event handlers to the comboboxes
     * Feels like this could be done easier if I could pass the mutator methods of door like objects but here we are
     * TODO
     */
    public void attachHandlers() {

        gridPanels[0][0].attachCBAttributeHandler(door::setFloor);
        gridPanels[0][1].attachCBAttributeHandler(door::setRoom);
        gridPanels[0][2].attachCBAttributeHandler(door::setWallConstruction);
        gridPanels[0][3].attachTFAttributeHandler(door::setDoorType);
        gridPanels[0][4].attachCBAttributeHandler(door::setInternalExternal);

        gridPanels[1][0].attachCBAttributeHandler(door::setPartMThreshold);
        gridPanels[1][1].attachCBAttributeHandler(door::setFireRating);
        gridPanels[1][2].attachCBAttributeHandler(door::setGlazed);

        gridPanels[1][3].attachSpecificAttributeHandler(door::setLeafType, leafTypeInnerPanel, leafTypeComboBox);
        gridPanels[1][3].attachSpecificAttributeHandler(door::setLeafWidth, leafWidthInnerPanel, leafWidthComboBox);
        gridPanels[1][3].attachSpecificAttributeHandler(door::setLeafHeight, leafHeightInnerPanel, leafHeightComboBox);
        leafTypeComboBox.addActionListener(e -> {
            switch (String.valueOf(leafTypeComboBox.getSelectedItem())) {
                case "Imperial" -> {
                    DetailPanel.populateGivenComboBox(leafWidthComboBox, new String[]{"", "610", "762", "838", "910", "Custom"});
                    DetailPanel.populateGivenComboBox(leafHeightComboBox, new String[]{"", "1981","Custom"});
                    leafHeightComboBox.setSelectedItem("1981");
                }
                case "Metric" -> {
                    DetailPanel.populateGivenComboBox(leafWidthComboBox, new String[]{"", "626", "726", "826", "926", "Custom"});
                    DetailPanel.populateGivenComboBox(leafHeightComboBox, new String[]{"", "2040","Custom"});
                    leafHeightComboBox.setSelectedItem("2040");
                }
                case "Bespoke" -> {
                    DetailPanel.populateGivenComboBox(leafWidthComboBox, new String[]{"", "Custom"});
                    leafWidthComboBox.setSelectedItem("Custom");
                    DetailPanel.populateGivenComboBox(leafHeightComboBox, new String[]{"", "Custom"});
                    leafHeightComboBox.setSelectedItem("Custom");
                }
                default -> {
                    DetailPanel.populateGivenComboBox(leafWidthComboBox, new String[]{""});
                    DetailPanel.populateGivenComboBox(leafHeightComboBox, new String[]{""});
                }
            }
        });

        gridPanels[1][3].attachSpecificAttributeHandler(door::setLeafNumber, leafNumberInnerPanel, leafNumberComboBox);

        //TODO
//        JPanel leafSizeInputPanel = new JPanel();
//        leafSizeInputPanel.setLayout(new BoxLayout(leafSizeInputPanel, BoxLayout.LINE_AXIS));
//        leafSizeInputPanel.add(leafTypeComboBox);
//        leafSizeInputPanel.add(leafSizeComboBox);
//        leafSizeInputPanel.add(leafNumberCheckbox);
//        gridPanels[1][3].setup("Leaf Size", leafSizeInputPanel);

        //        gridPanels[1][4].add();

//        gridPanels[2][0].attachCBAttributeHandler(door::setClearOpening);
        gridPanels[2][1].attachCBAttributeHandler(door::setEntranceLevel);
        gridPanels[2][2].attachCBAttributeHandler(door::setPartMCompliant);
        gridPanels[2][3].attachCBAttributeHandler(door::setAdditionalPlyLining);

        //        gridPanels[2][4].add();

        //todo view drop down table description at top
//        gridPanels[3][0].attachCBAttributeHandler(door::setStructuralOpening);

        gridPanels[3][1].attachTFAttributeHandler(door::setStructuralOpeningDetails);
        gridPanels[3][2].attachTFAttributeHandler(door::setFrameDetails);
        gridPanels[3][3].attachTFAttributeHandler(door::setSillDetails);
        gridPanels[3][4].attachTFAttributeHandler(door::setArchitraveType);

        gridPanels[4][0].attachCBAttributeHandler(door.getIronmongery()::setHinges);
        gridPanels[4][1].attachCBAttributeHandler(door.getIronmongery()::setLatch);
        gridPanels[4][2].attachCBAttributeHandler(door.getIronmongery()::setLock);
        gridPanels[4][3].attachCBAttributeHandler(door.getIronmongery()::setHandle);
        gridPanels[4][4].attachTFAttributeHandler(door::setAdditionalNotes);

    }


    ////////////////////////////////////
    // getters and setters
    ////////////////////////////////////

    public Door getDoor() {
        return door;
    }

    @Override
    public int getCount() {
        return door.getCount();
    }

    public void setCount(int newCount) {
        door.setCount(newCount);
    }
}

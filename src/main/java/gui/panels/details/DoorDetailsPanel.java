package gui.panels.details;

import enums.YesNoOptions;
import gui.handlers.AttributeTextFieldHandler;
import gui.handlers.CustomOptionHandler;
import gui.handlers.AttributeComboBoxHandler;
import items.doors.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.util.List;

/**
 * Subclass to be used when creating a door report
 */
public class DoorDetailsPanel extends JPanel implements SpecificDetailInterface {

    private JPanel parentPanel;
    private Door door;
    private GridPanel[][] gridPanels; // left as raw as it may be YesNoOption or String

    private final JComboBox<String> floorComboBox = new JComboBox<>();
    private final JComboBox<String> roomComboBox = new JComboBox<>();
    private final JComboBox<String> wallConstructionComboBox = new JComboBox<>();
    private final JComboBox<String> doorTypeComboBox = new JComboBox<>();
    private final JComboBox<String> internalExternalComboBox = new JComboBox<>();
    private final JComboBox<YesNoOptions> partMThresholdComboBox = new JComboBox<>();
    private final JComboBox<String> fireRatingComboBox = new JComboBox<>();
    private final JComboBox<YesNoOptions> glazedComboBox = new JComboBox<>();

    private final JComboBox<String> leafTypeComboBox = new JComboBox<>();
    private final JComboBox<String> leafSizeComboBox = new JComboBox<>();
    // want a dropdown next to this with sizes: imperial, metric or bespoke
    // default should be imperial
    // this then influences the options available from the numbers
    private final JComboBox<String> leafNumberCheckbox = new JComboBox<>();
    // this should be a dropdown box with options: single, double, triple, quad

    private final JComboBox<Integer> clearOpeningComboBox = new JComboBox<>();
    private final JComboBox<YesNoOptions> entranceLevelComboBox = new JComboBox<>();
    private final JComboBox<YesNoOptions> partMCompliantComboBox = new JComboBox<>();
    // this doesn't actually need to be a partM compliant box, but should be updates as a result
    // of an if elseif else statement (look at the Excel sheet for reference)

    private final JComboBox<YesNoOptions> additionalPlyLiningComboBox = new JComboBox<>();
    private final JComboBox<String> structuralOpeningComboBox = new JComboBox<>();
    // this should be a lookup table
    private final JTextField structuralOpeningDetailsTextField = new JTextField();

    private final JTextField frameDetailsTextField = new JTextField();
    private final JTextField sillDetailsTextField = new JTextField();
    private final JTextField architraveTypeTextField = new JTextField();

    private final JComboBox<String> hingesComboBox = new JComboBox<>();
    private final JComboBox<YesNoOptions> latchComboBox = new JComboBox<>();
    private final JComboBox<YesNoOptions> lockComboBox = new JComboBox<>();
    private final JComboBox<YesNoOptions> handleComboBox = new JComboBox<>();
    private final JTextField additionalNotesTextField = new JTextField();

    /**
     * Constructor called when the add item is called in door report state, instancing the
     * different inputs to then be added in later
     *
     * @param parentPanel the panel which this panel is nested in
     * @param count       the door number
     */
    public DoorDetailsPanel(JPanel parentPanel, int count) {

        this.parentPanel = parentPanel;
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
        setBackground(new Color(255, 0, 255));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        for (int row = 0; row < gridPanels.length; row++) {
            for (int column = 0; column < gridPanels[0].length; column++) {
                gridPanels[row][column] = new GridPanel(this.door); //  the best part about GridPanel, is that it can be repeated using any type of Item, aren't I the best
                gridPanels[row][column].setLayout(new BoxLayout(gridPanels[row][column], BoxLayout.PAGE_AXIS));
                gridPanels[row][column].setPreferredSize(new Dimension(50, 100));
                gridPanels[row][column].setBorder(new LineBorder(Color.BLACK));
                gridPanels[row][column].setBackground(new Color(199, 166, 199));

                gbc.fill = GridBagConstraints.BOTH; // stretch both horizontally and vertically
                gbc.weightx = 1.0; // expand in both directions at equal rates
                gbc.weighty = 1.0;

                gbc.gridx = column;
                gbc.gridy = row;

                if (row == 1 && column == 3) gbc.gridwidth = 2;
                else gbc.gridwidth = 1;

                add(gridPanels[row][column], gbc);
            }
        }

        populateComboBoxes();
        positionForms();
        setFormDefaults();
        attachHandlers();
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
        gridPanels[0][3].setup("Door Type", doorTypeComboBox);
        gridPanels[0][4].setup("Internal or External", internalExternalComboBox);

        gridPanels[1][0].setup("Part M Threshold", partMThresholdComboBox);
        gridPanels[1][1].setup("Fire Rating", fireRatingComboBox);
        gridPanels[1][2].setup("Glazed", glazedComboBox);

        JPanel leafSizeInputPanel = new JPanel();
        leafSizeInputPanel.setLayout(new BoxLayout(leafSizeInputPanel, BoxLayout.LINE_AXIS));
        leafSizeInputPanel.add(leafTypeComboBox);
        leafSizeInputPanel.add(leafSizeComboBox);
        leafSizeInputPanel.add(leafNumberCheckbox);
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
     * Populates a given combobox with the contents of the array passed to it
     *
     * @param comboBox     the combobox to add items to
     * @param optionsToAdd the options to add
     * @param <T>          the type parameter ensuring that equal data types are held in the array list as
     *                     are used in the combobox
     */
    public <T> void populateGivenComboBox(JComboBox<T> comboBox,
                                          T[] optionsToAdd) {
        DefaultComboBoxModel<T> model =
                (DefaultComboBoxModel<T>) comboBox.getModel();
        model.addAll(List.of(optionsToAdd));
    }

    /**
     * Populates all the different comboboxes in the application
     */
    public void populateComboBoxes() {

        populateGivenComboBox(floorComboBox, new String[]{"", "Ground Floor", "First Floor",
                "Second Floor", "Third Floor", "Lower Ground Floor", "Upper Ground Floor", "Mezzanine",
                "Basement 1", "Basement 2", "Custom"});
        populateGivenComboBox(roomComboBox, new String[]{"", "Custom"});
        populateGivenComboBox(wallConstructionComboBox, new String[]{"", "Masonry cavity wall",
                "Timberframe", "SIPS panel", "100mm blockwork", "140mm blockwork", "215mm blockwork",
                "89mm partition", "100mm partition", "140mm partition", "Custom"});
        populateGivenComboBox(internalExternalComboBox, new String[]{"", "Internal", "External " +
                "[1]", "Custom"});
        populateGivenComboBox(partMThresholdComboBox, new YesNoOptions[]{YesNoOptions.BLANK,
                YesNoOptions.Y_2, YesNoOptions.CUSTOM});
        populateGivenComboBox(fireRatingComboBox, new String[]{"", "FD20 [3]", "FD30 [3]", "FD30-SC" +
                " " +
                "[3]", "FD60 [3]", "Custom"});
        populateGivenComboBox(glazedComboBox, new YesNoOptions[]{YesNoOptions.BLANK,
                YesNoOptions.Y_4, YesNoOptions.CUSTOM});
        populateGivenComboBox(leafTypeComboBox, new String[]{"", "Imperial", "Metric", "Bespoke",
                "Custom"});
        populateGivenComboBox(leafSizeComboBox, new String[]{"", "610", "686", "762", "838", "626",
                "726", "826", "926", "2 x 610", "2 x 686", "2 x 762", "2 x 838", "2 x 626", "2 x 726", "2 x 826"
                , "2 x 926", "Custom"});
        populateGivenComboBox(leafNumberCheckbox, new String[]{"Single", "Double", "Triple",
                "Quad", "Custom"});
        populateGivenComboBox(entranceLevelComboBox, new YesNoOptions[]{YesNoOptions.BLANK,
                YesNoOptions.Y, YesNoOptions.CUSTOM});
//        populateGivenComboBox(partMCompliantComboBox, new YesNoOptions[]{YesNoOptions.BLANK, }); // this should be the lookup table
        populateGivenComboBox(additionalPlyLiningComboBox, new YesNoOptions[]{YesNoOptions.BLANK,
                YesNoOptions.Y, YesNoOptions.CUSTOM});
        // needs turning into the unicode values
        populateGivenComboBox(hingesComboBox,
                new String[]{"", "1 pair", "1 1/2 pair", "2 pair", "Custom"});

        populateGivenComboBox(latchComboBox, new YesNoOptions[]{YesNoOptions.BLANK,
                YesNoOptions.Y, YesNoOptions.CUSTOM});
        populateGivenComboBox(lockComboBox, new YesNoOptions[]{YesNoOptions.BLANK, YesNoOptions.Y
                , YesNoOptions.Y_5, YesNoOptions.CUSTOM});
        populateGivenComboBox(handleComboBox, new YesNoOptions[]{YesNoOptions.Y, YesNoOptions.CUSTOM});

    }

    /**
     * Add the event handlers to the comboboxes
     * Feels like this could be done easier if I could pass the mutator methods of door like objects but here we are
     * TODO
     */
    public void attachHandlers() {

        gridPanels[0][0].attachAttributeHandler(door::setFloor);
//        gridPanels[3][2].attachAttributeHandler("Frame Details text field");

//        floorComboBox.addActionListener(new CustomOptionHandler<>(floorComboBox, gridPanels[0][0]));
//        floorComboBox.addActionListener(e -> door.setFloor((String) floorComboBox.getSelectedItem()));
//
//        roomComboBox.addActionListener(new CustomOptionHandler<>(roomComboBox, gridPanels[0][1]));
//        roomComboBox.addActionListener(e -> door.setRoom((String) roomComboBox.getSelectedItem()));
//
//        wallConstructionComboBox.addActionListener(new CustomOptionHandler<>(wallConstructionComboBox, gridPanels[0][2]));
//        wallConstructionComboBox.addActionListener(e -> door.setWallConstruction((String) wallConstructionComboBox.getSelectedItem()));
//
//        doorTypeComboBox.addActionListener(new CustomOptionHandler<>(doorTypeComboBox, gridPanels[0][3]));
//        doorTypeComboBox.addActionListener(e -> door.setDoorType((String) doorTypeComboBox.getSelectedItem()));
//
//        internalExternalComboBox.addActionListener(new CustomOptionHandler<>(internalExternalComboBox, gridPanels[0][4]));
//        internalExternalComboBox.addActionListener(e -> door.setInternalExternal((String) internalExternalComboBox.getSelectedItem()));
//
//        partMThresholdComboBox.addActionListener(new CustomOptionHandler<>(partMThresholdComboBox, gridPanels[1][0]));
//        partMThresholdComboBox.addActionListener(e -> door.setPartMThreshold((YesNoOptions) partMThresholdComboBox.getSelectedItem()));
//
//        fireRatingComboBox.addActionListener(new CustomOptionHandler<>(fireRatingComboBox, gridPanels[1][1]));
//        fireRatingComboBox.addActionListener(e -> door.setFireRating((String) fireRatingComboBox.getSelectedItem()));
//
//        glazedComboBox.addActionListener(new CustomOptionHandler<>(glazedComboBox, gridPanels[1][2]));
//        glazedComboBox.addActionListener(e -> door.setGlazed((YesNoOptions) glazedComboBox.getSelectedItem()));
//
//        handleComboBox.addActionListener(new CustomOptionHandler<>(handleComboBox,
//                gridPanels[4][3]));

//        attachAttributeHandler(additionalNotesTextField, "additional notes");

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

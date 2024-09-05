package gui.panels.details;

import gui.Styling;
import items.doors.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Objects;

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

    // todo lookup table relative to leaf size
    // anything which is relative could ask whether want a dropdown which gets default filled with custom option, or just use a text field
    private final JComboBox<String> clearOpeningComboBox = new JComboBox<>();
    private final JComboBox<String> entranceLevelComboBox = new JComboBox<>();
    private final JComboBox<String> partMCompliantComboBox = new JComboBox<>();
    //todo:
    // this doesn't actually need to be a partM compliant box, but should be updates as a result
    // of an if elseif else statement (look at the Excel sheet for reference)

    private final JComboBox<String> additionalPlyLiningComboBox = new JComboBox<>();

    private final JPanel structuralOpeningWidthInnerPanel = new JPanel();
    private final JPanel structuralOpeningHeightInnerPanel = new JPanel();
    private final JPanel structuralOpeningDetailsInnerPanel = new JPanel();
    private final JComboBox<String> structuralOpeningWidthComboBox = new JComboBox<>();
    private final JTextField structuralOpeningHeightTextField = new JTextField();
    // this should be a lookup table
    private final JComboBox<String> structuralOpeningDetailsComboBox = new JComboBox<>();

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

                if (row == 1 && column == 2 ) {
                    gbc.gridwidth = 3;
                }
                else if (row == 3 && column == 0) {
                    gbc.gridwidth = 2;
                }
                else gbc.gridwidth = 1;

                add(gridPanels[row][column], gbc);
            }
        }

        populateComboBoxes();
        positionPanels();
        attachHandlers();
        setFormDefaults();
    }

    /**
     * Positions the contents of the leaf size grid panel
     *
     * @return the completed input panel
     */
    private JPanel positionLeafSizeInputPanel() {
        JPanel leafSizeInputPanel = new JPanel();
        leafSizeInputPanel.setLayout(new GridLayout(1, 4));

        leafTypeInnerPanel.setLayout(new BoxLayout(leafTypeInnerPanel, BoxLayout.PAGE_AXIS));
        leafTypeInnerPanel.add(leafTypeComboBox);

        leafWidthInnerPanel.setLayout(new BoxLayout(leafWidthInnerPanel, BoxLayout.PAGE_AXIS));
        GridPanel.setupSpecificLabel(leafWidthInnerPanel, "Width");
        leafWidthInnerPanel.add(leafWidthComboBox);

        leafHeightInnerPanel.setLayout(new BoxLayout(leafHeightInnerPanel, BoxLayout.PAGE_AXIS));
        GridPanel.setupSpecificLabel(leafHeightInnerPanel, "Height");
        leafHeightInnerPanel.add(leafHeightComboBox);

        leafNumberInnerPanel.setLayout(new BoxLayout(leafNumberInnerPanel, BoxLayout.PAGE_AXIS));
        leafNumberInnerPanel.add(leafNumberComboBox);

        leafSizeInputPanel.add(leafTypeInnerPanel);
        leafSizeInputPanel.add(leafWidthInnerPanel);
        leafSizeInputPanel.add(leafHeightInnerPanel);
        leafSizeInputPanel.add(leafNumberInnerPanel);

        return leafSizeInputPanel;
    }

    /**
     * Positions the contents of the structural opening grid panel
     *
     * @return the completed input panel
     */
    private JPanel positionStructuralOpeningInputPanel() {
        // probably could put this into a static GridPanel method using (JComboBox...) data type, provided I don't care to keep track of the panel pointers
        JPanel structuralOpeningInputPanel = new JPanel();
        structuralOpeningInputPanel.setLayout(new GridLayout(1, 4));

        structuralOpeningWidthInnerPanel.setLayout(new BoxLayout(structuralOpeningWidthInnerPanel, BoxLayout.PAGE_AXIS));
        GridPanel.setupSpecificLabel(structuralOpeningWidthInnerPanel, "Width");
        structuralOpeningWidthInnerPanel.add(structuralOpeningWidthComboBox);

        structuralOpeningHeightInnerPanel.setLayout(new BoxLayout(structuralOpeningHeightInnerPanel, BoxLayout.PAGE_AXIS));
        GridPanel.setupSpecificLabel(structuralOpeningHeightInnerPanel, "Height");
        structuralOpeningHeightInnerPanel.add(structuralOpeningHeightTextField);

        structuralOpeningDetailsInnerPanel.setLayout(new BoxLayout(structuralOpeningDetailsInnerPanel, BoxLayout.PAGE_AXIS));
        GridPanel.setupSpecificLabel(structuralOpeningDetailsInnerPanel, "Height Measured From");
        structuralOpeningDetailsInnerPanel.add(structuralOpeningDetailsComboBox);

        structuralOpeningInputPanel.add(structuralOpeningWidthInnerPanel);
        structuralOpeningInputPanel.add(structuralOpeningHeightInnerPanel);
        structuralOpeningInputPanel.add(structuralOpeningDetailsInnerPanel);
        return structuralOpeningInputPanel;
    }

    /**
     * Position the inputs in the grid bag layout
     */
    public void positionPanels() {

        gridPanels[0][0].setup("Floor", floorComboBox);
        gridPanels[0][1].setup("Room", roomComboBox);
        gridPanels[0][2].setup("Wall Construction", wallConstructionComboBox);
        gridPanels[0][3].setup("Internal or External", internalExternalComboBox);
        gridPanels[0][4].setup("Door Type", doorTypeTextField);


        gridPanels[1][0].setup("Entrance Level", entranceLevelComboBox);
        gridPanels[1][1].setup("Part M Threshold", partMThresholdComboBox);
        gridPanels[1][2].setup("Leaf Size", positionLeafSizeInputPanel());

        gridPanels[2][0].setup("Clear Opening", clearOpeningComboBox);
        gridPanels[2][1].setup("Part M Compliant", partMCompliantComboBox);
        gridPanels[2][2].setup("Fire Rating", fireRatingComboBox);
        gridPanels[2][3].setup("Glazed", glazedComboBox);
        gridPanels[2][4].setup("Additional Ply Lining", additionalPlyLiningComboBox);


        gridPanels[3][0].setup("Structural Opening", positionStructuralOpeningInputPanel());
        gridPanels[3][2].setup("Frame Details", frameDetailsTextField);
        gridPanels[3][3].setup("Sill Details", sillDetailsTextField);
        gridPanels[3][4].setup("Architrave Type", architraveTypeTextField);

        gridPanels[4][0].setup("Hinges", hingesComboBox);
        gridPanels[4][1].setup("Latch", latchComboBox);
        gridPanels[4][2].setup("Lock", lockComboBox);
        gridPanels[4][3].setup("Handle", handleComboBox);
        gridPanels[4][4].setup("Additional Notes", additionalNotesTextField);

    }

    /**
     * Sets the default values of the different inputs
     * I could use the populateComboBox(JComboBox, String[], String) method to set one at the same time but this is more readable
     */
    public void setFormDefaults() {
        floorComboBox.setSelectedItem("Ground Floor");
        wallConstructionComboBox.setSelectedItem("89mm partition");
        doorTypeTextField.setText("TBA by client");
        internalExternalComboBox.setSelectedItem("Internal");
        entranceLevelComboBox.setSelectedItem("Yes");
        leafTypeComboBox.setSelectedItem("Imperial");
        leafNumberComboBox.setSelectedItem("Single");
        leafWidthComboBox.setSelectedItem("838");


        //todo
        hingesComboBox.setSelectedItem("1/2 pair");
        latchComboBox.setSelectedItem("Yes");
    }

    /**
     * Populates all the different Comboboxes in the application
     */
    public void populateComboBoxes() {

        DetailPanel.populateGivenComboBox(floorComboBox, new String[]{"", "Ground Floor", "First Floor", "Second Floor", "Third Floor", "Lower Ground Floor", "Upper Ground Floor", "Mezzanine", "Basement 1", "Basement 2", "Custom"});
        DetailPanel.populateGivenComboBox(roomComboBox, new String[]{"", "Entrance Hall", "Plant Room", "Utility", "WC", "Reception", "Bedroom 1", "Bedroom 2", "Bedroom 3", "Bedroom 4", "Ensuite 1", "Ensuite 2", "Ensuite 3", "Ensuite 4", "Coats", "Kitchen", "Kitchen / Dining", "Dining", "Linen", "Custom"});
        DetailPanel.populateGivenComboBox(wallConstructionComboBox, new String[]{"", "Masonry Cavity wall", "Timberframe", "SIPS panel", "100mm blockwork", "140mm blockwork", "215mm blockwork", "89mm partition", "100mm partition", "140mm partition", "Custom"});
        DetailPanel.populateGivenComboBox(internalExternalComboBox, new String[]{"", "Internal", "External [1]", "Custom"});
        DetailPanel.populateGivenComboBox(partMThresholdComboBox, new String[]{"", "Y [2]", "Custom"});
        DetailPanel.populateGivenComboBox(fireRatingComboBox, new String[]{"", "FD20 [3]", "FD30 [3]", "FD30-SC [3]", "FD60 [3]", "Custom"});
        DetailPanel.populateGivenComboBox(glazedComboBox, new String[]{"", "Y [4]", "Custom"});
        DetailPanel.populateGivenComboBox(leafTypeComboBox, new String[]{"", "Imperial", "Metric", "Bespoke"});

        DetailPanel.populateGivenComboBox(leafWidthComboBox, new String[]{"", "610", "686", "762", "838", "626", "726", "826", "926", "Custom"}); // these all get overwritten anyway
        DetailPanel.populateGivenComboBox(leafHeightComboBox, new String[]{"", "1981", "2040", "Custom"});
        DetailPanel.populateGivenComboBox(leafNumberComboBox, new String[]{"Single", "Double", "Triple", "Quad", "Custom"});

        DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "549", "625", "701", "778", "566", "666", "766", "866", "1130", "1278", "1434", "1592", "1166", "1366", "1566", "1766"});
        //todo clear opening thing, want to be relative to leaf size width, but then also part of a drop down to override

        DetailPanel.populateGivenComboBox(entranceLevelComboBox, new String[]{"", "Yes", "Custom"});

        // todo DetailPanel.populateGivenComboBox(partMCompliantComboBox, new
        //  YesNoOptions[]{YesNoOptions.BLANK, }); // this should be the lookup table

        DetailPanel.populateGivenComboBox(additionalPlyLiningComboBox, new String[]{"", "Yes"});

        DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "690", "765", "840", "920", "710", "810", "910", "1010", "1315", "1460", "1620", "1770", "1340", "1540", "1740", "1840"});
        DetailPanel.populateGivenComboBox(structuralOpeningDetailsComboBox, new String[]{"", "Above Screed", "Above Ply Deck", "Above Slab", "Above Plinth", "Below Screed", "Below Ply Deck", "Below Slab", "Inc. Sill", "Custom"});

        // needs turning into the Unicode values
        DetailPanel.populateGivenComboBox(hingesComboBox, new String[]{"", "1/2 pair", "1 pair", "1 1/2 pair", "2 pair", "Custom"});

        DetailPanel.populateGivenComboBox(latchComboBox, new String[]{"", "Yes", "Custom"});
        DetailPanel.populateGivenComboBox(lockComboBox, new String[]{"", "Yes", "Y [5]", "Custom"});
        DetailPanel.populateGivenComboBox(handleComboBox, new String[]{"Yes", "Custom"});

    }

    /**
     * Autofills in the leaf width  when selecting the leaf type
     */
    private void leafWidthHeightLookup() {
        switch (String.valueOf(leafTypeComboBox.getSelectedItem())) {
            case "Imperial" -> {
                DetailPanel.populateGivenComboBox(leafWidthComboBox, new String[]{"", "610", "686", "762", "838", "Custom"});
                DetailPanel.populateGivenComboBox(leafHeightComboBox, new String[]{"", "1981", "Custom"}, "1981");
            }
            case "Metric" -> {
                DetailPanel.populateGivenComboBox(leafWidthComboBox, new String[]{"", "626", "726", "826", "926", "Custom"});
                DetailPanel.populateGivenComboBox(leafHeightComboBox, new String[]{"", "2040", "Custom"}, "2040");
//                    leafHeightComboBox.setSelectedItem("2040");
            }
            case "Bespoke" -> {
                DetailPanel.populateGivenComboBox(leafWidthComboBox, new String[]{"", "Custom"}, "Custom");
                DetailPanel.populateGivenComboBox(leafHeightComboBox, new String[]{"", "Custom"}, "Custom");
            }
            default -> {
                DetailPanel.populateGivenComboBox(leafWidthComboBox, new String[]{""});
                DetailPanel.populateGivenComboBox(leafHeightComboBox, new String[]{""});
            }
        }
    }

    /**
     * Holds the lookup system for the structural opening width
     */
    private void structuralOpeningWidthLookup() {
        // if additional play lining != yes, then use columns A and B
        if (!Objects.equals(additionalPlyLiningComboBox.getSelectedItem(), "Yes")) {
            // if it is a single door do this
            if (Objects.equals(leafNumberComboBox.getSelectedItem(), "Single")) {
                switch (String.valueOf(leafWidthComboBox.getSelectedItem())) {

                    case "610" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "690", "Custom"}, "690");
                    case "686" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "765", "Custom"}, "765");
                    case "762" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "840", "Custom"}, "840");
                    case "838" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "920", "Custom"}, "920");
                    case "626" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "710", "Custom"}, "710");
                    case "726" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "810", "Custom"}, "810");
                    case "826" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "910", "Custom"}, "910");
                    case "926" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1010", "Custom"}, "1010");
                    default -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "Custom"});
                }
            }
            // if it is a double door do this
            else if (Objects.equals(leafNumberComboBox.getSelectedItem(), "Double")) {
                switch (String.valueOf(leafWidthComboBox.getSelectedItem())) {
                    case "610" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1315", "Custom"}, "1315");
                    case "686" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1460", "Custom"}, "1460");
                    case "762" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1620", "Custom"}, "1620");
                    case "838" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1770", "Custom"}, "1770");
                    case "626" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1340", "Custom"}, "1340");
                    case "726" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1540", "Custom"}, "1540");
                    case "826" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1740", "Custom"}, "1740");
                    case "926" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1840", "Custom"}, "1840");
                    default -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "Custom"});
                }
            }
            else
                DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "Custom"});
            //todo for triple and quad

        }
        // if ply lining equals yes then use columns A and C
        else {
            // if it is a single door do this
            if (Objects.equals(leafNumberComboBox.getSelectedItem(), "Single")) {
                switch (String.valueOf(leafWidthComboBox.getSelectedItem())) {

                    case "610" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "725", "Custom"}, "725");
                    case "686" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "800", "Custom"}, "800");
                    case "762" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "875", "Custom"}, "875");
                    case "838" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "955", "Custom"}, "955");
                    case "626" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "750", "Custom"}, "750");
                    case "726" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "850", "Custom"}, "850");
                    case "826" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "950", "Custom"}, "950");
                    case "926" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1050", "Custom"}, "1050");
                    default -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "Custom"});
                }
            }
            // if it is a double door do this
            else if (Objects.equals(leafNumberComboBox.getSelectedItem(), "Double")) {
                switch (String.valueOf(leafWidthComboBox.getSelectedItem())) {
                    case "610" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1350", "Custom"}, "1350");
                    case "686" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1490", "Custom"}, "1490");
                    case "762" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1650", "Custom"}, "1650");
                    case "838" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1800", "Custom"}, "1800");
                    case "626" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1370", "Custom"}, "1370");
                    case "726" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1570", "Custom"}, "1570");
                    case "826" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1770", "Custom"}, "1770");
                    case "926" -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "1870", "Custom"}, "1870");
                    default -> DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "Custom"});
                }
            }
            else
                DetailPanel.populateGivenComboBox(structuralOpeningWidthComboBox, new String[]{"", "Custom"});
            //todo for triple and quad
        }
    }

    /**
     * Holds the lookup system where the clear opening value is relative to the leaf width and number
     */
    private void clearOpeningLookup() {
        // if it is a single door do this
        if (Objects.equals(leafNumberComboBox.getSelectedItem(), "Single")) {
            switch (String.valueOf(leafWidthComboBox.getSelectedItem())) {

                case "610" -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "549", "Custom"}, "549");
                case "686" -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "625", "Custom"}, "625");
                case "762" -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "701", "Custom"}, "701");
                case "838" -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "778", "Custom"}, "778");
                case "626" -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "566", "Custom"}, "566");
                case "726" -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "666", "Custom"}, "666");
                case "826" -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "766", "Custom"}, "766");
                case "926" -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "866", "Custom"}, "866");
                default -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "Custom"});
            }
        }
        // if it is a double door do this
        else if (Objects.equals(leafNumberComboBox.getSelectedItem(), "Double")) {
            switch (String.valueOf(leafWidthComboBox.getSelectedItem())) {
                case "610" -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "1130", "Custom"}, "1130");
                case "686" -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "1278", "Custom"}, "1278");
                case "762" -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "1434", "Custom"}, "1434");
                case "838" -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "1592", "Custom"}, "1592");
                case "626" -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "1166", "Custom"}, "1166");
                case "726" -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "1366", "Custom"}, "1366");
                case "826" -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "1566", "Custom"}, "1566");
                case "926" -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "1766", "Custom"}, "1766");
                default -> DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "Custom"});
            }
        }
        else
            DetailPanel.populateGivenComboBox(clearOpeningComboBox, new String[]{"", "Custom"});
        //todo for triple and quad
    }

    //todo
    private void partMCompliantLookup() {
        try {
            int clearOpeningValue = Integer.parseInt(String.valueOf(clearOpeningComboBox.getSelectedItem()));
            String entranceLevelValue = String.valueOf(entranceLevelComboBox.getSelectedItem());

            if (entranceLevelValue.equals("Yes") && clearOpeningValue > 749) {
                DetailPanel.populateGivenComboBox(partMCompliantComboBox, new String[]{"", "Yes", "Custom"}, "Yes");
            }
            else if (entranceLevelValue.equals("Yes")) { // less than 750
                DetailPanel.populateGivenComboBox(partMCompliantComboBox, new String[]{"", "No", "Custom"}, "No");
            }
            else { // not "yes"
                DetailPanel.populateGivenComboBox(partMCompliantComboBox, new String[]{"", "Custom"}, "");
            }
        }
        catch (NumberFormatException e) {
            /* Invalid clear opening value (wasn't a number) to decide Part M Compliant value when selecting from the
            options available basically meaning either null, "", or Custom selected, so fill with blank and custom overwrite*/
            DetailPanel.populateGivenComboBox(partMCompliantComboBox, new String[]{"", "Custom"});

        }
    }

    /**
     * Add the event handlers to the comboboxes
     * For some special cases where one combobox triggers an event in another, used item listeners, as the program
     * removes from and reads to the different combobox models, which triggers events, so intead I am only running the
     * lookup methods when an item is selected, preventing unnecessary chains
     * TODO
     */
    public void attachHandlers() {

        gridPanels[0][0].attachCBAttributeHandler(door::setFloor);
        gridPanels[0][1].attachCBAttributeHandler(door::setRoom);
        gridPanels[0][2].attachCBAttributeHandler(door::setWallConstruction);
        gridPanels[0][3].attachCBAttributeHandler(door::setInternalExternal);
        gridPanels[0][4].attachTFAttributeHandler(door::setDoorType);

        gridPanels[1][0].attachCBAttributeHandler(door::setEntranceLevel);
        entranceLevelComboBox.addItemListener(e -> {if (e.getStateChange() == ItemEvent.SELECTED) partMCompliantLookup();});
        gridPanels[1][1].attachCBAttributeHandler(door::setPartMThreshold);
        gridPanels[1][2].attachSpecificAttributeHandler(door::setLeafType, leafTypeInnerPanel, leafTypeComboBox);
        gridPanels[1][2].attachSpecificAttributeHandler(door::setLeafWidth, leafWidthInnerPanel, leafWidthComboBox);
        gridPanels[1][2].attachSpecificAttributeHandler(door::setLeafHeight, leafHeightInnerPanel, leafHeightComboBox);
        leafTypeComboBox.addItemListener(e -> {if (e.getStateChange() == ItemEvent.SELECTED) leafWidthHeightLookup();});
        leafWidthComboBox.addItemListener(e -> {if (e.getStateChange() == ItemEvent.SELECTED) structuralOpeningWidthLookup();});
        leafWidthComboBox.addItemListener(e -> {if (e.getStateChange() == ItemEvent.SELECTED) clearOpeningLookup();});
        gridPanels[1][2].attachSpecificAttributeHandler(door::setLeafNumber, leafNumberInnerPanel, leafNumberComboBox);
        leafNumberComboBox.addItemListener(e -> {if (e.getStateChange() == ItemEvent.SELECTED) structuralOpeningWidthLookup();});
        leafNumberComboBox.addItemListener(e -> {if (e.getStateChange() == ItemEvent.SELECTED) clearOpeningLookup();});


        gridPanels[2][0].attachCBAttributeHandler(door::setClearOpening);
        clearOpeningComboBox.addItemListener(e -> {if (e.getStateChange() == ItemEvent.SELECTED) partMCompliantLookup();});

        gridPanels[2][1].attachCBAttributeHandler(door::setPartMCompliant);
        gridPanels[2][2].attachCBAttributeHandler(door::setFireRating);
        gridPanels[2][3].attachCBAttributeHandler(door::setGlazed);
        gridPanels[2][4].attachCBAttributeHandler(door::setAdditionalPlyLining);
        additionalPlyLiningComboBox.addItemListener(e -> {if (e.getStateChange() == ItemEvent.SELECTED)structuralOpeningWidthLookup();
        });


        gridPanels[3][0].attachSpecificAttributeHandler(door::setStructuralOpeningWidth, structuralOpeningWidthInnerPanel, structuralOpeningWidthComboBox);
        gridPanels[3][0].attachSpecificAttributeHandler(door::setStructuralOpeningHeight, structuralOpeningHeightTextField);
        gridPanels[3][0].attachSpecificAttributeHandler(door::setStructuralOpeningDetails, structuralOpeningDetailsInnerPanel, structuralOpeningDetailsComboBox);
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

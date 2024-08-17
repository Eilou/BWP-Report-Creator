package gui.panels.details;

import enums.YesNoOptions;
import gui.handlers.CustomOptionHandler;
import items.doors.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

/**
 * Subclass to be used when creating a door report
 */
public class DoorDetailsPanel extends JPanel implements SpecificDetailInterface{

    private JPanel parentPanel;
    private Door door;
    private GridPanel[][] gridPanels;

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
//        setPreferredSize(new Dimension(0, 250));
//        setMaximumSize(getPreferredSize());

        this.door = new Door(count);
        int rows = 5;
        int columns = 5;
        gridPanels = new GridPanel[rows][columns];

    }

    /**
     * Sets up the overall panel and the inner panel divisions ready for the different input forms
     */
    public void setup() {

//        setBorder(border);
        setLayout(new GridBagLayout());
        setBackground(new Color(255,0,255));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        for (int row = 0; row < gridPanels.length; row++) {
            for (int column = 0; column < gridPanels[0].length; column++) {
                gridPanels[row][column] = new GridPanel();
                gridPanels[row][column].setLayout(new BoxLayout(gridPanels[row][column], BoxLayout.PAGE_AXIS));
                gridPanels[row][column].setPreferredSize(new Dimension(50, 100));
                gridPanels[row][column].setBorder(new LineBorder(Color.BLACK));
                gridPanels[row][column].setBackground(new Color(255, 0, 255));

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

        JLabel floorLabel = new JLabel("Floor");
        JPanel floorLabelPanel = new JPanel();
        floorLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        floorLabelPanel.add(floorLabel);
        gridPanels[0][0].add(floorLabelPanel);
        gridPanels[0][0].add(floorComboBox);

        JLabel roomLabel = new JLabel("Room");
        JPanel roomLabelPanel = new JPanel();
        roomLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        roomLabelPanel.add(roomLabel);
        gridPanels[0][1].add(roomLabelPanel);
        gridPanels[0][1].add(roomComboBox);

        JLabel wallConstructionLabel = new JLabel("Wall Construction");
        JPanel wallConstructionLabelPanel = new JPanel();
        wallConstructionLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        wallConstructionLabelPanel.add(wallConstructionLabel);
        gridPanels[0][2].add(wallConstructionLabelPanel);
        gridPanels[0][2].add(wallConstructionComboBox);

        JLabel doorTypeLabel = new JLabel("Door Type");
        JPanel doorTypeLabelPanel = new JPanel();
        doorTypeLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        doorTypeLabelPanel.add(doorTypeLabel);
        gridPanels[0][3].add(doorTypeLabelPanel);
        gridPanels[0][3].add(doorTypeComboBox);

        JLabel internalExternalLabel = new JLabel("Internal or External");
        JPanel internalExternalLabelPanel = new JPanel();
        internalExternalLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        internalExternalLabelPanel.add(internalExternalLabel);
        gridPanels[0][4].add(internalExternalLabelPanel);
        gridPanels[0][4].add(internalExternalComboBox);

        JLabel partMThresholdLabel = new JLabel("Part M Threshold");
        JPanel partMThresholdLabelPanel = new JPanel();
        partMThresholdLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        partMThresholdLabelPanel.add(partMThresholdLabel);
        gridPanels[1][0].add(partMThresholdLabelPanel);
        gridPanels[1][0].add(partMThresholdComboBox);

        JLabel fireRatingLabel = new JLabel("Fire Rating");
        JPanel fireRatingLabelPanel = new JPanel();
        fireRatingLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        fireRatingLabelPanel.add(fireRatingLabel);
        gridPanels[1][1].add(fireRatingLabelPanel);
        gridPanels[1][1].add(fireRatingComboBox);

        JLabel glazedLabel = new JLabel("Glazed");
        JPanel glazedLabelPanel = new JPanel();
        glazedLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        glazedLabelPanel.add(glazedLabel);
        gridPanels[1][2].add(glazedLabelPanel);
        gridPanels[1][2].add(glazedComboBox);

        JLabel leafSizeLabel = new JLabel("Leaf Size");
        JPanel leafSizeLabelPanel = new JPanel();
        leafSizeLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leafSizeLabelPanel.add(leafSizeLabel);
        gridPanels[1][3].add(leafSizeLabelPanel);
        JPanel leafSizeInputPanel = new JPanel();
        leafSizeInputPanel.setLayout(new BoxLayout(leafSizeInputPanel, BoxLayout.LINE_AXIS));
        leafSizeInputPanel.add(leafTypeComboBox);
        leafSizeInputPanel.add(leafSizeComboBox);
        leafSizeInputPanel.add(leafNumberCheckbox);
        gridPanels[1][3].add(leafSizeInputPanel);

        //        gridPanels[1][4].add();

        JLabel clearOpeningLabel = new JLabel("Clear Opening");
        JPanel clearOpeningLabelPanel = new JPanel();
        clearOpeningLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        clearOpeningLabelPanel.add(clearOpeningLabel);
        gridPanels[2][0].add(clearOpeningLabelPanel);
        gridPanels[2][0].add(clearOpeningComboBox);

        JLabel entranceLevelLabel = new JLabel("Entrance Level");
        JPanel entranceLevelLabelPanel = new JPanel();
        entranceLevelLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        entranceLevelLabelPanel.add(entranceLevelLabel);
        gridPanels[2][1].add(entranceLevelLabelPanel);
        gridPanels[2][1].add(entranceLevelComboBox);

        JLabel partMCompliantLabel = new JLabel("Part M Compliant");
        JPanel partMCompliantLabelPanel = new JPanel();
        partMCompliantLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        partMCompliantLabelPanel.add(partMCompliantLabel);
        gridPanels[2][2].add(partMCompliantLabelPanel);
        gridPanels[2][2].add(partMCompliantComboBox);

        JLabel additionalPlyLiningLabel = new JLabel("Additional Ply Lining");
        JPanel additionalPlyLiningLabelPanel = new JPanel();
        additionalPlyLiningLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        additionalPlyLiningLabelPanel.add(additionalPlyLiningLabel);
        gridPanels[2][3].add(additionalPlyLiningLabelPanel);
        gridPanels[2][3].add(additionalPlyLiningComboBox);
//        gridPanels[2][4].add();

        JLabel structuralOpeningLabel = new JLabel("Structural Opening");
        JPanel structuralOpeningLabelPanel = new JPanel();
        structuralOpeningLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        structuralOpeningLabelPanel.add(structuralOpeningLabel);
        gridPanels[3][0].add(structuralOpeningLabelPanel);
        gridPanels[3][0].add(structuralOpeningComboBox);

        JLabel structuralOpeningDetailsLabel = new JLabel("Structural Opening Details");
        JPanel structuralOpeningDetailsLabelPanel = new JPanel();
        structuralOpeningDetailsLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        structuralOpeningDetailsLabelPanel.add(structuralOpeningDetailsLabel);
        gridPanels[3][1].add(structuralOpeningDetailsLabelPanel);
        gridPanels[3][1].add(structuralOpeningDetailsTextField);

        JLabel frameDetailsLabel = new JLabel("Frame Details");
        JPanel frameDetailsLabelPanel = new JPanel();
        frameDetailsLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        frameDetailsLabelPanel.add(frameDetailsLabel);
        gridPanels[3][2].add(frameDetailsLabelPanel);
        gridPanels[3][2].add(frameDetailsTextField);

        JLabel sillDetailsLabel = new JLabel("Sill Details");
        JPanel sillDetailsLabelPanel = new JPanel();
        sillDetailsLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        sillDetailsLabelPanel.add(sillDetailsLabel);
        gridPanels[3][3].add(sillDetailsLabelPanel);
        gridPanels[3][3].add(sillDetailsTextField);

        JLabel architraveTypeLabel = new JLabel("Architrave Type");
        JPanel architraveTypeLabelPanel = new JPanel();
        architraveTypeLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        architraveTypeLabelPanel.add(architraveTypeLabel);
        gridPanels[3][4].add(architraveTypeLabelPanel);
        gridPanels[3][4].add(architraveTypeTextField);

        JLabel hingesLabel = new JLabel("Hinges");
        JPanel hingesLabelPanel = new JPanel();
        hingesLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        hingesLabelPanel.add(hingesLabel);
        gridPanels[4][0].add(hingesLabelPanel);
        gridPanels[4][0].add(hingesComboBox);

        JLabel latchLabel = new JLabel("Latch");
        JPanel latchLabelPanel = new JPanel();
        latchLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        latchLabelPanel.add(latchLabel);
        gridPanels[4][1].add(latchLabelPanel);
        gridPanels[4][1].add(latchComboBox);

        JLabel lockLabel = new JLabel("Lock");
        JPanel lockLabelPanel = new JPanel();
        lockLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        lockLabelPanel.add(lockLabel);
        gridPanels[4][2].add(lockLabelPanel);
        gridPanels[4][2].add(lockComboBox);

        JLabel handleLabel = new JLabel("Handle");
        JPanel handleLabelPanel = new JPanel();
        handleLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        handleLabelPanel.add(handleLabel);
        gridPanels[4][3].add(handleLabelPanel);
        gridPanels[4][3].add(handleComboBox);

        JLabel additionalNotesLabel = new JLabel("Additional Notes");
        JPanel additionalNotesLabelPanel = new JPanel();
        additionalNotesLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        additionalNotesLabelPanel.add(additionalNotesLabel);
        gridPanels[4][4].add(additionalNotesLabelPanel);
        gridPanels[4][4].add(additionalNotesTextField);

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

        populateGivenComboBox(floorComboBox, new String[]{"", "Ground Floor","First Floor",
                "Second " +
                "Floor","Third Floor","Lower Ground Floor","Upper Ground Floor","Mezzanine",
                "Basement 1","Basement 2", "Custom"});
        populateGivenComboBox(wallConstructionComboBox, new String[]{"", "Masonry cavity wall",
                "Timberframe","SIPS panel","100mm blockwork","140mm blockwork","215mm blockwork",
                "89mm partition","100mm partition","140mm partition", "Custom"});
        populateGivenComboBox(internalExternalComboBox, new String[]{"", "Internal", "External " +
                "[1]",
                "Custom"});
        populateGivenComboBox(partMThresholdComboBox, new YesNoOptions[]{YesNoOptions.BLANK,
                YesNoOptions.Y_2, YesNoOptions.CUSTOM});
        populateGivenComboBox(fireRatingComboBox, new String[]{"", "FD20 [3]","FD30 [3]","FD30-SC" +
                " " +
                "[3]","FD60 [3]", "Custom"});
        populateGivenComboBox(glazedComboBox, new YesNoOptions[]{YesNoOptions.BLANK,
                YesNoOptions.Y_4, YesNoOptions.CUSTOM});
        populateGivenComboBox(leafTypeComboBox, new String[]{"", "Imperial", "Metric", "Bespoke",
                "Custom"});
        populateGivenComboBox(leafSizeComboBox, new String[]{"", "610","686","762","838","626",
                "726","826","926","2 x 610","2 x 686","2 x 762","2 x 838","2 x 626","2 x 726","2 x 826"
                ,"2 x 926", "Custom"});
        populateGivenComboBox(leafNumberCheckbox, new String[]{"Single", "Double", "Triple",
                "Quad", "Custom"});
        populateGivenComboBox(entranceLevelComboBox, new YesNoOptions[]{YesNoOptions.BLANK,
                YesNoOptions.Y, YesNoOptions.CUSTOM});
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
     */
    public void attachHandlers() {
        floorComboBox.addActionListener(new CustomOptionHandler<>(floorComboBox,
                gridPanels[0][0]));
        roomComboBox.addActionListener(new CustomOptionHandler<>(roomComboBox, gridPanels[0][1]));
        handleComboBox.addActionListener(new CustomOptionHandler<>(handleComboBox,
                gridPanels[4][3]));
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

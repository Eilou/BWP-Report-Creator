package gui.panels.details;

import enums.ReportState;
import enums.YesNoOptions;
import items.doors.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Subclass to be used when creating a door report
 */
public class DoorDetailsPanel extends DetailPanel {
    private Door door;
    private JPanel[][] gridPanels;

    private final JComboBox<String> floorComboBox = new JComboBox<>();
    private final JComboBox<String> roomComboBox = new JComboBox<>();
    private final JComboBox<String> wallConstructionComboBox = new JComboBox<>();
    private final JComboBox<String> doorTypeComboBox = new JComboBox<>();
    private final JComboBox<String> internalExternalComboBox = new JComboBox<>();
    private final JComboBox<YesNoOptions> partMThresholdComboBox = new JComboBox<>();
    private final JComboBox<String> fireRatingComboBox = new JComboBox<>();
    private final JComboBox<YesNoOptions> glazedComboBox = new JComboBox<>();
    private final JComboBox<String> leafSizeComboBox = new JComboBox<>();
    private final JCheckBox doubleLeafSizeCheckbox = new JCheckBox();

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
     * @param reportState the current type of report being processed
     * @param count       the door number
     */
    public DoorDetailsPanel(JPanel parentPanel, ReportState reportState, int count) {
        super(parentPanel, reportState, count);
        this.door = new Door(count);
        int rows = 5;
        int columns = 5;
        gridPanels = new JPanel[rows][columns];

    }

    /**
     * Sets up the overall panel and the inner panel divisions ready for the different input forms
     */
    public void setup() {
        super.setup();

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        for (int row = 0; row < gridPanels.length; row++) {
            for (int column = 0; column < gridPanels[0].length; column++) {
                gridPanels[row][column] = new JPanel();
                gridPanels[row][column].setLayout(new BoxLayout(gridPanels[row][column], BoxLayout.PAGE_AXIS));
                gridPanels[row][column].setPreferredSize(new Dimension(50, 50));
                gridPanels[row][column].setBorder(new LineBorder(Color.BLACK));
                gridPanels[row][column].setBackground(new Color(255, 0, 255));

                gbc.fill = GridBagConstraints.BOTH; // stretch both horizontally and vertically
                gbc.weightx = 1.0; // expand in both directions at equal rates
                gbc.weighty = 1.0;

                gbc.gridx = column;
                gbc.gridy = row;

                add(gridPanels[row][column], gbc);
            }
        }

        populateComboBoxes();
        positionForms();
    }

    /**
     * Position the inputs in the grid bag layout
     */
    public void positionForms() {
        gridPanels[0][0].add(floorComboBox);
        gridPanels[0][1].add(roomComboBox);
        gridPanels[0][2].add(wallConstructionComboBox);
        gridPanels[0][3].add(doorTypeComboBox);
        gridPanels[0][4].add(internalExternalComboBox);

        gridPanels[1][0].add(partMThresholdComboBox);
        gridPanels[1][1].add(fireRatingComboBox);
//        gridPanels[1][2].add();
        gridPanels[1][3].add(glazedComboBox);

        gridPanels[1][4].setLayout(new BoxLayout(gridPanels[1][4], BoxLayout.LINE_AXIS));
        gridPanels[1][4].add(leafSizeComboBox);
        gridPanels[1][4].add(doubleLeafSizeCheckbox);

        gridPanels[2][0].add(clearOpeningComboBox);
        gridPanels[2][1].add(entranceLevelComboBox);
        gridPanels[2][2].add(partMCompliantComboBox);
        gridPanels[2][3].add(additionalPlyLiningComboBox);
//        gridPanels[2][4].add();

        gridPanels[3][0].add(structuralOpeningComboBox);
        gridPanels[3][1].add(structuralOpeningDetailsTextField);
        gridPanels[3][2].add(frameDetailsTextField);
        gridPanels[3][3].add(sillDetailsTextField);
        gridPanels[3][4].add(architraveTypeTextField);

        gridPanels[4][0].add(hingesComboBox);
        gridPanels[4][1].add(latchComboBox);
        gridPanels[4][2].add(lockComboBox);
        gridPanels[4][3].add(handleComboBox);
        gridPanels[4][4].add(additionalNotesTextField);

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
        populateGivenComboBox(floorComboBox, new String[]{"Ground Floor","First Floor","Second Floor","Third Floor","Lower Ground Floor","Upper Ground Floor","Mezzanine","Basement 1","Basement 2"});
        populateGivenComboBox(wallConstructionComboBox, new String[]{"Masonry cavity wall","Timberframe","SIPS panel","100mm blockwork","140mm blockwork","215mm blockwork","89mm partition","100mm partition","140mm partition"});
        populateGivenComboBox(internalExternalComboBox, new String[]{"Internal", "External [1]"});
        populateGivenComboBox(partMThresholdComboBox, new YesNoOptions[]{YesNoOptions.BLANK, YesNoOptions.Y_2});
        populateGivenComboBox(fireRatingComboBox, new String[]{"FD20 [3]","FD30 [3]","FD30-SC [3]","FD60 [3]"});
        populateGivenComboBox(glazedComboBox, new YesNoOptions[]{YesNoOptions.BLANK, YesNoOptions.Y_4});
        populateGivenComboBox(leafSizeComboBox, new String[]{"610","686","762","838","626","726", "826","926","2 x 610","2 x 686","2 x 762","2 x 838","2 x 626","2 x 726","2 x 826","2 x 926"});
        populateGivenComboBox(entranceLevelComboBox, new YesNoOptions[]{YesNoOptions.BLANK, YesNoOptions.Y});
        populateGivenComboBox(additionalPlyLiningComboBox, new YesNoOptions[]{YesNoOptions.BLANK, YesNoOptions.Y});
        populateGivenComboBox(hingesComboBox, new String[]{"1 pair", "1 1/2 pair", "2 pair"}); //
        // needs turning into the unicode values
        populateGivenComboBox(latchComboBox, new YesNoOptions[]{YesNoOptions.BLANK, YesNoOptions.Y});
        populateGivenComboBox(lockComboBox, new YesNoOptions[]{YesNoOptions.BLANK, YesNoOptions.Y, YesNoOptions.Y_5});
        populateGivenComboBox(handleComboBox, new YesNoOptions[]{YesNoOptions.Y});




    }

}

package gui.panels.details;

import enums.ReportState;
import enums.YesNoOptions;
import items.doors.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Subclass to be used when creating a door report
 */
public class DoorDetailsPanel extends DetailPanel{
    private Door door;
    private JPanel[][] gridPanels;

    private JComboBox<String> floorComboBox;
    private JComboBox<String> roomComboBox;
    private JComboBox<String> wallConstructionComboBox;
    private JComboBox<String> doorTypeComboBox;
    private JComboBox<String> internalExternalComboBox;
    private JComboBox<YesNoOptions> partMThresholdComboBox;
    private JComboBox<String> fireRatingComboBox;
    private JComboBox<YesNoOptions> glazedComboBox;
    private JComboBox<String> leafSizeComboBox;
    private JCheckBox doubleLeafSizeCheckbox;

    private JComboBox<Integer> clearOpeningComboBox;
    private JComboBox<YesNoOptions> entranceLevelComboBox;
    private JComboBox<YesNoOptions> partMCompliantComboBox;
    private JComboBox<YesNoOptions> additionalPlyLiningComboBox;
    private JComboBox<String> structuralOpeningComboBox;
    private JTextField structuralOpeningDetailsTextField;

    private JTextField frameDetailsTextField;
    private JTextField sillDetailsTextField;
    private JTextField architraveTypeTextField;

    private JComboBox<String> hingesComboBox;
    private JComboBox<YesNoOptions> latchComboBox;
    private JComboBox<YesNoOptions> lockComboBox;
    private JComboBox<YesNoOptions> handleComboBox;
    private JTextField additionalNotesTextField;

    /**
     * Constructor called when the add item is called in door report state, instancing the
     * different inputs to then be added in later
     * @param parentPanel
     * @param reportState
     * @param count
     */
    public DoorDetailsPanel(JPanel parentPanel, ReportState reportState, int count) {
        super(parentPanel, reportState, count);
        this.door = new Door(count);
        int rows = 5;
        int columns = 5;
        gridPanels = new JPanel[rows][columns];

        this.floorComboBox = new JComboBox<>();
        this.roomComboBox = new JComboBox<>();
        this.wallConstructionComboBox = new JComboBox<>();
        this.doorTypeComboBox = new JComboBox<>();
        this.internalExternalComboBox = new JComboBox<>();
        this.partMThresholdComboBox = new JComboBox<>();
        this.fireRatingComboBox = new JComboBox<>();
        this.glazedComboBox = new JComboBox<>();
        this.leafSizeComboBox = new JComboBox<>();
        this.doubleLeafSizeCheckbox = new JCheckBox();

        this.clearOpeningComboBox = new JComboBox<>();
        this.entranceLevelComboBox = new JComboBox<>();
        this.partMCompliantComboBox = new JComboBox<>();
        this.additionalPlyLiningComboBox = new JComboBox<>();

        this.structuralOpeningComboBox = new JComboBox<>();
        this.structuralOpeningDetailsTextField = new JTextField();

        this.frameDetailsTextField = new JTextField();
        this.sillDetailsTextField = new JTextField();
        this.architraveTypeTextField = new JTextField();

        this.hingesComboBox = new JComboBox<>();
        this.latchComboBox = new JComboBox<>();
        this.lockComboBox = new JComboBox<>();
        this.handleComboBox = new JComboBox<>();
        this.additionalNotesTextField = new JTextField();
    }

    /**
     * Sets up the overall panel and the inner panel divisions ready for the different input forms
     */
    public void setup() {
        super.setup();

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5,5,5,5);

        for (int row = 0; row < gridPanels.length; row++) {
            for (int column = 0; column < gridPanels[0].length; column++) {
                gridPanels[row][column] = new JPanel();
                gridPanels[row][column].setLayout(new BoxLayout(gridPanels[row][column], BoxLayout.PAGE_AXIS));
                gridPanels[row][column].setPreferredSize(new Dimension(50,50));
                gridPanels[row][column].setBorder(new LineBorder(Color.BLACK));
                gridPanels[row][column].setBackground(new Color(255,0,255));

                gbc.fill = GridBagConstraints.BOTH; // stretch both horizontally and vertically
                gbc.weightx = 1.0; // expand in both directions at equal rates
                gbc.weighty = 1.0;

                gbc.gridx = column;
                gbc.gridy = row;

                add(gridPanels[row][column], gbc);
            }
        }

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

}

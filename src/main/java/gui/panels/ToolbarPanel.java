package gui.panels;

import enums.ReportState;
import exporting.DoorReportBuilder;
import gui.StyledButton;
import gui.Styling;
import gui.handlers.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


/**
 * Provides the toolbar on the left hand side of the application
 */
public class ToolbarPanel extends JPanel {

    public static final Dimension BUTTON_SIZE = new Dimension(50, 50);

    private ReportState reportState;
    private ReportCreationPanel reportCreationPanel;
    private ProjectDetailsPanel projectDetailsPanel;

    private JPanel toolsContainer;
    private JScrollPane toolsScrollPane;

    private JLabel newFileLabel;
    private StyledButton newFileButton;

    private JLabel saveLabel;
    private StyledButton saveButton;

    private JLabel openLabel;
    private StyledButton openButton;


    private JLabel addDetailLabel;
    private StyledButton addDetailButton;
    private JCheckBox backfillCheckbox;

    private JLabel removeDetailLabel;
    private StyledButton removeDetailButton;

    private JLabel generateReportLabel;
    private StyledButton generateReportButton;


    private JLabel summaryLabel;
    private StyledButton summaryButton;


    public ToolbarPanel(ReportState reportState, ReportCreationPanel reportCreationPanel, ProjectDetailsPanel projectDetailsPanel) {
        this.reportState = reportState;
        this.reportCreationPanel = reportCreationPanel;
        this.projectDetailsPanel = projectDetailsPanel;

        toolsContainer = new JPanel();
        toolsScrollPane = new JScrollPane(toolsContainer);

        newFileLabel = new JLabel("New");
        saveLabel = new JLabel("Save");
        openLabel = new JLabel("Open");
        addDetailLabel = new JLabel("Add " + reportState);
        removeDetailLabel = new JLabel("Remove last " + reportState);
        generateReportLabel = new JLabel("Generate " + reportState + " Report");
        summaryLabel = new JLabel("Summary");

        newFileButton = new StyledButton();
        saveButton = new StyledButton();
        openButton = new StyledButton();


        addDetailButton = new StyledButton();
        backfillCheckbox = new JCheckBox();

        removeDetailButton = new StyledButton();
        generateReportButton = new StyledButton();

        summaryButton = new StyledButton();

    }

    /**
     * Add the buttons onto the GUI and locate them as appropriate
     */
    public void setup() {

        setBackground(Styling.BACKGROUND);
        setForeground(Styling.TEXT);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        toolsContainer.setLayout(new GridBagLayout());
        toolsContainer.setBackground(Styling.BACKGROUND);
        toolsContainer.setForeground(Styling.TEXT);
        toolsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        toolsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        toolsScrollPane.setBorder(new EmptyBorder(0,0,0,0));

        setBorder(new EmptyBorder(0,0,0,0));
        toolsContainer.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Styling.TEXT),
                new LineBorder(Styling.FOREGROUND, 5)));

        styleButtons();
        attachHandlers();
        positionPanels();
    }

    /**
     * Style the panel as to the size I want them to be
     *
     * @param label what the button does
     * @param components the elements being put into a panel below the label
     * @return a styled JPanel
     */
    private JPanel stylePanel(JLabel label, JComponent... components) {
        JPanel innerPanel = new JPanel();

        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.PAGE_AXIS));
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.add(label);

        JPanel componentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        for (JComponent component : components) {
            componentPanel.add(component);
        }
        innerPanel.add(labelPanel);
        innerPanel.add(componentPanel);

        innerPanel.setBackground(Styling.FOREGROUND);
        labelPanel.setBackground(Styling.BACKGROUND);
        label.setForeground(Styling.TEXT);
        componentPanel.setBackground(Styling.FOREGROUND);
        innerPanel.setBorder(new CompoundBorder(new EmptyBorder(5,0,5,0), new LineBorder(Styling.TEXT, 2)));
        return innerPanel;
    }

    /**
     * Positions the panels in the panel
     */
    private void positionPanels() {

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0,0,0,0);

        gbc.fill = GridBagConstraints.BOTH; // stretch both horizontally and vertically
        gbc.weightx = 1; // expand in the x direction but not in the y
        gbc.weighty = 0;

        gbc.gridx = 0;
        gbc.gridy = 0;

        toolsContainer.add(stylePanel(newFileLabel, newFileButton), gbc);
        gbc.gridy = 1;
        toolsContainer.add(stylePanel(saveLabel, saveButton), gbc);
        gbc.gridy = 2;
        toolsContainer.add(stylePanel(openLabel, openButton), gbc);

        gbc.gridy = 3;
        toolsContainer.add(stylePanel(addDetailLabel, addDetailButton, backfillCheckbox), gbc);
        gbc.gridy = 4;
        toolsContainer.add(stylePanel(removeDetailLabel, removeDetailButton), gbc);
        gbc.gridy = 5;
        toolsContainer.add(stylePanel(generateReportLabel, generateReportButton), gbc);
        gbc.gridy = 6;
        toolsContainer.add(stylePanel(summaryLabel, summaryButton), gbc);

        add(toolsScrollPane);
    }


    /**
     * Style the button elements
     */
    public void styleButtons() {

        newFileButton.setup(StyledButton.getScaledImage(
                (ImageIcon) UIManager.getIcon("FileView.fileIcon"), BUTTON_SIZE.width, BUTTON_SIZE.height));
        saveButton.setup(StyledButton.getScaledImage(
                (ImageIcon) UIManager.getIcon("FileView.floppyDriveIcon"), BUTTON_SIZE.width, BUTTON_SIZE.height));
        openButton.setup(StyledButton.getScaledImage(
                (ImageIcon) UIManager.getIcon("FileView.directoryIcon"), BUTTON_SIZE.width, BUTTON_SIZE.height));

        addDetailButton.setup(StyledButton.getScaledImage(
                new ImageIcon("src/main/resources/buttonIcons/addItemIcon.png"), BUTTON_SIZE.width, BUTTON_SIZE.height));
        backfillCheckbox.setIcon(StyledButton.getScaledImage(
                new ImageIcon("src/main/resources/buttonIcons/backfillIcon-Disabled.png"), BUTTON_SIZE.width, BUTTON_SIZE.height));
        Styling.setComponentColours(backfillCheckbox, Styling.FOREGROUND, Styling.TEXT);
        backfillCheckbox.setBorder(new EmptyBorder(5, 5, 5, 5));

        removeDetailButton.setup(StyledButton.getScaledImage(
                new ImageIcon("src/main/resources/buttonIcons/deleteLastIcon.png"), BUTTON_SIZE.width, BUTTON_SIZE.width * 3/4));

        generateReportButton.setup(StyledButton.getScaledImage(
                new ImageIcon("src/main/resources/buttonIcons/generateIcon.png"), BUTTON_SIZE.width, BUTTON_SIZE.height));
        summaryButton.setup(StyledButton.getScaledImage(
                new ImageIcon("src/main/resources/buttonIcons/summaryIcon.png"), BUTTON_SIZE.width, BUTTON_SIZE.height));

    }

    /**
     * Attach the handlers to the buttons to give them functionality
     */
    public void attachHandlers() {

        newFileButton.addActionListener(new NewFileButtonHandler(reportCreationPanel, projectDetailsPanel));
        saveButton.addActionListener(new SaveButtonHandler(reportCreationPanel, projectDetailsPanel));
        openButton.addActionListener(new OpenButtonHandler(reportCreationPanel, projectDetailsPanel));

        addDetailButton.addActionListener(new AddDetailButtonHandler(reportState, reportCreationPanel, backfillCheckbox));
        backfillCheckbox.addActionListener(e -> {
            if (backfillCheckbox.isSelected())
                backfillCheckbox.setIcon(StyledButton.getScaledImage(new ImageIcon("src/main/resources/buttonIcons/backfillIcon-Enabled.png"), BUTTON_SIZE.width, BUTTON_SIZE.height));
            else
                backfillCheckbox.setIcon(StyledButton.getScaledImage(new ImageIcon("src/main/resources/buttonIcons/backfillIcon-Disabled.png"), BUTTON_SIZE.width, BUTTON_SIZE.height));
        });

        removeDetailButton.addActionListener(new RemoveLastDetailButtonHandler(reportState, reportCreationPanel));
        generateReportButton.addActionListener(new GenerateReportHandler(new DoorReportBuilder(reportCreationPanel, projectDetailsPanel)));
    }

    /**
     * Updates the Generate report button to match whatever state the report creator is currently in by changing which
     * type of report builder the GenerateReportHandler is using
     */
    public void reloadGenerateReportButtonHandlers() {

        ((GenerateReportHandler) generateReportButton.getActionListeners()[0]).setReportBuilder(
                switch (reportState) {
                    case DOOR -> new DoorReportBuilder(reportCreationPanel, projectDetailsPanel);
                    default -> null;
                }
        );

    }


}

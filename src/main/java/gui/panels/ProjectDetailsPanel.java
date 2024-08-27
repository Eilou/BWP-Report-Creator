package gui.panels;

import enums.ReportState;
import gui.Styling;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Holds general information about the
 */
public class ProjectDetailsPanel extends JPanel {

    private JPanel parentPanel;
    private ReportState reportState;

    private JPanel titlePanel;
    private JLabel titleLabel;
    private JTextField titleField;

    private JPanel projectNumberPanel;
    private JLabel projectNumberLabel;
    private JTextField projectNumberField;

    private JPanel itemNumberPanel;
    private JLabel itemNumberLabel;
    private JTextField itemNumberField;

    public ProjectDetailsPanel(JPanel parentPanel, ReportState reportState) {
        this.parentPanel = parentPanel;
        this.reportState = reportState;
//        setPreferredSize(new Dimension(parentPanel.getWidth(), 50));

//        titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel = new JPanel(new BorderLayout());
        titleLabel = new JLabel("Project Title:");
        titleField = new JTextField("Insert title here");

        projectNumberPanel = new JPanel(new BorderLayout());
        projectNumberLabel = new JLabel("Project Number:");
        projectNumberField = new JTextField("XXX ####");

        itemNumberPanel = new JPanel(new BorderLayout());

        String labelText = "";
        String fieldText = "";
        switch (this.reportState) {
            case DOOR -> {
                labelText = "Door Schedule:";
                fieldText = "CZ01";
            }
            default -> {}
        }
        itemNumberLabel = new JLabel(labelText);
        itemNumberField = new JTextField(fieldText);

    }

    /**
     * adds in the text to the statistics panel
     */
    public void setup() {
        setBackground(Styling.FOREGROUND);
        setForeground(Styling.TEXT);

        setLayout(new GridBagLayout());
        setBorder(new LineBorder(Styling.FOREGROUND, 10));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 0, 0, 0);

        gbc.fill = GridBagConstraints.BOTH; // stretch both horizontally and vertically
        gbc.weightx = 1.0; // expand in both directions at equal rates
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;

        titlePanel.setBackground(Styling.FOREGROUND);
        titleLabel.setForeground(Styling.TEXT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        titlePanel.add(titleLabel, BorderLayout.WEST);
        titlePanel.add(titleField, BorderLayout.CENTER);

        projectNumberPanel.setBackground(Styling.FOREGROUND);
        projectNumberLabel.setForeground(Styling.TEXT);
        projectNumberLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        projectNumberPanel.add(projectNumberLabel, BorderLayout.WEST);
        projectNumberPanel.add(projectNumberField, BorderLayout.CENTER);

        itemNumberPanel.setBackground(Styling.FOREGROUND);
        itemNumberLabel.setForeground(Styling.TEXT);
        itemNumberLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        itemNumberPanel.add(itemNumberLabel, BorderLayout.WEST);
        itemNumberPanel.add(itemNumberField, BorderLayout.CENTER);

        add(titlePanel, gbc);
        gbc.gridx = 1;
        add(projectNumberPanel, gbc);
        gbc.gridx = 2;
        add(itemNumberPanel, gbc);

        repaint();
        revalidate();

    }

    ////
    // getters and setters
    ////

    public JTextField getItemNumberField() {
        return itemNumberField;
    }

    public void setItemNumberField(JTextField itemNumberField) {
        this.itemNumberField = itemNumberField;
    }

    public JTextField getProjectNumberField() {
        return projectNumberField;
    }

    public void setProjectNumberField(JTextField projectNumberField) {
        this.projectNumberField = projectNumberField;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public void setTitleField(JTextField titleField) {
        this.titleField = titleField;
    }
}

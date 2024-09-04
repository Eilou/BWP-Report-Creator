package gui.panels;

import enums.ReportState;
import gui.Styling;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;

/**
 * Holds general information about the
 */
public class ProjectDetailsPanel extends JPanel implements Serializable {

    private ReportState reportState;

    private final transient JPanel titlePanel;
    private final transient JLabel titleLabel;
    private transient JTextField titleField;
    private String titleValue;

    private final transient JPanel projectNumberPanel;
    private final transient JLabel projectNumberLabel;
    private transient JTextField projectNumberField;
    private String projectNumberValue;

    private final transient JPanel itemNumberPanel;
    private final transient JLabel itemNumberLabel;
    private transient JTextField itemNumberField;
    private String itemNumberValue;

    public ProjectDetailsPanel(ReportState reportState) {
        this.reportState = reportState;

        titlePanel = new JPanel(new BorderLayout());
        titleLabel = new JLabel();
        titleField = new JTextField();

        projectNumberPanel = new JPanel(new BorderLayout());
        projectNumberLabel = new JLabel();
        projectNumberField = new JTextField();

        itemNumberPanel = new JPanel(new BorderLayout());
        itemNumberLabel = new JLabel();
        itemNumberField = new JTextField();

        setDefaultValues();
    }

    /**
     * adds in the text to the statistics panel
     */
    public void setup() {
        setBackground(Styling.FOREGROUND);
        setForeground(Styling.TEXT);

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Styling.TEXT),
                new LineBorder(Styling.FOREGROUND, 10)
                )
        );

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

    /**
     * Sets the default texts for the different components
     */
    public void setDefaultValues() {
        titleLabel.setText("Project Title:");
        titleField.setText("Insert title here");

        projectNumberLabel.setText("Project Number:");
        projectNumberField.setText("XXX ####");

        String labelText = "";
        String fieldText = "";
        switch (this.reportState) {
            case DOOR -> {
                labelText = "Door Schedule:";
                fieldText = "CZ01";
            }
            default -> {
            }
        }
        itemNumberLabel.setText(labelText);
        itemNumberField.setText(fieldText);
    }

    /**
     * Gets the value held in the text fields, then serializes them
     *
     * @param file the file path to save to
     * @throws IOException if the file path doesn't exist
     */
    public void save(File file) throws IOException {

        titleValue = titleField.getText();
        projectNumberValue = projectNumberField.getText();
        itemNumberValue = itemNumberField.getText();

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(reportState);
        oos.writeObject(titleValue);
        oos.writeObject(projectNumberValue);
        oos.writeObject(itemNumberValue);

        fos.close();
    }

    /**
     * Deserializes the values then assigns them to their respective text fields
     *
     * @param file the file to read from
     * @return the amount of bytes of the file already read
     * @throws IOException if the file path doesn't exist
     */
    public long load(File file) throws IOException {

        FileInputStream fis = new FileInputStream(file);

        try {
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.reportState = (ReportState) ois.readObject();
            this.titleValue = (String) ois.readObject();
            this.projectNumberValue = (String) ois.readObject();
            this.itemNumberValue = (String) ois.readObject();

            titleField.setText(titleValue);
             projectNumberField.setText(projectNumberValue);
             itemNumberField.setText(itemNumberValue);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally { // not optimal but more readable
            long readSoFar = fis.getChannel().position();
            fis.close();
            return readSoFar;
        }
    }

    /**
     * Resets the different elements back to their original values
     */
    public void reset() {
        setDefaultValues();
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

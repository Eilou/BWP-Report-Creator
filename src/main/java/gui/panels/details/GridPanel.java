package gui.panels.details;

import javax.swing.*;

/**
 * Means we can access a custom text box to add or remove later
 * Having it as a class means that we always have a pointer readily available to read from when outputting to a PDF
 */
public class GridPanel extends JPanel {

    private JTextField customTextBox;

    /**
     * Constructor for a panel within the grid
     */
    public GridPanel() {
        super();
        customTextBox = new JTextField("Custom option here");
    }

    /**
     * Called when the custom option is selected in a combobox to add a section to add what you want to
     */
    public void addCustomOption() {
        add(customTextBox);
        revalidate();
        repaint();
    }

    /**
     * Remove the custom input section when the custom option not selected from the combobox
     */
    public void removeCustomOption() {
        remove(customTextBox);
        revalidate();
        repaint();
    }

    public JTextField getCustomTextBox() {
        return customTextBox;
    }

    public void setCustomTextBox(JTextField customTextBox) {
        this.customTextBox = customTextBox;
    }
}

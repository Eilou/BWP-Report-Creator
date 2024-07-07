package gui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Provides the toolbar on the left hand side of the application
 */
public class ToolbarPanel extends JPanel {

    private JPanel parentPanel;
    private JButton addButton;
    private JButton removeButton;
    private JButton previewButton;
    private JButton printButton;

    public ToolbarPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;
        setPreferredSize(new Dimension(100, parentPanel.getHeight()));

        addButton = new JButton("Add Door");
        removeButton = new JButton("Remove Door");
        previewButton = new JButton("Preview Report");
        printButton = new JButton("Print Report");
    }

    public void setup() {
        setBackground(Color.green);
        setLayout(new GridLayout(0,1));

        JLabel text = new JLabel();
        text.setText("Toolbar Filler Text");
        text.setHorizontalAlignment(JLabel.CENTER);
        add(text);

        add(addButton);
        add(removeButton);
        add(previewButton);
        add(printButton);
    }

}

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

    public ToolbarPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;
        setPreferredSize(new Dimension(100, parentPanel.getHeight()));

        addButton = new JButton("Add Door");
        removeButton = new JButton("Remove Door");
    }

    public void setup() {
        setBackground(Color.green);
        setLayout(new GridLayout(0,1));

        JLabel text = new JLabel();
        text.setText("Toolbar Filler Text");
        text.setHorizontalAlignment(JLabel.CENTER);
        add(text);

        addButton.set
        add(addButton);
        add(removeButton);
    }

}

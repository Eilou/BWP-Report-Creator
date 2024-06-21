package gui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Provides the toolbar on the left hand side of the application
 */
public class ToolbarPanel extends JPanel {

    private JPanel parentPanel;

    public ToolbarPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;
        setPreferredSize(new Dimension(100, parentPanel.getHeight()));
    }

    public void setup() {
        setBackground(Color.green);
    }

}

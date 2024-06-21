package gui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Holds the area where you can create a report
 */
public class ReportCreationPanel extends JPanel {

    private JPanel parentPanel;
    public ReportCreationPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    public void setup() {
        setBackground(Color.yellow);
    }
}

package gui.panels;

import enums.ReportState;
import gui.handlers.AddDetailButtonHandler;
import gui.handlers.RemoveLastDetailButtonHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Provides the toolbar on the left hand side of the application
 */
public class ToolbarPanel extends JPanel {

    private JPanel parentPanel;
    private ReportState reportState;
    private ReportCreationPanel reportCreationPanel;
    private JButton addDetailButton;
    private JButton removeDetailButton;
    private JButton previewButton;
    private JButton printButton;

    public ToolbarPanel(JPanel parentPanel, ReportState reportState, ReportCreationPanel reportCreationPanel) {
        this.parentPanel = parentPanel;
        this.reportState = reportState;
        this.reportCreationPanel = reportCreationPanel;
        setPreferredSize(new Dimension(100, parentPanel.getHeight()));

        addDetailButton = new JButton("Add " + reportState);
        removeDetailButton = new JButton("Remove " + reportState);
        previewButton = new JButton("Preview Report");
        printButton = new JButton("Print Report");
    }

    /**
     * Add the buttons onto the GUI and locate them as appropriate
     */
    public void setup() {
        setBackground(Color.green);
        setLayout(new GridLayout(0,1));

        JLabel text = new JLabel();
        text.setText("Toolbar Filler Text");
        text.setHorizontalAlignment(JLabel.CENTER);
        add(text);

        attachHandlers();

        add(addDetailButton);
        add(removeDetailButton);
        add(previewButton);
        add(printButton);
    }

    /**
     * Attach the handlers to the buttons to give them functionality
     */
    public void attachHandlers() {
        addDetailButton.addActionListener(
                new AddDetailButtonHandler(reportState, reportCreationPanel));
        removeDetailButton.addActionListener(
                new RemoveLastDetailButtonHandler(reportState, reportCreationPanel));
    }

}

package gui.panels;

import enums.ReportState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Holds the area where you can create a report
 */
public class ReportCreationPanel extends JPanel {

    private JPanel parentPanel;
    private ArrayList<DetailPanel> listOfDetails;
    private ReportState reportState;

    private JPanel detailsContainer;
    private JScrollPane detailsScrollPane;
    public ReportCreationPanel(JPanel parentPanel, ReportState reportState) {
        this.parentPanel = parentPanel;
        this.reportState = reportState;
        listOfDetails = new ArrayList<>();

        detailsContainer = new JPanel();
        detailsScrollPane = new JScrollPane(detailsContainer);
    }

    public void setup() {
        setBackground(Color.yellow);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        detailsContainer.setLayout(new BoxLayout(detailsContainer, BoxLayout.PAGE_AXIS));
        createDefaultItems();
        detailsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(detailsScrollPane);
    }

    /**
     * Generates a base amount of doors to fill out
     * Needs to be in a separate method, so it can be called after set visible is true
     */
    public void createDefaultItems() {
        for (int i = 0; i < 5; i++) {
            // will want to add an if statement here depending on the report state to determine
            // which subclass of detail panel to use
            DetailPanel currentPanel = new DetailPanel(this, reportState, i+1);
            currentPanel.setup();
            listOfDetails.add(currentPanel);
            detailsContainer.add(currentPanel);
        }

    }

}

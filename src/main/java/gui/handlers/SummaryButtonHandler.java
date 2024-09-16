package gui.handlers;

import gui.panels.ReportCreationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Gets a summary and outputs it in a pop out frame
 */
public class SummaryButtonHandler implements ActionListener {

    private ReportCreationPanel reportCreationPanel;

    public SummaryButtonHandler(ReportCreationPanel reportCreationPanel) {
        this.reportCreationPanel = reportCreationPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        HashMap<String, Object> summary = reportCreationPanel.generateSummary();

        System.out.println("----------");
        System.out.println("Summary Generated");
        for (String key : summary.keySet()) {
            System.out.println(key + ": " + summary.get(key)); // isn't the prettiest but it'll do
        }
        System.out.println("----------");

    }
}

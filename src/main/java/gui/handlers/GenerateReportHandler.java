package gui.handlers;

import exporting.ReportBuilder;

import java.awt.event.*;

/**
 * Generates the report when the button is pressed
 */
public class GenerateReportHandler implements ActionListener {
    private ReportBuilder reportBuilder;

    public GenerateReportHandler(ReportBuilder reportBuilder) {
        this.reportBuilder = reportBuilder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        reportBuilder.generateHTML();
    }

    /**
     * Used when switching between different report states, eg so can switch between door report builder
     * and fire report
     * Saves removing all event listeners and then adding a new one each time
     *
     * @param reportBuilder the report builder tool to use
     */
    public void setReportBuilder(ReportBuilder reportBuilder) {
        this.reportBuilder = reportBuilder;
    }
}

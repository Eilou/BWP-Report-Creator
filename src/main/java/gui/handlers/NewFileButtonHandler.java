package gui.handlers;

import gui.panels.ProjectDetailsPanel;
import gui.panels.ReportCreationPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles making a new file (and checking that the user definitely wants to do this
 */
public class NewFileButtonHandler implements ActionListener {

    private ReportCreationPanel reportCreationPanel;
    private ProjectDetailsPanel projectDetailsPanel;

    public NewFileButtonHandler(ReportCreationPanel reportCreationPanel, ProjectDetailsPanel projectDetailsPanel) {
        this.reportCreationPanel = reportCreationPanel;
        this.projectDetailsPanel = projectDetailsPanel;
    }

    /**
     * Asks to confirm before allowing the user to open a new file
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int returnValue = JOptionPane.showOptionDialog(
                null, "Are you sure you want to open a new file", "Open a new file?",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
                new String[]{"Yes", "Cancel"}, "Yes");
        if (returnValue == JOptionPane.YES_OPTION) {
            projectDetailsPanel.reset();
            reportCreationPanel.reset();
        }


    }
}

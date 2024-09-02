package gui.handlers;

import gui.panels.ReportCreationPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Saves the contents of the report creation panel to a file
 */
public class SaveButtonHandler implements ActionListener {
    private ReportCreationPanel reportCreationPanel;

    public SaveButtonHandler(ReportCreationPanel reportCreationPanel) {
        this.reportCreationPanel = reportCreationPanel;
    }

    /**
     * Prompts the user with a pop out window to choose the file to save to then updates writes to this file if appropriate one chosen
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "BWP Report Creator Files", "bwparchirc");

        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(null); // null parent so appears in the middle
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("User chose to save file: " + chooser.getSelectedFile().getName());
            try {
                reportCreationPanel.save(chooser.getSelectedFile());
            } catch (IOException exception) {
                System.out.println("User has chosen to write to a file which doesn't yet exist");
                exception.printStackTrace();
            }
        }
    }
}

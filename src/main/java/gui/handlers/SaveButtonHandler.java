package gui.handlers;

import gui.panels.ProjectDetailsPanel;
import gui.panels.ReportCreationPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Saves the contents of the report creation panel to a file
 */
public class SaveButtonHandler implements ActionListener {
    private ReportCreationPanel reportCreationPanel;
    private ProjectDetailsPanel projectDetailsPanel;

    public SaveButtonHandler(ReportCreationPanel reportCreationPanel, ProjectDetailsPanel projectDetailsPanel) {
        this.reportCreationPanel = reportCreationPanel;
        this.projectDetailsPanel = projectDetailsPanel;
    }

    /**
     * Prompts the user with a pop out window to choose the file to save to then updates writes to this file if appropriate one chosen
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "BWP Report Creator Files", "bwparchirc");

        chooser.setFileFilter(filter);
        // suggested name prompt
        chooser.setSelectedFile(new File(projectDetailsPanel.getItemNumberField().getText() + ".bwparchirc"));

        int returnVal = chooser.showSaveDialog(null); // null parent so appears in the middle of a user's screen
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            String fileName = chooser.getSelectedFile().getName();
            if (fileName.lastIndexOf('.') == -1 || !(fileName.strip().substring(fileName.lastIndexOf('.'))).equals(".bwparchirc")) {
                JOptionPane.showOptionDialog(null, "Chosen invalid file type", "File Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Okay"}, "Okay");
                System.out.println("Chosen invalid file type");
            } else {

                System.out.println("User chose to save file: " + fileName);
                try {
                    // makes sense to write the file title details first then the actual file content
                    projectDetailsPanel.save(chooser.getSelectedFile());
                    reportCreationPanel.save(chooser.getSelectedFile());

                } catch (IOException exception) {
                    System.out.println("User has chosen to write to a file which doesn't yet exist");
                    exception.printStackTrace();
                }
            }
        }
    }
}

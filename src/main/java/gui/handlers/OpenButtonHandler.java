package gui.handlers;

import gui.panels.ProjectDetailsPanel;
import gui.panels.ReportCreationPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Opens a file and loads it into the application to use
 */
public class OpenButtonHandler implements ActionListener {
    private final ReportCreationPanel reportCreationPanel;
    private final ProjectDetailsPanel projectDetailsPanel;

    public OpenButtonHandler(ReportCreationPanel reportCreationPanel, ProjectDetailsPanel projectDetailsPanel) {
        this.reportCreationPanel = reportCreationPanel;
        this.projectDetailsPanel = projectDetailsPanel;
    }

    /**
     * Prompts the user with a pop out window to choose the file to read from then opens it,  if an appropriate file chosen
     * If an invalid file format is chosen then nothing happens (it outputs to the console an issue but that's it)
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "BWP Report Creator Files", "bwparchirc");

        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null); // null parent so appears in the middle

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            // checks if the extension is valid
            String fileName = chooser.getSelectedFile().getName();
            if (fileName.lastIndexOf('.') == -1 || !(fileName.strip().substring(fileName.lastIndexOf('.'))).equals(".bwparchirc")) {
                JOptionPane.showOptionDialog(null, "Chosen invalid file type", "File Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Okay"}, "Okay");
                System.out.println("Chosen invalid file type");
            }
            else {

                projectDetailsPanel.reset();
                reportCreationPanel.reset();

                System.out.println("User chose to open file: " + fileName);
                System.out.println(chooser.getSelectedFile());
                try {

                    // makes sense to write the file title details first then the actual file content
                    long readSoFar = projectDetailsPanel.load(chooser.getSelectedFile());
                    reportCreationPanel.load(chooser.getSelectedFile(), readSoFar);

                } catch (IOException exception) {
                    System.out.println("User has chosen to read from a file which doesn't yet exist");
                    exception.printStackTrace();
                }
            }
        }
    }
}

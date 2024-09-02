package gui.handlers;

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
    private ReportCreationPanel reportCreationPanel;

    public OpenButtonHandler(ReportCreationPanel reportCreationPanel) {
        this.reportCreationPanel = reportCreationPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "BWP Report Creator Files", "bwparchirc");

        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null); // null parent so appears in the middle
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("User chose to open file: " + chooser.getSelectedFile().getName());
            try {
                reportCreationPanel.load(chooser.getSelectedFile());
            } catch (IOException exception) {
                System.out.println("User has chosen to read from a file which doesn't yet exist");
                exception.printStackTrace();
            }
        }
    }
}

package gui;

import enums.ReportState;
import gui.panels.*;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.io.*;

/**
 * Frame to hold the main window for the GUI
 */
public class GUIFrame extends JFrame {

    public Toolkit toolkit = Toolkit.getDefaultToolkit();

    private ReportState reportState;
    private ProjectDetailsPanel projectDetailsPanel;
    private ToolbarPanel toolbarPanel;
    private ReportCreationPanel reportCreationPanel;


    public GUIFrame() {
        reportState = ReportState.DOOR;
        setTitle("BWP Report Creator");
        setSize((int) (toolkit.getScreenSize().width * 0.75),
                (int) (toolkit.getScreenSize().height * 0.75));

        reportCreationPanel = new ReportCreationPanel(reportState);
        projectDetailsPanel = new ProjectDetailsPanel(reportState);
        toolbarPanel = new ToolbarPanel(reportState, reportCreationPanel, projectDetailsPanel);
    }

    /**
     * Define the panels and the hierarchy
     */
    public void setup() {

        // set up panels
//        contentPane.setBackground(Color.red);
        Container contentPane = getContentPane();

        contentPane.setLayout(new BorderLayout());

        projectDetailsPanel.setup();
        contentPane.add(projectDetailsPanel, BorderLayout.NORTH);

        toolbarPanel.setup();
        contentPane.add(toolbarPanel, BorderLayout.WEST);

        reportCreationPanel.setup();
        contentPane.add(reportCreationPanel, BorderLayout.CENTER);

        // show on the screen
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Writes the current state of the app to a file to be loaded in later
     *
     * @param file the file path to save to
     * @throws IOException if the file path doesn't exist
     */
    public void save(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(reportState);
        oos.writeObject(projectDetailsPanel);
        oos.writeObject(toolbarPanel);
        oos.writeObject(reportCreationPanel);

        fos.close();
    }

    /**
     * Retrieves a saved version of the app in byte form, then updates the current one to match,
     * finally it runs setup() to ensure all handlers are appropriately assigned
     *
     * @param file file to read from
     * @throws IOException if the file does not exist
     */
    public void load(File file) throws IOException {

        FileInputStream fis = new FileInputStream(file);

        try {
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.reportState = (ReportState) ois.readObject();
            this.projectDetailsPanel = (ProjectDetailsPanel) ois.readObject();
            this.toolbarPanel = (ToolbarPanel) ois.readObject();
            this.reportCreationPanel = (ReportCreationPanel) ois.readObject();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally { // not optimal but more readable
            fis.close();
        }
    }


}

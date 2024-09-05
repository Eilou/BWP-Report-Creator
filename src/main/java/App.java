import gui.GUIFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @author Louie Brooks
 * Development started Summer 2024
 *
 * Entry point to the program
 */
public class App {

    public static void main (String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
//                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                    // https://www.geeksforgeeks.org/java-swing-look-feel/ for a good list
                    GUIFrame guiFrame = new GUIFrame();
                    guiFrame.setup();
                    System.out.println("Application GUI has loaded");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}

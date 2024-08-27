import gui.GUIFrame;

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

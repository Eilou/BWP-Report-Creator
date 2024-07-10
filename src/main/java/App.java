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
        GUIFrame guiFrame = new GUIFrame();
        guiFrame.setup();
        System.out.println(new Dimension(30,50));
    }
}

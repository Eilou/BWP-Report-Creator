import gui.GUIFrame;

import java.awt.*;
//import com.itextpdf.io.font.FontConstant;

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
        System.out.println("Application GUI has loaded");
    }
}

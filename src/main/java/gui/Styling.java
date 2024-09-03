package gui;

import java.awt.*;
import javax.swing.border.*;
import javax.swing.JComponent;

/**
 * Encapsulates the colours, fonts etc. used in the project, so they can be changed throughout easily
 */
public class Styling {
     public final static Color FOREGROUND = new Color(68, 80, 96);
    public final static Color BACKGROUND = new Color(51, 57, 71);
    public final static Color TEXT = new Color(215,221,232);
    public final static Color BORDER = new Color(35, 43, 55);

    public static void setComponentColours(JComponent component, Color background,
                                           Color foreground) {
        setComponentColours(component, background, foreground, null);
    }

    /**
     * Function to speed up specifying all the different parts of a component
     *
     * @param component the component to style
     * @param background background colour
     * @param foreground foreground colour (text or font colour)
     * @param border component's desired border
     */
    public static void setComponentColours(JComponent component, Color background,
                                           Color foreground, Border border) {
        component.setBackground(background);
        component.setForeground(foreground);
        component.setBorder(border);
    }
}

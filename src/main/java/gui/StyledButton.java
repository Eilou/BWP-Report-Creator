package gui;

import gui.Styling;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * A class to help with styling buttons easier, as well as add some interactiveness
 * such as through mimicking the css pseudo-class hover
 */
public class StyledButton extends JButton {

    public StyledButton() {
        this(null, null);
    }

    public StyledButton(Icon icon) {
        this(null, icon);
    }

    public StyledButton(String text) {
        this(text, null);
    }

    public StyledButton(String text, Icon icon) {
        super(text, icon);
    }

    public void setup() {
        setup(null);
    }

    public void setup(Icon icon) {
        setup(Styling.FOREGROUND, Styling.TEXT, icon);
    }

    public void setup(Color background, Color foreground) {
        setup(background, foreground, null);
    }

    /**
     * Sets up the appearance of the button, as well as attaches a listener to
     * hovering and pressing functionality
     *
     * @param icon an icon to be displayed
     */
    public void setup(Color background, Color foreground, Icon icon) {
        setBackground(background);
        setForeground(foreground);
        setIcon(icon);

        getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();

            if (model.isPressed()) { // pressed
                setForeground(Color.BLACK);
            } else if (model.isRollover()) { // hovered
                setBackground(background.brighter());
                setForeground(foreground);
            } else { // default
                setBackground(background);
                setForeground(foreground);
            }

        });
    }

}
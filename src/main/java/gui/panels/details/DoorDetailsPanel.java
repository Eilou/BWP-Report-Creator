package gui.panels.details;

import enums.ReportState;
import items.doors.*;

import javax.swing.*;
import java.awt.*;

/**
 * Subclass to be used when creating a door report
 */
public class DoorDetailsPanel extends DetailPanel{
    private Door door;

    public DoorDetailsPanel(JPanel parentPanel, ReportState reportState, int count) {
        super(parentPanel, reportState, count);
        this.door = new Door();
    }

}

package gui.handlers;

import gui.panels.ReportCreationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Gets a summary and outputs it in a pop out frame
 */
public class SummaryButtonHandler implements ActionListener {

    private ReportCreationPanel reportCreationPanel;

    public SummaryButtonHandler(ReportCreationPanel reportCreationPanel) {
        this.reportCreationPanel = reportCreationPanel;
    }

    /**
     * Provides a console output of the summary
     *
     * @param e the event to be processed
     */
    @SuppressWarnings("unchecked")
    // this is to counter the summary.get("...") being casted as a particular generic, but I know they're correct so it's fine
    @Override
    public void actionPerformed(ActionEvent e) {
        HashMap<String, Object> summary = reportCreationPanel.generateSummary();

        System.out.println("----------");
        System.out.println("Summary Generated");
        System.out.println("Leaf Sizes: ");

        // Accesses the custom leaf sizes and the other organised leaf sizes
        if (summary.get("Leaf Sizes") == null)
            System.out.println("Null value for key: Leaf Sizes"); // protection for suppressing the warning
        else {
            TreeMap<String, Object> leafSizesMap = (TreeMap<String, Object>) summary.get("Leaf Sizes");
            for (String key : leafSizesMap.keySet())
                System.out.println("\t" + key + " : " + leafSizesMap.get(key));
        }
        if (summary.get("Leaf Sizes Custom") == null)
            System.out.println("Null value for key: Leaf Sizes Custom");
        // protection for suppressing the warning
        else {
            HashMap<String, Object> leafSizesCustomMap = (HashMap<String, Object>) summary.get(
                    "Leaf Sizes Custom");
            for (String key : leafSizesCustomMap.keySet())
                System.out.println("\t" + key + " : " + leafSizesCustomMap.get(key));
        }

        System.out.println("Hinges:");
        if (summary.get("Hinges") == null)
            System.out.println("Null value mapped for key: Hinges"); // protection for suppressing the warning
        else {
            HashMap<String, Object> hingeQuantityCustomMap = (HashMap<String, Object>) summary.get("Hinges");
            System.out.println("\tQuantity : " + hingeQuantityCustomMap.get("Quantity"));
            System.out.println("\tCustom : "); //might be better to move this into the bit below
            if (hingeQuantityCustomMap.get("Custom") == null)
                System.out.println("Null value mapped for key: Custom"); // protection for suppressing the warning
            else {
                HashMap<String, Object> hingeCustomNumberMap = (HashMap<String, Object>) hingeQuantityCustomMap.get("Custom");
                for (String key : hingeCustomNumberMap.keySet())
                    System.out.println("\t\t" + key + " : " + hingeCustomNumberMap.get(key));
            }
        }

        System.out.println("Handles");
        if (summary.get("Handles") == null)
            System.out.println("Null value mapped for key: Handles"); // protection for suppressing the warning
        else {
            HashMap<String, Object> handlesQuantityCustomMap = (HashMap<String, Object>) summary.get("Handles");
            System.out.println("\tQuantity : " + handlesQuantityCustomMap.get("Quantity"));
            System.out.println("\tCustom : "); //might be better to move this into the bit below
            if (handlesQuantityCustomMap.get("Custom") == null)
                System.out.println("Null value mapped for key: Custom"); // protection for suppressing the warning
            else {
                HashMap<String, Object> handlesCustomNumberMap = (HashMap<String, Object>) handlesQuantityCustomMap.get("Custom");
                for (String key : handlesCustomNumberMap.keySet())
                    System.out.println("\t\t" + key + " : " + handlesCustomNumberMap.get(key));
            }
        }


        System.out.println("----------");

    }
}

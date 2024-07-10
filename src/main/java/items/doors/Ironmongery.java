package items.doors;

import enums.YesNoOptions;

public class Ironmongery {
    private String hinges;
    private Boolean doubleHinges;  // if the per leaf option selected then this is a double
    // could do that these options only appear if the double leaf size option is checked
    private YesNoOptions latch; // Y, N, blank
    private YesNoOptions lock; // true = Y, false = Y[5]
    private YesNoOptions handle; // true = Y, null = Blank
    private String additionalNotes;

    /**
     * default constructor sets values to null as the gui will determine the values
     * initially each door panel will have a space to be filled out
     */
    public Ironmongery() {
        hinges = null;
        doubleHinges = null;
        latch = null;
        lock = null;
        handle = null;
        additionalNotes = null;
    }
}

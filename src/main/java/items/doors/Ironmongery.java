package items.doors;

import enums.YesNoOptions;

public class Ironmongery {
    private String hinges;
    private String doubleHinges;
    private YesNoOptions latch; // Y, N, blank
    private YesNoOptions lock; // true = Y, false = Y[5]
    private YesNoOptions handle; // true = Y, null = Blank

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
    }
}

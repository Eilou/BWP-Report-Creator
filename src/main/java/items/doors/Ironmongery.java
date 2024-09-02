package items.doors;

import java.io.Serializable;

public class Ironmongery implements Serializable {
    private Door door; // the door this iron mongery is linked to
    private String hinges;
    private Boolean doubleHinges;  // if the per leaf option selected then this is a double
    // could do that these options only appear if the double leaf size option is checked
    private String latch; // Y, N, blank
    private String lock; // true = Y, false = Y[5]
    private String handle; // true = Y, null = Blank

    /**
     * default constructor sets values to null as the gui will determine the values
     * initially each door panel will have a space to be filled out
     */
    public Ironmongery(Door door) {
        this.door = door;
        hinges = "";
        doubleHinges = false;
        latch = "";
        lock = "";
        handle = "";
    }

    ////////////////////////////////////
    // getters and setters
    ////////////////////////////////////

    public String getHinges() {
        return hinges;
    }

    public void setHinges(String hinges) {
        this.hinges = hinges;
        System.out.println("Door " + door.getCount() + ": set Hinges to: " + hinges);
    }

    public Boolean getDoubleHinges() {
        return doubleHinges;
    }

    // to do checkbox
    public void setDoubleHinges(Boolean doubleHinges) {
        this.doubleHinges = doubleHinges;
    }

    public String getLatch() {
        return latch;
    }

    public void setLatch(String latch) {
        this.latch = latch;
        System.out.println("Door " + door.getCount() + ": set Latch to: " + latch);
    }

    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock;
        System.out.println("Door " + door.getCount() + ": set Lock to: " + lock);
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
        System.out.println("Door " + door.getCount() + ": set Handle to: " + handle);
    }

}

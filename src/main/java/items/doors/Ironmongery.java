package items.doors;

import com.sun.jdi.connect.Connector;
import enums.YesNoOptions;

public class Ironmongery {
    private Door door; // the door this iron mongery is linked to
    private String hinges;
    private Boolean doubleHinges;  // if the per leaf option selected then this is a double
    // could do that these options only appear if the double leaf size option is checked
    private YesNoOptions latch; // Y, N, blank
    private YesNoOptions lock; // true = Y, false = Y[5]
    private YesNoOptions handle; // true = Y, null = Blank

    /**
     * default constructor sets values to null as the gui will determine the values
     * initially each door panel will have a space to be filled out
     */
    public Ironmongery(Door door) {
        this.door = door;
        hinges = "";
        doubleHinges = false;
        latch = YesNoOptions.BLANK;
        lock = YesNoOptions.BLANK;
        handle = YesNoOptions.BLANK;
    }

    ////////////////////////////////////
    // getters and setters
    // in some setters there is one with an object parameter, this is due to how I have multiple types of combo box
    ////////////////////////////////////

    public String getHinges() {
        return hinges;
    }

    public void setHinges(Object hinges) { setHinges(String.valueOf(hinges));}
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

    public YesNoOptions getLatch() {
        return latch;
    }

    public void setLatch(Object latch) { setLatch(YesNoOptions.convert(String.valueOf(latch)));}
    public void setLatch(YesNoOptions latch) {
        this.latch = latch;
        System.out.println("Door " + door.getCount() + ": set Latch to: " + latch);
    }

    public YesNoOptions getLock() {
        return lock;
    }

    public void setLock(Object lock) { setLock(YesNoOptions.convert(String.valueOf(lock)));}
    public void setLock(YesNoOptions lock) {
        this.lock = lock;
        System.out.println("Door " + door.getCount() + ": set Lock to: " + lock);
    }

    public YesNoOptions getHandle() {
        return handle;
    }

    public void setHandle(Object handle) { setHandle(YesNoOptions.convert(String.valueOf(handle)));}
    public void setHandle(YesNoOptions handle) {
        this.handle = handle;
        System.out.println("Door " + door.getCount() + ": set Handle to: " + handle);
    }

}

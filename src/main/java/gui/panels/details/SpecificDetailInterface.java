package gui.panels.details;

/**
 * Ensures each different report detail data panel has the same methods which will end up doing
 * different things
 */
public interface SpecificDetailInterface {
    public void setup();
    public int getCount();
    public void setCount(int newCount);
}

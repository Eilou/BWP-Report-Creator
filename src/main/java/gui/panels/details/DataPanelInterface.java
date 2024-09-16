package gui.panels.details;

/**
 * Ensures each different report detail data panel has the same methods which will end up doing
 * different things
 */
public interface DataPanelInterface {
    void setup();
    void attachHandlers();
    int getCount();
    void setCount(int newCount);
}

package enums;

public enum ReportState {
    DOOR,
    WINDOW,
    PARTO,
    // wants stuff from window to load into this
    FIRE_STRATEGY;

    @Override
    public String toString() {
        return super.toString().charAt(0) + super.toString().substring(1).toLowerCase();
    }

}

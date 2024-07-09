package enums;

/**
 * Created an enum to hold the different variations of yes and no used in the original
 * spreadsheet, this way I don't have to constantly check which boolean value corresponds to
 * which actual value
 */
public enum YesNoOptions {
    YES,
    Y,
    Y_2,
    Y_4,
    Y_5,
    NO,
    NO_EXCLAIM,
    N,
    BLANK;

    /**
     * Controls how the enum will be printed as a string, will come in particularly handy for
     * drop down boxes
     * @return a formatted String value of the enums
     */
    @Override
    public String toString() {
        return switch (this) {
            case YES, Y, NO, N -> super.toString();
            case Y_2 -> "Y[2]";
            case Y_4 -> "Y[4]";
            case Y_5 -> "Y[5]";
            case NO_EXCLAIM -> "NO!";
            case BLANK -> " ";
        };
    }
}

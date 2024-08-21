package items.doors;

import enums.YesNoOptions;
import items.Item;

import java.awt.*;

public class Door extends Item {

    private String floor;
    private String room;
    private String wallConstruction;
    private String doorType;
    private String internalExternal; // true = internal, false = external (not boolean as might need to be blank)
    // this might require a reference to the key (numbered 1)
    private YesNoOptions partMThreshold; // true = yes, false = no
    private String fireRating;
    private YesNoOptions glazed; // yes or no

    private String leafType;
    private Dimension leafSize;
    private String leafNumber; // single, double, triple, quad

    private int clearOpening;
    private YesNoOptions entranceLevel;
    private YesNoOptions partMCompliant; // YES or NO!... when null that means to leave blank
    // null is different to NO!
    private YesNoOptions additionalPlyLining; // yes or no
    private Dimension structuralOpening;
    private String structuralOpeningDetails;
    private String frameDetail;
    private String sillDetail;
    private String architraveType;
    private Ironmongery ironmongery; // all parts to this group of fields will be encapsulated in this object
    private String additionalNotes;

    /**
     * default constructor sets values to null as the gui will determine the values
     * initially each door panel will have a space to be filled out
     */
    public Door(int count) {
        super(count);

        floor = null;
        room = null;
        wallConstruction = null;
        doorType = null;
        internalExternal = null;
        partMThreshold = null;
        fireRating = null;
        glazed = null;

        leafType = null;
        leafSize = null;
        leafNumber = null;

        clearOpening = 0;
        entranceLevel = null;
        partMCompliant = null;
        additionalPlyLining = null;
        structuralOpening = null;
        structuralOpeningDetails = null;
        frameDetail = null;
        sillDetail = null;
        architraveType = null;
        ironmongery = new Ironmongery();
        additionalNotes = null;
    }

    @Override
    public String toString() {
        return "Door{" +
                "floor='" + floor + '\'' +
                ", room='" + room + '\'' +
                ", wallConstruction='" + wallConstruction + '\'' +
                ", doorType='" + doorType + '\'' +
                ", internalExternal='" + internalExternal + '\'' +
                ", partMThreshold=" + partMThreshold +
                ", fireRating='" + fireRating + '\'' +
                ", glazed=" + glazed +
                ", leafType='" + leafType + '\'' +
                ", leafSize=" + leafSize +
                ", leafNumber='" + leafNumber + '\'' +
                ", clearOpening=" + clearOpening +
                ", entranceLevel=" + entranceLevel +
                ", partMCompliant=" + partMCompliant +
                ", additionalPlyLining=" + additionalPlyLining +
                ", structuralOpening=" + structuralOpening +
                ", structuralOpeningDetails='" + structuralOpeningDetails + '\'' +
                ", frameDetail='" + frameDetail + '\'' +
                ", sillDetail='" + sillDetail + '\'' +
                ", architraveType='" + architraveType + '\'' +
                ", ironmongery=" + ironmongery +
                ", additionalNotes='" + additionalNotes + '\'' +
                "} " + super.toString();
    }

    ////////////////////////////////////
    // getters and setters
    ////////////////////////////////////

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setCount(int newCount) {
        count = newCount;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(Object floor) { setFloor(String.valueOf(floor));}
    public void setFloor(String floor) {
        this.floor = floor;
        System.out.println("Door " + count + ": set Floor:" + floor);
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getWallConstruction() {
        return wallConstruction;
    }

    public void setWallConstruction(String wallConstruction) {
        this.wallConstruction = wallConstruction;
    }

    public String getDoorType() {
        return doorType;
    }

    public void setDoorType(String doorType) {
        this.doorType = doorType;
    }

    public String getInternalExternal() {
        return internalExternal;
    }

    public void setInternalExternal(String internalExternal) {
        this.internalExternal = internalExternal;
    }

    public YesNoOptions getPartMThreshold() {
        return partMThreshold;
    }

    public void setPartMThreshold(YesNoOptions partMThreshold) {
        this.partMThreshold = partMThreshold;
    }

    public String getFireRating() {
        return fireRating;
    }

    public void setFireRating(String fireRating) {
        this.fireRating = fireRating;
    }

    public YesNoOptions getGlazed() {
        return glazed;
    }

    public void setGlazed(YesNoOptions glazed) {
        this.glazed = glazed;
    }

    public String getLeafType() {
        return leafType;
    }

    public void setLeafType(String leafType) {
        this.leafType = leafType;
    }

    public Dimension getLeafSize() {
        return leafSize;
    }

    public void setLeafSize(Dimension leafSize) {
        this.leafSize = leafSize;
    }

    public String getLeafNumber() {
        return leafNumber;
    }

    public void setLeafNumber(String leafNumber) {
        this.leafNumber = leafNumber;
    }

    public int getClearOpening() {
        return clearOpening;
    }

    public void setClearOpening(int clearOpening) {
        this.clearOpening = clearOpening;
    }

    public YesNoOptions getEntranceLevel() {
        return entranceLevel;
    }

    public void setEntranceLevel(YesNoOptions entranceLevel) {
        this.entranceLevel = entranceLevel;
    }

    public YesNoOptions getPartMCompliant() {
        return partMCompliant;
    }

    public void setPartMCompliant(YesNoOptions partMCompliant) {
        this.partMCompliant = partMCompliant;
    }

    public YesNoOptions getAdditionalPlyLining() {
        return additionalPlyLining;
    }

    public void setAdditionalPlyLining(YesNoOptions additionalPlyLining) {
        this.additionalPlyLining = additionalPlyLining;
    }

    public Dimension getStructuralOpening() {
        return structuralOpening;
    }

    public void setStructuralOpening(Dimension structuralOpening) {
        this.structuralOpening = structuralOpening;
    }

    public String getStructuralOpeningDetails() {
        return structuralOpeningDetails;
    }

    public void setStructuralOpeningDetails(String structuralOpeningDetails) {
        this.structuralOpeningDetails = structuralOpeningDetails;
    }

    public String getFrameDetail() {
        return frameDetail;
    }

    public void setFrameDetail(String frameDetail) {
        this.frameDetail = frameDetail;
    }

    public String getSillDetail() {
        return sillDetail;
    }

    public void setSillDetail(String sillDetail) {
        this.sillDetail = sillDetail;
    }

    public String getArchitraveType() {
        return architraveType;
    }

    public void setArchitraveType(String architraveType) {
        this.architraveType = architraveType;
    }

    public Ironmongery getIronmongery() {
        return ironmongery;
    }

    public void setIronmongery(Ironmongery ironmongery) {
        this.ironmongery = ironmongery;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
        System.out.println("Door " + count + ": set Additional Notes to: " + additionalNotes);
    }
}

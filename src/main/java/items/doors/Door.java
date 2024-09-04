package items.doors;

import items.Item;

import java.awt.*;
import java.io.Serializable;

public class Door extends Item implements Serializable {

    private String floor;
    private String room;
    private String wallConstruction;
    private String doorType;
    private String internalExternal; // true = internal, false = external (not boolean as might need to be blank)
    // this might require a reference to the key (numbered 1)
    private String partMThreshold; // true = yes, false = no
    private String fireRating;
    private String glazed; // yes or no

    private String leafType;
    private String leafWidth;
    private String leafHeight;
    private String leafNumber; // single, double, triple, quad

    private int clearOpening;
    private String entranceLevel;
    private String partMCompliant; // YES or NO!... when null that means to leave blank
    // null is different to NO!
    private String additionalPlyLining; // yes or no
    private String structuralOpeningWidth;
    private String structuralOpeningHeight;
    private String structuralOpeningDetails;
    private String frameDetails;
    private String sillDetails;
    private String architraveType;
    private Ironmongery ironmongery; // all parts to this group of fields will be encapsulated in this object
    private String additionalNotes;

    /**
     * default constructor sets values to null as the gui will determine the values
     * initially each door panel will have a space to be filled out
     */
    public Door(int count) {
        super(count);

        floor = "";
        room = "";
        wallConstruction = "";
        doorType = "";
        internalExternal = "";
        partMThreshold = "";
        fireRating = "";
        glazed = "";

        leafType = "";
        leafWidth = "";
        leafHeight = "";
        leafNumber = "";

        clearOpening = -1;
        entranceLevel = "";
        partMCompliant = "";
        additionalPlyLining = "";
        structuralOpeningWidth = "";
        structuralOpeningHeight = "";
        structuralOpeningDetails = "";
        frameDetails = "";
        sillDetails = "";
        architraveType = "";
        ironmongery = new Ironmongery(this); // passing in a door so can go back and forth if needed (eg to get the count)
        additionalNotes = "";
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
                ", leafWidth=" + leafWidth +
                ", leafHeight=" + leafHeight +
                ", leafNumber='" + leafNumber + '\'' +
                ", clearOpening=" + clearOpening +
                ", entranceLevel=" + entranceLevel +
                ", partMCompliant=" + partMCompliant +
                ", additionalPlyLining=" + additionalPlyLining +
                ", structuralOpeningWidth=" + structuralOpeningWidth +
                ", structuralOpeningHeight=" + structuralOpeningHeight +
                ", structuralOpeningDetails='" + structuralOpeningDetails + '\'' +
                ", frameDetail='" + frameDetails + '\'' +
                ", sillDetail='" + sillDetails + '\'' +
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

    public void setFloor(String floor) {
        this.floor = floor;
        System.out.println("Door " + count + ": set Floor: " + floor);
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
        System.out.println("Door " + count + ": set Room: " + room);
    }

    public String getWallConstruction() {
        return wallConstruction;
    }

    public void setWallConstruction(String wallConstruction) {
        this.wallConstruction = wallConstruction;
        System.out.println("Door " + count + ": set Wall Construction: " + wallConstruction);
    }

    public String getDoorType() {
        return doorType;
    }

    public void setDoorType(String doorType) {
        this.doorType = doorType;
        System.out.println("Door " + count + ": set Door Type: " + doorType);
    }

    public String getInternalExternal() {
        return internalExternal;
    }

    public void setInternalExternal(String internalExternal) {
        this.internalExternal = internalExternal;
        System.out.println("Door " + count + ": set Internal External: " + internalExternal);
    }

    public String getPartMThreshold() {
        return partMThreshold;
    }

    public void setPartMThreshold(String partMThreshold) {
        this.partMThreshold = partMThreshold;
        System.out.println("Door " + count + ": set Part M Threshold: " + partMThreshold);
    }

    public String getFireRating() {
        return fireRating;
    }

    public void setFireRating(String fireRating) {
        this.fireRating = fireRating;
        System.out.println("Door " + count + ": set Fire Rating: " + fireRating);
    }

    public String getGlazed() {
        return glazed;
    }

    public void setGlazed(String glazed) {
        this.glazed = glazed;
        System.out.println("Door " + count + ": set Glazed: " + glazed);
    }

    public String getLeafType() {
        return leafType;
    }

    //todo
    public void setLeafType(String leafType) {
        this.leafType = leafType;
        System.out.println("Door " + count + ": set Leaf Type: " + leafType);
    }

    public String getLeafWidth() {
        return leafWidth;
    }

    //todo
    public void setLeafWidth(String leafWidth) {
        this.leafWidth = leafWidth;
        System.out.println("Door " + count + ": set Leaf Width: " + leafWidth);
    }

    public String getLeafHeight() {
        return leafHeight;
    }

    public void setLeafHeight(String leafHeight) {
        this.leafHeight = leafHeight;
        System.out.println("Door " + count + ": set Leaf Height: " + leafHeight);

    }

    public String getLeafNumber() {
        return leafNumber;
    }

    //todo
    public void setLeafNumber(String leafNumber) {
        this.leafNumber = leafNumber;
        System.out.println("Door " + count + ": set Leaf Number: " + leafNumber);
    }

    public int getClearOpening() {
        return clearOpening;
    }

    public void setClearOpening(int clearOpening) {
        this.clearOpening = clearOpening;
        System.out.println("Door " + count + ": set Clear Opening: " + clearOpening);
    }

    public String getEntranceLevel() {
        return entranceLevel;
    }

    public void setEntranceLevel(String entranceLevel) {
        this.entranceLevel = entranceLevel;
        System.out.println("Door " + count + ": set Entrance Level: " + entranceLevel);
    }

    public String getPartMCompliant() {
        return partMCompliant;
    }

    public void setPartMCompliant(String partMCompliant) {
        this.partMCompliant = partMCompliant;
        System.out.println("Door " + count + ": set Part M Compliant: " + partMCompliant);
    }

    public String getAdditionalPlyLining() {
        return additionalPlyLining;
    }

    public void setAdditionalPlyLining(String additionalPlyLining) {
        this.additionalPlyLining = additionalPlyLining;
        System.out.println("Door " + count + ": set Additional Ply Lining: " + additionalPlyLining);
    }

    public String getStructuralOpeningWidth() {
        return structuralOpeningWidth;
    }

    public void setStructuralOpeningWidth(String structuralOpeningWidth) {
        this.structuralOpeningWidth = structuralOpeningWidth;
        System.out.println("Door " + count + ": set Structural Opening Width: " + structuralOpeningWidth);
    }

    public String getStructuralOpeningHeight() {
        return structuralOpeningHeight;
    }

    public void setStructuralOpeningHeight(String structuralOpeningHeight) {
        this.structuralOpeningHeight = structuralOpeningHeight;
        System.out.println("Door " + count + ": set Structural Opening Height: " + structuralOpeningHeight);
    }

    public String getStructuralOpeningDetails() {
        return structuralOpeningDetails;
    }

    public void setStructuralOpeningDetails(String structuralOpeningDetails) {
        this.structuralOpeningDetails = structuralOpeningDetails;
        System.out.println("Door " + count + ": set Structural Opening Details: " + structuralOpeningDetails);
    }

    public String getFrameDetails() {
        return frameDetails;
    }

    public void setFrameDetails(String frameDetails) {
        this.frameDetails = frameDetails;
        System.out.println("Door " + count + ": set Frame Details: " + frameDetails);
    }

    public String getSillDetails() {
        return sillDetails;
    }

    public void setSillDetails(String sillDetails) {
        this.sillDetails = sillDetails;
        System.out.println("Door " + count + ": set Sill Details: " + sillDetails);
    }

    public String getArchitraveType() {
        return architraveType;
    }

    public void setArchitraveType(String architraveType) {
        this.architraveType = architraveType;
        System.out.println("Door " + count + ": set Architrave Type: " + architraveType);
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

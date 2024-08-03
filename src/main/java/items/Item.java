package items;

/**
 * Ensures every item has a count and that count will be retrievable
 */
public abstract class Item {
    protected int count;
    public abstract int getCount();

    public Item(int count) {
        this.count = count;
    }
}

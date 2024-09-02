package items;

import java.io.Serializable;

/**
 * Ensures every item has a count and that count will be retrievable
 */
public abstract class Item implements Serializable {
    protected int count;
    public abstract int getCount();
    public abstract void setCount(int newCount);

    public Item(int count) {
        this.count = count;
    }
}

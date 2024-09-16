package items;

import java.io.Serial;
import java.io.Serializable;

/**
 * Ensures every item has a count and that count will be retrievable
 */
public abstract class Item implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    protected int count; // used for location in app
    protected String name; // used for presenting outwardly
    public abstract int getCount();
    public abstract void setCount(int newCount);
    public abstract String getName();
    public abstract void setName(String name);

    public Item(int count) {
        this.count = count;
    }
}

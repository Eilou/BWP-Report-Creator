package gui.handlers;

/**
 * Used to call the setter method references passed to event handlers
 * @param <T> type for the value to be set to
 */
@FunctionalInterface
public interface Setter<T> {
    void apply(T value);
}


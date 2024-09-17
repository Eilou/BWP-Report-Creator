package summarising;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class to ensure you can build the correct summary each time
 */
public abstract class SummaryBuilder {
    public abstract HashMap<String, Object> generateSummary();

    /**
     * Private method to abstract the generate summary method()
     * Checks first if there is an instance of a key in the hashmap, if so it increments it to a certain value, else it
     * just adds the value onto the existing count
     *
     * @param hashMap the hashmap to check (is type Map so can be used on TreeMaps or HashMaps
     * @param key     the key to look at
     * @param value   the value to set or increment by
     */
    public void checkAbsentOrIncrement(Map<String, Object> hashMap, String key, double value) {
        if (hashMap.containsKey(key))
            hashMap.put(key, (double) hashMap.get(key) + value);
        else
            hashMap.putIfAbsent(key, value);
    }

}

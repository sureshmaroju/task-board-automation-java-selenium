package utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Shared context for scenario data
 * Used with PicoContainer for dependency injection
 */
public class ScenarioContext {
    private Map<String, Object> data = new HashMap<>();

    public void set(String key, Object value) {
        data.put(key, value);
    }

    public <T> T get(String key, Class<T> type) {
        Object value = data.get(key);
        if (value == null) {
            return null;
        }
        return type.cast(value);
    }

    public void clear() {
        data.clear();
    }

    public boolean containsKey(String key) {
        return data.containsKey(key);
    }

    public Object get(String key) {
        return data.get(key);
    }
}

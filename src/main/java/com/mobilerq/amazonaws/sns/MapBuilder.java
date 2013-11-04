package com.mobilerq.amazonaws.sns;

import java.util.HashMap;
import java.util.Map;


/**
 * A very basic map builder to get the party started. <a href="https://code.google.com/p/guava-libraries/>Guava</a> has way better builders.
 * <p>Usage:
 * <pre>
 * {@code
 * Map<String,String> map = new MapBuilder<String,String>().put("key1", "value1").put("key2", "value2").build();
 * }
 * </pre>
 *
 * @param <K> The key type.
 * @param <V> The value type.
 */
public class MapBuilder<K, V> {

    private Map<K, V> map;

    public MapBuilder() {
        this.map = new HashMap<K, V>();
    }

    public MapBuilder(Map<K, V> map) {
        this.map = map;
    }

    public MapBuilder<K, V> put(K key, V value) {
        map.put(key, value);
        return this;
    }

    public MapBuilder<K, V> putAll(Map<? extends K, ? extends V> map) {
        this.map.putAll(map);
        return this;
    }

    public MapBuilder<K, V> putAll(Iterable<Map.Entry<? extends K, ? extends V>> iterable) {
        for (Map.Entry<? extends K, ? extends V> entry : iterable) {
            map.put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public Map<K, V> build() {
        return map;
    }
}

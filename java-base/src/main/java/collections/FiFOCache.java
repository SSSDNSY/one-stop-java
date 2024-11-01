package collections;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author pengzh
 * @since 2020-06-30
 */
public class FiFOCache<K, V> extends LinkedHashMap<K, V> {
    private int cacheSize;

    public FiFOCache(int cacheSize) {
        this.cacheSize = cacheSize;
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
        return size() > cacheSize;
    }

    @Override
    public String toString() {
        for (Map.Entry e : entrySet()) {
            System.out.print(e.getKey() + ":" + e.getValue() + " ");
        }
        return null;
    }

    public static void main(String[] args) {
        final FiFOCache<Integer, String> cache = new FiFOCache<>(3);
        cache.put(1, "A");
        cache.put(2, "AA");
        cache.put(3, "AAA");
        System.out.println(cache);
        cache.put(4, "AAA");
        cache.put(5, "AAA");
        System.out.println(cache);
    }

}

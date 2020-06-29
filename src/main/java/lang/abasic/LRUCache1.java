package lang.abasic;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author pengzh
 * @since 2020-06-29
 */
public class LRUCache1<K, V> extends LinkedHashMap<K, V> {
    private int size;

    public LRUCache1(int size) {
        super(size, 0.75F, true);
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
        return size() > size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<K, V> entry : entrySet()) {
            System.out.print(String.format(" %S:%S ", entry.getKey(), entry.getValue()));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        LRUCache1<Integer,Integer> cache = new LRUCache1(3);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        System.out.println(cache.toString());
        cache.get(1);//231
        System.out.println(cache.toString());
        cache.put(4,4);//314
        System.out.println(cache.toString());



    }
}

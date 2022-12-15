package middleware.cache.local.interfaces;

import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @class middleware.cache.local.interfaces.IReadWriteCache
 * @desc
 * @since 2020-10-20
 */
public interface IReadWriteCache {
    void refresh();

    Object get(String key) throws Exception;

    Object put(String key, Object value) throws Exception;

    boolean containsKey(String key);

    boolean isEmpty();

    Set<String> keySet();

    Object remove(String key);

    int size() throws Exception;

    LinkedHashMap<Long, Integer> getRefreshHistory();

    String getName();

    void setName(String name);
}

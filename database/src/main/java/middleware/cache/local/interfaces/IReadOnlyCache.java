package middleware.cache.local.interfaces;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @class middleware.cache.local.interfaces.IReadOnlyCache
 * @desc
 * @since 2020-10-20
 */
public interface IReadOnlyCache {
    int size();

    Set<String> keySet();

    void refresh() throws Exception;

    Object get(String key) throws Exception;

    LinkedHashMap<Long, Integer> getRefreshHistory();

    Map<String, Object> loadData() throws Exception;

    String getClassName();

    void setClassName(String key);
}

package middleware.cache.local;

import middleware.cache.local.interfaces.IReadWriteCache;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @class middleware.cache.local.ReadWriteCache
 * @desc
 * @since 2020-10-20
 */
public class ReadWriteCache implements IReadWriteCache {
    private static final Logger LOG = Logger.getLogger(ReadWriteCache.class);
    private LinkedHashMap<Long, Integer> refreshHistory = new LinkedHashMap();
    private ConcurrentLRUMap<String, Object> cache;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReadWriteCache(int maxSize) {
        this.cache = new ConcurrentLRUMap(maxSize);
    }

    public void refresh() {
        LOG.info("读写缓存刷新! name: " + this.getName());
        this.updateRefreshHistory();
        this.cache.clear();
    }

    public Object get(String key) throws Exception {
        return this.cache.get(key);
    }

    public Object put(String key, Object value) throws Exception {
        return this.cache.put(key, value);
    }

    public int size() throws Exception {
        return this.cache.size();
    }

    public boolean containsKey(String key) {
        return this.cache.containsKey(key);
    }

    public boolean isEmpty() {
        return this.cache.isEmpty();
    }

    public Set<String> keySet() {
        return this.cache.keySet();
    }

    public Object remove(String key) {
        return this.cache.remove(key);
    }

    private final void updateRefreshHistory() {
        this.refreshHistory.put(System.currentTimeMillis(), this.cache.size());
        if (this.refreshHistory.size() > 10) {
            Iterator<Long> i = this.refreshHistory.keySet().iterator();
            i.next();
            i.remove();
        }

    }

    public LinkedHashMap<Long, Integer> getRefreshHistory() {
        return this.refreshHistory;
    }
}

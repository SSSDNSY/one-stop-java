package cache.local;

import cache.local.interfaces.IReadOnlyCache;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @class AbstractReadOnlyCache
 * @desc 制度缓存
 * @since 2020-10-20
 */
public abstract class AbstractReadOnlyCache implements IReadOnlyCache {
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(AbstractReadOnlyCache.class);
    private Map<String, Object> cache;
    private String className;
    private LinkedHashMap<Long, Integer> refreshHistory = new LinkedHashMap<>();

    public AbstractReadOnlyCache() {

    }

    public AbstractReadOnlyCache(Map<String, Object> map) {
        this.cache = map;
        this.updateRefreshHistory();
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public synchronized void refresh() throws Exception {
        LOG.info("只读缓存刷新！className" + this.getClassName());
        Map<String, Object> newCache = this.loadData();
        Map<String, Object> oladCache = this.cache;
        this.cache = newCache;
        this.updateRefreshHistory();
        if (null != oladCache) {
            oladCache.clear();
            oladCache = null;
        }
    }

    public Object get(String key) {
        return this.cache.get(key);
    }

    public int size() {
        return this.cache.size();
    }

    public Set<String> keySet() {
        return this.cache.keySet();
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

    public abstract Map<String, Object> loadData() throws Exception;
}

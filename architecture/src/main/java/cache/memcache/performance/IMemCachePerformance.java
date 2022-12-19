package cache.memcache.performance;

/**
 * @class middleware.cache.memcache.performance.impl.IMemCachePerformance
 * @desc
 * @since 2020-10-20
 */
public interface IMemCachePerformance {
    void report(String cmd, String key, long cCost, long eCost);
}

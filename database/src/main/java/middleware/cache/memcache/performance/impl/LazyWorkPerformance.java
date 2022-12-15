package middleware.cache.memcache.performance.impl;

import middleware.cache.memcache.performance.IMemCachePerformance;

/**
 * @class middleware.cache.memcache.performance.impl.MemCachePerformanceImpl
 * @desc 懒加载性能监控器
 * @since 2020-10-20
 */
public class LazyWorkPerformance implements IMemCachePerformance {
    public LazyWorkPerformance() {
    }

    public void report(String cmd, String key, long cCost, long eCost) {
    }
}

/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sssdnsy.cache.memcache.performance.impl;

import sssdnsy.cache.memcache.performance.IMemCachePerformance;

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

/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sssdnsy.cache.memcache.performance;

/**
 * @class middleware.cache.memcache.performance.impl.IMemCachePerformance
 * @desc
 * @since 2020-10-20
 */
public interface IMemCachePerformance {
    void report(String cmd, String key, long cCost, long eCost);
}

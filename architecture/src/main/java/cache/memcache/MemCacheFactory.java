/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cache.memcache;

import cache.memcache.client.TextClient;
import cache.memcache.interfaces.IMemCache;
import cache.memcache.performance.IMemCachePerformance;
import cache.memcache.performance.impl.LazyWorkPerformance;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @class middleware.cache.memcache.MemCacheFactory
 * @desc
 * @since 2020-10-20
 */
public final class MemCacheFactory {
    private static final Logger log = Logger.getLogger(MemCacheFactory.class);
    private static final Map<String, IMemCache> caches = new HashMap();
    public static IMemCachePerformance performance = new LazyWorkPerformance();
    public static final String IMPOSSIBLE_VALUE = "<-- IMPOSSIBLE_VALUE -->";
    public static final String SESSION_CACHE = "session_cache";
    public static final String PRIV_CACHE = "priv_cache";
    public static final String CODECODE_CACHE = "codecode_cache";
    public static final String STATICPARAM_CACHE = "staticparam_cache";
    public static final String BCC_CACHE = "bcc_cache";
    public static final String DMB_CACHE = "dmb_cache";

    private MemCacheFactory() {
    }

    public static IMemCache getCache(String cacheName) {
        IMemCache cache = (IMemCache) caches.get(cacheName);
        if (null == cache) {
            throw new IllegalArgumentException(cacheName + "连接池中没有可用的连接，请确认缓存地址是否配置正确、缓存是否开启！");
        } else {
            return cache;
        }
    }

    public static void init() throws Exception {
    }

    static {
        MemCacheXml xml = new MemCacheXml();
        xml.load();
        String dataCenter = MemCacheXml.getDefaultDataCenter();
        String serverName;
//        if (StringUtils.isBlank(dataCenter)) {
//            serverName = System.getProperty("wade.server.name");
//            if (StringUtils.isBlank(serverName)) {
//                throw new NullPointerException("生产模式下必须配置wade.server.name启动参数!");
//            }
//            Iterator iterator = MemCacheXml.getMapping().keySet().iterator();
//            while (iterator.hasNext()) {
//                String key = (String) iterator.next();
//                String prefix = StringUtils.stripEnd(key, "*");
//                if (serverName.startsWith(prefix)) {
//                    dataCenter = (String) MemCacheXml.getMapping().get(key);
//                    break;
//                }
//            }
//        }
//
//        serverName = MemCacheXml.getPerformanceClazz();
//
//        try {
//            Class<?> clazz = Class.forName(serverName);
//            performance = (IMemCachePerformance) clazz.newInstance();
//        } catch (Exception var16) {
//            var16.printStackTrace();
//        }

        Map<String, Map<String, MemCacheCluster>> centers = MemCacheXml.getDataCenters();
        Map<String, MemCacheCluster> clusters = (Map) centers.get(dataCenter);
        if (null == clusters) {
            throw new NullPointerException("根据中心编码:" + dataCenter + ",无法获取中心配置数据!");
        } else {

            boolean flag = true;
            String clusterName = null;
            MemCacheAddress[] address = null;
            int poolSize = 0;
            int heartbeatSecond = 0;
            MemCacheCluster cluster = null;

            Iterator clusIterator = clusters.keySet().iterator();
            do {

                clusterName = (String) clusIterator.next();
                cluster = (MemCacheCluster) clusters.get(clusterName);
                address = (MemCacheAddress[]) cluster.getAddresses().toArray(new MemCacheAddress[0]);
                poolSize = cluster.getPoolSize();
                heartbeatSecond = cluster.getHeartBeatSecond();
                IMemCache cache = new TextClient(address, poolSize, heartbeatSecond, cluster.isUseNio());
                caches.put(clusterName, cache);


                log.info("------ memcached连接池初始化成功! ------");
                log.info("分组组名: " + clusterName);
                log.info("地址集合:");
                MemCacheAddress[] arr$ = address;
                int len$ = address.length;

                for (int i = 0; i < len$; ++i) {
                    MemCacheAddress addr = arr$[i];
                    if (null != addr.getFollower()) {
                        log.info("  master " + addr.getLeader() + " -> slave " + addr.getFollower());
                    } else {
                        log.info("  " + addr.getLeader());
                    }
                }

                log.info("连接数量: " + poolSize);
                log.info("心跳周期: " + heartbeatSecond);
                log.info("IO模式: " + (cluster.isUseNio() ? "NIO" : "BIO") + "\n");
            } while (clusIterator.hasNext());
        }
    }
}

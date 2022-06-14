package middleware.cache.local;

import middleware.cache.local.CacheXml.ReadOnlyCacheItem;
import middleware.cache.local.CacheXml.ReadWriteCacheItem;
import middleware.cache.local.interfaces.IReadOnlyCache;
import middleware.cache.local.interfaces.IReadWriteCache;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;
import java.util.*;

/**
 * @class middleware.cache.local.CacheFactory
 * @desc
 * @since 2020-10-20
 */
public class CacheFactory {
    private static final transient Logger log = Logger.getLogger(CacheFactory.class);
    private static Map<Class, IReadOnlyCache> ROCACHES = new HashMap();
    private static Set<String> ROCACHE_CLAZZNAME = new HashSet();
    private static Set<String> ROCACHE_NEEDINIT = new HashSet();
    private static Map<String, IReadWriteCache> RWCACHES = new HashMap();
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    private static Scheduler scheduler;
    private static List<ReadOnlyCacheItem> readonlyCacheItems;
    private static List<ReadWriteCacheItem> readwriteCacheItems;

    public CacheFactory() {

    }

    static boolean isNotUsed(Class clazz) {
        return !ROCACHES.containsKey(clazz);
    }

    public static IReadOnlyCache getReadOnlyCache(Class clazz) throws Exception {
        if (!ROCACHE_CLAZZNAME.contains(clazz.getName())) {
            log.error("缓存类在配置文件中未定义!" + clazz.getName());
            return null;
        } else {
            IReadOnlyCache cache = (IReadOnlyCache) ROCACHES.get(clazz);
            if (null == cache) {
                synchronized (clazz) {
                    if ((cache = (IReadOnlyCache) ROCACHES.get(clazz)) != null) {
                        return cache;
                    }

                    long start = System.currentTimeMillis();
                    cache = (IReadOnlyCache) clazz.newInstance();
                    cache.setClassName(clazz.getName());
                    cache.refresh();
                    ROCACHES.put(clazz, cache);
                    log.info("ReadOnlyCache:" + clazz.getName() + "刷新成功，加载数据量:" + cache.size() + "条，耗时:" + (System.currentTimeMillis() - start) + "毫秒");
                }
            }

            return cache;
        }
    }

    public static final IReadWriteCache getReadWriteCache(String cacheName) {
        IReadWriteCache cache = (IReadWriteCache) RWCACHES.get(cacheName);
        return cache;
    }


    private static final void initReadOnlyCaches(List<ReadOnlyCacheItem> items) {
        Iterator i$ = items.iterator();

        while (i$.hasNext()) {
            CacheXml.ReadOnlyCacheItem item = (CacheXml.ReadOnlyCacheItem) i$.next();
            ROCACHE_CLAZZNAME.add(item.className);
            if (item.isInitial) {
                ROCACHE_NEEDINIT.add(item.className);
            }

            try {
                Class clazz = Class.forName(item.className);
                if (null != item.cronExpr) {
                    startSchedulerIfNotStarted();
                    JobDetail jobDetail = new JobDetail("refresh_" + item.className + "_job", "CacheRefreshGroup", CacheAutoRefreshJob.class);
                    jobDetail.getJobDataMap().put("CACHE_TYPE", "READONLY_CACHE");
                    jobDetail.getJobDataMap().put("CACHE_NAME", clazz);
                    CronTrigger trigger = new CronTrigger("refresh_" + item.className + "_trigger", "d");

                    try {
                        trigger.setCronExpression(item.cronExpr);
                        scheduler.scheduleJob(jobDetail, trigger);
                    } catch (ParseException var7) {
                        log.error(var7);
                    } catch (SchedulerException var8) {
                        log.error(var8);
                    }
                }
            } catch (Exception var9) {
                log.error("ReadOnlyCache配置加载出错! " + item.className, var9);
            }
        }

    }

    private static final void initReadWriteCaches(List<ReadWriteCacheItem> items) {
        Iterator i$ = items.iterator();

        while (i$.hasNext()) {
            ReadWriteCacheItem item = (ReadWriteCacheItem) i$.next();
            IReadWriteCache cache = new ReadWriteCache(item.maxSize);
            cache.setName(item.name);
            String name = item.name;
            RWCACHES.put(name, cache);
            if (null != item.cronExpr) {
                startSchedulerIfNotStarted();
                JobDetail jobDetail = new JobDetail("refresh_" + name + "_job", "CacheRefreshGroup", CacheAutoRefreshJob.class);
                jobDetail.getJobDataMap().put("CACHE_TYPE", "READWRITE_CACHE");
                jobDetail.getJobDataMap().put("CACHE_NAME", name);
                CronTrigger trigger = new CronTrigger("refresh_" + name + "_trigger", "d");

                try {
                    trigger.setCronExpression(item.cronExpr);
                    scheduler.scheduleJob(jobDetail, trigger);
                } catch (ParseException var8) {
                    log.error(var8);
                } catch (SchedulerException var9) {
                    log.error(var9);
                }
            }
        }

    }

    public static final List<Map<String, String>> listReadOnlyCache() {
        List<Map<String, String>> rtn = new ArrayList();
        Iterator i$ = readonlyCacheItems.iterator();

        while (i$.hasNext()) {
            ReadOnlyCacheItem item = (ReadOnlyCacheItem) i$.next();
            Map<String, String> map = new HashMap();
            map.put("className", item.className);
            map.put("init", String.valueOf(item.isInitial));
            map.put("cronExpr", item.cronExpr);
            rtn.add(map);
        }

        return rtn;
    }

    public static final List<Map<String, String>> listReadWriteCache() {
        List<Map<String, String>> rtn = new ArrayList();
        Iterator i$ = readwriteCacheItems.iterator();

        while (i$.hasNext()) {
            ReadWriteCacheItem item = (ReadWriteCacheItem) i$.next();
            Map<String, String> map = new HashMap();
            map.put("name", item.name);
            map.put("maxSize", String.valueOf(item.maxSize));
            map.put("cronExpr", item.cronExpr);
            rtn.add(map);
        }

        return rtn;
    }

    public static final void init() {
        Class var0 = CacheFactory.class;
        synchronized (CacheFactory.class) {
            try {
                Iterator i$ = ROCACHE_NEEDINIT.iterator();

                while (i$.hasNext()) {
                    String clazzName = (String) i$.next();
                    long start = System.currentTimeMillis();
                    Class clazz = Class.forName(clazzName);
                    IReadOnlyCache cache = (IReadOnlyCache) clazz.newInstance();
                    cache.setClassName(clazzName);
                    cache.refresh();
                    ROCACHES.put(clazz, cache);
                    log.info("ReadOnlyCache:" + clazz.getName() + "刷新成功，加载数据量:" + cache.size() + "条，耗时:" + (System.currentTimeMillis() - start) + "毫秒");
                }
            } catch (Exception var8) {
                log.error("本地只读缓存初始化时发生错误!", var8);
            }

        }
    }

    public static final void destroy() {
        if (null != scheduler) {
            try {
                scheduler.shutdown();
            } catch (SchedulerException var1) {
                log.error("销毁quartz调度线程池失败!", var1);
            }
        }

    }

    private static final void startSchedulerIfNotStarted() {
        if (null == scheduler) {
            try {
                scheduler = schedulerFactory.getScheduler();
                scheduler.start();
            } catch (SchedulerException var1) {
                log.error("缓存定时刷新调度器初始化失败! " + var1);
            }

        }
    }

    static {
        try {
            CacheXml cacheXml = CacheXml.getInstance();
            readonlyCacheItems = cacheXml.getReadOnlyCacheItems();
            readwriteCacheItems = cacheXml.getReadWriteCacheItems();
            initReadOnlyCaches(readonlyCacheItems);
            initReadWriteCaches(readwriteCacheItems);
        } catch (Exception var1) {
            var1.printStackTrace();
        }

    }


}

package middleware.cache.local;

import middleware.cache.local.interfaces.IReadOnlyCache;
import middleware.cache.local.interfaces.IReadWriteCache;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * @class middleware.cache.local.CacheAutoRefreshJob
 * @desc 缓存自动刷新任务器
 * @since 2020-10-20
 */
public class CacheAutoRefreshJob implements Job {
    private static final transient Logger log = Logger.getLogger(CacheAutoRefreshJob.class);

    private static final String CACHE_NAME = "CACHE_NAME";
    private static final String CACHE_TYPE = "CACHE_TYPE";
    private static final String READONLY_CACHE = "READONLY_CACHE";
    private static final String READWRITE_CACHE = "READWRITE_CACHE";

    public CacheAutoRefreshJob() {

    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String isPreparead = System.getProperty("isPrepared", "");
        if (!isPreparead.startsWith("StartTime")) {
            log.info("系统为预热，本地缓存不进行自动刷新");
        } else {
            JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
            String cacheType = map.getString(CACHE_TYPE);
            if (READONLY_CACHE.equals(cacheType)) {
                Class clazz = (Class) map.get(CACHE_NAME);
                if (CacheFactory.isNotUsed(clazz)) {
                    return;
                }
                try {
                    IReadOnlyCache cache = CacheFactory.getReadOnlyCache(clazz);
                    int oldSize = cache.size();
                    if (null != cache) {
                        cache.refresh();
                        int newSize = cache.size();
                        log.info("本地只读缓存自动刷新成功! " + clazz.getName() + ",刷新前:" + oldSize + "条数据，刷新后:" + newSize + "条数据。");
                    }
                } catch (Exception var9) {
                    log.error("本地只读缓存自动刷新失败! " + clazz.getName() + var9);
                }
            } else if ("READWRITE_CACHE".equals(cacheType)) {
                String cachename = map.getString("CACHE_NAME");
                IReadWriteCache cache = CacheFactory.getReadWriteCache(cachename);
                cache.refresh();
                log.info("本地读写缓存自动刷新成功! " + cachename);
            }
        }
    }

}

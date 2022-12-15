package middleware.cache.memcache.util;

import middleware.cache.memcache.MemCacheFactory;
import middleware.cache.memcache.interfaces.IMemCache;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @class middleware.cache.memcache.util.MemcacheLock
 * @desc
 * @since 2020-10-20
 */
public final class MemcacheLock {
    private static final transient Logger log = Logger.getLogger(MemcacheLock.class);
    private static int DEF_SEC_TTL = 120;
    private static IMemCache cache = MemCacheFactory.getCache("bcc_cache");

    public static boolean lock(String key) {
        return lock(key, DEF_SEC_TTL);
    }

    public static boolean lock(String key, int ttl) {
        boolean b = cache.add(key, 0, ttl);
        log.info("[MemCacheLock] lock key [" + key + "]");
        return b;
    }

    public static boolean lockAwait(String key, int secTTL) {
        return lockAwait(key, 120, secTTL);
    }

    public static boolean lockAwait(String key, int ttl, int secTTL) {
        int ttlMs = secTTL * 1000;
        int waitMs = 0;
        try {
            do {
                boolean success = cache.add(key, 0, secTTL);
                if (success) {
                    return true;
                }
            } while (waitMs < ttlMs);
        } catch (Exception exception) {
            log.info("[MemCacheLock] lockAwait key [" + key + "]");
            return false;
        }
        return false;
    }

    public static final boolean unlock(String key) {
        boolean delete = cache.delete(key);
        log.info("[MemCacheLock] unlock key [" + key + "]");
        return delete;
    }

    public static final void unlock(String key, int ttl) {
        final long decr = cache.decr(key, ttl);
        log.info("[MemCacheLock] unlock key [" + key + "] in" + decr + " ms");
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> list = new ArrayList<>();
        int length = 100;
        CountDownLatch latch = new CountDownLatch(length);
        for (int i = 0; i < length; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final boolean test = MemcacheLock.lock("TEST");
                    System.out.println(Thread.currentThread().getName() + " 获取锁:" + test);
                    latch.countDown();
                }
            }, "thread" + i).start();
        }
        latch.await();
        final Object test1 = cache.get("TEST");
        System.out.println(test1);
    }

}

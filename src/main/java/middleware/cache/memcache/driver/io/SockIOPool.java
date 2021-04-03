package middleware.cache.memcache.driver.io;

import middleware.cache.memcache.MemCacheAddress;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @class middleware.cache.memcache.driver.io.SockIOPool
 * @desc
 * @since 2020-10-20
 */
public class SockIOPool {
    private static final Logger log = Logger.getLogger(SockIOPool.class);
    private List<SockIOBucket> buckets = new ArrayList();
    private List<SockIOBucket> deadBuckets = new ArrayList();
    private int heartbeatSecond = 5;

    public SockIOPool(MemCacheAddress[] address, int poolSize, int heartbeatSecond, boolean useNIO) {
        this.heartbeatSecond = heartbeatSecond;
        MemCacheAddress[] arr$ = address;
        int len$ = address.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            MemCacheAddress addr = arr$[i$];
            String[] masterPart = StringUtils.split(addr.getLeader(), ':');
            if (2 != masterPart.length) {
                throw new IllegalArgumentException("memcached主地址格式不正确！" + addr.getLeader());
            }

            String masterHost = masterPart[0];
            int masterPort = Integer.parseInt(masterPart[1]);
            String[] slavePart = StringUtils.split(addr.getFollower(), ':');
            if (null != slavePart && 2 != slavePart.length) {
                throw new IllegalArgumentException("memcached备地址格式不正确！" + addr.getFollower());
            }

            SockIOBucket bucket = null;

            try {
                if (null == addr.getFollower()) {
                    bucket = new SimpleSockIOBucket(masterHost, masterPort, poolSize, useNIO);
                } else {
                    String slaveHost = slavePart[0];
                    int slavePort = Integer.parseInt(slavePart[1]);
                    bucket = new HASockIOBucket(masterHost, masterPort, slaveHost, slavePort, poolSize, useNIO);
                }

                this.buckets.add(bucket);
                ((SockIOBucket) bucket).init();
            } catch (IOException var16) {
                var16.printStackTrace();
            }
        }

        SockIOPool.MaintTask task = new SockIOPool.MaintTask();
        task.setDaemon(true);
        task.start();
    }

    public ISockIO getSock(String cacheKey) {
        int hashCode = hash(cacheKey);
        int divisor = this.buckets.size();
        if (0 == divisor) {
            return null;
        } else {
            int position = hashCode % divisor;
            position = position < 0 ? -position : position;
            SockIOBucket bucket = (SockIOBucket) this.buckets.get(position);
            return bucket.borrowSockIO();
        }
    }

    private static int hash(String cacheKey) {
        int h = cacheKey.hashCode();
        return h;
    }

    private final class MaintTask extends Thread {
        private MaintTask() {
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep((long) (1000 * SockIOPool.this.heartbeatSecond));
                    this.bucketHeartbeat();
                    this.bucketReconnect();
                } catch (Exception var2) {
                    SockIOPool.log.error("memcache连接池心跳线程发生异常!", var2);
                }
            }
        }

        private void bucketHeartbeat() {
            try {
                Iterator iter = SockIOPool.this.buckets.iterator();

                while (true) {
                    while (iter.hasNext()) {
                        SockIOBucket bucket = (SockIOBucket) iter.next();
                        int preStateCode = bucket.getStateCode();
                        int curStateCode = bucket.healthCheck();
                        if (4 != curStateCode && 0 != curStateCode) {
                            if (preStateCode != curStateCode) {
                                SockIOPool.log.info("桶状态变更: " + SockIOBucket.STATES[preStateCode] + " -> " + SockIOBucket.STATES[curStateCode]);
                                bucket.close();
                                bucket.setStateCode(curStateCode);
                                bucket.init();
                            }
                        } else {
                            bucket.close();
                            iter.remove();
                            SockIOPool.this.deadBuckets.add(bucket);
                            SockIOPool.log.error("memcached桶心跳失败！" + bucket.getAddress());
                        }
                    }

                    return;
                }
            } catch (Throwable var5) {
                var5.printStackTrace();
            }
        }

        private void bucketReconnect() {
            Iterator iter = SockIOPool.this.deadBuckets.iterator();

            while (iter.hasNext()) {
                SockIOBucket bucket = (SockIOBucket) iter.next();

                try {
                    boolean success = bucket.init();
                    if (success) {
                        iter.remove();
                        SockIOPool.this.buckets.add(bucket);
                        Collections.sort(SockIOPool.this.buckets);
                        SockIOPool.log.info("-------------------------");
                        Iterator i$ = SockIOPool.this.buckets.iterator();

                        while (i$.hasNext()) {
                            SockIOBucket bkt = (SockIOBucket) i$.next();
                            SockIOPool.log.info("-- " + bkt.getAddress());
                        }

                        SockIOPool.log.info("-------------------------");
                        SockIOPool.log.info("memcached桶复活！" + bucket.getAddress());
                    }
                } catch (IOException var6) {
                    var6.printStackTrace();
                }
            }

        }
    }
}

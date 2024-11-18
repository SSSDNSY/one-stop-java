package fun.sssdnsy.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RedisLockService {

    private static final String LOCK_PREFIX = "lock:";

/*    private static final RedisScript<Long> UNLOCK_SCRIPT = new DefaultRedisScript<>(
            "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                    "  return redis.call('del', KEYS[1]) " +
                    "else " +
                    "  return 0 " +
                    "end",
            Long.class
    );  */

    private static final RedisScript<Long> UNLOCK_SCRIPT = new DefaultRedisScript<>(
            STR."""
                       if redis.call('get', KEYS[1]) == ARGV[1] then 
                          return redis.call('del', KEYS[1])   
                        else 
                          return 0 
                        end  
                    """, Long.class);


    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 尝试加锁
     *
     * @param lockKey    锁的key
     * @param requestId  锁的持有者标识符（如UUID）
     * @param expireTime 锁的过期时间（秒）
     * @return 加锁成功返回true，否则返回false
     */
    public boolean tryLock(String lockKey, String requestId, long expireTime) {
        String lockKeyWithPrefix = LOCK_PREFIX + lockKey;
        // 使用SET命令的NX和PX选项来加锁  
        Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKeyWithPrefix, requestId, expireTime, TimeUnit.SECONDS);
        return result != null && result;
    }

    /**
     * 解锁
     *
     * @param lockKey   锁的key
     * @param requestId 锁的持有者标识符（如UUID）
     * @return 解锁成功返回true，否则返回false
     */
    public boolean unlock(String lockKey, String requestId) {
        String lockKeyWithPrefix = LOCK_PREFIX + lockKey;
        // 使用Lua脚本来验证锁的持有者并解锁  
        Long result = (Long) redisTemplate.execute(UNLOCK_SCRIPT, Collections.singletonList(lockKeyWithPrefix), requestId);
        return result != null && result == 1L;
    }

    // 示例用法  
    public void demoLock() {
        String lockKey = "myLock";
        String requestId = UUID.randomUUID().toString();
        long expireTime = 10L; // 锁的过期时间为10秒  

        // 尝试加锁  
        if (tryLock(lockKey, requestId, expireTime)) {
            try {
                // 执行需要同步的代码块  
                // ...  

                // 模拟处理时间  
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } finally {
                // 释放锁  
                unlock(lockKey, requestId);
            }
        } else {
            // 处理加锁失败的情况  
            System.out.println("Lock acquisition failed");
        }
    }
}

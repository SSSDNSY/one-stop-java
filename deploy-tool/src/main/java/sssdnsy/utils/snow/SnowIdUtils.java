package sssdnsy.utils.snow;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Description ❄雪花算法
 * 1bit 因为二进制中最高位是符号位，1表示负数，0表示正数。生成的ID都是正整数，所以最高位固定为0。
 * 41bit-时间戳 精确到毫秒级，41位的长度可以使用69年。时间位还有一个很重要的作用是可以根据时间进行排序。
 * 10bit-工作机器id 10位的机器标识，10位的长度最多支持部署1024个节点。
 * 12bit-序列号 序列号即一系列的自增id，可以支持同一节点同一毫秒生成多个ID序号。
 * 12位（bit）可以表示的最大正整数是2^{12}-1 = 4095，即可以用0、1、2、3、....4094这4095个数字，来表示同一机器同一时间截（毫秒)内产生的4095个ID序号。
 * <p>
 * 说明 由于在Java中64bit的整数是long类型,所以在Java中SnowFlake算法生成的id就是long来存储的。
 * @Since 2020-10-16
 */
@Slf4j
public class SnowIdUtils {

    /**
     * 私有的 静态内部类
     */
    private static class SnowFlake {

        /**
         * 内部类对象（单例模式）
         */
        private static final SnowIdUtils.SnowFlake SNOW_FLAKE = new SnowIdUtils.SnowFlake();
        /**
         * 起始的时间戳
         */
        private final long START_TIMESTAMP = 1557489395327L;
        /**
         * 序列号占用位数
         */
        private final long SEQUENCE_BIT = 12;
        /**
         * 机器标识占用位数
         */
        private final long MACHINE_BIT = 10;
        /**
         * 时间戳位移位数
         */
        private final long TIMESTAMP_LEFT = SEQUENCE_BIT + MACHINE_BIT;
        /**
         * 最大序列号  （4095）
         */
        private final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
        /**
         * 最大机器编号 （1023）
         */
        private final long MAX_MACHINE_ID = ~(-1L << MACHINE_BIT);
        /**
         * 生成id机器标识部分
         */
        private long machineIdPart;
        /**
         * 序列号
         */
        private long sequence = 0L;
        /**
         * 上一次时间戳
         */
        private long lastStamp = -1L;

        /**
         * 构造函数初始化机器编码
         */
        private SnowFlake() {
            //模拟这里获得本机机器编码
            long localIp = 4321;
            //localIp & MAX_MACHINE_ID最大不会超过1023,在左位移12位
            machineIdPart = (localIp & MAX_MACHINE_ID) << SEQUENCE_BIT;
        }

        /**
         * 获取雪花ID
         */
        public synchronized long nextId() {
            long currentStamp = timeGen();
            //避免机器时钟回拨
            while (currentStamp < lastStamp) {
                // //服务器时钟被调整了,ID生成器停止服务.
                throw new RuntimeException(String.format("时钟已经回拨.  Refusing to generate id for %d milliseconds", lastStamp - currentStamp));
            }
            if (currentStamp == lastStamp) {
                // 每次+1
                sequence = (sequence + 1) & MAX_SEQUENCE;
                // 毫秒内序列溢出
                if (sequence == 0) {
                    // 阻塞到下一个毫秒,获得新的时间戳
                    currentStamp = getNextMill();
                }
            } else {
                //不同毫秒内，序列号置0
                sequence = 0L;
            }
            lastStamp = currentStamp;
            //时间戳部分+机器标识部分+序列号部分
            return (currentStamp - START_TIMESTAMP) << TIMESTAMP_LEFT | machineIdPart | sequence;
        }

        /**
         * 阻塞到下一个毫秒，直到获得新的时间戳
         */
        private long getNextMill() {
            long mill = timeGen();
            //
            while (mill <= lastStamp) {
                mill = timeGen();
            }
            return mill;
        }

        /**
         * 返回以毫秒为单位的当前时间
         */
        protected long timeGen() {
            return System.currentTimeMillis();
        }
    }

    /**
     * 获取long类型雪花ID
     */
    public static long uniqueLong() {
        return SnowIdUtils.SnowFlake.SNOW_FLAKE.nextId();
    }

    /**
     * 获取String类型雪花ID
     */
    public static String uniqueLonghex() {
        return String.format("%016x", uniqueLong());
    }

    /**
     * 测试
     */
    public static void main(String[] args) throws InterruptedException {
        //计时开始时间
        long start = System.currentTimeMillis();
        //让100个线程同时进行
        final CountDownLatch latch = new CountDownLatch(100);
        //判断生成的20万条记录是否有重复记录
        final Map<Long, Integer> map = new ConcurrentHashMap();
        for (int i = 0; i < 100; i++) {
            //创建100个线程
            new Thread(() -> {
                for (int s = 0; s < 2000; s++) {
                    long snowID = SnowIdUtils.uniqueLong();
                    log.info("生成雪花ID={}", snowID);
                    Integer put = map.put(snowID, 1);
                    if (put != null) {
                        throw new RuntimeException("主键重复");
                    }
                }
                latch.countDown();
            }).start();
        }


        //让上面100个线程执行结束后，在走下面输出信息
        latch.await();
        log.info("生成20万条雪花ID总用时={}", System.currentTimeMillis() - start);

        final File file = new File("E:/desktop/snowId.txt");
        try {
            final FileWriter fileWriter = new FileWriter(file);
            map.forEach((aLong, integer) -> {
                try {
                    fileWriter.write(aLong + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


package lang.jvm.vmargs;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * @Description: 串行垃圾回收器
 * vm args:
 * 串行：-XX:+UseSerialGC -XX:+PrintGCDetails -Xms16m -Xmx16m
 *
 * 并行：-XX:+UseParNewGC
 *
 *       -XX:+UseParallelGC
 *       -XX:+UseParallelOldGC
 *       -XX:MaxGCPauseMillis=100
 *
 * CMS: +XX:+UseConcMarkSweepGC
 *
 * G1:-XX:+UseG1GC
 *
 * @Author: pengzh
 * @date: 2019/11/17
 */
public class SerialGCTest {
    public static void main(String[] args) throws InterruptedException {
        List list = new ArrayList();
        while (true) {
            int time = new Random().nextInt(500);
            for (int i = 0; i < 100000; i++) {
                long timeMillis = System.currentTimeMillis();
                if (timeMillis % 2 == 0) {
                    list.clear();
                } else {
                    Properties prop = new Properties();
                    prop.put("key_" + i, "val_" + timeMillis);
                    list.add(prop);
                }
            }
            Thread.sleep(time);
        }

    }
}

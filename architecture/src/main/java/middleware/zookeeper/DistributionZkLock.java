/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package middleware.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author pengzh
 * @since 2020-08-08
 */
public class DistributionZkLock implements Runnable {
    private static Integer count = 0;

    //使用自定义的zk锁
    private ZkLock lock = new ZkLock();

    @Override
    public void run() {
        try {
            lock.getLock();
            getDistributionZkLockNum();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //创建完成后关闭zk连接
            lock.unLock();
        }
    }

    private void getDistributionZkLockNum() {
        System.out.println("当前线程：" + Thread.currentThread().getName() + "---" + "订单号：" + createOderNum());
    }

    public String createOderNum() {
        //为了测试效果，创建订单号设置慢一点
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            // TODO: handle exception
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String oderNum = format.format(new Date()) + ++count;
        return oderNum.replace("-", "");

    }

    public static void main(String[] args) {
        System.out.println("生产订单号开始");
        //模拟多个节点获取连接
        for (int i = 0; i < 10000; i++) {
            new Thread(new DistributionZkLock()).start();
        }
    }
}


class ZkLock {
    /**
     * 为了方便测试，下边的常量就不写到配置文件中
     */
    // zk连接地址
    private static final String CONNECTSTRING = "127.0.0.1:2181";
    // 创建zk连接
    protected ZkClient zkClient = new ZkClient(CONNECTSTRING);
    protected static final String PATH = "/lock";
    //信号量变量
    protected CountDownLatch countDownLatch = null;


    //是否获取锁成功，若成功：true，失败：false
    boolean tryLock() {
        try {
            zkClient.createEphemeral(PATH);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //等待锁释放
    void waitLock() {
        //当进入等待状态，开启监听
        //使用事件监听，当监听到节点不存在，即zk断开连接节点被删除，此时唤醒
        IZkDataListener listener = new IZkDataListener() {

            //当节点被删除时候，进入此方法
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                if (countDownLatch != null) {
                    //信号量-1,变为0，此时唤醒，去获取锁
                    countDownLatch.countDown();
                }
            }

            //当节点改变时候，进入此方法
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {

            }
        };
        //对PATH进行监听
        zkClient.subscribeDataChanges(PATH, listener);

        //判断节点是否存在,存在，进入等待
        if (zkClient.exists(PATH)) {
            countDownLatch = new CountDownLatch(1);
            try {
                //等待，当信号为0时候
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //当唤醒后，关闭监听
        zkClient.unsubscribeDataChanges(PATH, listener);
    }

    //获取锁实现
    public void getLock() {
        if (tryLock()) {
            System.out.println("#####获取锁成功#####");
        } else {
            waitLock();
            tryLock();
        }
    }

    //释放锁实现
    public void unLock() {
        if (zkClient != null) {
            zkClient.close();
            System.out.println("#####关闭连接，释放ZK锁#####");
        }
    }

}

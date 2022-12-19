/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: zookeeper 分布式客户端配置同步 示例
 * @Author: pengzh
 * @date: 2019/10/18
 */
public class ZooKeeperSynsDemo implements Watcher {

    static String PATH = "/username";
    static ZooKeeper zk = null;
    static final CountDownLatch connnectedSemaphore = new CountDownLatch(1);
    static Stat stat = new Stat();

    public static void main(String[] args) throws InterruptedException, KeeperException, IOException {
//        System.out.println("启动客户端-" + Math.floor(Math.random() * 100)+"    args="+args[0]);
        //连接zookeeper并且注册一个默认的监听器
        zk = new ZooKeeper("111.229.192.247:2181", 6666, new ZooKeeperSynsDemo());
        //等待zk连接成功的通知
        connnectedSemaphore.await();
        //获取path目录节点的配置数据，并注册默认的监听器
        System.out.println("\t\t\t\t======>服务端值：" + new String(zk.getData(PATH, true, stat)));
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && watchedEvent.getPath() == null) {
                connnectedSemaphore.countDown();
            } else if (Event.EventType.NodeDataChanged == watchedEvent.getType()) {
                try {
                    System.out.println("\t\t\t\t======>服务端值已修改"+new String(zk.getData(watchedEvent.getPath(), true, stat)));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

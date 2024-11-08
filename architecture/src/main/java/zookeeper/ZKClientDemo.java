/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @since 2020-08-07
 */
public class ZKClientDemo {
    //zookeeper地址。集群时使用逗号分隔
//    private static final String zkServers = "111.229.192.247:2181";
    private static final String zkServers = "127.0.0.1:2181";

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        ZKClientDemo demo = new ZKClientDemo();
        demo.manageData();
    }

    public void manageData() throws KeeperException, InterruptedException, IOException {

        // 创建zk连接
        ZooKeeper zk = new ZooKeeper(
                zkServers,
                20000,
                null
        );

        // 创建节点
        zk.create("/abc", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 获取节点数据
        // getData(String path, boolean watch, Stat stat);
        Stat stat = new Stat();
        byte[] data = zk.getData("/abc", false, stat);
        System.out.println(new String(data));       // 数据内容 123
        System.out.println(stat.getDataLength());   // 节点状态（数据长度） 3

        // 对/abc进行watch
        zk.getData("/abc",
                watchedEvent -> {
                    System.out.println("path:" + watchedEvent.getPath());
                    System.out.println("KeeperState:" + watchedEvent.getState());
                    System.out.println("EventType:" + watchedEvent.getType());
                },
                null);

        // 设置节点数据
        // setData(String path, byte[] data, int version)
        // 指定version为-1，表示不关心版本
        zk.setData("/abc", "456".getBytes(), -1);

        // 设置两次，第二次不会触发通知
        zk.setData("/abc", "789".getBytes(), -1);

        // 阻塞，以等待通知
        Thread.sleep(1000);
        zk.close();
    }
}

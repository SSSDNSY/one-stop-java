/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cache.memcache;

import java.util.TreeSet;

/**
 * @class middleware.cache.memcache.MemcacheCluster
 * @desc
 * @since 2020-10-20
 */
class MemCacheCluster {
    private String name;
    private int heartBeatSecond = 5;
    private int poolSize = 5;
    private boolean useNio = false;
    private TreeSet<MemCacheAddress> addresses = null;

    public MemCacheCluster() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeartBeatSecond() {
        return heartBeatSecond;
    }

    public void setHeartBeatSecond(int heartBeatSecond) {
        this.heartBeatSecond = heartBeatSecond;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public boolean isUseNio() {
        return useNio;
    }

    public void setUseNio(boolean useNio) {
        this.useNio = useNio;
    }

    public TreeSet<MemCacheAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(TreeSet<MemCacheAddress> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "MemcacheCluster{" +
                "name='" + name + '\'' +
                ", heartBeatSecond=" + heartBeatSecond +
                ", poolSize=" + poolSize +
                ", useNio=" + useNio +
                ", addresses=" + addresses +
                '}';
    }
}

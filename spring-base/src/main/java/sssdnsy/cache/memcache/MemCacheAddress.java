/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sssdnsy.cache.memcache;

/**
 * @class middleware.cache.memcache.MemCacheAddress
 * @desc
 * @since 2020-10-20
 */
public class MemCacheAddress implements Comparable<MemCacheAddress> {
    private String leader;
    private String follower;

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    @Override
    public String toString() {
        return "MemCacheAddress{" +
                "\r\n\tleader='" + leader + '\'' +
                ",\n" +
                "\t follower='" + follower + '\'' +
                '}' + "\n";
    }

    @Override
    public int compareTo(MemCacheAddress otherMaster) {
        return leader.compareTo(otherMaster.leader);
    }
}

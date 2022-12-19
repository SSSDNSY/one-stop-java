package middleware.cache.memcache;

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

package cache.memcache.driver.io;

import java.io.IOException;

/**
 * @class middleware.cache.memcache.driver.io.SockIOBucket
 * @desc
 * @since 2020-10-20
 */
public abstract class SockIOBucket implements Comparable<SockIOBucket> {

    public static final int STATE_ERER = 0;
    public static final int STATE_OKER = 1;
    public static final int STATE_EROK = 2;
    public static final int STATE_OKOK = 3;
    public static final int STATE_ER = 4;
    public static final int STATE_OK = 5;
    public static final int STATE_RAW = -1;
    public static final String[] STATES = new String[]{"STATE_ERER", "STATE_OKER", "STATE_EROK", "STATE_OKOK", "STATE_ER", "STATE_OK"};

    public abstract boolean init() throws IOException;

    public abstract void close();

    public abstract ISockIO borrowSockIO();

    public abstract ISockIO borrowSockIO(long var1);

    public abstract void returnSockIO(ISockIO var1);

    public abstract boolean delSock(ISockIO var1);

    public abstract String getAddress();

    public abstract int healthCheck() throws IOException, InterruptedException;

    public abstract int getStateCode();

    public abstract void setStateCode(int var1);

    public abstract int compareTo(SockIOBucket var1);
}

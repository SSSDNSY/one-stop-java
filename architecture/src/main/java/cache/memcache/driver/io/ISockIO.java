package cache.memcache.driver.io;

import java.io.IOException;

/**
 * @class middleware.cache.memcache.driver.io.ISockIO
 * @desc
 * @since 2020-10-20
 */
public interface ISockIO {

    boolean init();

    void write(byte[] var1) throws IOException;

    int read(byte[] var1, int var2, int var3) throws IOException;

    void flush() throws IOException;

    boolean isConnected();

    boolean isAlive();

    void close() throws IOException;

    byte[] readLineBytes() throws IOException;

    void release();

    SockIOBucket getBucket();

    String getHost();

    int getPort();

    int getVersion();

    boolean isMaster();
}

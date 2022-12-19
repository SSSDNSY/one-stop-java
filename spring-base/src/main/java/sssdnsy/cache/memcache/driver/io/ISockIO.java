/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sssdnsy.cache.memcache.driver.io;

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

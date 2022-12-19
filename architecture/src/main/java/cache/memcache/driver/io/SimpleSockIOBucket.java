/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package middleware.cache.memcache.driver.io;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @class middleware.cache.memcache.driver.io.SimpleSockIOBucket
 * @desc
 * @since 2020-10-20
 */
public class SimpleSockIOBucket extends SockIOBucket {
    private static final Logger log = Logger.getLogger(SimpleSockIOBucket.class);
    private boolean useNIO = true;
    private String masterHost;
    private int masterPort;
    private int poolSize;
    private int stateCode = -1;
    private int version = 0;
    private LinkedBlockingQueue<ISockIO> masterSocks = new LinkedBlockingQueue();

    public SimpleSockIOBucket(String masterHost, int masterPort, int poolSize, boolean useNIO) {
        this.masterHost = masterHost;
        this.masterPort = masterPort;
        this.poolSize = poolSize;
        this.useNIO = useNIO;
    }

    public boolean init() throws IOException {
        ++this.version;

        for (int i = 0; i < this.poolSize; ++i) {
            ISockIO sock = null;
            if (this.useNIO) {
                sock = new SockNIO(this, this.masterHost, this.masterPort, this.version, true);
            } else {
                sock = new SockBIO(this, this.masterHost, this.masterPort, this.version, true);
            }

            if (!((ISockIO) sock).init()) {
                break;
            }

            this.masterSocks.add(sock);
        }

        if (this.masterSocks.size() == this.poolSize) {
            this.stateCode = 5;
            return true;
        } else {
            this.stateCode = 4;
            this.close();
            return false;
        }
    }

    public void close() {
        for (int i = 0; i < this.poolSize; ++i) {
            try {
                ISockIO sock = (ISockIO) this.masterSocks.poll();
                if (null != sock) {
                    sock.close();
                }
            } catch (Exception var3) {
                log.error("memcached心跳发生异常!", var3);
            }
        }

        this.masterSocks.clear();
    }

    public ISockIO borrowSockIO() {
        return this.borrowSockIO(5L);
    }

    public ISockIO borrowSockIO(long timeout) {
        ISockIO sock = null;

        try {
            sock = (ISockIO) this.masterSocks.poll(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException var5) {
            var5.printStackTrace();
        }

        return sock;
    }

    public void returnSockIO(ISockIO sock) {
        if (this.version != sock.getVersion()) {
            try {
                sock.close();
            } catch (IOException var3) {
                log.error("memcached释放过期连接发生异常!", var3);
            }

        } else {
            this.masterSocks.offer(sock);
        }
    }

    public boolean addSock(ISockIO sock) {
        return this.masterSocks.add(sock);
    }

    public boolean delSock(ISockIO sock) {
        return this.masterSocks.remove(sock);
    }

    public int healthCheck() throws IOException, InterruptedException {
        int curStateCode = 4;
        ISockIO io = this.borrowSockIO();
        if (null != io) {
            curStateCode = io.isAlive() ? 5 : 4;
            io.release();
            return curStateCode;
        } else {
            ISockIO sock = null;
            if (this.useNIO) {
                sock = new SockNIO(this, this.masterHost, this.masterPort, this.version, true);
            } else {
                sock = new SockBIO(this, this.masterHost, this.masterPort, this.version, true);
            }

            if (null != sock) {
                if (((ISockIO) sock).init()) {
                    curStateCode = ((ISockIO) sock).isAlive() ? 5 : 4;
                }

                ((ISockIO) sock).close();
            }

            return curStateCode;
        }
    }

    public int getStateCode() {
        return this.stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public String getAddress() {
        return this.masterHost + ":" + this.masterPort;
    }

    public int compareTo(SockIOBucket o) {
        return this.getAddress().compareTo(o.getAddress());
    }
}

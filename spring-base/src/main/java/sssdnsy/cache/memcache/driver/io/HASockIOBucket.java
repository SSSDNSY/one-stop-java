/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sssdnsy.cache.memcache.driver.io;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @class middleware.cache.memcache.driver.io.HASockIOBucket
 * @desc
 * @since 2020-10-20
 */
public class HASockIOBucket extends SockIOBucket {
    private static final Logger log = Logger.getLogger(HASockIOBucket.class);
    private boolean useNIO = true;
    private LinkedBlockingQueue<ISockIO> masterSocks = new LinkedBlockingQueue();
    private LinkedBlockingQueue<ISockIO> slaveSocks = new LinkedBlockingQueue();
    private String masterHost;
    private int masterPort;
    private String slaveHost;
    private int slavePort;
    private int poolSize;
    private int version = 0;
    private int stateCode = -1;

    public HASockIOBucket(String masterHost, int masterPort, String slaveHost, int slavePort, int poolSize, boolean useNIO) {
        this.masterHost = masterHost;
        this.masterPort = masterPort;
        this.slaveHost = slaveHost;
        this.slavePort = slavePort;
        this.poolSize = poolSize;
        this.useNIO = useNIO;
    }

    public boolean init() throws IOException {
        ++this.version;

        int i;
        ISockIO sock = null;
        for (i = 0; i < this.poolSize; ++i) {
            sock = null;
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


        if (this.useNIO) {
            sock = new SockNIO(this, this.slaveHost, this.slavePort, this.version, false);
        } else {
            sock = new SockBIO(this, this.slaveHost, this.slavePort, this.version, false);
        }

        if (((ISockIO) sock).init()) {
            this.slaveSocks.add(sock);
        }

        if (this.masterSocks.size() == this.poolSize) {
            if (this.slaveSocks.size() == 1) {
                this.stateCode = 3;
            } else {
                this.stateCode = 1;
            }

            return true;
        } else if (this.slaveSocks.size() == 1) {
            this.stateCode = 2;

            for (i = 0; i < this.poolSize - 1; ++i) {
                sock = null;
                if (this.useNIO) {
                    sock = new SockNIO(this, this.slaveHost, this.slavePort, this.version, false);
                } else {
                    sock = new SockBIO(this, this.slaveHost, this.slavePort, this.version, false);
                }

                if (((ISockIO) sock).init()) {
                    this.slaveSocks.add(sock);
                }
            }

            this.closeMaster();
            return true;
        } else {
            this.stateCode = 0;
            this.close();
            return false;
        }
    }

    public void close() {
        int i;
        ISockIO sock;
        for (i = 0; i < this.poolSize; ++i) {
            try {
                sock = (ISockIO) this.masterSocks.poll();
                if (null != sock) {
                    sock.close();
                }
            } catch (Exception var4) {
                log.error("memcached释放主地址连接发生异常!", var4);
            }
        }

        for (i = 0; i < this.poolSize; ++i) {
            try {
                sock = (ISockIO) this.slaveSocks.poll();
                if (null != sock) {
                    sock.close();
                }
            } catch (Exception var3) {
                log.error("memcached释放备地址连接发生异常!", var3);
            }
        }

        this.masterSocks.clear();
        this.slaveSocks.clear();
    }

    private void closeMaster() {
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

    }

    public ISockIO borrowSockIO() {
        return this.borrowSockIO(5L);
    }

    public ISockIO borrowSockIO(long timeout) {
        ISockIO sock = null;

        try {
            switch (this.stateCode) {
                case 1:
                    sock = (ISockIO) this.masterSocks.poll(timeout, TimeUnit.SECONDS);
                    break;
                case 2:
                    sock = (ISockIO) this.slaveSocks.poll(timeout, TimeUnit.SECONDS);
                    break;
                case 3:
                    sock = (ISockIO) this.masterSocks.poll(timeout, TimeUnit.SECONDS);
            }
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
            if (sock.isMaster()) {
                this.masterSocks.add(sock);
            } else {
                this.slaveSocks.add(sock);
            }

        }
    }

    public boolean delSock(ISockIO sock) {
        return sock.isMaster() ? this.masterSocks.remove(sock) : this.slaveSocks.remove(sock);
    }

    public int healthCheck() throws IOException, InterruptedException {
        boolean masterAlive = false;
        boolean slaveAlive = false;
        ISockIO io = (ISockIO) this.masterSocks.poll(1L, TimeUnit.SECONDS);
        Object sock;
        if (null != io) {
            if (io.isAlive()) {
                masterAlive = true;
            }

            io.release();
        } else {
            sock = null;
            if (this.useNIO) {
                sock = new SockNIO(this, this.masterHost, this.masterPort, -1, false);
            } else {
                sock = new SockBIO(this, this.masterHost, this.masterPort, -1, false);
            }

            if (null != sock) {
                if (((ISockIO) sock).init() && ((ISockIO) sock).isAlive()) {
                    masterAlive = true;
                }

                ((ISockIO) sock).close();
            }
        }

        io = (ISockIO) this.slaveSocks.poll(1L, TimeUnit.SECONDS);
        if (null != io) {
            if (io.isAlive()) {
                slaveAlive = true;
            }

            io.release();
        } else {
            sock = null;
            if (this.useNIO) {
                sock = new SockNIO(this, this.slaveHost, this.slavePort, -1, false);
            } else {
                sock = new SockBIO(this, this.slaveHost, this.slavePort, -1, false);
            }

            if (((ISockIO) sock).init()) {
                if (((ISockIO) sock).isAlive()) {
                    slaveAlive = true;
                }

                ((ISockIO) sock).close();
            }
        }

        if (masterAlive) {
            return slaveAlive ? 3 : 1;
        } else {
            return slaveAlive ? 2 : 0;
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

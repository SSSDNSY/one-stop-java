package cache.memcache.driver.io;

import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @class middleware.cache.memcache.driver.io.SockBIO
 * @desc
 * @since 2020-10-20
 */
public class SockBIO implements ISockIO {
    private static final Logger log = Logger.getLogger(SockBIO.class);
    private static final int BUFFED_INITSIZE = 65536;
    private SockIOBucket bucket;
    private String host;
    private int port;
    private int timeout = 5000;
    private boolean tcpNoDelay = true;
    private Socket socket;
    private BufferedInputStream in;
    private BufferedOutputStream out;
    private ByteArrayOutputStream baos;
    private boolean isMaster;
    private int version = 0;

    public SockBIO(SockIOBucket bucket, String host, int port, int version, boolean isMaster) {
        this.bucket = bucket;
        this.host = host;
        this.port = port;
        this.version = version;
        this.isMaster = isMaster;
    }

    public boolean init() {
        try {
            InetAddress addr = InetAddress.getByName(this.host);
            this.socket = new Socket();
            this.socket.connect(new InetSocketAddress(addr, this.port), 3000);
            if (this.timeout > 0) {
                this.socket.setSoTimeout(this.timeout);
            }

            this.socket.setTcpNoDelay(this.tcpNoDelay);
            this.in = new BufferedInputStream(this.socket.getInputStream(), 65536);
            this.out = new BufferedOutputStream(this.socket.getOutputStream(), 65536);
            this.baos = new ByteArrayOutputStream(300);
            return true;
        } catch (IOException var2) {
            log.error("memcached socket连接建立失败" + this.host + ":" + this.port + " " + var2);
            return false;
        }
    }

    public void write(byte[] bytes) throws IOException {
        this.out.write(bytes);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        return this.in.read(b, off, len);
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public boolean isConnected() {
        return this.socket != null && this.socket.isConnected();
    }

    public boolean isAlive() {
        if (!this.isConnected()) {
            return false;
        } else {
            try {
                this.write("version\r\n".getBytes());
                this.flush();
                byte[] rtn = this.readLineBytes();
                return null == rtn ? false : (new String(rtn)).startsWith("VERSION");
            } catch (IOException var2) {
                log.error("心跳检测异常！", var2);
                return false;
            }
        }
    }

    public void close() throws IOException {
        if (null != this.in) {
            this.in.close();
            this.in = null;
        }

        if (null != this.out) {
            this.out.close();
            this.out = null;
        }

        if (null != this.socket) {
            this.socket.close();
            this.socket = null;
        }

        this.bucket.delSock(this);
    }

    public byte[] readLineBytes() throws IOException {
        byte[] rtn = null;
        boolean eol = false;
        byte[] b = new byte[1];

        while (this.in.read(b, 0, 1) != -1) {
            if (13 == b[0]) {
                eol = true;
            } else {
                if (eol && 10 == b[0]) {
                    break;
                }

                eol = false;
                this.baos.write(b, 0, 1);
            }
        }

        if (null != this.baos && this.baos.size() > 0) {
            rtn = this.baos.toByteArray();
            this.baos.reset();
            return rtn;
        } else {
            return null;
        }
    }

    public void release() {
        this.bucket.returnSockIO(this);
    }

    public SockIOBucket getBucket() {
        return this.bucket;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public int getVersion() {
        return this.version;
    }

    public boolean isMaster() {
        return this.isMaster;
    }

}

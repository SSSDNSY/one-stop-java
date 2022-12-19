package cache.memcache.driver.io;

import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @class middleware.cache.memcache.driver.io.SockNIO
 * @desc nio socket
 * @since 2020-10-20
 */
public class SockNIO implements ISockIO {
    private static final Logger log = Logger.getLogger(SockNIO.class);
    private SockIOBucket bucket;
    private String host;
    private int port;
    private SocketChannel channel;
    private ByteBuffer readBuffer;
    private ByteBuffer writeBuffer;
    private ByteArrayOutputStream bos;
    private boolean isMaster;
    private int version = 0;

    public SockNIO(SockIOBucket bucket, String host, int port, int version, boolean isMaster) {
        this.bucket = bucket;
        this.host = host;
        this.port = port;
        this.isMaster = isMaster;
        this.version = version;
        this.readBuffer = ByteBuffer.allocateDirect(8192);
        this.writeBuffer = ByteBuffer.allocateDirect(8192);
        this.bos = new ByteArrayOutputStream();
        this.readBuffer.flip();
    }

    public boolean init() {
        try {
            InetAddress addr = InetAddress.getByName(this.host);
            this.channel = SocketChannel.open(new InetSocketAddress(addr, this.port));
            this.channel.configureBlocking(true);
            return true;
        } catch (IOException var2) {
            log.error("memcached socket连接建立失败" + this.host + ":" + this.port + " " + var2);
            return false;
        }
    }

    public void write(byte[] bytes) throws IOException {
        this.write(bytes, 0, bytes.length);
    }

    private final void write(byte[] b, int off, int len) throws IOException {
        if (len != 0) {
            if (this.writeBuffer.remaining() >= len) {
                this.writeBuffer.put(b, off, len);
            } else {
                int written = 0;
                int size;
                int remain;
                for (boolean var6 = false; (remain = len - written) > 0; written += size) {
                    size = this.writeBuffer.remaining();
                    size = size < remain ? size : remain;
                    this.writeBuffer.put(b, off + written, size);
                    this.flush();
                }
            }

        }
    }

    public void flush() throws IOException {
        this.writeBuffer.flip();
        this.channel.write(this.writeBuffer);
        this.writeBuffer.clear();
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int remain = this.readBuffer.limit() - this.readBuffer.position();
        if (len < remain) {
            this.readBuffer.get(b, off, len);
            return len;
        } else {
            this.readBuffer.get(b, off, remain);
            this.readChannel();
            return remain;
        }
    }

    private final byte read() throws IOException {
        try {
            byte b = this.readBuffer.get();
            return b;
        } catch (BufferUnderflowException var2) {
            this.readChannel();
            return this.readBuffer.get();
        }
    }

    private final void readChannel() throws IOException {
        this.readBuffer.clear();
        this.channel.read(this.readBuffer);
        this.readBuffer.flip();
    }

    public boolean isConnected() {
        return this.channel != null && this.channel.isConnected();
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
            } catch (Exception var2) {
                log.error("心跳检测异常！", var2);
                return false;
            }
        }
    }

    public void close() throws IOException {
        if (null != this.channel) {
            this.channel.close();
        }

        this.bucket.delSock(this);
    }

    public byte[] readLineBytes() throws IOException {
        boolean eol = false;

        byte one;
        while ((one = this.read()) != -1) {
            if (13 == one) {
                eol = true;
            } else {
                if (eol && 10 == one) {
                    break;
                }

                eol = false;
                this.bos.write((byte) one);
            }
        }

        if (null != this.bos && this.bos.size() > 0) {
            byte[] rtn = this.bos.toByteArray();
            this.bos.reset();
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

    public int getVersion() {
        return this.version;
    }

    public boolean isMaster() {
        return this.isMaster;
    }
}

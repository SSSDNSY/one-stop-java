package sssdnsy.dio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @Description: java nio 编程 server端
 * @Author: pengzh
 * @date: 2019/9/29
 */
public class NIOServer implements Runnable {

    private ByteBuffer buffer;
    private Selector selector;


    public static void main(String[] args) {
        new Thread(new NIOServer(8899)).start();
    }

    public NIOServer(int port) {
        buffer = ByteBuffer.allocate(1025);
        try {
            selector = Selector.open();
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.bind(new InetSocketAddress(port));
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Nio Server start...");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                this.selector.select();
                Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();
                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            this.accept(key);
                        }
                        if (key.isReadable()) {
                            this.read(key);
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void accept(SelectionKey key) {
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);
            sc.register(this.selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key) {
        try {
            buffer.clear();
            SocketChannel sc = (SocketChannel) key.channel();
            int count = sc.read(this.buffer);
            if (count == -1) {
                sc.close();
                key.cancel();
            }
            buffer.flip();
            byte[] bytes = new byte[this.buffer.remaining()];
            buffer.get(bytes);
            String msg = new String(bytes).trim();
            System.out.println("server:" + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

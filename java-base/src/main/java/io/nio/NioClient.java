package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Description: nio的客户端

 * @date: 2019/10/5
 */
public class NioClient {

    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8899);
        SocketChannel sc = null;
        ByteBuffer buff = ByteBuffer.allocate(1024);

        try {
            sc = SocketChannel.open();
            sc.connect(address);
            while (true) {
                byte[] bytes = new byte[1024];
                System.in.read(bytes);
                buff.put(bytes);
                buff.flip();
                sc.write(buff);
                buff.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sc != null) {
                try {
                    sc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

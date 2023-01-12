package netty.basic.demo0.aio;


import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

/**
 * @author fun.pengzh
 * @class netty.basic.demo0.aio.AioClient
 * @desc
 * @since 2022-05-05
 */
public class AioClient {
    public static void main(String[] args) throws Exception {
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        Future<Void> future = socketChannel.connect(new InetSocketAddress("127.0.0.1", 8993));
        System.out.println("aio client start done. ");
        future.get();
        socketChannel.read(ByteBuffer.allocate(1024), null, new AioClientHandler(socketChannel, CharsetUtil.UTF_8));
        Thread.sleep(100000);
    }

}

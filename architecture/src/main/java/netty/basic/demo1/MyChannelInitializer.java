package netty.basic.demo1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @class netty.basic.demo1.MyChannelIntializer
 * @desc
 * @since 2022-05-06
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("客户端连接");
        System.out.println("IP=" + socketChannel.localAddress().getHostString());
        System.out.println("PORT=" + socketChannel.localAddress().getPort());
    }
}

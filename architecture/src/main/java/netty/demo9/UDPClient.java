package netty.demo9;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;
import netty.demo9.ChannelClientInitializer;

import java.net.InetSocketAddress;

/**
 * @author pengzh
 * @desc
 * @class UDPClient
 * @since 2022-05-10
 */
public class UDPClient {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioDatagramChannel.class)
                    .handler(new ChannelClientInitializer());
            Channel ch = b.bind(9000).sync().channel();

            ch.writeAndFlush(new DatagramPacket(
                    Unpooled.copiedBuffer("asdfasdfasdfasdf 大是大非 你好 helloworld ！！!! ", CharsetUtil.UTF_8),
                    new InetSocketAddress("127.0.0.1", 8999))).sync();
            ch.closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

}

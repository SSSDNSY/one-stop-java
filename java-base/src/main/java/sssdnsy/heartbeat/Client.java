package sssdnsy.heartbeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import sssdnsy.serial.MarshallingCodeFactory;
import sssdnsy.utils.GZipUtils;

import java.io.IOException;

/**
 * @Description: netty 使用marshalling包 编解码 客户端
 * @Author: pengzh
 * @date: 2019/10/20
 */
public class Client {

    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(MarshallingCodeFactory.buildMarshallingDecoder());
                        sc.pipeline().addLast(MarshallingCodeFactory.buildMarshallingEncoder());
                    }
                });

        ChannelFuture cf = bootstrap.connect("127.0.0.1", 8899).sync();
        cf.channel().closeFuture().sync();
        eventLoopGroup.shutdownGracefully();
    }
}

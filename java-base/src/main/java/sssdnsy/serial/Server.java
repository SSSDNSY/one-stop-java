package sssdnsy.serial;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.Logginghandler;

/**
 * @Description: netty 使用marshalling包 编解码
 * @Author: pengzh
 * @date: 2019/10/20
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                .group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .handler(new Logginghandler(LogLevel.INFO))
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(MarshallingCodeFactory.buildMarshallingEncoder());
                        sc.pipeline().addLast(MarshallingCodeFactory.buildMarshallingDecoder());
                    }
                });
        ChannelFuture f = bootstrap.bind(5678).sync();
        f.channel().closeFuture().sync();
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }
}

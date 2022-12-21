package heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import serial.MarshallingCodeFactory;

/**
 * @Description: netty 使用marshalling包 编解码
 * @Author: pengzh
 * @date: 2019/10/20
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {
        //接受网络连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //处理实际工作
        EventLoopGroup workGroup = new NioEventLoopGroup();
        //实例化出来
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                .group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
//                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //添加日志处理
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(MarshallingCodeFactory.buildMarshallingDecoder());
                        sc.pipeline().addLast(MarshallingCodeFactory.buildMarshallingEncoder());
                    }
                });

        ChannelFuture cf = bootstrap.bind(8899).sync();
        cf.channel().closeFuture().sync();
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }
}

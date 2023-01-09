package netty.basic.demo3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pengzh
 * @desc
 * @class NettyServer
 * @since 2022-05-07
 */
public class NettyServer {

    private transient final Logger log = LoggerFactory.getLogger(NettyServer.class);

    public static void main(String[] args) {
        new NettyServer().bind(8993);
    }

    private void bind(int port) {

        EventLoopGroup parentGroup = new NioEventLoopGroup();
        NioEventLoopGroup childeGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(parentGroup, childeGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new MyChannelIntializer());

            ChannelFuture future = boot.bind(port).sync();
            log.info("NettyServer start ,listen:{}", port);
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            parentGroup.shutdownGracefully();
            childeGroup.shutdownGracefully();
        }
    }

}

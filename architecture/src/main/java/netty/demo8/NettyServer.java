package netty.demo8;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import netty.demo8.ServerChannelInitializer;

/**
 * @author pengzh
 * @desc
 * @class NettyServer
 * @since 2022-05-10
 */
public class NettyServer {

    private final transient Logger log = LoggerFactory.getLogger(NettyServer.class);

    public static void main(String[] args) {
        new NettyServer().bind(8998);
    }

    private void bind(int port) {
        NioEventLoopGroup parentGroup = new NioEventLoopGroup();
        NioEventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap().group(parentGroup, childGroup)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerChannelInitializer());
            ChannelFuture future = bootstrap.bind(port).sync();
            log.info("NettyServer start ,listen:{} <<<", port);
            future.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }


}

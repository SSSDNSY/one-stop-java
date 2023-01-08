package netty.intermediate.demo9;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pengzh
 * @desc
 * @class UDPServer
 * @since 2022-05-10
 */
public class UDPServer {

    private static final Logger log = LoggerFactory.getLogger(UDPServer.class);

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .option(ChannelOption.SO_RCVBUF, 2048 * 2048)
                    .option(ChannelOption.SO_SNDBUF, 1024 * 1024)
                    .handler(new ServerHandler());
            ChannelFuture f = bootstrap.bind(8999).sync();
            log.info("NettyServer start ,listen:{} <<<", 8999);
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

}

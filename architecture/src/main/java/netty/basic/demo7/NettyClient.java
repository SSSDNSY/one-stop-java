package netty.basic.demo7;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fun.pengzh
 * @class netty.basic.demo7.NettyServer
 * @desc 客户端
 * @since 2022-05-09
 */
public class NettyClient {

    private final transient Logger log = LoggerFactory.getLogger(NettyClient.class);

    public static void main(String[] args) {
        new NettyClient().connect(8997);
    }

    private void connect(int port) {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap().group(workGroup)
                    .option(ChannelOption.AUTO_READ, true)
                    .channel(NioSocketChannel.class)
                    .handler(new MyChannelInitializer());
            ChannelFuture future = bootstrap.connect("127.0.0.1", port).sync();
            log.info("NettyClient start ,connect:{} <<<", port);
            future.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }

}

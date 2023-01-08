package netty.basic.demo12.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.basic.demo12.util.MsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fun.pengzh
 * @class netty.basic.demo12.client.NettyClient
 * @desc
 * @since 2022-05-11
 */
public class NettyClient {

    private final transient Logger log = LoggerFactory.getLogger(NettyClient.class);

    public static void main(String[] args) {
        new NettyClient().bind("127.0.0.1", 9012);
    }


    private void bind(String host, int port) {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(workGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.AUTO_READ, true);
            bootstrap.handler(new MyChannelInitializer());

            ChannelFuture future = bootstrap.connect(host, port).sync();
            log.info("NettyClient start >>> ");

            future.channel().writeAndFlush(MsgUtil.buildMsg(future.channel().id().toString(), "是打发斯蒂芬"));
            future.channel().writeAndFlush(MsgUtil.buildMsg(future.channel().id().toString(), "123123123 "));
            future.channel().writeAndFlush(MsgUtil.buildMsg(future.channel().id().toString(), "+.+- **）（%^*^ "));
            future.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }
}

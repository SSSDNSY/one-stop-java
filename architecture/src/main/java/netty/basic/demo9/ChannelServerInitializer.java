package netty.basic.demo9;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author pengzh
 * @desc
 * @class ChannelServerInitializer
 * @since 2022-05-10
 */
public class ChannelServerInitializer extends ChannelInitializer<NioDatagramChannel> {


    @Override
    protected void initChannel(NioDatagramChannel nioDatagramChannel) throws Exception {
        nioDatagramChannel.pipeline().addLast(new NioEventLoopGroup(), new ServerHandler());
    }
}

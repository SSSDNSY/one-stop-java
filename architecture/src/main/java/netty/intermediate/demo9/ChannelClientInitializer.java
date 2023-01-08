package netty.intermediate.demo9;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author pengzh
 * @desc
 * @class ChannelClientInitializer
 * @since 2022-05-10
 */
public class ChannelClientInitializer extends ChannelInitializer<NioDatagramChannel> {


    @Override
    protected void initChannel(NioDatagramChannel nioDatagramChannel) throws Exception {
            nioDatagramChannel.pipeline().addLast(new ClientHandler());
    }
}

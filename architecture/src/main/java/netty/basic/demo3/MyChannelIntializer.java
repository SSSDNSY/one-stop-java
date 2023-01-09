package netty.basic.demo3;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;


/**
 * @author pengzh
 * @desc
 * @class MyChannelIntializer
 * @since 2022-05-07
 */
public class MyChannelIntializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        socketChannel.pipeline().addLast(new StringDecoder(Charset.forName("utf-8")));
        socketChannel.pipeline().addLast(new MyChannelHandler());
    }
}

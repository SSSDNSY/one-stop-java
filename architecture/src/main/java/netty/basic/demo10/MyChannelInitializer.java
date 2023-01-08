package netty.basic.demo10;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author pengzh
 * @desc
 * @class MyChannelInitializer
 * @since 2022-05-10
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        socketChannel.pipeline().addLast(new HttpResponseEncoder());

        socketChannel.pipeline().addLast(new HttpRequestDecoder());

        socketChannel.pipeline().addLast(new MyChannelHandler());
    }
}

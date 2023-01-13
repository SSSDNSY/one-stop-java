package netty.basic.demo7;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author fun.pengzh
 * @class netty.basic.demo7.MyChannelInitializer
 * @desc
 * @since 2022-05-09
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //解码器
        socketChannel.pipeline().addLast(new StringDecoder());
        //编码器
        socketChannel.pipeline().addLast(new StringEncoder());
        //自定义数据处理器
        socketChannel.pipeline().addLast(new ChannelHandler());
    }
}

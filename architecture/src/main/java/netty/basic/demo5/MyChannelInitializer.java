package netty.basic.demo5;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

/**
 * @desc
 * @class MyChannelIntializer
 * @since 2022-05-07
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 基于换行符
        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // GBK编码
        socketChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
        // GBK解码
        socketChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
        // 自定义数据处理
        socketChannel.pipeline().addLast(new MyChannelHandler());
    }
}

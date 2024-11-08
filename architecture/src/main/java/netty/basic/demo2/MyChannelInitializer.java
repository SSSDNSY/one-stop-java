package netty.basic.demo2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * @class netty.basic.demo1.MyChannelIntializer
 * @desc
 * @since 2022-05-06
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 基于换行符
//        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 基于指定字符串【换行符，这样功能等同于LineBasedFrameDecoder】
        // socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, false, Delimiters.lineDelimiter()));
        // 基于最大长度
        socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(4));
        // 中添加解码格式
        socketChannel.pipeline().addLast(new StringDecoder(Charset.forName("utf-8")));
        // 数据处理方式
        socketChannel.pipeline().addLast(new MyChannelHandler());
    }
}

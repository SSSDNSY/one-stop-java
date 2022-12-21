package netty.demo7;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author fun.pengzh
 * @class netty.demo7.MyChannelInitializer
 * @desc
 * @since 2022-05-09
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //自定义解码器
        socketChannel.pipeline().addLast(new MyDeCoder());
        //自定义编码
        socketChannel.pipeline().addLast(new MyEnCoder());
        //自定义数据处理器
        socketChannel.pipeline().addLast(new ChannelHandler());
    }
}

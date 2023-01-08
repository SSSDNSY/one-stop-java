package netty.intermediate.demo1.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import netty.intermediate.demo1.codec.ObjDecoder;
import netty.intermediate.demo1.codec.ObjEncoder;
import netty.intermediate.demo1.domain.MsgInfo;

/**
 * @author fun.pengzh
 * @class netty.intermediate.demo13.client.MyChannelInitializer
 * @desc
 * @since 2022-05-15
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //对象传输处理
        channel.pipeline().addLast(new ObjDecoder(MsgInfo.class));
        channel.pipeline().addLast(new ObjEncoder(MsgInfo.class));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyClientHandler());
    }
}

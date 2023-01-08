package netty.intermediate.demo2.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import netty.intermediate.demo2.codec.ObjDecoder;
import netty.intermediate.demo2.codec.ObjEncoder;
import netty.intermediate.demo2.domain.FileTransferProtocol;

/**
 * @author fun.pengzh
 * @class netty.intermediate.demo14.server.MyChannelInitializer
 * @desc
 * @since 2022-05-15
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        //对象传输处理
        channel.pipeline().addLast(new ObjDecoder(FileTransferProtocol.class));
        channel.pipeline().addLast(new ObjEncoder(FileTransferProtocol.class));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyServerHandler());
    }

}

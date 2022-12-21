package netty.demo14.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import netty.demo13.client.MyClientHandler;
import netty.demo13.codec.ObjDecoder;
import netty.demo13.codec.ObjEncoder;
import netty.demo14.domain.FileTransferProtocol;

/**
 * @author fun.pengzh
 * @class netty.demo14.client.MyChannelInitializer
 * @desc
 * @since 2022-05-15
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //对象传输处理
        channel.pipeline().addLast(new ObjDecoder(FileTransferProtocol.class));
        channel.pipeline().addLast(new ObjEncoder(FileTransferProtocol.class));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyClientHandler());
    }

}

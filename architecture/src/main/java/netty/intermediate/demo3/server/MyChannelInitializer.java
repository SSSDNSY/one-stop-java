package netty.intermediate.demo3.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import netty.intermediate.demo3.codec.ObjDecoder;
import netty.intermediate.demo3.codec.ObjEncoder;
import netty.intermediate.demo3.domain.TransportProtocol;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @desc
 * @since 2023-01-12
 */
@Service("myChannelInitializer")
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Resource
    private MyServerHandler myServerHandler;

    @Override
    protected void initChannel(SocketChannel channel) {
        //对象传输处理
        channel.pipeline().addLast(new ObjDecoder(TransportProtocol.class));
        channel.pipeline().addLast(new ObjEncoder(TransportProtocol.class));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(myServerHandler);
    }

}

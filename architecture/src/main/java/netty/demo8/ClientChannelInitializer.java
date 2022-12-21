package netty.demo8;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author pengzh
 * @desc
 * @class ClientChannelInitializer
 * @since 2022-05-10
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 基于换行符号
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8
        channel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8
        channel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MsgOutHandler());//消息出站处理器，在Client发送消息时候会触发此处理器
        channel.pipeline().addLast(new MsgInHandler()); //消息入站处理器
    }


}

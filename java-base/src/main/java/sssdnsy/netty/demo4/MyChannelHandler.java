package sssdnsy.netty.demo4;

import cn.hutool.core.date.DateUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * @author pengzh
 * @desc
 * @class MyChannelHandler
 * @since 2022-05-07
 */
public class MyChannelHandler extends ChannelInboundHandlerAdapter {

    private transient final Logger log = LoggerFactory.getLogger(MyChannelHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        log.info("{}:{}>>>已连接", channel.remoteAddress().getHostString(), channel.remoteAddress().getPort());

        String msg = new Date() + "已建立连接！";
        ByteBuf buf = Unpooled.buffer(msg.getBytes().length);
        buf.writeBytes(msg.getBytes("utf-8"));
        channel.writeAndFlush(buf);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        log.info("{}:{}>>>已断开", channel.remoteAddress().getHostString(), channel.remoteAddress().getPort());

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("{} 收到消息：{}", DateUtil.now(), msg);
        //通知客户端链消息发送成功
        String str = "服务端收到：" + new Date() + " " + msg + "\r\n";
        ByteBuf buf = Unpooled.buffer(str.getBytes().length);
        buf.writeBytes(str.getBytes("utf-8"));
        ctx.writeAndFlush(buf);
    }


    @Override
    public void exceptionCauone-stop-

    javat(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        log.error("异常信息:", cause);
    }
}

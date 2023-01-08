package netty.intermediate.demo6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author fun.pengzh
 * @class netty.basic.demo6.MyChannelHandler
 * @desc
 * @since 2022-05-08
 */
public class MyChannelHandler extends ChannelInboundHandlerAdapter {


    private transient final Logger log = LoggerFactory.getLogger(netty.intermediate.demo4.MyChannelHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        ChannelHandler.channelGroup.add(channel);
        String msgStr = remoteInfo(ctx) + "已连接";
        log.info(msgStr);
        ChannelHandler.channelGroup.writeAndFlush(msgStr);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        String msgStr = remoteInfo(ctx) + "已断开";
        log.info(msgStr);
        ChannelHandler.channelGroup.writeAndFlush(msgStr);
        ChannelHandler.channelGroup.remove(channel);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String msgStr = remoteInfo(ctx) + msg;
        log.info(msgStr);
        //通知客户端链消息发送成功
        ChannelHandler.channelGroup.writeAndFlush(msgStr);

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        log.error("连接已关闭，异常信息:", cause);
    }

    private String remoteInfo(ChannelHandlerContext ctx) {
        SocketChannel channel = (SocketChannel) ctx.channel();
        InetSocketAddress remoteAddress = channel.remoteAddress();
        return "【" + remoteAddress.getHostString() + ":" + remoteAddress.getPort() + "】";
    }
}

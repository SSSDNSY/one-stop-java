package net.netty.demo8;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author pengzh
 * @desc
 * @class MyServerHandler
 * @since 2022-05-10
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {


    private transient final Logger log = LoggerFactory.getLogger(net.netty.demo4.MyChannelHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        String msgStr = remoteInfo(ctx) + "已连接";
        log.info(msgStr);
        channel.writeAndFlush(msgStr);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String msgStr = remoteInfo(ctx) + "已断开";
        log.info(msgStr);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String msgStr = remoteInfo(ctx) + msg;
        log.info(msgStr);
        //通知客户端链消息发送成功
        ctx.writeAndFlush(msgStr);

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

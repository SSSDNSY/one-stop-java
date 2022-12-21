package netty.demo3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author fun.pengzh
 * @class netty.demo3.MyChannelHandler
 * @desc
 * @since 2022-05-06
 */
public class MyChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel socketChannel = (SocketChannel) ctx.channel();
        System.out.println("客户端连接");
        System.out.print("IP=" + socketChannel.remoteAddress().getHostString());
        System.out.println(",PORT=" + socketChannel.remoteAddress().getPort());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel socketChannel = (SocketChannel) ctx.channel();
        System.out.println("客户端断开连接");
        System.out.print("IP=" + socketChannel.remoteAddress().getHostString());
        System.out.println(",PORT=" + socketChannel.remoteAddress().getPort());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 消息： " + msg);
    }
}

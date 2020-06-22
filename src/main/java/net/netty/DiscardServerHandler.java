package net.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Description: DISCARD服务(丢弃服务 ， 指的是会忽略所有接收的数据的一种协议)
 * @Author: pengzh
 * @date: 2019/10/6
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {

    /**
     * netty5已经被废弃 ，没有该方法可以继承。
      */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // Discard the received data silently.
        ((ByteBuf) msg).release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}

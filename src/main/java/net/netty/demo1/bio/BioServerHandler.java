package net.netty.demo1.bio;

import net.netty.demo1.ChannelAdapter;
import net.netty.demo1.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author fun.pengzh
 * @class net.netty.demo1.bio.BioServerHander
 * @desc
 * @since 2022-05-03
 */
public class BioServerHandler extends ChannelAdapter {

    public BioServerHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        System.out.println("链接报告LocalAddress:" + ctx.socket().getLocalAddress());
        ctx.writeAndFlush("hi! 我是bugstack虫洞栈 BioServer to msg for you \r\n");
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);
        ctx.writeAndFlush("hi 我已经收到你的消息Success！\r\n");
    }

}

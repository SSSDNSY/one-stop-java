package net.serial;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.EventExecutorGroup;
import utils.GZipUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * @Description: 服务器处理方法
 * @Author: pengzh
 * @date: 2019/10/20
 */
public class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Req req = (Req) msg;
        System.out.println("Server receive: " + req.toString());
        byte[] data = req.getAttachment();
        File file = new File("W:\\code\\algorithm\\gh\\src\\main\\resources\\login-panda"+req.getId()+".png");
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(GZipUtils.ungzip(data));
        fos.flush();
        fos.close();
        Resp resp = new Resp();
        resp.setId(req.getId());
        resp.setName("resp" + req.getId());
        resp.setRequestMesage("服务端给的响应内容" + new Date());
        ctx.writeAndFlush(resp);
    }

}

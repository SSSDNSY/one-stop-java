package netty.intermediate.demo1.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.intermediate.demo1.util.MsgUtil;

/**
 * @author fun.pengzh
 * @class netty.intermediate.demo13.client.NettyClient
 * @desc
 * @since 2022-05-15
 */
public class NettyClient {

    public static void main(String[] args) {
        new NettyClient().connect("127.0.0.1", 9013);
    }

    private void connect(String inetHost, int inetPort) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.AUTO_READ, true);
            b.handler(new MyChannelInitializer());
            ChannelFuture f = b.connect(inetHost, inetPort).sync();
            System.out.println("itstack-demo-netty client start done.");

            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(), "你好，使用protobuf通信格式的服务端"));
            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(), "你好，使用protobuf通信格式的服务端"));
            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(), "你好，使用protobuf通信格式的服务端"));
            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(), "你好，使用protobuf通信格式的服务端"));
            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(), "你好，使用protobuf通信格式的服务端"));

            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}

//package net.http.upload;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.codec.http.HttpObjectAggregator;
//import io.netty.handler.codec.http.HttpRequestDecoder;
//import io.netty.handler.codec.http.HttpResponseEncoder;
//import io.netty.handler.logging.LogLevel;
//import io.netty.handler.logging.Logginone-stop-javaandler;
//import io.netty.handler.stream.ChunkedWriteHandler;
//
//public class HttpFileServer {
//    public static void main(String[] args) throws InterruptedException {
//        HttpFileServer.run(9999);
//    }
//
//    public static void run(int port) throws InterruptedException {
//        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
//        EventLoopGroup workGroup = new NioEventLoopGroup();
//
//        ServerBootstrap bootstrap = new ServerBootstrap();
//        bootstrap
//                .group(bossGroup, workGroup)
//                .channel(NioServerSocketChannel.class)
//                .handler(new Logginone-stop-javaandler(LogLevel.INFO))
//                .childHandler(new HttpFileUploadInitializer());
//        ChannelFuture cf = bootstrap.bind("127.0.0.1", port).sync();
//        System.err.println("Open your Browser and navigator to http://127.0.0.1:" + port);
//        cf.channel().closeFuture().sync();
//        bossGroup.shutdownGracefully();
//        workGroup.shutdownGracefully();
//    }
//}

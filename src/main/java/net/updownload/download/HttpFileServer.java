//package net.http.download;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.codec.http.HttpObjectAggregator;
//import io.netty.handler.codec.http.HttpRequestDecoder;
//import io.netty.handler.codec.http.HttpRequestEncoder;
//import io.netty.handler.codec.http.HttpResponseEncoder;
//import io.netty.handler.logging.LogLevel;
//import io.netty.handler.logging.LoggingHandler;
//import io.netty.handler.stream.ChunkedWriteHandler;
//import net.http.NettyHttpInitializer;
//
//public class HttpFileServer {
//    public static void main(String[] args) throws InterruptedException {
//        HttpFileServer.run("/main/resources/",9999);
//    }
//    public static void run(String url,int port) throws InterruptedException {
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup workGroup = new NioEventLoopGroup();
//
//        ServerBootstrap bootstrap = new ServerBootstrap();
//        bootstrap
//                .group(bossGroup,workGroup)
//                .channel(NioServerSocketChannel.class)
////                .option(ChannelOption.SO_BACKLOG,1024)
////                .handler(new LoggingHandler(LogLevel.INFO))
//                .childHandler(new ChannelInitializer<SocketChannel>() {
//                    @Override
//                    protected void initChannel(SocketChannel sc) throws Exception {
////                        加入http解码器
//                        sc.pipeline().addLast("http-decoder",new HttpRequestDecoder());
//                        //多个消息转化为单一的
//                        sc.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
////                        加入http编码器
//                        sc.pipeline().addLast("htpp-encode",new HttpResponseEncoder());
////                        加入chunked主要作用是支持异步的码流 大文件传输
//                        sc.pipeline().addLast("htpp-chunked",new ChunkedWriteHandler());
////                        加入自定义的文件处理的handler
////                        sc.pipeline().addLast("fileServerHandler",new HttpFileServerHandler(url));
//                    }
//                });
//        ChannelFuture cf = bootstrap.bind("127.0.0.1",port).sync();
//        System.err.println("Open your Browser and navigator to http://127.0.0.1:"+port);
//        cf.channel().closeFuture().sync();
//        bossGroup.shutdownGracefully();
//        workGroup.shutdownGracefully();
//    }
//}

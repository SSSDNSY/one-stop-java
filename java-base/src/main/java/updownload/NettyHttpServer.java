//package net.http;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.logging.LogLevel;
//import io.netty.handler.logging.Logginghandler;
//import io.netty.handler.ssl.SslContext;
//import io.netty.handler.ssl.util.SelfSignedCertificate;
//
//import javax.net.ssl.SSLException;
//import java.security.cert.CertificateException;
//
//public class NettyHttpServer {
//
//    static final  boolean SSL = System.getProperty("ssl") != null;
//    static final int PORT = Integer.parseInt(System.getProperty("port",SSL?"443":"80"));
//    public static void main(String[] args) throws Exception{
//
//        final SslContext sslContext ;
//        if(SSL){
//            SelfSignedCertificate ssc = new SelfSignedCertificate();
//            sslContext = SslContext.newServerContext(ssc.certificate(),ssc.privateKey());
//        }else {
//            sslContext = null;
//        }
//
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup workGroup = new NioEventLoopGroup();
//
//        ServerBootstrap bootstrap = new ServerBootstrap();
//        bootstrap
//                .group(bossGroup,workGroup)
//                .channel(NioServerSocketChannel.class)
//                .option(ChannelOption.SO_BACKLOG,1024)
//                .handler(new Logginghandler(LogLevel.INFO))
//                .childHandler(new NettyHttpInitializer(sslContext));
//        ChannelFuture cf = bootstrap.bind("127.0.0.1",PORT).sync();
//        System.err.println("Open your Browser and navigator to "+(SSL?"Https":"http")+"://127.0.0.1:"+PORT);
//        cf.channel().closeFuture().sync();
//        bossGroup.shutdownGracefully();
//        workGroup.shutdownGracefully();
//
//    }
//}

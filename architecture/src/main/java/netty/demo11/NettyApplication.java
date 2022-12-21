package netty.demo11;

/**
 * @author pengzh
 * @desc
 * @class NettyApplication
 * @since 2022-05-10
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
//@ComponentScan("org.itstack.demo.netty")
//public class NettyApplication implements CommandLineRunner {
//
//    @Value("${netty.host}")
//    private String host;
//    @Value("${netty.port}")
//    private int port;
//    @Autowired
//    private NettyServer nettyServer;
//
//    public static void main(String[] args) {
//        SpringApplication.run(NettyApplication.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        InetSocketAddress address = new InetSocketAddress(host, port);
//        ChannelFuture channelFuture = nettyServer.bing(address);
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> nettyServer.destroy()));
//        channelFuture.channel().closeFuture().syncUninterruptibly();
//    }
//
//}

//package sssdnsy.serial;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import sssdnsy.utils.GZipUtils;
//
//import java.io.*;
//import java.util.Date;
//
///**
// * @Description:
// * @Author: pengzh
// * @date: 2019/10/20
// */
//public class Client {
//
//    public static void main(String[] args) throws InterruptedException, IOException {
//        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
//        Bootstrap bootstrap = new Bootstrap();
//        bootstrap.group(eventLoopGroup)
//                .channel(NioSocketChannel.class)
//                .handler(new ChannelInitializer<SocketChannel>() {
//                    @Override
//                    protected void initChannel(SocketChannel sc) throws Exception {
//                        sc.pipeline().addLast(MarshallingCodeFactory.buildMarshallingDecoder());
//                        sc.pipeline().addLast(MarshallingCodeFactory.buildMarshallingEncoder());
//                        sc.pipeline().addLast(new ClientHandler());
//                    }
//                });
//        ChannelFuture cf = bootstrap.connect("127.0.0.1", 5678).sync();
//        int i = 0;
//        String name = "客户端" + Math.floor(Math.random() * 10);
//        while (true) {
//            Req req = new Req();
//            req.setId(i + 1 + "");
//            req.setName(name);
//            Thread.sleep((long) Math.floor(Math.random() * 300));
//            req.setRequestMesage("数据包新消息" + new Date());
//            File file = new File("W:\\code\\algorithm\\gh\\src\\main\\resources\\login-panda.png");
//            FileInputStream fis = new FileInputStream(file);
//            byte[] data = new byte[fis.available()];
//            fis.read(data);
//            req.setAttachment(GZipUtils.gzip(data));
//            fis.close();
//            cf.channel().writeAndFlush(req);
//            i++;
//            if (i > 50) {
//                break;
//            }
//        }
//    }
//}

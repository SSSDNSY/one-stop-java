//package net.http;
//
//import io.netty.channel.Channel;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelPipeline;
//import io.netty.handler.codec.http.HttpServerCodec;
//import io.netty.handler.ssl.SslContext;
//
//public class NettyHttpInitializer extends ChannelInitializer {
//
//    private final SslContext sslContext;
//
//    public NettyHttpInitializer(SslContext sslContext) {
//        this.sslContext = sslContext;
//    }
//
//    @Override
//    protected void initChannel(Channel channel) throws Exception {
//        ChannelPipeline cp = channel.pipeline();
//        if(sslContext != null){
//            cp.addLast(sslContext.newHandler(channel.alloc()));
//        }
//        cp.addLast(new HttpServerCodec());
//        cp.addLast(new NettyHttpServerHandler());
//    }
//}

//package net.http.upload;
//
//import io.netty.channel.ChannelHandler;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelPipeline;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.handler.codec.http.HttpContentCompressor;
//import io.netty.handler.codec.http.HttpRequestDecoder;
//import io.netty.handler.codec.http.HttpResponseEncoder;
//
///**
//*@Description: upload初始化类
//*@Author: pengzh
//*@date: 2019/11/28
//*/
//public class HttpFileUploadInitializer extends ChannelInitializer<SocketChannel> {
//    @Override
//    protected void initChannel(SocketChannel socketChannel) throws Exception {
//        ChannelPipeline pipeline = socketChannel.pipeline();
//        pipeline.addLast(new HttpRequestDecoder());
//        pipeline.addLast(new HttpResponseEncoder());
//
//        pipeline.addLast(new HttpContentCompressor());
//        pipeline.addLast(new HttpFileUploadServerHandler());
//    }
//}

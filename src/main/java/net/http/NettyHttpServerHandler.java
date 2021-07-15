//package net.http;
//
//import io.netty.buffer.Unpooled;
//import io.netty.channel.*;
//import io.netty.handler.codec.http.*;
//import io.netty.util.concurrent.EventExecutorGroup;
//
//import static io.netty.handler.codec.http.HttpHeaderNames.*;
//import static io.netty.handler.codec.http.HttpVersion.*;
//import static io.netty.handler.codec.http.HttpResponseStatus.*;
//
//public class NettyHttpServerHandler extends ChannelHandlerAdapter {
//
//    private static  final  byte[] CONTEXT = "你好，世界! \n Powered by netty server".getBytes();
//
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.flush();
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        if(msg instanceof HttpRequest){
//            HttpRequest req = (HttpRequest) msg;
//
//            if(HttpHeaderUtil.is100ContinueExpected(req)){
//                ctx.write(new DefaultFullHttpResponse(HTTP_1_1 ,CONTINUE));
//            }
//            boolean keepAlive = HttpHeaderUtil.isKeepAlive(req);
//            FullHttpResponse resp = new DefaultFullHttpResponse(HTTP_1_1,OK, Unpooled.wrappedBuffer(CONTEXT));
//            resp.headers().set(CONTENT_TYPE,"text/plain; charset=UTF-8");
//            resp.headers().setInt(CONTENT_LENGTH,resp.content().readableBytes());
//            if(!keepAlive){
//                ctx.write(resp).addListener(ChannelFutureListener.CLOSE);
//            }else {
//                resp.headers().set(CONNECTION,HttpHeaderValues.KEEP_ALIVE);
//                ctx.write(resp);
//            }
//        }
//    }
//
//}

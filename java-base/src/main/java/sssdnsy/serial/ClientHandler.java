//package sssdnsy.serial;
//
//import io.netty.channel.ChannelHandlerAdapter;
//import io.netty.channel.ChannelHandlerContext;
//
///**
// * @Description: 服务器处理方法
// * @Author: pengzh
// * @date: 2019/10/20
// */
//public class ClientHandler extends ChannelHandlerAdapter {
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        Resp resp = (Resp) msg;
//        System.out.println("Client receive: " + resp.toString());
////         resp = new Resp();
////        resp.setId(resp.getId());
////        resp.setName("resp" + resp.getId());
////        resp.setRequestMesage("响应内容" + resp.getId());
////        ctx.writeAndFlush(resp);
//    }
//
//}

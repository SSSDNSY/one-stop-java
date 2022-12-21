//package sssdnsy.heartbeat;
//
//import io.netty.channel.*;
//import io.netty.handler.logging.Logginghandler;
//import io.netty.util.concurrent.EventExecutorGroup;
//
//import java.util.HashMap;
//
///**
// * @Description:
// * @Author: pengzh
// * @date: 2019/10/21
// */
//public class ServerHeartBeeatHandler extends ChannelHandlerAdapter {
//
//    //内存kv库 保存各个客户端验证信息
//    private static HashMap<String, String> AUTH_MAP = new HashMap();
//    private static final String SECCESS_KEY = "AUTH_SECCESS";
//
//    static {
//        //默认加载客户端验证配置
//        AUTH_MAP.put("169.254.78.244", "12345");
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
////        System.out.println(">客户端接入"+ctx.channel().remoteAddress().toString());
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        if (msg instanceof String) {
//            auth(ctx, msg);
//        } else
//            if (msg instanceof HeartBeatInfo) {
//            HeartBeatInfo heartBeatInfo = (HeartBeatInfo) msg;
//            String msgs = "---------------------INFO_RECEIVED-----------------------";
//            System.out.println(msgs);
//            System.out.println(msg);
//            ctx.writeAndFlush(msgs);
//        } else {
//            ctx.writeAndFlush("CONNECTED_FAILURE").addListener(ChannelFutureListener.CLOSE);
//        }
//    }
//
//    //验证方法
//    private boolean auth(ChannelHandlerContext ctx, Object msg) {
//        String ret = (String) msg;
//        String[] rets = ret.split(",");
//        if (rets.length == 2 && AUTH_MAP.get(rets[0]) != null
//                && AUTH_MAP.get(rets[0]).equals(rets[1])) {
//            ctx.writeAndFlush(SECCESS_KEY);
//            return true;
//        } else {
//            ctx.writeAndFlush("AUTH_FAILURE").addListener(ChannelFutureListener.CLOSE);
//            return false;
//        }
//    }
//}

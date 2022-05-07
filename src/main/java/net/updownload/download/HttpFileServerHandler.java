//package net.http.download;
//
//import cn.hutool.core.util.CharUtil;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.*;
//import io.netty.handler.codec.http.*;
//import io.netty.handler.codec.http2.HttpUtil;
//import io.netty.handler.stream.ChunkedFile;
//import io.netty.util.CharsetUtil;
//import javax.activation.MimetypesFileTypeMap;
//import java.io.File;
//import java.io.RandomAccessFile;
//
//import static io.netty.handler.codec.http.HttpHeaderNames.*;
//import static io.netty.handler.codec.http.HttpResponseStatus.*;
//import static io.netty.handler.codec.http.HttpMethod.*;
//import static io.netty.handler.codec.http.HttpVersion.*;
//
///**
// * @author imi
// */
//public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
//
//    private final String url;
//
//    public HttpFileServerHandler(String url) {
//        this.url = url;
//    }
//
//    @Override
//    protected void messageReceived(ChannelHandlerContext context, FullHttpRequest request) throws Exception {
//        if (!request.decoderResult().isSuccess()) {
//            sendError(context, BAD_REQUEST);
//            return;
//        }
//        if (request.method() != GET) {
//            sendError(context, METHOD_NOT_ALLOWED);
//            return;
//        }
//        final String uri = request.uri();
//        final String path = sanitizeUri(uri);
//        if (path == null) {
//            sendError(context, FORBIDDEN);
//        }
//        File file = new File(path);
//        if (file.isHidden() || !file.exists()) {
//            sendError(context, NOT_FOUND);
//        }
//        if (file.isDirectory()) {
//            sendFileList(context, file, null);
//            return;
//        }
//        if (!file.isFile()) {
//            return;
//        }
//        RandomAccessFile randomAccessFile = null;
//        try {
//            randomAccessFile = new RandomAccessFile(file, "r");
//        } catch (Exception e) {
//            System.out.println(" new RandomAccessFile" + e);
//        }
//        long fileLength = randomAccessFile.length();
//        HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);
//        HttpHeaderUtil.setContentLength(response, fileLength);
//        setContentTyep(response, file);
//        context.write(response);
//
//        ChannelFuture cf;
//        cf = context.write(new ChunkedFile(randomAccessFile, 0, fileLength, 1024), context.newProgressivePromise());
//        cf.addListener(new ChannelProgressiveFutureListener() {
//            @Override
//            public void operationProgressed(ChannelProgressiveFuture channelProgressiveFuture, long proccess, long total) throws Exception {
//                if (total < 0) {
//                    System.out.println("Transfer process:" + proccess);
//                } else {
//                    System.out.println("Transfer process:" + proccess + " / " + total);
//                }
//            }
//
//            @Override
//            public void operationComplete(ChannelProgressiveFuture channelProgressiveFuture) throws Exception {
//                System.out.println("Transfer complete");
//            }
//        });
//        ChannelFuture whiteBlock = context.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
////        if (HttpUtil(request)) {
////            whiteBlock.addListener(ChannelFutureListener.CLOSE);
////        }
//    }
//
//    private void setContentTyep(HttpResponse response, File file) {
//        MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();
//        response.headers().set(CONTENT_TYPE, mimetypesFileTypeMap.getContentType(file.getPath()));
//    }
//
//    private String sanitizeUri(String uri) {
//        return System.getProperty("user.dir") + "/" + uri;
//
//    }
//
//    private static void sendFileList(ChannelHandlerContext context, File dir, String dirName) {
//        FullHttpResponse resp = new DefaultFullHttpResponse(HTTP_1_1, OK);
//        resp.headers().set(CONTENT_TYPE, "text/html;charset=UTF-8");
//        StringBuilder sb = new StringBuilder();
//        String dirPath = dir.getPath();
//        sb.append("<H3>" + dirPath + "目录</H3>");
//        sb.append("<ul>" + dirPath);
//
//
//        for (File f : dir.listFiles()) {
//            if (f.isHidden() || !f.canRead()) {
//                continue;
//            }
//            String fn = "";
//            if (dirName != null && dirName.length() > 0) {
//                fn += dirName + "/" + f.getName();
//            } else {
//                fn += f.getName();
//            }
//            sb.append("<li>链接：<a href=\"");
//            sb.append(fn);
//            sb.append("\"></li>");
//            sb.append(fn);
//        }
//        sb.append("</ul>");
//        ByteBuf buf = Unpooled.copiedBuffer(sb, CharsetUtil.UTF_8);
//        resp.content().writeBytes(buf);
//        context.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
//    }
//
//    private void sendError(ChannelHandlerContext context, HttpResponseStatus status) {
//        FullHttpResponse response = new DefaultFullHttpResponse(
//                HTTP_1_1, status, Unpooled.copiedBuffer("Failure " + status.toString() + "\r\n", CharsetUtil.UTF_8));
//        response.headers().set(CONTENT_TYPE, "text/plain;charset=UTF-8");
//        context.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
//    }
//}

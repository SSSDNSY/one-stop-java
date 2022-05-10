package net.netty.demo10;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pengzh
 * @desc
 * @class MyChannelHandler
 * @since 2022-05-10
 */
public class MyChannelHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(MyChannelHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof HttpRequest) {
            DefaultHttpRequest request = (DefaultHttpRequest) msg;
            log.info("URL:{}", request.getUri());
            log.error(msg.toString());
        }

        if (msg instanceof HttpContent) {
            LastHttpContent con = (LastHttpContent) msg;
            ByteBuf buf = con.content();
            if (!(buf instanceof EmptyByteBuf)) {
                byte[] msgBtyes = new byte[buf.readableBytes()];
                buf.readBytes(msgBtyes);
                log.info(new String(msgBtyes, CharsetUtil.UTF_8));
            }
        }


        String respMsg = "From nettyServer http response ! QAQ asdflkasdfjasdsdfa234#$$%#$R（）*&（）*&SDF水电费水电费";
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(respMsg.getBytes(CharsetUtil.UTF_8)));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf-8");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        ctx.writeAndFlush(response);
        ctx.flush();
    }
}

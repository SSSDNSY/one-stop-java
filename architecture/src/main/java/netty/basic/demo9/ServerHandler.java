package netty.basic.demo9;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author pengzh
 * @desc
 * @class ServerHandler
 * @since 2022-05-10
 */
public class ServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {


    private static final Logger log = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        String msg = datagramPacket.content().toString(CharsetUtil.UTF_8);
        log.info(" <<< {}", msg);
        String json = "已收到 \r\n";
        DatagramPacket packet = new DatagramPacket(Unpooled.copiedBuffer(json.getBytes(CharsetUtil.UTF_8)), datagramPacket.sender());
        channelHandlerContext.writeAndFlush(packet);
    }
}

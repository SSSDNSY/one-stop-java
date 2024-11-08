package netty.basic.demo9;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @desc
 * @class ClientHandler
 * @since 2022-05-10
 */
public class ClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {


    private final transient Logger log = LoggerFactory.getLogger(ClientHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        String s = datagramPacket.content().toString(CharsetUtil.UTF_8);
        log.info(s);
    }
}

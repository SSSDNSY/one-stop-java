package netty.basic.demo8.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @desc
 * @class NettyServer
 * @since 2022-05-10
 */
public class MyEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object in, ByteBuf out) throws Exception {

        String msg = in.toString();
        byte[] bytes = msg.getBytes();

        byte[] send = new byte[bytes.length + 2];
        System.arraycopy(bytes, 0, send, 1, bytes.length);
        send[0] = 0x02;
        send[send.length - 1] = 0x03;

        out.writeInt(send.length);
        out.writeBytes(send);

    }

}

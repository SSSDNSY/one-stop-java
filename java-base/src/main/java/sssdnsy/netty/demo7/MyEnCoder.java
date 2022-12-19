package sssdnsy.netty.demo7;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author fun.pengzh
 * @class sssdnsy.netty.demo7.MyEnCoder
 * @desc
 * @since 2022-05-09
 */
public class MyEnCoder extends MessageToByteEncoder {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object in, ByteBuf byteBuf) throws Exception {
        String s = in.toString();
        byte[] bytes = s.getBytes();

        byte[] send = new byte[bytes.length + 2];
        System.arraycopy(bytes, 0, send, 1, bytes.length);
        send[0] = 0x02;
        send[send.length - 1] = 0x03;
        byteBuf.writeInt(send.length);
        byteBuf.writeBytes(send);
    }
}

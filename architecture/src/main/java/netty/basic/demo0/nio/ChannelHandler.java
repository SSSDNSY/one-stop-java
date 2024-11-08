package netty.basic.demo0.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**

 * @class netty.basic.demo0.nio.ChannelHandler
 * @desc 消息处理
 * @since 2022-05-04
 */
public class ChannelHandler {

    private SocketChannel socketChannel;
    private Charset charset;

    public ChannelHandler(SocketChannel socketChannel, Charset charset) {
        this.socketChannel = socketChannel;
        this.charset = charset;
    }

    public void writeAndFlush(Object msg) {
        try {
            byte[] bytes = msg.toString().getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SocketChannel channel() {
        return socketChannel;
    }

}

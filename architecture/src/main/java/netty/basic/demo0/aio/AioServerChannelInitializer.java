package netty.basic.demo0.aio;

import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.TimeUnit;

/**

 * @class netty.basic.demo0.aio.AioServerChannelInitializer
 * @desc
 * @since 2022-05-05
 */
public class AioServerChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(AsynchronousSocketChannel channel) throws Exception {
        channel.read(ByteBuffer.allocate(1024), 10, TimeUnit.SECONDS, null, new AioServerHandler(channel, CharsetUtil.UTF_8));
    }
}

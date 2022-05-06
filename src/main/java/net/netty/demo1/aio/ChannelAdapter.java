package net.netty.demo1.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author fun.pengzh
 * @class net.netty.demo1.aio.ChannelAdapter
 * @desc
 * @since 2022-05-05
 */
public abstract class ChannelAdapter implements CompletionHandler<Integer, Object> {

    private AsynchronousSocketChannel channel;
    private Charset charset;

    public ChannelAdapter(AsynchronousSocketChannel channel, Charset charset) {
        this.channel = channel;
        this.charset = charset;
        if (channel.isOpen()) {
            channelActive(new ChannelHandler(channel, charset));
        }
    }

    @Override
    public void completed(Integer resulet, Object attachment) {
        try {
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            final long timeout = 60 * 60L;
            channel.read(buffer, timeout, TimeUnit.SECONDS, null, new CompletionHandler<Integer, Object>() {

                @Override
                public void completed(Integer result, Object attachment) {
                    if (resulet == -1) {
                        try {
                            channelInactive(new ChannelHandler(channel, charset));
                            channel.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    buffer.flip();
                    channelRead(new ChannelHandler(channel, charset), charset.decode(buffer));
                    buffer.clear();
                    channel.read(buffer, timeout, TimeUnit.SECONDS, null, this);
                }

                @Override
                public void failed(Throwable throwable, Object o) {
                    throwable.getStackTrace();
                }
            });

        } catch (Exception e) {

        }
    }

    @Override
    public void failed(Throwable throwable, Object o) {
        throwable.getStackTrace();
    }

    public abstract void channelActive(ChannelHandler ctx);

    public abstract void channelInactive(ChannelHandler ctx);

    public abstract void channelRead(ChannelHandler ctx, Object msg);

}

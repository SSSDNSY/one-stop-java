package netty.basic.demo0.aio;


import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @class netty.basic.demo0.aio.ChannelInitializer
 * @desc
 * @since 2022-05-05
 */
public abstract class ChannelInitializer implements CompletionHandler<AsynchronousSocketChannel, AioServer> {

    @Override
    public void completed(AsynchronousSocketChannel asynchronousSocketChannel, AioServer aioServer) {
        try {
            initChannel(asynchronousSocketChannel);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            aioServer.serverSocketChannel().accept(aioServer, this);
        }
    }

    @Override
    public void failed(Throwable throwable, AioServer aioServer) {
        throwable.getStackTrace();
    }

    protected abstract void initChannel(AsynchronousSocketChannel channel) throws Exception;

}

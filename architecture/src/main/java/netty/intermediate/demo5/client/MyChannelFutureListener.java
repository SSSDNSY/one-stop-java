package netty.intermediate.demo5.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

/**
 * @author fun.pengzh
 * @desc
 * @since 2023-01-12
 */
public class MyChannelFutureListener implements ChannelFutureListener {
    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        if (channelFuture.isSuccess()) {
            System.out.println("netty client start done. ");
//            return;
        }
        final EventLoop loop = channelFuture.channel().eventLoop();
        loop.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    new NettyClient().connect("127.0.0.1", 23456);
                    System.out.println("netty client start done . ");
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println("netty client start error go reconnect ... ");
                }
            }
        }, 1L, TimeUnit.SECONDS);
    }
}

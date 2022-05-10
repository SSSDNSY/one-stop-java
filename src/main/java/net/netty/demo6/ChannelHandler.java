package net.netty.demo6;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author fun.pengzh
 * @class net.netty.demo6.ChannelHandler
 * @desc
 * @since 2022-05-08
 */
public class ChannelHandler {

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}

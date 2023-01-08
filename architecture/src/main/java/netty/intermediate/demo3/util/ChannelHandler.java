package netty.intermediate.demo3.util;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author fun.pengzh
 * @class netty.intermediate.demo15.util.ChannelHandler
 * @desc
 * @since 2022-05-15
 */
public class ChannelHandler {

    //用于存放用户Channel信息，也可以建立map结构模拟不同的消息群
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}

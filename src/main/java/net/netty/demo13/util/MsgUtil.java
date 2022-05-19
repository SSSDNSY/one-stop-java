package net.netty.demo13.util;

import net.netty.demo13.domain.MsgInfo;

/**
 * @author fun.pengzh
 * @class net.netty.demo13.util.MsgInfo
 * @desc
 * @since 2022-05-15
 */
public class MsgUtil {

    public static MsgInfo buildMsg(String channelId, String msgContent) {
        return new MsgInfo(channelId, msgContent);
    }
}

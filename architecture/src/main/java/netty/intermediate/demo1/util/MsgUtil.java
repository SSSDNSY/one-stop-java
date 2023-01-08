package netty.intermediate.demo1.util;

import netty.intermediate.demo1.domain.MsgInfo;

/**
 * @author fun.pengzh
 * @class netty.intermediate.demo13.util.MsgInfo
 * @desc
 * @since 2022-05-15
 */
public class MsgUtil {

    public static MsgInfo buildMsg(String channelId, String msgContent) {
        return new MsgInfo(channelId, msgContent);
    }
}

package net.netty.demo12.util;

import net.netty.demo12.domain.MsgBody;

/**
 * @author fun.pengzh
 * @class net.netty.demo12.util.MsgUtil
 * @desc
 * @since 2022-05-11
 */
public class MsgUtil {

    /**
     * 构建Protobuf消息体
     *
     * @param channelId
     * @param msgInfo
     * @return
     */
    public static MsgBody buildMsg(String channelId, String msgInfo) {
        MsgBody.Builder msg = MsgBody.newBuilder();
        msg.setChannelId(channelId);
        msg.setMsgInfo(msgInfo);
        return msg.build();
    }

}

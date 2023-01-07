package netty.demo14.util;

import netty.demo14.domain.FileBurstInstruct;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fun.pengzh
 * @class netty.demo14.util.CacheUtil
 * @desc
 * @since 2022-05-15
 */
public class CacheUtil {

    public static Map<String, FileBurstInstruct> burstDataMap = new ConcurrentHashMap<>();

}
package middleware.cache.local;

import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @class middleware.cache.local.CacheUtil
 * @desc
 * @since 2020-10-20
 */
public class CacheUtil {
    private static final Logger log = Logger.getLogger(CacheUtil.class);

    private CacheUtil() {
    }

    public static final int objectSize(Object obj) {
        int size = 0;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;

        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            byte[] bytes = bos.toByteArray();
            size = bytes.length;
        } catch (IOException var13) {
            log.error("计算对象所占内存大小发生错误！");
        } finally {
            try {
                oos.close();
            } catch (IOException var12) {
                log.error("关闭ObjectOutputStream时发生错误！");
            }

        }

        return size;
    }
}

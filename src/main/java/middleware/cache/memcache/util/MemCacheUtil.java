package middleware.cache.memcache.util;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @class middleware.cache.memcache.util.MemCacheUtil
 * @desc
 * @since 2020-10-20
 */
public final class MemCacheUtil {
    public static Map<String, String> excuteCmd(String hostIp, String cmd) {
        Map<String, String> rtn = new HashMap<>();

        Socket socket = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String out;
        try {
            final String[] strs = StrUtil.splitToArray(hostIp, ":");
            socket = new Socket(strs[0], Integer.parseInt(strs[1]));
            bis = new BufferedInputStream(socket.getInputStream());
            bos = new BufferedOutputStream(socket.getOutputStream());
            bos.write((cmd + "\r\n").getBytes());
            bos.flush();
            for (out = readLine(bis); !"END".equals(out); out = readLine(bis)) {
                String[] outs = StrUtil.splitToArray(out, " ");
                rtn.put(outs[1], outs[2]);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            out = null;
        } finally {
            try {
                if (null != bis) {
                    bis.close();
                }

                if (null != bos) {
                    bos.close();
                }

                if (null != socket) {
                    socket.close();
                }
            } catch (IOException var15) {
                var15.printStackTrace();
                out = null;
            }
        }

        return rtn;
    }

    private static String readLine(BufferedInputStream bis) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1];
        boolean eof = false;
        try {
            while (bis.read(bytes, 0, 1) != -1) {
                if (13 == bytes[0]) {
                    eof = true;
                } else {
                    if (eof && 10 == bytes[0]) {
                        break;
                    }
                    eof = false;
                    baos.write(bytes, 0, 1);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos != null && baos.size() > 0 ? new String(baos.toByteArray()) : null;
    }

    public static void main(String[] args) {
//        final Map<String, String> stats = excuteCmd("127.0.0.1:11004", "stats");
//        System.out.println(stats);
//        final Map<String, String> statsSet = excuteCmd("127.0.0.1:11003", "stats settings");
//        System.out.println(statsSet);

        String operNum = "0";
        String resNo = "12312";
        boolean result = StringUtils.isEmpty(resNo) || Integer.parseInt(operNum) <= 0;
        System.out.println("结果"+result);
    }
}

/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sssdnsy.cache.util;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @class middleware.cache.util.MD5Util
 * @desc
 * @since 2020-10-20
 */
public final class MD5Util {
    private static final Logger LOG = Logger.getLogger(MD5Util.class);
    private static final char[] hexchar = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private MD5Util() {
    }

    public static final String hexdigest(String str) {
        MessageDigest alg = null;

        try {
            alg = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var3) {
            LOG.error(var3);
        }

        byte[] digest = alg.digest(str.getBytes());
        return bytesToHex(digest);
    }

    public static final String hexdigest(byte[] bytes) {
        MessageDigest alg = null;

        try {
            alg = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var3) {
            LOG.error(var3);
        }

        byte[] digest = alg.digest(bytes);
        return bytesToHex(digest);
    }

    private static final String bytesToHex(byte[] digest) {
        StringBuilder sb = new StringBuilder(digest.length * 2);
        int i = 0;

        for(int size = digest.length; i < size; ++i) {
            sb.append(hexchar[(digest[i] & 240) >>> 4]);
            sb.append(hexchar[digest[i] & 15]);
        }

        return sb.toString();
    }
}

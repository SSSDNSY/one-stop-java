/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cache.util;

import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @class middleware.cache.util.AbstractSerializable
 * @desc
 * @since 2020-10-20
 */
public abstract class AbstractSerializable implements ISerializable {
    private static final transient Logger log = Logger.getLogger(AbstractSerializable.class);

    public AbstractSerializable() {
    }

    public byte[] encodeGzip(byte[] bytes) {
        ByteArrayOutputStream bos = null;
        GZIPOutputStream gos = null;

        try {
            bos = new ByteArrayOutputStream(bytes.length);
            gos = new GZIPOutputStream(bos);
            gos.write(bytes, 0, bytes.length);
            gos.finish();
            byte[] var4 = bos.toByteArray();
            return var4;
        } catch (IOException var14) {
        } finally {
            if (null != gos) {
                try {
                    gos.close();
                } catch (IOException var13) {
                    var13.printStackTrace();
                }
            }

        }

        return null;
    }

    public byte[] decodeGzip(byte[] datas) {
        GZIPInputStream gis = null;
        ByteArrayOutputStream baos = null;

        try {
            gis = new GZIPInputStream(new ByteArrayInputStream(datas));
            baos = new ByteArrayOutputStream(datas.length);
            byte[] tmp = new byte[datas.length];

            int cnt;
            while ((cnt = gis.read(tmp)) != -1) {
                baos.write(tmp, 0, cnt);
            }

            byte[] var6 = baos.toByteArray();
            return var6;
        } catch (IOException var20) {
            log.error(var20);
        } finally {
            if (null != gis) {
                try {
                    gis.close();
                } catch (IOException var19) {
                    var19.printStackTrace();
                }
            }

            if (null != baos) {
                try {
                    baos.close();
                } catch (IOException var18) {
                    var18.printStackTrace();
                }
            }

        }
        return null;
    }
}

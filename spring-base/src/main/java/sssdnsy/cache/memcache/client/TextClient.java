/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sssdnsy.cache.memcache.client;

import sssdnsy.cache.memcache.MemCacheAddress;
import sssdnsy.cache.memcache.MemCacheFactory;
import sssdnsy.cache.memcache.driver.io.ISockIO;
import sssdnsy.cache.memcache.driver.io.SockIOPool;
import sssdnsy.cache.memcache.driver.util.FastConvertor;
import sssdnsy.cache.memcache.interfaces.IMemCache;
import sssdnsy.cache.util.DefaultSerializable;
import sssdnsy.cache.util.IOUtil;
import sssdnsy.cache.util.ISerializable;
import sssdnsy.cache.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;

/**
 * @class middleware.cache.memcache.client.TextClient
 * @desc
 * @since 2020-10-20
 */
public final class TextClient implements IMemCache {
    private static final Logger log = Logger.getLogger(TextClient.class);
    private static final byte[] CMD_ADD = "add".getBytes();
    private static final byte[] CMD_APPEND = "append".getBytes();
    private static final byte[] CMD_SET = "set".getBytes();
    private static final byte[] CMD_GET = "get".getBytes();
    private static final byte[] CMD_DELETE = "delete".getBytes();
    private static final byte[] CMD_INCR = "incr".getBytes();
    private static final byte[] CMD_DECR = "decr".getBytes();
    private static final byte[] CMD_TOUCH = "touch".getBytes();
    private static final byte[] SPACE = " ".getBytes();
    private static final byte[] CRLF = "\r\n".getBytes();
    private static final byte[] ZERO = "0".getBytes();
    private static final byte[] SERVER_STATUS_STORED = "STORED".getBytes();
    private static final byte[] SERVER_STATUS_DELETED = "DELETED".getBytes();
    private static final byte[] SERVER_STATUS_BYTES_NOT_FOUND = "NOT_FOUND".getBytes();
    private static final byte[] SERVER_STATUS_BYTES_TOUCHED = "TOUCHED".getBytes();
    private static final byte[] SERVER_STATUS_BYTES_ERROR = "ERROR".getBytes();
    private static final byte[] SERVER_STATUS_BYTES_CLIENT_ERROR = "CLIENT_ERROR".getBytes();
    private static final byte[] SERVER_STATUS_BYTES_SERVER_ERROR = "SERVER_ERROR".getBytes();
    private static final byte[] SERVER_STATUS_BYTES_END = "END".getBytes();
    private static final byte[] SERVER_STATUS_BYTES_VALUE = "VALUE".getBytes();
    private SockIOPool pool;
    private static final int MAX_KEYSIZE = 250;
    private static final int MAX_VALUESIZE = 1048576;
    private static final ISerializable SERIALIZER = new DefaultSerializable();

    public TextClient(MemCacheAddress[] address, int poolSize, int heartbeatSecond, boolean useNIO) {
        try {
            this.pool = new SockIOPool(address, poolSize, heartbeatSecond, useNIO);
        } catch (Exception var6) {
            log.error("初始化memcached连接池出错！" + StringUtils.join(address, ','), var6);
        }

    }

    public boolean set(String cacheKey, Object value) {
        return this.set(cacheKey, value, 0, 0);
    }

    public boolean set(String cacheKey, Byte value) {
        return this.set(cacheKey, value, 0, 4);
    }

    public boolean set(String cacheKey, Integer value) {
        return this.set(cacheKey, value, 0, 32);
    }

    public boolean set(String cacheKey, Character value) {
        return this.set(cacheKey, value, 0, 8);
    }

    public boolean set(String cacheKey, String value) {
        return this.set(cacheKey, value, 0, 512);
    }

    public boolean set(String cacheKey, StringBuffer value) {
        return this.set(cacheKey, value, 0, 1024);
    }

    public boolean set(String cacheKey, StringBuilder value) {
        return this.set(cacheKey, value, 0, 2048);
    }

    public boolean set(String cacheKey, Float value) {
        return this.set(cacheKey, value, 0, 128);
    }

    public boolean set(String cacheKey, Short value) {
        return this.set(cacheKey, value, 0, 16);
    }

    public boolean set(String cacheKey, Double value) {
        return this.set(cacheKey, value, 0, 256);
    }

    public boolean set(String cacheKey, Date value) {
        return this.set(cacheKey, value, 0, 4096);
    }

    public boolean set(String cacheKey, byte[] value) {
        return this.set(cacheKey, value, 0, 8192);
    }

    public boolean set(String cacheKey, Boolean value) {
        return this.set(cacheKey, value, 0, 2);
    }

    public boolean set(String cacheKey, Long value) {
        return this.set(cacheKey, value, 0, 64);
    }

    public boolean set(String cacheKey, Object value, int secTTL) {
        return this.set(cacheKey, value, secTTL, 0);
    }

    public boolean set(String cacheKey, Byte value, int secTTL) {
        return this.set(cacheKey, value, secTTL, 4);
    }

    public boolean set(String cacheKey, Integer value, int secTTL) {
        return this.set(cacheKey, value, secTTL, 32);
    }

    public boolean set(String cacheKey, Character value, int secTTL) {
        return this.set(cacheKey, value, secTTL, 8);
    }

    public boolean set(String cacheKey, String value, int secTTL) {
        return this.set(cacheKey, value, secTTL, 512);
    }

    public boolean set(String cacheKey, StringBuffer value, int secTTL) {
        return this.set(cacheKey, value, secTTL, 1024);
    }

    public boolean set(String cacheKey, StringBuilder value, int secTTL) {
        return this.set(cacheKey, value, secTTL, 2048);
    }

    public boolean set(String cacheKey, Float value, int secTTL) {
        return this.set(cacheKey, value, secTTL, 128);
    }

    public boolean set(String cacheKey, Short value, int secTTL) {
        return this.set(cacheKey, value, secTTL, 16);
    }

    public boolean set(String cacheKey, Double value, int secTTL) {
        return this.set(cacheKey, value, secTTL, 256);
    }

    public boolean set(String cacheKey, Date value, int secTTL) {
        return this.set(cacheKey, value, secTTL, 4096);
    }

    public boolean set(String cacheKey, byte[] value, int secTTL) {
        return this.set(cacheKey, value, secTTL, 8192);
    }

    public boolean set(String cacheKey, Boolean value, int secTTL) {
        return this.set(cacheKey, value, secTTL, 2);
    }

    public boolean set(String cacheKey, Long value, int secTTL) {
        return this.set(cacheKey, value, secTTL, 64);
    }

    private boolean set(String bizCacheKey, Object value, int secTTL, int flag) {
        if (null != bizCacheKey && null != value) {
            String cacheKey = sanitizeKey(bizCacheKey);
            if (null == cacheKey) {
                return false;
            } else {
                byte[] datas = null;
                if (flag > 1) {
                    datas = FastConvertor.encode(value, flag);
                } else {
                    datas = SERIALIZER.encode(value);
                }

                if (datas.length > 1048576) {
                    int gzipBefore = datas.length;
                    if (flag > 1) {
                        datas = SERIALIZER.encode(value);
                    }

                    datas = SERIALIZER.encodeGzip(datas);
                    flag = 1;
                    log.warn("cacheKey=" + bizCacheKey);
                    log.warn("对象过大，开启压缩，压缩前" + gzipBefore + "byte，压缩后:" + datas.length + "byte，" + (datas.length > 1048576 ? "仍不满足缓存条件！" : "满足缓存条件。"));
                }

                long cStart = System.currentTimeMillis();
                ISockIO io = this.pool.getSock(cacheKey);
                long eStart = System.currentTimeMillis();
                long cCost = eStart - cStart;
                if (null == io) {
                    log.error("从MemCache连接池获取SockIO对象为空!");
                    return false;
                } else {
                    boolean var23 = false;

                    boolean var15;
                    long eCost;
                    label241:
                    {
                        label242:
                        {
                            label243:
                            {
                                label259:
                                {
                                    label260:
                                    {
                                        try {
                                            var23 = true;
                                            io.write(CMD_SET);
                                            io.write(SPACE);
                                            io.write(cacheKey.getBytes());
                                            io.write(SPACE);
                                            io.write(IOUtil.encode((long) flag));
                                            io.write(SPACE);
                                            io.write(IOUtil.encode((long) secTTL));
                                            io.write(SPACE);
                                            io.write(IOUtil.encode((long) datas.length));
                                            io.write(CRLF);
                                            io.write(datas);
                                            io.write(CRLF);
                                            io.flush();
                                            byte[] bytes = io.readLineBytes();
                                            if (null == bytes) {
                                                log.error("服务器返回空！");
                                                var15 = false;
                                                var23 = false;
                                                break label241;
                                            }

                                            if (Arrays.equals(bytes, SERVER_STATUS_STORED)) {
                                                var15 = true;
                                                var23 = false;
                                                break label242;
                                            }

                                            if (equals(bytes, SERVER_STATUS_BYTES_CLIENT_ERROR, SERVER_STATUS_BYTES_CLIENT_ERROR.length)) {
                                                log.error("指令格式错误!" + new String(bytes));
                                                var15 = false;
                                                var23 = false;
                                                break label243;
                                            }

                                            if (equals(bytes, SERVER_STATUS_BYTES_SERVER_ERROR, SERVER_STATUS_BYTES_SERVER_ERROR.length)) {
                                                log.error("缓存服务器内部错误！" + new String(bytes));
                                                var15 = false;
                                                var23 = false;
                                                break label259;
                                            }

                                            var23 = false;
                                        } catch (Exception var24) {
                                            log.error("set时发生错误！", var24);
                                            var23 = false;
                                            break label260;
                                        } finally {
                                            if (var23) {
                                                if (null != io) {
                                                    io.release();
                                                }

                                                eCost = System.currentTimeMillis() - eStart;
                                                MemCacheFactory.performance.report("set", cacheKey, cCost, eCost);
                                            }
                                        }

                                        if (null != io) {
                                            io.release();
                                        }

                                        eCost = System.currentTimeMillis() - eStart;
                                        MemCacheFactory.performance.report("set", cacheKey, cCost, eCost);
                                        return false;
                                    }

                                    if (null != io) {
                                        io.release();
                                    }

                                    eCost = System.currentTimeMillis() - eStart;
                                    MemCacheFactory.performance.report("set", cacheKey, cCost, eCost);
                                    return false;
                                }

                                if (null != io) {
                                    io.release();
                                }

                                eCost = System.currentTimeMillis() - eStart;
                                MemCacheFactory.performance.report("set", cacheKey, cCost, eCost);
                                return var15;
                            }

                            if (null != io) {
                                io.release();
                            }

                            eCost = System.currentTimeMillis() - eStart;
                            MemCacheFactory.performance.report("set", cacheKey, cCost, eCost);
                            return var15;
                        }

                        if (null != io) {
                            io.release();
                        }

                        eCost = System.currentTimeMillis() - eStart;
                        MemCacheFactory.performance.report("set", cacheKey, cCost, eCost);
                        return var15;
                    }

                    if (null != io) {
                        io.release();
                    }

                    eCost = System.currentTimeMillis() - eStart;
                    MemCacheFactory.performance.report("set", cacheKey, cCost, eCost);
                    return var15;
                }
            }
        } else {
            return false;
        }
    }

    public boolean keyExists(String cacheKey) {
        Object value = this.get(cacheKey);
        return null != value;
    }

    public Object get(String cacheKey) {
        if (null == cacheKey) {
            return null;
        } else {
            cacheKey = sanitizeKey(cacheKey);
            if (null == cacheKey) {
                return null;
            } else {
                Object rtn = null;
                long cStart = System.currentTimeMillis();
                ISockIO io = this.pool.getSock(cacheKey);
                long eStart = System.currentTimeMillis();
                long cCost = eStart - cStart;
                if (null == io) {
                    log.error("从MemCache连接池获取SockIO对象为空!");
                    return null;
                } else {
                    boolean var24 = false;

                    Object var11;
                    long eCost;
                    label295:
                    {
                        label296:
                        {
                            label297:
                            {
                                label298:
                                {
                                    Object var16;
                                    label314:
                                    {
                                        label315:
                                        {
                                            label301:
                                            {
                                                try {
                                                    var24 = true;
                                                    io.write(CMD_GET);
                                                    io.write(SPACE);
                                                    io.write(cacheKey.getBytes());
                                                    io.write(CRLF);
                                                    io.flush();
                                                    byte[] bytes = io.readLineBytes();
                                                    if (null != bytes) {
                                                        if (Arrays.equals(bytes, SERVER_STATUS_BYTES_ERROR)) {
                                                            log.error("未知的指令！服务器返回信息:" + new String(bytes) + " KEY:" + cacheKey);
                                                            var11 = null;
                                                            var24 = false;
                                                            break label295;
                                                        }

                                                        if (equals(bytes, SERVER_STATUS_BYTES_CLIENT_ERROR, SERVER_STATUS_BYTES_CLIENT_ERROR.length)) {
                                                            log.error("指令格式错误!" + new String(bytes));
                                                            var11 = null;
                                                            var24 = false;
                                                            break label296;
                                                        }

                                                        if (equals(bytes, SERVER_STATUS_BYTES_SERVER_ERROR, SERVER_STATUS_BYTES_SERVER_ERROR.length)) {
                                                            log.error("缓存服务器内部错误！" + new String(bytes));
                                                            var11 = null;
                                                            var24 = false;
                                                            break label297;
                                                        }

                                                        if (Arrays.equals(bytes, SERVER_STATUS_BYTES_END)) {
                                                            var11 = null;
                                                            var24 = false;
                                                            break label298;
                                                        }

                                                        if (!equals(bytes, SERVER_STATUS_BYTES_VALUE, SERVER_STATUS_BYTES_VALUE.length)) {
                                                            var24 = false;
                                                            break label315;
                                                        }

                                                        int[] part = cmdParse2(bytes);
                                                        int flag = part[0];
                                                        int size = part[1];
                                                        byte[] datas = new byte[size];

                                                        for (int cnt = 0; cnt < size; cnt += io.read(datas, cnt, size - cnt)) {
                                                        }

                                                        io.readLineBytes();
                                                        bytes = io.readLineBytes();
                                                        if (null == bytes) {
                                                            var16 = rtn;
                                                            var24 = false;
                                                            break label314;
                                                        }

                                                        if (Arrays.equals(bytes, SERVER_STATUS_BYTES_END)) {
                                                            if (flag > 1) {
                                                                rtn = FastConvertor.decode(datas, flag);
                                                                var24 = false;
                                                            } else if (flag == 1) {
                                                                datas = SERIALIZER.decodeGzip(datas);
                                                                rtn = SERIALIZER.decode(datas);
                                                                var24 = false;
                                                            } else {
                                                                rtn = SERIALIZER.decode(datas);
                                                                var24 = false;
                                                            }
                                                        } else {
                                                            var24 = false;
                                                        }
                                                        break label315;
                                                    }

                                                    log.error("服务端返回空!");
                                                    var11 = null;
                                                    var24 = false;
                                                } catch (Exception var25) {
                                                    log.error("get指令发生错误!", var25);
                                                    var24 = false;
                                                    break label301;
                                                } finally {
                                                    if (var24) {
                                                        if (null != io) {
                                                            io.release();
                                                        }

                                                        eCost = System.currentTimeMillis() - eStart;
                                                        MemCacheFactory.performance.report("get", cacheKey, cCost, eCost);
                                                    }
                                                }

                                                if (null != io) {
                                                    io.release();
                                                }

                                                eCost = System.currentTimeMillis() - eStart;
                                                MemCacheFactory.performance.report("get", cacheKey, cCost, eCost);
                                                return var11;
                                            }

                                            if (null != io) {
                                                io.release();
                                            }

                                            eCost = System.currentTimeMillis() - eStart;
                                            MemCacheFactory.performance.report("get", cacheKey, cCost, eCost);
                                            return rtn;
                                        }

                                        if (null != io) {
                                            io.release();
                                        }

                                        eCost = System.currentTimeMillis() - eStart;
                                        MemCacheFactory.performance.report("get", cacheKey, cCost, eCost);
                                        return rtn;
                                    }

                                    if (null != io) {
                                        io.release();
                                    }

                                    eCost = System.currentTimeMillis() - eStart;
                                    MemCacheFactory.performance.report("get", cacheKey, cCost, eCost);
                                    return var16;
                                }

                                if (null != io) {
                                    io.release();
                                }

                                eCost = System.currentTimeMillis() - eStart;
                                MemCacheFactory.performance.report("get", cacheKey, cCost, eCost);
                                return var11;
                            }

                            if (null != io) {
                                io.release();
                            }

                            eCost = System.currentTimeMillis() - eStart;
                            MemCacheFactory.performance.report("get", cacheKey, cCost, eCost);
                            return var11;
                        }

                        if (null != io) {
                            io.release();
                        }

                        eCost = System.currentTimeMillis() - eStart;
                        MemCacheFactory.performance.report("get", cacheKey, cCost, eCost);
                        return var11;
                    }

                    if (null != io) {
                        io.release();
                    }

                    eCost = System.currentTimeMillis() - eStart;
                    MemCacheFactory.performance.report("get", cacheKey, cCost, eCost);
                    return var11;
                }
            }
        }
    }

    public boolean add(String cacheKey, long value) {
        return this.add(cacheKey, value, 0);
    }

    public boolean add(String cacheKey, long value, int secTTL) {
        if (null == cacheKey) {
            return false;
        } else {
            cacheKey = sanitizeKey(cacheKey);
            if (null == cacheKey) {
                return false;
            } else {
                int flag = 64;
                long cStart = System.currentTimeMillis();
                ISockIO io = this.pool.getSock(cacheKey);
                long eStart = System.currentTimeMillis();
                long cCost = eStart - cStart;
                if (null == io) {
                    log.error("从MemCache连接池获取SockIO对象为空!");
                    return false;
                } else {
                    boolean var23 = false;

                    long eCost;
                    label201:
                    {
                        boolean var15;
                        label202:
                        {
                            label203:
                            {
                                label204:
                                {
                                    label216:
                                    {
                                        try {
                                            var23 = true;
                                            byte[] datas = FastConvertor.encode(value, flag);
                                            io.write(CMD_ADD);
                                            io.write(SPACE);
                                            io.write(cacheKey.getBytes());
                                            io.write(SPACE);
                                            io.write(IOUtil.encode((long) flag));
                                            io.write(SPACE);
                                            io.write(IOUtil.encode((long) secTTL));
                                            io.write(SPACE);
                                            io.write(IOUtil.encode((long) datas.length));
                                            io.write(CRLF);
                                            io.write(datas);
                                            io.write(CRLF);
                                            io.flush();
                                            byte[] bytes = io.readLineBytes();
                                            if (null == bytes) {
                                                log.error("服务端返回空!");
                                                var15 = false;
                                                var23 = false;
                                                break label202;
                                            }

                                            if (Arrays.equals(bytes, SERVER_STATUS_STORED)) {
                                                var15 = true;
                                                var23 = false;
                                                break label203;
                                            }

                                            if (equals(bytes, SERVER_STATUS_BYTES_CLIENT_ERROR, SERVER_STATUS_BYTES_CLIENT_ERROR.length)) {
                                                log.error("指令格式错误!" + new String(bytes));
                                                var15 = false;
                                                var23 = false;
                                                break label204;
                                            }

                                            if (!equals(bytes, SERVER_STATUS_BYTES_SERVER_ERROR, SERVER_STATUS_BYTES_SERVER_ERROR.length)) {
                                                var23 = false;
                                                break label201;
                                            }

                                            log.error("缓存服务器内部错误！" + new String(bytes));
                                            var15 = false;
                                            var23 = false;
                                            break label216;
                                        } catch (Exception var24) {
                                            log.error("add发生错误！", var24);
                                            var23 = false;
                                        } finally {
                                            if (var23) {
                                                if (null != io) {
                                                    io.release();
                                                }

                                                eCost = System.currentTimeMillis() - eStart;
                                                MemCacheFactory.performance.report("add", cacheKey, cCost, eCost);
                                            }
                                        }

                                        if (null != io) {
                                            io.release();
                                        }

                                        eCost = System.currentTimeMillis() - eStart;
                                        MemCacheFactory.performance.report("add", cacheKey, cCost, eCost);
                                        return false;
                                    }

                                    if (null != io) {
                                        io.release();
                                    }

                                    eCost = System.currentTimeMillis() - eStart;
                                    MemCacheFactory.performance.report("add", cacheKey, cCost, eCost);
                                    return var15;
                                }

                                if (null != io) {
                                    io.release();
                                }

                                eCost = System.currentTimeMillis() - eStart;
                                MemCacheFactory.performance.report("add", cacheKey, cCost, eCost);
                                return var15;
                            }

                            if (null != io) {
                                io.release();
                            }

                            eCost = System.currentTimeMillis() - eStart;
                            MemCacheFactory.performance.report("add", cacheKey, cCost, eCost);
                            return var15;
                        }

                        if (null != io) {
                            io.release();
                        }

                        eCost = System.currentTimeMillis() - eStart;
                        MemCacheFactory.performance.report("add", cacheKey, cCost, eCost);
                        return var15;
                    }

                    if (null != io) {
                        io.release();
                    }

                    eCost = System.currentTimeMillis() - eStart;
                    MemCacheFactory.performance.report("add", cacheKey, cCost, eCost);
                    return false;
                }
            }
        }
    }

    public boolean append(String cacheKey, byte[] bytes) {
        if (null == cacheKey) {
            return false;
        } else {
            cacheKey = sanitizeKey(cacheKey);
            if (null == cacheKey) {
                return false;
            } else {
                long cStart = System.currentTimeMillis();
                ISockIO io = this.pool.getSock(cacheKey);
                long eStart = System.currentTimeMillis();
                long cCost = eStart - cStart;
                if (null == io) {
                    log.error("从MemCache连接池获取SockIO对象为空!");
                    return false;
                } else {
                    boolean var19 = false;

                    boolean var11;
                    long eCost;
                    label176:
                    {
                        label177:
                        {
                            label192:
                            {
                                label193:
                                {
                                    try {
                                        var19 = true;
                                        io.write(CMD_APPEND);
                                        io.write(SPACE);
                                        io.write(cacheKey.getBytes());
                                        io.write(SPACE);
                                        io.write(IOUtil.encode(8192L));
                                        io.write(SPACE);
                                        io.write(ZERO);
                                        io.write(SPACE);
                                        io.write(IOUtil.encode((long) bytes.length));
                                        io.write(CRLF);
                                        io.write(bytes);
                                        io.write(CRLF);
                                        io.flush();
                                        byte[] cmd = io.readLineBytes();
                                        if (Arrays.equals(cmd, SERVER_STATUS_STORED)) {
                                            var11 = true;
                                            var19 = false;
                                            break label176;
                                        }

                                        if (equals(bytes, SERVER_STATUS_BYTES_CLIENT_ERROR, SERVER_STATUS_BYTES_CLIENT_ERROR.length)) {
                                            log.error("指令格式错误!" + new String(bytes));
                                            var11 = false;
                                            var19 = false;
                                            break label177;
                                        }

                                        if (equals(bytes, SERVER_STATUS_BYTES_SERVER_ERROR, SERVER_STATUS_BYTES_SERVER_ERROR.length)) {
                                            log.error("缓存服务器内部错误！" + new String(bytes));
                                            var11 = false;
                                            var19 = false;
                                            break label192;
                                        }

                                        var19 = false;
                                    } catch (Exception var20) {
                                        log.error("append发生错误！", var20);
                                        var19 = false;
                                        break label193;
                                    } finally {
                                        if (var19) {
                                            if (null != io) {
                                                io.release();
                                            }

                                            eCost = System.currentTimeMillis() - eStart;
                                            MemCacheFactory.performance.report("append", cacheKey, cCost, eCost);
                                        }
                                    }

                                    if (null != io) {
                                        io.release();
                                    }

                                    eCost = System.currentTimeMillis() - eStart;
                                    MemCacheFactory.performance.report("append", cacheKey, cCost, eCost);
                                    return false;
                                }

                                if (null != io) {
                                    io.release();
                                }

                                eCost = System.currentTimeMillis() - eStart;
                                MemCacheFactory.performance.report("append", cacheKey, cCost, eCost);
                                return false;
                            }

                            if (null != io) {
                                io.release();
                            }

                            eCost = System.currentTimeMillis() - eStart;
                            MemCacheFactory.performance.report("append", cacheKey, cCost, eCost);
                            return var11;
                        }

                        if (null != io) {
                            io.release();
                        }

                        eCost = System.currentTimeMillis() - eStart;
                        MemCacheFactory.performance.report("append", cacheKey, cCost, eCost);
                        return var11;
                    }

                    if (null != io) {
                        io.release();
                    }

                    eCost = System.currentTimeMillis() - eStart;
                    MemCacheFactory.performance.report("append", cacheKey, cCost, eCost);
                    return var11;
                }
            }
        }
    }

    public boolean delete(String cacheKey) {
        if (null == cacheKey) {
            return false;
        } else {
            cacheKey = sanitizeKey(cacheKey);
            if (null == cacheKey) {
                return false;
            } else {
                long cStart = System.currentTimeMillis();
                ISockIO io = this.pool.getSock(cacheKey);
                long eStart = System.currentTimeMillis();
                long cCost = eStart - cStart;
                if (null == io) {
                    log.error("从MemCache连接池获取SockIO对象为空!");
                    return false;
                } else {
                    boolean var18 = false;

                    boolean var10;
                    long eCost;
                    label176:
                    {
                        label177:
                        {
                            label192:
                            {
                                label193:
                                {
                                    try {
                                        var18 = true;
                                        io.write(CMD_DELETE);
                                        io.write(SPACE);
                                        io.write(cacheKey.getBytes());
                                        io.write(CRLF);
                                        io.flush();
                                        byte[] bytes = io.readLineBytes();
                                        if (Arrays.equals(bytes, SERVER_STATUS_DELETED)) {
                                            var10 = true;
                                            var18 = false;
                                            break label176;
                                        }

                                        if (equals(bytes, SERVER_STATUS_BYTES_CLIENT_ERROR, SERVER_STATUS_BYTES_CLIENT_ERROR.length)) {
                                            log.error("指令格式错误!" + new String(bytes));
                                            var10 = false;
                                            var18 = false;
                                            break label177;
                                        }

                                        if (equals(bytes, SERVER_STATUS_BYTES_SERVER_ERROR, SERVER_STATUS_BYTES_SERVER_ERROR.length)) {
                                            log.error("缓存服务器内部错误！" + new String(bytes));
                                            var10 = false;
                                            var18 = false;
                                            break label192;
                                        }

                                        var18 = false;
                                    } catch (Exception var19) {
                                        log.error("delete发生错误！", var19);
                                        var18 = false;
                                        break label193;
                                    } finally {
                                        if (var18) {
                                            if (null != io) {
                                                io.release();
                                            }

                                            eCost = System.currentTimeMillis() - eStart;
                                            MemCacheFactory.performance.report("delete", cacheKey, cCost, eCost);
                                        }
                                    }

                                    if (null != io) {
                                        io.release();
                                    }

                                    eCost = System.currentTimeMillis() - eStart;
                                    MemCacheFactory.performance.report("delete", cacheKey, cCost, eCost);
                                    return false;
                                }

                                if (null != io) {
                                    io.release();
                                }

                                eCost = System.currentTimeMillis() - eStart;
                                MemCacheFactory.performance.report("delete", cacheKey, cCost, eCost);
                                return false;
                            }

                            if (null != io) {
                                io.release();
                            }

                            eCost = System.currentTimeMillis() - eStart;
                            MemCacheFactory.performance.report("delete", cacheKey, cCost, eCost);
                            return var10;
                        }

                        if (null != io) {
                            io.release();
                        }

                        eCost = System.currentTimeMillis() - eStart;
                        MemCacheFactory.performance.report("delete", cacheKey, cCost, eCost);
                        return var10;
                    }

                    if (null != io) {
                        io.release();
                    }

                    eCost = System.currentTimeMillis() - eStart;
                    MemCacheFactory.performance.report("delete", cacheKey, cCost, eCost);
                    return var10;
                }
            }
        }
    }

    public long incr(String cacheKey) {
        return this.incrWithTTL(cacheKey, 1, 0);
    }

    public long incr(String cacheKey, int inc) {
        return this.incrWithTTL(cacheKey, inc, 0);
    }

    public long incrWithTTL(String cacheKey, int secTTL) {
        return this.incrWithTTL(cacheKey, 1, secTTL);
    }

    public long incrWithTTL(String cacheKey, int inc, int secTTL) {
        if (null == cacheKey) {
            return -1L;
        } else {
            cacheKey = sanitizeKey(cacheKey);
            if (null == cacheKey) {
                return -1L;
            } else {
                long cStart = System.currentTimeMillis();
                ISockIO io = this.pool.getSock(cacheKey);
                long eStart = System.currentTimeMillis();
                long cCost = eStart - cStart;
                if (null == io) {
                    log.error("从MemCache连接池获取SockIO对象为空!");
                    return 0L;
                } else {
                    boolean var23 = false;

                    long var14;
                    long eCost;
                    label242:
                    {
                        label226:
                        {
                            label227:
                            {
                                label243:
                                {
                                    label244:
                                    {
                                        label230:
                                        {
                                            long var13;
                                            try {
                                                var23 = true;
                                                byte[] data = String.valueOf(inc).getBytes();
                                                io.write(CMD_INCR);
                                                io.write(SPACE);
                                                io.write(cacheKey.getBytes());
                                                io.write(SPACE);
                                                io.write(data);
                                                io.write(CRLF);
                                                io.flush();
                                                byte[] bytes = io.readLineBytes();
                                                if (Arrays.equals(bytes, SERVER_STATUS_BYTES_NOT_FOUND)) {
                                                    io.write(CMD_ADD);
                                                    io.write(SPACE);
                                                    io.write(cacheKey.getBytes());
                                                    io.write(SPACE);
                                                    io.write(IOUtil.encode(512L));
                                                    io.write(SPACE);
                                                    io.write(IOUtil.encode((long) secTTL));
                                                    io.write(SPACE);
                                                    io.write(IOUtil.encode((long) data.length));
                                                    io.write(CRLF);
                                                    io.write(data);
                                                    io.write(CRLF);
                                                    io.flush();
                                                    byte[] cmd = io.readLineBytes();
                                                    if (null == cmd) {
                                                        log.error("服务端返回空!");
                                                        var14 = -1L;
                                                        var23 = false;
                                                        break label242;
                                                    }

                                                    if (Arrays.equals(cmd, SERVER_STATUS_STORED)) {
                                                        var14 = (long) inc;
                                                        var23 = false;
                                                        break label226;
                                                    }

                                                    if (equals(cmd, SERVER_STATUS_BYTES_CLIENT_ERROR, SERVER_STATUS_BYTES_CLIENT_ERROR.length)) {
                                                        log.error("指令格式错误!" + new String(cmd));
                                                        var14 = -1L;
                                                        var23 = false;
                                                        break label227;
                                                    }

                                                    if (equals(cmd, SERVER_STATUS_BYTES_SERVER_ERROR, SERVER_STATUS_BYTES_SERVER_ERROR.length)) {
                                                        log.error("缓存服务器内部错误！" + new String(cmd));
                                                        var14 = -1L;
                                                        var23 = false;
                                                        break label243;
                                                    }

                                                    var23 = false;
                                                    break label244;
                                                }

                                                var13 = FastConvertor.strBytes2Long(bytes);
                                                var23 = false;
                                            } catch (Exception var24) {
                                                log.error("incrWithTTL发生错误！", var24);
                                                var23 = false;
                                                break label230;
                                            } finally {
                                                if (var23) {
                                                    if (null != io) {
                                                        io.release();
                                                    }

                                                    eCost = System.currentTimeMillis() - eStart;
                                                    MemCacheFactory.performance.report("incr", cacheKey, cCost, eCost);
                                                }
                                            }

                                            if (null != io) {
                                                io.release();
                                            }

                                            eCost = System.currentTimeMillis() - eStart;
                                            MemCacheFactory.performance.report("incr", cacheKey, cCost, eCost);
                                            return var13;
                                        }

                                        if (null != io) {
                                            io.release();
                                        }

                                        eCost = System.currentTimeMillis() - eStart;
                                        MemCacheFactory.performance.report("incr", cacheKey, cCost, eCost);
                                        return -1L;
                                    }

                                    if (null != io) {
                                        io.release();
                                    }

                                    eCost = System.currentTimeMillis() - eStart;
                                    MemCacheFactory.performance.report("incr", cacheKey, cCost, eCost);
                                    return -1L;
                                }

                                if (null != io) {
                                    io.release();
                                }

                                eCost = System.currentTimeMillis() - eStart;
                                MemCacheFactory.performance.report("incr", cacheKey, cCost, eCost);
                                return var14;
                            }

                            if (null != io) {
                                io.release();
                            }

                            eCost = System.currentTimeMillis() - eStart;
                            MemCacheFactory.performance.report("incr", cacheKey, cCost, eCost);
                            return var14;
                        }

                        if (null != io) {
                            io.release();
                        }

                        eCost = System.currentTimeMillis() - eStart;
                        MemCacheFactory.performance.report("incr", cacheKey, cCost, eCost);
                        return var14;
                    }

                    if (null != io) {
                        io.release();
                    }

                    eCost = System.currentTimeMillis() - eStart;
                    MemCacheFactory.performance.report("incr", cacheKey, cCost, eCost);
                    return var14;
                }
            }
        }
    }

    public long decr(String cacheKey) {
        return this.decr(cacheKey, 1);
    }

    public long decr(String cacheKey, int inc) {
        if (null == cacheKey) {
            return -1L;
        } else {
            cacheKey = sanitizeKey(cacheKey);
            if (null == cacheKey) {
                return -1L;
            } else {
                long cStart = System.currentTimeMillis();
                ISockIO io = this.pool.getSock(cacheKey);
                long eStart = System.currentTimeMillis();
                long cCost = eStart - cStart;
                if (null == io) {
                    log.error("从MemCache连接池获取SockIO对象为空!");
                    return 0L;
                } else {
                    boolean var20 = false;

                    label172:
                    {
                        long var11;
                        long eCost;
                        label173:
                        {
                            label174:
                            {
                                label175:
                                {
                                    try {
                                        var20 = true;
                                        io.write(CMD_DECR);
                                        io.write(SPACE);
                                        io.write(cacheKey.getBytes());
                                        io.write(SPACE);
                                        io.write(IOUtil.encode((long) inc));
                                        io.write(CRLF);
                                        io.flush();
                                        byte[] bytes = io.readLineBytes();
                                        if (Arrays.equals(bytes, SERVER_STATUS_BYTES_NOT_FOUND)) {
                                            var11 = -1L;
                                            var20 = false;
                                            break label173;
                                        }

                                        if (equals(bytes, SERVER_STATUS_BYTES_CLIENT_ERROR, SERVER_STATUS_BYTES_CLIENT_ERROR.length)) {
                                            log.error("指令格式错误!" + new String(bytes));
                                            var11 = -1L;
                                            var20 = false;
                                            break label174;
                                        }

                                        if (equals(bytes, SERVER_STATUS_BYTES_SERVER_ERROR, SERVER_STATUS_BYTES_SERVER_ERROR.length)) {
                                            log.error("缓存服务器内部错误！" + new String(bytes));
                                            var11 = -1L;
                                            var20 = false;
                                            break label175;
                                        }

                                        var11 = FastConvertor.strBytes2Long(bytes);
                                        var20 = false;
                                    } catch (Exception var21) {
                                        log.error("decr发生错误！", var21);
                                        var20 = false;
                                        break label172;
                                    } finally {
                                        if (var20) {
                                            if (null != io) {
                                                io.release();
                                            }

                                            eCost = System.currentTimeMillis() - eStart;
                                            MemCacheFactory.performance.report("decr", cacheKey, cCost, eCost);
                                        }
                                    }

                                    if (null != io) {
                                        io.release();
                                    }

                                    eCost = System.currentTimeMillis() - eStart;
                                    MemCacheFactory.performance.report("decr", cacheKey, cCost, eCost);
                                    return var11;
                                }

                                if (null != io) {
                                    io.release();
                                }

                                eCost = System.currentTimeMillis() - eStart;
                                MemCacheFactory.performance.report("decr", cacheKey, cCost, eCost);
                                return var11;
                            }

                            if (null != io) {
                                io.release();
                            }

                            eCost = System.currentTimeMillis() - eStart;
                            MemCacheFactory.performance.report("decr", cacheKey, cCost, eCost);
                            return var11;
                        }

                        if (null != io) {
                            io.release();
                        }

                        eCost = System.currentTimeMillis() - eStart;
                        MemCacheFactory.performance.report("decr", cacheKey, cCost, eCost);
                        return var11;
                    }

                    if (null != io) {
                        io.release();
                    }

                    long eCost = System.currentTimeMillis() - eStart;
                    MemCacheFactory.performance.report("decr", cacheKey, cCost, eCost);
                    return -1L;
                }
            }
        }
    }

    public boolean touch(String cacheKey, int secTTL) {
        if (null == cacheKey) {
            return false;
        } else {
            cacheKey = sanitizeKey(cacheKey);
            if (null == cacheKey) {
                return false;
            } else {
                long cStart = System.currentTimeMillis();
                ISockIO io = this.pool.getSock(cacheKey);
                long eStart = System.currentTimeMillis();
                long cCost = eStart - cStart;
                if (null == io) {
                    log.error("从MemCache连接池获取SockIO对象为空!");
                    return false;
                } else {
                    boolean var19 = false;

                    long eCost;
                    label201:
                    {
                        boolean var11;
                        label202:
                        {
                            label203:
                            {
                                label204:
                                {
                                    label216:
                                    {
                                        try {
                                            var19 = true;
                                            io.write(CMD_TOUCH);
                                            io.write(SPACE);
                                            io.write(cacheKey.getBytes());
                                            io.write(SPACE);
                                            io.write(IOUtil.encode((long) secTTL));
                                            io.write(CRLF);
                                            io.flush();
                                            byte[] bytes = io.readLineBytes();
                                            if (Arrays.equals(bytes, SERVER_STATUS_BYTES_ERROR)) {
                                                log.error("缓存服务器接收到不可识别的指令TOUCH，请确认服务器版本>=1.4.15，服务端返回：" + new String(bytes));
                                                var11 = false;
                                                var19 = false;
                                                break label202;
                                            }

                                            if (equals(bytes, SERVER_STATUS_BYTES_CLIENT_ERROR, SERVER_STATUS_BYTES_CLIENT_ERROR.length)) {
                                                log.error("指令格式错误!" + new String(bytes));
                                                var11 = false;
                                                var19 = false;
                                                break label203;
                                            }

                                            if (equals(bytes, SERVER_STATUS_BYTES_SERVER_ERROR, SERVER_STATUS_BYTES_SERVER_ERROR.length)) {
                                                log.error("缓存服务器内部错误！" + new String(bytes));
                                                var11 = false;
                                                var19 = false;
                                                break label204;
                                            }

                                            if (!Arrays.equals(bytes, SERVER_STATUS_BYTES_TOUCHED)) {
                                                var19 = false;
                                                break label201;
                                            }

                                            var11 = true;
                                            var19 = false;
                                            break label216;
                                        } catch (Exception var20) {
                                            log.error("touch发生错误！", var20);
                                            var19 = false;
                                        } finally {
                                            if (var19) {
                                                if (null != io) {
                                                    io.release();
                                                }

                                                eCost = System.currentTimeMillis() - eStart;
                                                MemCacheFactory.performance.report("touch", cacheKey, cCost, eCost);
                                            }
                                        }

                                        if (null != io) {
                                            io.release();
                                        }

                                        eCost = System.currentTimeMillis() - eStart;
                                        MemCacheFactory.performance.report("touch", cacheKey, cCost, eCost);
                                        return false;
                                    }

                                    if (null != io) {
                                        io.release();
                                    }

                                    eCost = System.currentTimeMillis() - eStart;
                                    MemCacheFactory.performance.report("touch", cacheKey, cCost, eCost);
                                    return var11;
                                }

                                if (null != io) {
                                    io.release();
                                }

                                eCost = System.currentTimeMillis() - eStart;
                                MemCacheFactory.performance.report("touch", cacheKey, cCost, eCost);
                                return var11;
                            }

                            if (null != io) {
                                io.release();
                            }

                            eCost = System.currentTimeMillis() - eStart;
                            MemCacheFactory.performance.report("touch", cacheKey, cCost, eCost);
                            return var11;
                        }

                        if (null != io) {
                            io.release();
                        }

                        eCost = System.currentTimeMillis() - eStart;
                        MemCacheFactory.performance.report("touch", cacheKey, cCost, eCost);
                        return var11;
                    }

                    if (null != io) {
                        io.release();
                    }

                    eCost = System.currentTimeMillis() - eStart;
                    MemCacheFactory.performance.report("touch", cacheKey, cCost, eCost);
                    return false;
                }
            }
        }
    }

    private static final boolean equals(byte[] b1, byte[] b2, int len) {
        if (b1.length >= len && b2.length >= len) {
            for (int i = 0; i < len; ++i) {
                if (b1[i] != b2[i]) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    private static final int[] cmdParse2(byte[] bytes) {
        int flagStart = 0;
        int flagEnd = 0;
        int sizeStart = 0;
        int sizeEnd = 0;
        int count = 0;

        int i;
        for (i = 5; i < bytes.length; ++i) {
            if (32 == bytes[i]) {
                ++count;
                if (2 == count) {
                    flagStart = i + 1;
                }

                if (3 == count) {
                    flagEnd = i;
                    sizeStart = i + 1;
                    sizeEnd = bytes.length;
                }
            }
        }

        i = FastConvertor.strBytes2Int(bytes, flagStart, flagEnd);
        int size = FastConvertor.strBytes2Int(bytes, sizeStart, sizeEnd);
        return new int[]{i, size};
    }

    private static final String sanitizeKey(String rawKey) {
        String rtn = null;

        try {
            rtn = URLEncoder.encode(rawKey, "UTF-8");
            if (250 < rtn.length()) {
                rtn = MD5Util.hexdigest(rtn);
            }
        } catch (UnsupportedEncodingException var3) {
            log.error("编码时发生错误!", var3);
        }

        return rtn;
    }

}

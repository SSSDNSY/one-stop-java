/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sssdnsy.cache.memcache.interfaces;

import java.util.Date;

/**
 * @class middleware.cache.memcache.interfaces.IMemche
 * @desc
 * @since 2020-10-20
 */
public interface IMemCache {
    boolean keyExists(String key);

    boolean add(String key, long value);

    boolean add(String key, long value, int var4);

    boolean append(String key, byte[] value);

    Object get(String key);

    boolean set(String key, Object value);

    boolean set(String key, Byte value);

    boolean set(String key, Integer value);

    boolean set(String key, Character value);

    boolean set(String key, String value);

    boolean set(String key, StringBuffer value);

    boolean set(String key, StringBuilder value);

    boolean set(String key, Float value);

    boolean set(String key, Short value);

    boolean set(String key, Double value);

    boolean set(String key, Date value);

    boolean set(String key, byte[] value);

    boolean set(String key, Boolean value);

    boolean set(String key, Long value);

    boolean set(String key, Byte value, int expire);

    boolean set(String key, Integer value, int expire);

    boolean set(String key, Character value, int expire);

    boolean set(String key, String value, int expire);

    boolean set(String key, StringBuffer value, int expire);

    boolean set(String key, StringBuilder value, int expire);

    boolean set(String key, Float value, int expire);

    boolean set(String key, Short value, int expire);

    boolean set(String key, Double value, int expire);

    boolean set(String key, Date value, int expire);

    boolean set(String key, byte[] value, int expire);

    boolean set(String key, Boolean value, int expire);

    boolean set(String key, Long value, int expire);

    boolean set(String key, Object value, int expire);

    boolean delete(String key);

    long incr(String key);

    long incr(String key, int value);

    long incrWithTTL(String key, int value);

    long incrWithTTL(String key, int value, int expire);

    long decr(String key);

    long decr(String key, int value);

    boolean touch(String key, int value);
}

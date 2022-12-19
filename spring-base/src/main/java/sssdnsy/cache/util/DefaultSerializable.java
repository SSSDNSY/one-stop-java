/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sssdnsy.cache.util;

/**
 * @class middleware.cache.util.DefaultSerializable
 * @desc
 * @since 2020-10-20
 */
public class DefaultSerializable extends AbstractSerializable {

    @Override
    public byte[] encode(Object var1) {
        return new byte[0];
    }

    @Override
    public Object decode(byte[] var1) {
        return null;
    }
}

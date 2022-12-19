package middleware.cache.util;

/**
 * @class middleware.cache.util.ISerializable
 * @desc
 * @since 2020-10-20
 */
public interface ISerializable {
    byte[] encode(Object var1);

    byte[] encodeGzip(byte[] var1);

    Object decode(byte[] var1);

    byte[] decodeGzip(byte[] var1);
}

package middleware.cache.util;

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

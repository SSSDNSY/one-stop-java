package middleware.cache.memcache.driver.util;

import java.util.Date;

/**
 * @class middleware.cache.memcache.driver.util.FastConertor
 * @desc 快速转换器
 * @since 2020-10-20
 */
public class FastConvertor {
    public static final int MARKER_COMPRESS = 1;
    public static final int MARKER_BOOLEAN = 2;
    public static final int MARKER_BYTE = 4;
    public static final int MARKER_CHARACTER = 8;
    public static final int MARKER_SHORT = 16;
    public static final int MARKER_INTEGER = 32;
    public static final int MARKER_LONG = 64;
    public static final int MARKER_FLOAT = 128;
    public static final int MARKER_DOUBLE = 256;
    public static final int MARKER_STRING = 512;
    public static final int MARKER_STRINGBUFFER = 1024;
    public static final int MARKER_STRINGBUILDER = 2048;
    public static final int MARKER_DATE = 4096;
    public static final int MARKER_BYTEARR = 8192;
    public static final int MARKER_OTHERS = 0;

    public FastConvertor() {
    }

    public static final byte[] encode(Byte value) {
        byte[] b = new byte[]{value};
        return b;
    }

    public static final byte[] encode(Object obj, int flag) {
        byte[] datas = null;
        switch (flag) {
            case 2:
                datas = encode((Boolean) obj);
                break;
            case 4:
                datas = encode((Byte) obj);
                break;
            case 8:
                datas = encode((Character) obj);
                break;
            case 16:
                datas = encode((Short) obj);
                break;
            case 32:
                datas = encode((Integer) obj);
                break;
            case 64:
                datas = encode((Long) obj);
                break;
            case 128:
                datas = encode((Float) obj);
                break;
            case 256:
                datas = encode((Double) obj);
                break;
            case 512:
                datas = encode((String) obj);
                break;
            case 1024:
                datas = encode((StringBuffer) obj);
                break;
            case 2048:
                datas = encode((StringBuilder) obj);
                break;
            case 4096:
                datas = encode((Date) obj);
                break;
            case 8192:
                datas = encode((byte[]) ((byte[]) obj));
        }

        return datas;
    }

    public static final byte[] encode(Boolean value) {
        byte[] b = new byte[1];
        if (value) {
            b[0] = 1;
        } else {
            b[0] = 0;
        }

        return b;
    }

    public static final byte[] encode(int value) {
        return getBytes(value);
    }

    public static final byte[] encode(long value) {
        return getBytes(value);
    }

    public static final byte[] encode(Date value) {
        return getBytes(value.getTime());
    }

    public static final byte[] encode(Character value) {
        return encode(value);
    }

    public static final byte[] encode(String value) {
        return value.getBytes();
    }

    public static final byte[] encode(StringBuffer value) {
        return encode(value.toString());
    }

    public static final byte[] encode(float value) {
        return encode(Float.floatToIntBits(value));
    }

    public static final byte[] encode(Short value) {
        return encode(value);
    }

    public static final byte[] encode(double value) {
        return encode(Double.doubleToLongBits(value));
    }

    public static final byte[] encode(StringBuilder value) {
        return encode(value.toString());
    }

    public static final byte[] encode(byte[] value) {
        return value;
    }

    public static final byte[] getBytes(int value) {
        byte[] b = new byte[]{(byte) (value >> 24 & 255),
                (byte) (value >> 16 & 255),
                (byte) (value >> 8 & 255), (byte) (value >> 0 & 255)};
        return b;
    }

    public static final byte[] getBytes(long value) {
        byte[] b = new byte[]{(byte) ((int) (value >> 56 & 255L)),
                (byte) ((int) (value >> 48 & 255L)),
                (byte) ((int) (value >> 40 & 255L)),
                (byte) ((int) (value >> 32 & 255L)),
                (byte) ((int) (value >> 24 & 255L)),
                (byte) ((int) (value >> 16 & 255L)),
                (byte) ((int) (value >> 8 & 255L)),
                (byte) ((int) (value >> 0 & 255L))};
        return b;
    }

    public static final long toLong(byte[] b) {
        return (((long) b[0] & 255L) << 56) +
                (((long) b[1] & 255L) << 48) +
                (((long) b[2] & 255L) << 40) +
                (((long) b[3] & 255L) << 32) +
                (((long) b[4] & 255L) << 24) +
                (((long) b[5] & 255L) << 16) +
                (((long) b[6] & 255L) << 8) +
                (((long) b[7] & 255L) << 0);
    }

    public static final Object decode(byte[] datas, int flag) {
        Object obj = null;
        switch (flag) {
            case 2:
                obj = decodeBoolean(datas);
                break;
            case 4:
                obj = decodeByte(datas);
                break;
            case 8:
                obj = decodeCharacter(datas);
                break;
            case 16:
                obj = decodeShort(datas);
                break;
            case 32:
                obj = decodeInteger(datas);
                break;
            case 64:
                obj = decodeLong(datas);
                break;
            case 128:
                obj = decodeFloat(datas);
                break;
            case 256:
                obj = decodeDouble(datas);
                break;
            case 512:
                obj = decodeString(datas);
                break;
            case 1024:
                obj = decodeStringBuffer(datas);
                break;
            case 2048:
                obj = decodeStringBuilder(datas);
                break;
            case 4096:
                obj = decodeDate(datas);
                break;
            case 8192:
                obj = decodeByteArr(datas);
        }

        return obj;
    }

    public static final Byte decodeByte(byte[] b) {
        return new Byte(b[0]);
    }

    public static final Boolean decodeBoolean(byte[] b) {
        boolean value = b[0] == 1;
        return value ? Boolean.TRUE : Boolean.FALSE;
    }

    public static final Integer decodeInteger(byte[] b) {
        return new Integer(toInt(b));
    }

    public static final Long decodeLong(byte[] b) {
        return new Long(toLong(b));
    }

    public static final Character decodeCharacter(byte[] b) {
        return new Character((char) decodeCharacter(b));
    }

    public static final String decodeString(byte[] b) {
        return new String(b);
    }

    public static final StringBuffer decodeStringBuffer(byte[] b) {
        return new StringBuffer(decodeString(b));
    }

    public static final StringBuilder decodeStringBuilder(byte[] b) {
        return new StringBuilder(decodeString(b));
    }

    public static final Float decodeFloat(byte[] b) {
        Integer integer = decodeInteger(b);
        return new Float(Float.intBitsToFloat(integer));
    }

    public static final Short decodeShort(byte[] b) {
        return new Short((short) decodeShort(b));
    }

    public static final Double decodeDouble(byte[] b) {
        Long l = decodeLong(b);
        return new Double(Double.longBitsToDouble(l));
    }

    public static final Date decodeDate(byte[] b) {
        return new Date(toLong(b));
    }

    public static final byte[] decodeByteArr(byte[] b) {
        return b;
    }

    public static final int toInt(byte[] b) {
        return ((b[0] & 255) << 24) + ((b[1] & 255) << 16) + ((b[2] & 255) << 8) + ((b[3] & 255) << 0);
    }


    public static final int strBytes2Int(byte[] bytes, int start, int end) {
        int rtn = 0;

        for (int i = start; i < end; ++i) {
            rtn = rtn * 10 + (bytes[i] - 48);
        }

        return rtn;
    }

    public static final long strBytes2Long(byte[] bytes) {
        long rtn = 0L;

        for (int i = 0; i < bytes.length; ++i) {
            rtn = rtn * 10L + (long) (bytes[i] - 48);
        }

        return rtn;
    }

    public static void main(String[] args) {
        long i = 1111111111111119123L;
        byte[] bytes = getBytes(i);
        for (int j = 0; j < bytes.length; j++) {
            System.out.print(bytes[j]);
        }
        System.out.println();
        System.out.println(toLong(bytes));
    }
}

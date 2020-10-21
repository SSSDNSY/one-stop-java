package middleware.cache.util;

/**
 * @class middleware.cache.util.IOUtil
 * @desc
 * @since 2020-10-20
 */
public final class IOUtil {
    private static final int NUM_MAPPING_SIZE = 10000;
    private static final Object[] NUM_MAPPING = new Object[10000];
    private static final byte[] digits;
    private static final byte[] DigitTens;
    private static final byte[] DigitOnes;
    private static final long[] sizeTable;

    public IOUtil() {
    }

    public static final int stringSize(long x) {
        int i;
        for(i = 0; x > sizeTable[i]; ++i) {
        }

        return i + 1;
    }

    public static final byte[] encode(long i) {
        if (i >= 0L && i < 10000L) {
            return (byte[])((byte[])NUM_MAPPING[(int)i]);
        } else {
            int size = i < 0L ? stringSize(-i) + 1 : stringSize(i);
            byte[] rtn = new byte[size];
            int charPos = size;
            byte sign = 0;
            if (i < 0L) {
                sign = 45;
                i = -i;
            }

            int r;
            while(i > 2147483647L) {
                long q = i / 100L;
                r = (int)(i - ((q << 6) + (q << 5) + (q << 2)));
                i = q;
                --charPos;
                rtn[charPos] = DigitOnes[r];
                --charPos;
                rtn[charPos] = DigitTens[r];
            }

            int q2;
            int i2;
            for(i2 = (int)i; i2 >= 65536; rtn[charPos] = DigitTens[r]) {
                q2 = i2 / 100;
                r = i2 - ((q2 << 6) + (q2 << 5) + (q2 << 2));
                i2 = q2;
                --charPos;
                rtn[charPos] = DigitOnes[r];
                --charPos;
            }

            do {
                q2 = i2 * '쳍' >>> 19;
                r = i2 - ((q2 << 3) + (q2 << 1));
                --charPos;
                rtn[charPos] = digits[r];
                i2 = q2;
            } while(q2 != 0);

            if (sign != 0) {
                --charPos;
                rtn[charPos] = sign;
            }

            return rtn;
        }
    }

    private static final byte[] encodeSlow(long n) {
        return Long.toString(n).getBytes();
    }

    public static final int parseInt(byte[] bytes) {
        int rtn = 0;

        for(int i = 0; i < bytes.length; ++i) {
            rtn = rtn * 10 + (bytes[i] - 48);
        }

        return rtn;
    }

    public static final long parseLong(byte[] bytes) {
        long rtn = 0L;

        for(int i = 0; i < bytes.length; ++i) {
            rtn = rtn * 10L + (long)(bytes[i] - 48);
        }

        return rtn;
    }

    public static final int decodeSlow(byte[] bytes) {
        return Integer.parseInt(new String(bytes));
    }

    public static void main(String[] args) throws Exception {
        long start = 0L;

        for(int k = 0; k < 10; ++k) {
            start = System.currentTimeMillis();

            int j;
            long i;
            for(j = 0; j < 1000; ++j) {
                for(i = 0L; i < 10000L; ++i) {
                    encodeSlow(i);
                }
            }

            System.out.print(" 慢速编码耗时:" + (System.currentTimeMillis() - start) + "毫秒");
            start = System.currentTimeMillis();

            for(j = 0; j < 1000; ++j) {
                for(i = 0L; i < 10000L; ++i) {
                    encode(i);
                }
            }

            System.out.println(" 快速编码耗时:" + (System.currentTimeMillis() - start) + "毫秒");
        }

    }

    static {
        for(int i = 0; i < 10000; ++i) {
            NUM_MAPPING[i] = String.valueOf(i).getBytes();
        }

        digits = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};
        DigitTens = new byte[]{48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57};
        DigitOnes = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57};
        sizeTable = new long[]{9L, 99L, 999L, 9999L, 99999L, 999999L, 9999999L, 99999999L, 999999999L, 9999999999L, 99999999999L, 999999999999L, 9999999999999L, 99999999999999L, 999999999999999L, 9999999999999999L, 99999999999999999L, 999999999999999999L, 9223372036854775807L};
    }
}

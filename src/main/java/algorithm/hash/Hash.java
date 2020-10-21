package algorithm.hash;

/**
 * @Description
 * @Since 2020-10-13
 */
public class Hash {
    private static final int hash(int h) {
        h += h << 15 ^ -12931;
        h ^= h >>> 10;
        h += h << 3;
        h ^= h >>> 6;
        h += (h << 2) + (h << 14);
        return h ^ h >>> 16;
    }

    public static void main(String[] args) {
        System.out.println(hash(1));
        System.out.println(hash(2));
        System.out.println(hash(0));
        System.out.println(hash(Integer.MAX_VALUE));
        System.out.println(hash(Integer.MIN_VALUE));
    }
}

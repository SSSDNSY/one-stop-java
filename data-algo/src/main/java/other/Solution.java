package other;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pengzh
 * @desc 其他分类的算法题解放这里
 * @since 2025-04-15
 */
public class Solution {

    /**
     * 递归没有优化
     * 算过的还是重复算
     *
     * @param n
     * @return
     */
    public int feb1(int n) {
        if (n <= 1) {
            return n;
        }

        return feb1(n - 1) + feb1(n - 2);
    }

    /**
     * 利用数组或者map优化,意思就是算过的放进去，直接返回
     */
    public int feb2(int n) {
        if (n <= 1) {
            return n;
        }
        int[] arr = new int[n + 1];
        Map<Integer, Integer> map = new HashMap(n);
        //   return feb21(n, arr);
        return feb22(n, map);
    }

    private int feb21(int n, int[] arr) {
        if (n <= 1) {
            return n;
        }
        if (arr[n] == 0) {
            arr[n] = feb21(n - 1, arr) + feb21(n - 2, arr);
        }
        return arr[n];

    }

    private int feb22(int n, Map<Integer, Integer> map) {
        if (n <= 1) {
            return n;
        }
        if (map.get(n) == null) {
            map.put(n, feb22(n - 1, map) + feb22(n - 2, map));
        }
        return map.get(n);
    }

    /**
     * 原地斐波那契
     */
    public int feb3(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0;
        int b = 1;
        int c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 切绳子
     * 数学推导得，绳子分为n段，其中每段长度为3(最接近自然常数e的整数），有3中情况
     * 余数为
     * 0：3^n
     * 1：把1给其中一个3 凑成  3^(n-1)*4
     * 2：3^(n-1)*2
     * <p>
     * PS:
     * ‌除数（Divisor）‌：在除法算式中，除号后面的数叫做除数‌
     * ‌商（Quotient）‌：除法运算的结果称为商‌
     * ‌余数（Remainder）‌：被除数除以除数后剩余的数称为余数‌
     * ‌被除数（Dividend）‌：在除法运算中被另一个数所除的数称为被除数‌
     */
    public int cutingRope(int n) {
        if (n <= 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int quitient = n / 3;
        int remainder = n % 3;
        if (remainder == 1) {
            return pow(3, quitient - 2) * 4;
        } else if (remainder == 2) {
            return pow(3, quitient - 1) * 2;
        } else {
            return pow(3, quitient);
        }
    }

    private int pow(int a, int b) {
        for (int i = 1; i <= b; i++) {
            a *= a;
        }
        return a;
    }

}

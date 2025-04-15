package other;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pengzh
 * @desc 不知都分类的算法题解放这里
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
     *
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
}

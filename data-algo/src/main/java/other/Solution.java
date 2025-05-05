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

    public int cutingRope2(int n) {
        if (n <= 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int p = 1000000007;
        int quitient = n / 3;
        int remainder = n % 3;
        if (remainder == 1) {
            return pow2(3, quitient - 2) * 4 % p;
        } else if (remainder == 2) {
            return pow2(3, quitient - 1) * 2 % p;
        } else {
            return pow2(3, quitient);
        }
    }

    private int pow2(int a, int b) {
        for (int i = 1; i <= b; i++) {
            a *= a;
        }
        return a;
    }

    /**
     * 求一个整数的二进制表示中有多少个1
     */
    public int countOneInNum(int num) {
        int sum = 0;
        while (num != 0) {
            if (num % 2 == 1) {
                sum++;
            }
            num /= 2;
        }
        return sum;
    }

    public int countOneInNum2(int num) {
        int sum = 0;
        while (num != 0) {
            num = num & num - 1;
            sum++;
        }
        return sum;
    }

    /**
     * 求一个数的幂，pow(x,n),不能使用库函数，不考虑大数问题
     */
    public double fastPow(int x, int n) {
        int res = 1;
        long number = n;
        if (number < 0) {
            number = -number;
            x = 1 / x;
        }
        while (number > 0) {
            if (number % 2 == 1) {
                res *= x;
            }
            x *= x;
            number /= 2;
        }
        return res;
    }

    /**
     * 剑指 Offer 17. 打印从1到最大的n位
     * <p>
     * 剑指offer最优解
     * 本问题对应的 leetcode 原文链接：剑指 Offer 17. 打印从1到最大的n位
     * <p>
     * 问题描述
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。
     * 比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     */

    public int[] countNumbers(int cnt) {
        int max = (int) Math.pow(10, cnt);
        int[] arr = new int[max - 1];
        for (int i = 0; i < max - 1; i++) {
            arr[i] = i + 1;
            System.out.println(arr[i]);
        }
        return arr;
    }

    public boolean isNumber(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        char[] ch = str.trim().toCharArray();
        int len = ch.length;
        if (len == 0) {
            return false;
        }

        boolean isNumber = false;
        boolean isDot = false;
        boolean isEe = false;
        for (int i = 0; i < len; i++) {
            if ('0' <= ch[i] && ch[i] <= '9') {
                if (isEe || isDot) {
                    return false;
                }
                isNumber = true;
            } else if (ch[i] == '.') {
                if (isDot || isEe) {
                    return false;
                }
                isDot = true;
            } else if (ch[i] == 'e' || ch[i] == 'e') {
                if (isEe || !isNumber) {
                    return false;
                }
                isEe = true;
                isNumber = false;
            } else if (ch[i] == '-' || ch[i] == '+') {
                if (i != 0 && ch[i - 1] != 'e' && ch[i - 1] != 'E') {
                    return false;
                }
            } else {
                return false;
            }
        }
        return isNumber;
    }

}

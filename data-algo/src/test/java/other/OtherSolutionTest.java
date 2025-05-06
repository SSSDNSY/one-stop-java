package other;

import org.junit.Before;
import org.junit.Test;

import static util.GeneralUtil.printArray;

/**
 * @author pengzh
 * @desc
 * @since 2025-04-15
 */
public class OtherSolutionTest {

    private Solution solution;

    @Before
    public void setup() {
        solution = new Solution();
    }

    @Test
    public void testFeb1() {
        long start = System.currentTimeMillis();
        int a = solution.feb1(1);
        int b = solution.feb1(5);
        int c = solution.feb1(10);
        int d = solution.feb1(20);
        int e = solution.feb1(30);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);

        assert a == 1;
        assert b == 5;
        assert d == 6765;
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void testFeb2() {
        long start = System.currentTimeMillis();

        int a = solution.feb2(1);
        int b = solution.feb2(5);
        int c = solution.feb2(10);
        int d = solution.feb2(20);
        int e = solution.feb2(30);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);

        assert a == 1;
        assert b == 5;
        assert d == 6765;
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void testFeb3() {
        long start = System.currentTimeMillis();

        int a = solution.feb3(1);
        int b = solution.feb3(5);
        int c = solution.feb3(10);
        int d = solution.feb3(20);
        int e = solution.feb3(30);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);

        assert a == 1;
        assert b == 5;
        assert d == 6765;
        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * 切绳子 测试
     * 2=1
     * 10=36
     */
    @Test
    public void testCuttingRope() {
        assert 1 == solution.cutingRope(2);
        assert 36 == solution.cutingRope(10);
    }

    /**
     * 切绳子2 测试
     * 2=1
     * 10=36
     */
    @Test
    public void testCuttingRope2() {
        assert 1 == solution.cutingRope2(2);
        assert 36 == solution.cutingRope2(10);
    }

    /**
     * 切绳子2 测试
     * 2=1
     * 10=36
     */
    @Test
    public void testCountOneInNum() {
        assert 2 == solution.countOneInNum(3);
        assert 1 == solution.countOneInNum(4);
        assert 3 == solution.countOneInNum(11);
    }

    @Test
    public void testCountOneInNum2() {
        assert 2 == solution.countOneInNum2(3);
        assert 1 == solution.countOneInNum2(4);
        assert 3 == solution.countOneInNum2(11);
    }

    @Test
    public void testFastPow() {
        assert 4 == solution.fastPow(2, 2);
        assert 81 == solution.fastPow(3, 4);
    }

    @Test
    public void testCountNumbers() {
        assert 99 == solution.countNumbers(2)[98];
        assert 999 == solution.countNumbers(3).length;
    }

    @Test
    public void testIsNumber() {
        assert solution.isNumber("-100");
        assert solution.isNumber("-100.3");
        assert solution.isNumber("+100E3");
        assert solution.isNumber(" 01223 ");
        assert solution.isNumber(" +100e-5 ");
    }

    @Test
    public void testExchangeArr() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        printArray(arr);
        solution.exchangeArr(arr);
        printArray(arr);
        assert arr[5] == 6;
        assert arr[4] == 2;
    }

    @Test
    public void testExchangeArr2() {
        int[] arr = new int[]{1, 2, 3, 1, 6};
        printArray(arr);
        solution.exchangeArr2(arr);
        printArray(arr);
//        assert arr[5] == 6;
//        assert arr[4] == 4;
    }


}

package other;

import org.junit.Before;
import org.junit.Test;

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
    public void testFeb3(){
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

}

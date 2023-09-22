package dp;

import org.junit.Before;
import org.junit.Test;

/**
 * @Desc 动态规划单元测试
 * @Author pengzh
 * @Since 2023-09-21
 */
public class DynamicProgrammingSolutionTest {

    private Solution solution;

    @Before
    public void before() {
        solution = new Solution();
    }

    @Test
    public void testFeb() {
        // 0 1 1 2 3 5 8 13 21 34 55
        int i1 = solution.feb1(3);
        int i2 = solution.feb2(8);
        int i3 = solution.feb3(10);
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
        assert i1 == 2 && i2 == 21 && i3 == 55;
    }


    @Test
    public void testMinCost(){
        int[] arr = new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int   i   = solution.minCostClaimStairs(arr);
        System.out.println(i);
        assert i==6;
    }



}

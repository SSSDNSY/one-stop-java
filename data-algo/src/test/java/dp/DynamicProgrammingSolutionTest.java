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
    public void testMinCost() {
        int[] arr = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int   i   = solution.minCostClaimStairs(arr);
        System.out.println(i);
        assert i == 6;
    }

    @Test
    public void testUniquePaths() {
        int result = solution.uniquePaths(3, 7);
        System.out.println(result);
        assert result == 28;
    }

    @Test
    public void testUniquePathsWithObstacles() {
        int result = solution.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
        System.out.println(result);
        assert result == 2;
    }

    @Test
    public void testIntegerBreak() {
        int result = solution.integerBreak(10);
        System.out.println(result);
        assert result == 36;
    }

    @Test
    public void testNumTrees() {
        int result = solution.numTrees(5);
        System.out.println(result);
        assert result == 42;
    }

    @Test
    public void testBagProblem() {
        int[] weight  = {1, 3, 4};
        int[] value   = {15, 20, 30};
        int   bagSize = 4;
        solution.bagProblem(weight, value, bagSize);
    }

    @Test
    public void testBagProblem2() {
        int[] weight  = {1, 3, 4};
        int[] value   = {15, 20, 30};
        int   bagSize = 4;
        solution.bagProblem2(weight, value, bagSize);
    }


    /**
     * 输入: [1, 5, 11, 5]
     * 输出: true
     * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
     * 示例 2:
     * <p>
     * 输入: [1, 2, 3, 5]
     * 输出: false
     * 解释: 数组不能分割成两个元素和相等的子集.
     */
    @Test
    public void testCanPartition() {
        int[] arr  = new int[]{1, 5, 11, 5};
        int[] arr1 = new int[]{1, 2, 3, 5};
        assert solution.canPartition(arr);
        assert !solution.canPartition(arr1);
    }

    /**
     * 输入：nums: [1, 1, 1, 1, 1], S: 3
     * 输出：5
     */
    @Test
    public void testFindTargetSumWay() {
        int targetSumWay = solution.findTargetSumWay(new int[]{1, 1, 1, 1, 1}, 3);
        System.out.println(targetSumWay);
        assert targetSumWay == 5;
    }

}

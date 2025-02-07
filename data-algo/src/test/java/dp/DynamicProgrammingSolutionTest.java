package dp;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Desc 动态规划单元测试
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
        int i = solution.minCostClaimStairs(arr);
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
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        solution.bagProblem(weight, value, bagSize);
    }

    @Test
    public void testBagProblem2() {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
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
        int[] arr = new int[]{1, 5, 11, 5};
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

    @Test
    public void testFindMaxForm() {
        int maxForm = solution.findMaxForm(new String[]{"10", "0", "1"}, 1, 1);
        System.out.println(maxForm);
        assert maxForm == 2;
    }

    @Test
    public void testCompletePack() {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;
        int[] dp = solution.completePackage(weight, value, bagWeight);
        Arrays.stream(dp).forEach(System.out::println);
        assert dp[4] == 60;
    }

    @Test
    public void testChange() {
        int[] coins = new int[]{1, 2, 5};
        int amount = 5;
        int[] dp = solution.change(amount, coins);
        Arrays.stream(dp).forEach(System.out::println);
        assert dp[5] == 4;
    }

    @Test
    public void testNumSquares() {
        int num = solution.numSquares(13);
        System.out.println(num);
        assert num == 2;
    }

    @Test
    public void rob3() {
        TreeNode<Integer> root = new TreeNode<>();
        root.value = 3;

        TreeNode<Integer> l1 = new TreeNode<>();
        l1.value = 4;

        TreeNode<Integer> l2 = new TreeNode<>();
        l2.value = 5;

        TreeNode<Integer> l3 = new TreeNode<>();
        l3.value = 1;

        TreeNode<Integer> l4 = new TreeNode<>();
        l4.value = 3;

        TreeNode<Integer> l5 = new TreeNode<>();
        l5.value = 1;

        root.left = l1;
        root.right = l2;

        l1.left = l3;
        l1.right = l4;
        l2.right = l5;

        int[] arr = solution.rob3(root);
        Arrays.stream(arr).forEach(System.out::println);

    }

    @Test
    public void testMaxProfitDp() {
        int[] arr = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(solution.maxProfitDP(arr));
    }

    @Test
    public void testMaxProfitGreedy() {
        int[] arr = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(solution.maxProfitGreedy(arr));
    }

    @Test
    public void testMaxProfitDpII() {
        int[] arr = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(solution.maxProfitDpII(arr));
    }

    @Test
    public void testMaxProfitDpIII() {
        int[] arr = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(solution.maxProfitDpIII(arr));
    }


    @Test
    public void testMaxProfitDpIV() {
        int[] arr = new int[]{3, 2, 6, 5, 0, 3};
        int maxProfit = solution.maxProfitDpIV(2, arr);
        System.out.println(maxProfit);
        assert maxProfit == 7;
    }

    @Test
    public void tesMmaxProfitDpV() {
        int[] arr = new int[]{1, 2, 3, 0, 2};
        int maxProfit = solution.maxProfitDpV(arr);
        System.out.println(maxProfit);
        assert maxProfit == 3;
    }


}

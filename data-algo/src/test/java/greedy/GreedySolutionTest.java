package greedy;

import org.junit.Before;
import org.junit.Test;

/**
 * @Desc 贪心算法的单元测试
 * @Author pengzh
 * @Since 2023-09-18
 */
public class GreedySolutionTest {

    private Solution solution;

    @Before
    public void before() {
        solution = new Solution();
    }

    @Test
    public void testChildrenAndCookie() {
        int num = solution.cookieAndChildren2(new int[]{1, 2}, new int[]{1, 2, 3});
        System.out.println(num);
        assert num == 2;
    }

    @Test
    public void testWiggleMaxLength() {
        int count = solution.wiggleMaxLength(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        System.out.println(count);
        assert count == 2;
    }

    @Test
    public void testMaxSubsetSum() {
        int maxSum = solution.maxSubseqSum(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(maxSum);
        assert maxSum == 6;
    }

    @Test
    public void testMaxStockProfit() {
        int profit = solution.maxStockProfit(new int[]{7, 1, 5, 3, 6, 4});
        System.out.println(profit);
        assert profit == 7;
    }

    @Test
    public void testJumpGame() {
        boolean bool = solution.jumpGame(new int[]{3, 2, 1, 0, 4});
        System.out.println(bool);
        assert !bool;
    }

    @Test
    public void testJumpGameII() {
        int step = solution.jumpGameII(new int[]{2, 3, 1, 1, 4});
        System.out.println(step);
        assert step == 2;
    }

    @Test
    public void testLargestSumAfterNegations() {
        int lagestSum = solution.largestSumAfterNegations(new int[]{2, -3, -1, 5, -4}, 2);
        System.out.println(lagestSum);
        assert lagestSum == 13;
    }

    @Test
    public void testCanCompleteCircuitWhile() {
        int i = solution.canCompleteCircuitWhile(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2});
        System.out.println(i);
        assert i == 3;
    }

    @Test
    public void testCanCompleteCircuitGreedy() {
        int i = solution.canCompleteCircuitGreedy(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2});
        System.out.println(i);
        assert i == 3;
    }

    @Test
    public void testCandy() {
        int sum = solution.candy(new int[]{1, 0, 1});
        System.out.println(sum);
        assert sum == 5;
    }

    @Test
    public void testLemocadeChange() {
        boolean bool  = solution.lemonadeChagne(new int[]{5, 5, 10, 10, 20});
        boolean bool2 = solution.lemonadeChagne(new int[]{5, 5, 10, 20});
        System.out.println(bool);
        System.out.println(bool2);
        assert !bool && bool2;
    }

    @Test
    public void testMinArrow() {
        int count = solution.findMinArrowShots(new int[][]{new int[]{1, 2}, new int[]{3, 4}, new int[]{5, 6}, new int[]{7, 8}});
        System.out.println(count);
        assert count == 4;
    }

    @Test
    public void testEraseOverlapIntervals() {
        int count  = solution.eraseOverlapIntervals(new int[][]{new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}, new int[]{1, 3}});
        int count1 = solution.eraseOverlapIntervals(new int[][]{new int[]{1, 2}, new int[]{2, 3}});
        int count2 = solution.eraseOverlapIntervals(new int[][]{new int[]{1, 2}, new int[]{1, 2}, new int[]{1, 2}});
        System.out.println(count);
        System.out.println(count1);
        System.out.println(count2);
        assert count == 1 && count1 == 0 && count2 == 2;
    }

}

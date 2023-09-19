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


}

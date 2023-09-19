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
    public void before(){
        solution = new Solution();
    }

    @Test
    public void testChildrenAndCookie() {
        int num = solution.cookieAndChildren(new int[]{1,2}, new int[]{1,2,3});
        System.out.println(num);
        assert num == 2;
    }



}

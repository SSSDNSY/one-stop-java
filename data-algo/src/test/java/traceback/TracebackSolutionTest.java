package traceback;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-09-08
 */
public class TracebackSolutionTest {

    Solution solution;

    @Before
    public void before(){
        solution = new Solution();
    }

    @Test
    public void TestCombine(){
        //示例: 输入: n = 4, k = 2 输出: [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4],]
        List<List<Integer>> combine = solution.combine(4, 2);
        assert  combine.size()==6;
    }

}

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
    public void before() {
        solution = new Solution();
    }

    @Test
    public void TestCombine() {
        // 示例: 输入: n = 4, k = 2 输出: [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4],]
        List<List<Integer>> combine = solution.combine(4, 2);
        assert combine.size() == 6;
    }

    @Test
    public void TestCombineSum() {
        // 示例: 输入: n = 4, k = 2 输出: [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4],]
        List<List<Integer>> combine = solution.combineSum(9, 3);
        System.out.println(combine);
        assert combine.size() == 3;
    }

    @Test
    public void TestCombineAlphabet() {
        // 示例: 输入: "23" 输出: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
        List<String> combine = solution.combineAlphabet("23");
        System.out.println(combine);
        assert combine.size() == 9;
    }

    @Test
    public void TestCombineSum2() {
        int[]               arr   = new int[]{2, 3, 6, 7};
        List<List<Integer>> lists = solution.combineSum(arr, 7);
        System.out.println(lists);
        assert lists.size() == 2;
    }

    @Test
    public void TestCombineSum3() {
        int[]               arr   = new int[]{10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> lists = solution.combineSum3(arr, 8);
        System.out.println(lists);
        assert lists.size() == 4;
    }

    @Test
    public void TestStringPartition() {
        List<List<String>> lists = solution.stringPartition("aab");
        System.out.println(lists);
        assert lists.size() == 2;
    }

    @Test
    public void TestIpRecover() {
        List<String> strings = solution.ipRecover("101023");
        assert strings.size() == 5;
    }

    @Test
    public void TestSubset() {
        List<List<Integer>> lists = solution.subset(new int[]{1, 2, 3});
        System.out.println(lists);
        assert lists.size() == 8;
    }

    @Test
    public void TestSubsetDup() {
        List<List<Integer>> lists = solution.subsetDup(new int[]{1, 2, 2});
        System.out.println(lists);
        assert lists.size() == 6;
    }

    @Test
    public void TestSubsetAsc() {
        List<List<Integer>> lists = solution.subsetAsc(new int[]{4, 6, 7, 7});
        System.out.println(lists);
        assert lists.size() == 8;
    }

    @Test
    public void TestArrange() {
        int[]               arr     = new int[]{1, 2, 3};
        List<List<Integer>> arrange = solution.arrange(arr);
        System.out.println(arrange);
        assert arrange.size() == 6;
    }

    @Test
    public void TestTracebackArrangeUnique() {
        int[]               arr   = new int[]{1, 2, 3};
        List<List<Integer>> lists = solution.arrangeUnique(arr);
        System.out.println(lists);
        assert lists.size() == 6;
    }


}

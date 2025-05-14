package string;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author pengzh
 * @desc
 * @since 2025-05-14
 */
public class StringSolutionTest {

    Solution solution;

    @Before
    public void setUp() throws Exception {
        solution = new Solution();
    }

    @Test
    public void test() {
        List<String> aba = solution.permutation("cba");
        System.out.println(aba);
        assert aba.size() == 6;
    }

}

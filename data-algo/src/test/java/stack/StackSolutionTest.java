package stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * @author pengzh
 * @desc
 * @since 2025-02-18
 */
@Slf4j
public class StackSolutionTest {

    Solution solution;
    Solution.MinStack minStack;

    @Before
    public void setUp() {
        solution = new Solution();
        minStack = new Solution.MinStack();
    }

    @Test
    public void testValid2() {

        log.info("{}", solution.isValid2("(]"));
        log.info("{}", solution.isValid2("[()]"));
        log.info("{}", solution.isValid2("[{}]"));

    }

    @Test
    public void testMinStackClass() {
        // 5,2,3,0,4,1,8
        minStack.push(5);
        assert minStack.min() == 5;
        minStack.push(2);
        assert minStack.min() == 2;
        minStack.push(3);
        assert minStack.min() == 2;
        minStack.push(0);
        assert minStack.min() == 0;
        minStack.push(4);
        minStack.push(1);
        minStack.push(8);

        assert minStack.pop() == 8;
        assert minStack.pop() == 1;
        assert minStack.peek() == 4;

        assert minStack.min() == 0;

    }

}

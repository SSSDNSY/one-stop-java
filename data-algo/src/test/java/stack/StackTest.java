package stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author pengzh
 * @desc
 * @since 2025-02-18
 */
@Slf4j
public class StackTest {

    @Test
    public void testValid2() {
        Solution solution = new Solution();
       log.info("{}",solution.isValid2("(]"));
       log.info("{}",solution.isValid2("[()]"));
       log.info("{}",solution.isValid2("[{}]"));

    }

}

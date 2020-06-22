package lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author pengzh
 * @since 2020-05-22
 */

@Slf4j
public class DataStructTest {
    @Test
    public void getHexShow(){
        log.debug("Integer.toHexString={}",Integer.toHexString(991));
    }
}

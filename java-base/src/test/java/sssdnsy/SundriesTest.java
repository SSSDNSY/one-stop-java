package sssdnsy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @Desc 杂项测试

 * @Since 2024-01-11
 */
@Slf4j
public class SundriesTest {

    @Test
    public void testStringUtil() {

        log.info("{}", StringUtils.isNumeric("2343242f34444444444444444234234"));

    }


}

package designpattern.chainofresponsibility;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**

 * @class designpattern.chainofresponsibility.ChainOfResponsibilityTest
 * @desc
 * @since 2023-01-20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChainOfResponsibilityTest {

    @Autowired
    private HandlerChainManager handlerChainManager;

    /**
     * 测试责任链模式
     */
    @Test
    public void test() {
        handlerChainManager.doHandler(null);
    }

}

package designpattern.strategy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author fun.pengzh
 * @class designpattern.chainofresponsibility.ChainOfResponsibilityTest
 * @desc
 * @since 2023-01-20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyTemplateTest {

    @Autowired
    private ShopStrategy strategy;

    /**
     * 测试责任链模式
     */
    @Test
    public void test() {
        strategy.doFill("TYPE_A");
    }

}

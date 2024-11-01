package designpattern.chainofresponsibility;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author fun.pengzh
 * @class designpattern.chainofresponsibility.InstanceCreateHandler
 * @desc 实例处理
 * @since 2023-01-20
 */
@Order(100)
@Component
public class InstanceHandler extends AbstractHandler {


    @Override
    public Object handle(Object o) {
        System.out.println("实例处理");

        return null;
    }
}

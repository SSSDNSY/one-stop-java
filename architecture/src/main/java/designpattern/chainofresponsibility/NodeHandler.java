package designpattern.chainofresponsibility;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author fun.pengzh
 * @class designpattern.chainofresponsibility.NodeHandler
 * @desc
 * @since 2023-01-20
 */
@Order(200)
@Component
public class NodeHandler extends AbstractHandler {

    @Override
    public Object handle(Object o) {
        System.out.println("节点处理");
        return null;
    }

}

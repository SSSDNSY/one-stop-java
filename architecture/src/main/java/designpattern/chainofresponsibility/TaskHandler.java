package designpattern.chainofresponsibility;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**

 * @class designpattern.chainofresponsibility.TaskCreateHandler
 * @desc
 * @since 2023-01-20
 */
@Order(300)
@Component
public class TaskHandler extends AbstractHandler {

    @Override
    public Object handle(Object o) {
        System.out.println("任务处理");
        return null;
    }

}

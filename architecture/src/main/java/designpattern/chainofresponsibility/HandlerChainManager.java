package designpattern.chainofresponsibility;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**

 * @class designpattern.chainofresponsibility.InstranceCreateManager
 * @desc 责任链调度门面类
 * @since 2023-01-20
 */
@Component
public class HandlerChainManager {

    @Autowired
    private List<Handler> handlerList;

    @PostConstruct
    public void initHandlerChain() {
        /**
         * 初始化责任链
         * handler1 ->  handler2 -> ... -> handlerN ->null
         */
        for (int i = 0; CollectionUtils.isNotEmpty(handlerList) && i < handlerList.size(); i++) {
            Handler handler = handlerList.get(i);
            if (i == handlerList.size() - 1) {
                handler.setNext(null);
            } else {
                handler.setNext(handlerList.get(i + 1));
            }
        }
    }

    public Object doHandler(Object object) {
        return handlerList.get(0).execute(object);
    }

}

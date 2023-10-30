package route.strategy;

import route.ExecutorRouter;

import java.util.List;

/**
 * Created by xuxueli on 17/3/10.
 */
public class ExecutorRouteLast extends ExecutorRouter {

    @Override
    public String route(String triggerParam, List<String> addressList) {
        return addressList.get(addressList.size() - 1);
    }

}

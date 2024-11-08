package designpattern.strategy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Desc
 * @Since 2023-02-16
 */

@Component
public class ShopStrategy implements ApplicationContextAware {


    private Map<String, IShopStrategy> userStrategyMap = new ConcurrentHashMap<>();

    public void doFill(String bizType) {
        IShopStrategy iUserStrategy = userStrategyMap.get(bizType);
        iUserStrategy.fillInfo();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, IShopStrategy> map = applicationContext.getBeansOfType(IShopStrategy.class);
        map.values().forEach(strategyService -> userStrategyMap.put(strategyService.getType(), strategyService));
    }

}

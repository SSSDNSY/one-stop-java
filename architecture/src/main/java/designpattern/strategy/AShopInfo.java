package designpattern.strategy;

import org.springframework.stereotype.Component;

/**
 * @Desc
 * @Since 2023-02-16
 */
@Component
public class AShopInfo extends AbstractShopTemplate {

    @Override
    public String getType() {
        return "TYPE_A";
    }

    @Override
    public void fillInfo() {
        super.fillInfo();
        System.out.println("AAAAA do something");
    }

}

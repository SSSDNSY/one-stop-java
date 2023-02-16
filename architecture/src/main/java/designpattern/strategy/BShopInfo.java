package designpattern.strategy;

import org.springframework.stereotype.Component;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-02-16
 */
@Component
public class BShopInfo extends AbstractShopTemplate {

    @Override
    public String getType() {
        return "TYPE_B";
    }

    @Override
    public void fillInfo() {
        super.fillInfo();
        System.out.println("BBBBB do something");
    }

}

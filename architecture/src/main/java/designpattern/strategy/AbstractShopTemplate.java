package designpattern.strategy;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-02-16
 */
public abstract class AbstractShopTemplate implements IShopStrategy {


    @Override
    public void fillInfo() {
        method1();
        method2();

    }

    public abstract String getType();

    void method1() {
        System.out.println("tempate method1");
    }

    void method2() {
        System.out.println("tempate method2");
    }

}

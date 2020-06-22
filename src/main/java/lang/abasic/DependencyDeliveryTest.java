package lang.abasic;

import java.io.Serializable;

/**
 * @author pengzh
 * @date 2020-04-07
 */
public class DependencyDeliveryTest {
    public static void main(String[] args) throws Exception {
        Class c = Class.forName("lang.abasic.Child");
        Class p = Class.forName("lang.abasic.Parent");

        Child ci = new Child();
        Parent pi = new Parent();

        System.out.println("子类信息");
        System.out.println(c.getGenericSuperclass());
        System.out.println(c.toGenericString());
        System.out.println(c.getInterfaces());
        System.out.println(c.getSuperclass());

        System.out.println("父类类信息");
        System.out.println(p.getGenericSuperclass());
        System.out.println(p.toGenericString());
        System.out.println(p.getInterfaces());
        System.out.println(p.getSuperclass());

        System.out.println("Childe是否实现Serializable 接口:" + Serializable.class.isAssignableFrom(c));
        System.out.println("Childe是否Parent 子类:" + (ci instanceof Parent));

        System.out.println("Parent是否实现Serializable 接口:" + Serializable.class.isAssignableFrom(p));
        System.out.println("Parent是否Object 子类:" + (pi instanceof Object));

    }
}

abstract class AbstractClass implements Serializable {
    /**
     * do sth.
     */
    abstract void init();
}

class Parent extends AbstractClass {
    @Override
    void init() {
        System.out.println("父类初始化");
    }
}

class Child extends Parent {
    @Override
    void init() {
        System.out.println("子类初始化");
    }
}
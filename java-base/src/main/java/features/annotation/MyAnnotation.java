package features.annotation;

import java.lang.annotation.*;


/**
 * 888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
 * 自定义的Annotation。 演示注解的继承Inherited
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited//注释掉看效果
@interface Inheritable {
}

@Inheritable
class InheritableFather {
    public InheritableFather() {
        // InheritableBase是否具有 Inheritable Annotation
        System.out.println("InheritableFather:" + InheritableFather.class.isAnnotationPresent(Inheritable.class));
    }
}

/**
 * InheritableSon 类只是继承于 InheritableFather，
 */
class InheritableSon extends InheritableFather {
    public InheritableSon() {
        super();    // 调用父类的构造函数
        // InheritableSon类是否具有 Inheritable Annotation
        System.out.println("InheritableSon:" + InheritableSon.class.isAnnotationPresent(Inheritable.class));
    }

    public static void main(String[] args) {
        InheritableSon is = new InheritableSon();
    }
}

/**
 * 888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
 *
 * @author pengzh
 * @date 2020-03-10
 * 自定义的注解
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

}

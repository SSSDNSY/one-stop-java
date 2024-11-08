package basic;

import java.io.FileNotFoundException;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 2020-06-29
 * <p>
 * 著作权归https://www.pdai.tech所有。
 * 链接：https://www.pdai.tech/md/java/basic/java-basic-x-annotation.html
 * <p>
 * 注解是JDK1.5版本开始引入的一个特性，用于对代码进行说明，可以对包、类、接口、字段、方法参数、局部变量等进行注解。
 * 它主要的作用有以下四方面：
 * 1生成文档，通过代码里标识的元数据生成javadoc文档。
 * 2编译检查，通过代码里标识的元数据让编译器在编译期间进行检查验证。
 * 3编译时动态处理，编译时通过代码里标识的元数据动态处理，例如动态生成代码。
 * 4运行时动态处理，运行时通过代码里标识的元数据动态处理，例如使用反射注入实例。
 * <p>
 * 注解与反射：
 * <p>
 * 定义注解后，如何获取注解中的内容呢？反射包java.lang.reflect下的AnnotatedElement接口提供这些方法。
 * 这里注意：只有注解被定义为RUNTIME后，该注解才能是运行时可见，当class文件被装载时被保存在class文件中的Annotation才会被虚拟机读取。
 */
public class Annotation {
// 常用注解 @Override、@Deprecated和@SuppressWarnings

// 元注解
//    @Target       注解的目标 包 接口 类 方法 参数 变量          ElementType
//    @Retention    注解保留的阶段 source class runtime        RetentionPolicy
//    @Inherited    注解是否可继承
//    @Documented   注解是否生成文档


    public static void main(String[] args) {
//注解的继承
//        new Worker().print();
//自定义注解
        TestMyDefineAnnotation.Test();
    }
//    @Native (Java8) 使用 @Native 注解修饰成员变量，则表示这个变量可以被本地代码引用，常常被代码生成工具使用

}

class A {
    void test() {

    }
}

class B extends A {
    @Override
    void test() {
        super.test();
    }

    @SuppressWarnings("warnings")
    void warning() {
        List o = null;
    }

    @Deprecated
    void deprecated() {

    }
}

//注解的继承
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface InheritedTest {

    String[] race();

    int age();

    boolean gender();//true male ,false female

}

@InheritedTest(race = "汉族", age = 123, gender = true)
class Human {
}

class Worker extends Human {
    void print() {
        final java.lang.annotation.Annotation[] annotations = Worker.class.getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i]);
        }
    }
}

//自定义注解
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyMethodAnnotation {
    String[] title();

    String[] description();
}

class TestMyDefineAnnotation {

    @Override
    @MyMethodAnnotation(title = "toString()", description = "toString方法")
    public String toString() {
        String str = "override Object toString";
        System.out.println(str);
        return str;
    }

    @Deprecated
    @MyMethodAnnotation(title = "OldMethod()", description = "OldMethod方法")
    public static void oldMethod() {
        System.out.println("这是一个过期的方法");
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @MyMethodAnnotation(title = "GenericTest()", description = "GenricTest方法")
    public static void GenericTest() throws FileNotFoundException {
        List list = new ArrayList<>();
        list.add("123");
        oldMethod();
    }

    public static void Test() {
        try {
            //取得该类的所有方法Method
            final Method[] methods = TestMyDefineAnnotation.class.getClassLoader()
                    .loadClass("sssdnsy.abasic.TestMyDefineAnnotation")
                    .getMethods();
            for (Method method : methods) {
                final java.lang.annotation.Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                // 获取并遍历方法上的所有注解
                for (java.lang.annotation.Annotation annotation : declaredAnnotations) {
                    System.out.println("Method=" + method + ",DeclaredAnnotations =" + annotation);
                }
                //是否有自定义MyMethodAnnotation注解
                if (method.isAnnotationPresent(MyMethodAnnotation.class)) {
                    final MyMethodAnnotation annotation = method.getAnnotation(MyMethodAnnotation.class);
                    System.out.println(" MyMethodAnnotation title =" + annotation.title());
                    System.out.println(" MyMethodAnnotation description =" + annotation.description());
                }
                System.out.println("");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

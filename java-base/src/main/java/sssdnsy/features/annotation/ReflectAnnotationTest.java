package sssdnsy.features.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * @author pengzh
 * @date 2020-03-10
 */
public class ReflectAnnotationTest {
    public static void main(String[] args) throws Exception {
        Person p = new Person();
        Class<Person> personClass = Person.class;
        Method msomeBody = personClass.getMethod("someBody", new Class[]{String.class, int.class});
        msomeBody.invoke(p, new Object[]{"配置1", 18});
        iteratorAnnotations(msomeBody);
        System.err.print("=================================");
        Method mempty = personClass.getMethod("empty", new Class[]{});
        mempty.invoke(p, new Object[]{});
        iteratorAnnotations(mempty);
    }

    public static void iteratorAnnotations(Method method) {

        // 判断 somebody() 方法是否包含MyAnnotation注解
        if (method.isAnnotationPresent(AnnotationTest.class)) {
            // 获取该方法的MyAnnotation注解实例
            AnnotationTest myAnnotation = method.getAnnotation(AnnotationTest.class);
            // 获取 myAnnotation的值，并打印出来
            String[] values = myAnnotation.value();
            for (String str : values) {
                System.out.printf(str + ", ");
            }
            System.out.println();
        }

        // 获取方法上的所有注解，并打印出来
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}

class Person {


    @Deprecated
    @AnnotationTest
    public void empty() {
        System.out.println("\n AnnotationTest default value!!!");
    }

    @AnnotationTest(value = {"girl", "boy"})
    public void someBody(String name, int age) {
        System.out.println("\nsomebody:" + "name：" +name+ "，age："+age);
    }


}

/**
 * Annotation在反射函数中的使用示例
 *
 * @author skywang
 * @email kuiwu-wang@163.com
 */
@Retention(RetentionPolicy.RUNTIME)
@interface AnnotationTest {
    String[] value() default "unknown";
}

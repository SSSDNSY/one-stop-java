package sssdnsy.features.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author pengzh
 * @date 2020-03-10
 */
public class RefTest2 {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        传统方式创建对象
        Student t1 = Student.builder().age(23).name("zhangsan").build();
        System.out.println(t1);
//        反射的方式获取对象
        Class<Student> c1 = Student.class;
        Constructor<Student> constructor = c1.getConstructor();
        Student t2 = constructor.newInstance();
        System.out.println(t2);
    }
}

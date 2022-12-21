package abasic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author pengzh
 * @since 2020-06-29
 * <p>
 * JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；
 * 对于任意一个对象，都能够调用它的任意一个方法和属性；
 * 这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
 * Java反射机制在框架设计中极为广泛，需要深入理解。
 * 本文综合多篇文章后，总结了Java 反射的相关知识，希望可以提升你对Java中反射的认知效率。@pdai
 * <p>
 * 著作权归https://www.pdai.tech所有。
 * 链接：https://www.pdai.tech/md/java/basic/java-basic-x-reflection.html
 * <p>
 * 反射调用流程小结 最后，用几句话总结反射的实现原理：
 * 反射类及反射方法的获取，都是通过从列表中搜寻查找匹配的方法，所以查找性能会随类的大小方法多少而变化；
 * 每个类都会有一个与之对应的Class实例，从而每个类都可以获取method反射方法，并作用到其他实例身上；
 * 反射也是考虑了线程安全的，放心使用；
 * 反射使用软引用relectionData缓存class信息，避免每次重新从jvm获取带来的开销；
 * 反射调用多次生成新代理Accessor, 而通过字节码生存的则考虑了卸载功能，所以会使用独立的类加载器；
 * 当找到需要的方法，都会copy一份出来，而不是使用原来的实例，从而保证数据隔离；
 * 调度反射方法，最终是由jvm执行invoke0()执行；
 */
public class ReflectionTest {
    //反射就是把java类中的各种成分映射成一个个的Java对象
    // 一个类有：成员变量、方法、构造方法、包等等信息，利用反射技术可以对一个类进行解剖，把个个组成部分映射成一个个对象。

    //1 Class
//    Class类也是类的一种，与class关键字是不一样的。
//    手动编写的类被编译后会产生一个Class对象，其表示的是创建的类的类型信息，而且这个Class对象保存在同名.class的文件中(字节码文件)
//    每个通过关键字class标识的类，在内存中有且只有一个与之对应的Class对象来描述其类型信息，无论创建多少个实例对象，其依据的都是用一个Class对象。
//    Class类只存私有构造函数，因此对应Class对象只能有JVM创建和加载
//    Class类的对象作用是运行时提供或获得某个对象的类型信息，这点对于反射技术很重要(关于反射稍后分析)。
//2 反射的使用
//    reflectUse();
//3 Class类的方法
//     classMethod();
//4 Constructor类及其用法
//  constructorUse();
//
//
//
    public static void main(String[] args) throws Exception {

        reflectUse();
        classMethod();
        Test.nameDiff(args);
        System.out.println("==============================================Constructor类及其用法");
        constructorUse();
    }

    private static void constructorUse() throws Exception {
        Class clazz = Class.forName("sssdnsy.abasic.User");
        //第一种方法，实例化默认构造方法，User必须无参构造函数,否则将抛异常
        User user = (User) clazz.newInstance();
        user.setAge(1);
        user.setName("实例化默认构造方法");
        System.out.println(user);
        //获取带int和String参数的public构造函数
        final Constructor constructor = clazz.getConstructor(int.class, String.class);
        //创建User
        final Object o = constructor.newInstance(2, "获取带String参数的public构造函数");
        System.out.println(o);


        System.out.println("--------------------------------------------");

        //取得指定带String参数构造函数,该方法是私有构造private
        final Constructor constructor1 = clazz.getDeclaredConstructor(int.class);
        //由于是private必须设置可访问
        constructor1.setAccessible(true);//false = IllegalAccessException
        //创建user对象
        final Object o1 = constructor1.newInstance(3);
        System.out.println(o1);

        System.out.println("--------------------------------------------");

        //获取所有构造包含private
        Constructor<?> cons[] = clazz.getDeclaredConstructors();
        // 查看每个构造方法需要的参数
        for (int i = 0; i < cons.length; i++) {
            //获取构造函数参数类型
            Class<?> clazzs[] = cons[i].getParameterTypes();
            System.out.println("构造函数[" + i + "]:" + cons[i].toString());
            System.out.print("参数类型[" + i + "]:(");
            for (int j = 0; j < clazzs.length; j++) {
                if (j == clazzs.length - 1)
                    System.out.print(clazzs[j].getName());
                else
                    System.out.print(clazzs[j].getName() + ",");
            }
            System.out.println(")");
        }
    }

    private static void reflectUse() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //获取Class对象方式
        System.out.println("Class获取:" + User.class);
        System.out.println("对象获取:" + new User().getClass());
        System.out.println("Class.forName:" + Class.forName(User.class.getName().toString()));
        //常用方法
        System.out.println("\n获取全名：" + User.class.getName());
        System.out.println("获取类名：" + User.class.getSimpleName());
        System.out.println("实例化类：" + User.class.newInstance());
    }

    private static void classMethod() throws Exception {
        Class<Dog> dog = Dog.class;
        //类名打印
        System.out.println(dog.getName()); //com.cry.Dog
        System.out.println(dog.getSimpleName()); //Dog
        System.out.println(dog.getCanonicalName());//com.cry.Dog
        //接口
        System.out.println(dog.isInterface()); //false
        for (Class iI : dog.getInterfaces()) {
            System.out.println(iI);
        }

        //父类
        System.out.println(dog.getSuperclass());//class com.cry.Animal
        //创建对象
        Dog d = dog.newInstance();
        //字段
        for (Field f : dog.getFields()) {
            System.out.println(f.getName());
        }
            /*
            mDogPublic
            sDogPublic
            mAnimalPublic
            sAnimalPublic
            mCellPublic  //父类的父类的公共字段也打印出来了
         */
        System.out.println("---------");
        for (Field f : dog.getDeclaredFields()) {
            System.out.println(f.getName());
        }
        /*只有自己类声明的字段
         mDogPrivate
         mDogPublic
         mDogProtected
         mDogDefault
         sDogPrivate
         sDogProtected
         sDogDefault
         sDogPublic
         */
    }
}

class User {
    private int age;
    private String name;

    public User() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    private User(int age) {
        super();
        this.age = age;
    }

    public User(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }
}


interface I1 {
}

interface I2 {
}

class Cell {
    public int mCellPublic;
}

class Animal extends Cell {
    private int mAnimalPrivate;
    protected int mAnimalProtected;
    int mAnimalDefault;
    public int mAnimalPublic;
    private static int sAnimalPrivate;
    protected static int sAnimalProtected;
    static int sAnimalDefault;
    public static int sAnimalPublic;
}

class Dog extends Animal implements I1, I2 {
    private int mDogPrivate;
    public int mDogPublic;
    protected int mDogProtected;
    private int mDogDefault;
    private static int sDogPrivate;
    protected static int sDogProtected;
    static int sDogDefault;
    public static int sDogPublic;
}

class Test {
    private class inner {
    }

    public static void nameDiff(String[] args) throws ClassNotFoundException {
        //普通类
        System.out.println(Test.class.getSimpleName()); //Test
        System.out.println(Test.class.getName()); //com.cry.Test
        System.out.println(Test.class.getCanonicalName()); //com.cry.Test
        //内部类
        System.out.println(inner.class.getSimpleName()); //inner
        System.out.println(inner.class.getName()); //com.cry.Test$inner
        System.out.println(inner.class.getCanonicalName()); //com.cry.Test.inner
        //数组
        System.out.println(args.getClass().getSimpleName()); //String[]
        System.out.println(args.getClass().getName()); //[Ljava.lang.String;
        System.out.println(args.getClass().getCanonicalName()); //java.lang.String[]
        //我们不能用getCanonicalName去加载类对象，必须用getName
        //Class.forName(inner.class.getCanonicalName()); 报错
        Class.forName(inner.class.getName());
    }
}

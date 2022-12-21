package abasic;

import java.io.*;

/**
 * @author pengzh
 * @since 2020-06-29
 * <p>
 * 只有当父加载器在它的搜索范围中没有找到所需的类时，即无法完成该加载，子加载器才会尝试自己去加载该类。
 * 双亲委派机制
 * 1、当AppClassLoader加载一个class时，它首先不会自己去尝试加载这个类，而是把类加载请求委派给父类加载器ExtClassLoader去完成。
 * 2、当ExtClassLoader加载一个class时，它首先也不会自己去尝试加载这个类，而是把类加载请求委派给BootStrapClassLoader去完成。
 * 3、如果BootStrapClassLoader加载失败(例如在$JAVA_HOME/jre/lib里未查找到该class)，会使用ExtClassLoader来尝试加载；
 * 4、若ExtClassLoader也加载失败，则会使用AppClassLoader来加载，如果AppClassLoader也加载失败，则会报出异常ClassNotFoundException。
 */
public class ClassLoaderTest {

    public static final String MAIN_CLASS = "sssdnsy.abasic.MainClass";

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("---------------------------------3种类加载器 boot ext app");

        final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
        System.out.println(contextClassLoader.getParent());//sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(contextClassLoader.getParent().getParent());//  sun.misc.Launcher$ExtClassLoader@6adca536
//        System.out.println(contextClassLoader.getParent().getParent().getParent());
        testClassLoader();
        MyClassLoader.TestClassLoader();
    }

    //类加载器实例
    public static void testClassLoader() throws ClassNotFoundException {
        System.out.println("---------------------------------3种加载类的方法");
        //不会执行静态方法
        System.out.println("加载器1");
        final ClassLoader classLoader = MainClass.class.getClassLoader();
        final Class<?> aClass = classLoader.loadClass(MAIN_CLASS);
        System.out.println("加载器2");
        final Class<?> aClass1 = Class.forName(MAIN_CLASS);
        System.out.println("加载器3");
        Class.forName(MAIN_CLASS, false, classLoader);
    }

    //双亲委派机制


    //      双亲委派优势       系统类防止内存中出现多份同样的字节码
//                      保证Java程序安全稳定运行
    public static void parentLoaderTest(String name) {
//        // 首先判断该类型是否已经被加载
//        Class c = findLoadedClass(name);
//        ClassLoader parent = c.getClassLoader();
//        if (c == null) {
//            //如果没有被加载，就委托给父类加载或者委派给启动类加载器加载
//            try {
//                if (parent != null) {
//                    //如果存在父类加载器，就委派给父类加载器加载
//                    c = parent.loadClass(name, false);
//                } else {
//                    //如果不存在父类加载器，就检查是否是由启动类加载器加载的类，通过调用本地方法native Class findBootstrapClass(String name)
//                    c = findBootstrapClass0(name);
//                }
//            } catch (ClassNotFoundException e) {
//                // 如果父类加载器和启动类加载器都不能完成加载任务，才调用自身的加载功能
//                c = findClass(name);
//            }
//        }
//        return c;
    }


}

//自定义classLoader
class MyClassLoader extends ClassLoader {

    private static String DIR = "E:" + File.separator + "desktop" + File.separator + "%s.class";

    public static void TestClassLoader() {
        System.out.println("---------------------------------自定义类加载器");
        final MyClassLoader myClassLoader = new MyClassLoader();
        try {
            final Class<?> test = myClassLoader.loadClass("Test");
            final Object o = test.newInstance();
            System.out.println(test.getClassLoader());
            System.out.println(o.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
// 只需要去重写这个 findClass 方法就能实现自定义的类加载器

//    这里有几点需要注意 :
//            1、这里传递的文件名需要是类的全限定性名称，即com.pdai.jvm.classloader.Test2格式的，因为 defineClass 方法是按这种格式进行处理的。
//            2、最好不要重写loadClass方法，因为这样容易破坏双亲委托模式。
//            3、这类Test 类本身可以被 AppClassLoader 类加载，因此我们不能把com/pdai/jvm/classloader/Test2.class 放在类路径下。否则，由于双亲委托机制的存在，会直接导致该类由 AppClassLoader 加载，而不会通过我们自定义类加载器来加载。
    @Override
    public Class<?> findClass(String s) throws ClassNotFoundException {
        byte[] data = this.loadClassData(s);
        if (data == null) {
            throw new ClassNotFoundException();
        }
        return defineClass(s, data, 0, data.length);
    }

    protected byte[] loadClassData(String s) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (final InputStream fileInputStream = new FileInputStream(String.format(DIR, s.replace('.', File.separatorChar)));
        ) {
            byte[] buff = new byte[1024];
            int length;
            while ((length = fileInputStream.read(buff)) > 0) {
                bos.write(buff, 0, length);
            }
            return bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package features.reflection;

/**
 * 对象instance的模板是类class，类class在JVM中模板是类对象Class
 *
 * @date 2020-03-10
 */
public class RefTest1 {

    public RefTest1() {
        System.out.println("构造");
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //类对象的3中获取方式
        Class c1 = Student.class;
        Class c2 = Class.forName("features.reflection.Student");
        Class c3 = new Student().getClass();

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);

        final Object o = c1.newInstance();
        final Object o1 = c2.newInstance();
        final Object o2 = c3.newInstance();
        System.out.println(o + "," + o.hashCode());
        System.out.println(o1 + "," + o1.hashCode());
        System.out.println(o2 + "," + o2.hashCode());

//        在一个JVM中，一种类，只会有一个类对象存在。所以以上三种方式取出来的类对象，都是一样的
        //准确的讲是一个ClassLoader下，一种类，只会有一个类对象存在。通常一个JVM下，只会有一个ClassLoader。
    }

    public <T> T getBean(Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}

package features.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author ：pengzh
 * @date ：Created in 2020/2/2 22:32
 * @description： 获取Java的unsafe类直接操作内存
 * https://www.cnblogs.com/suxuan/p/4948608.html#3935328
 * @modified By：
 */
public class A48Unsafe {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        非启动类加载器直接调用Unsafe.getUnsafe()方法会抛出SecurityException（具体原因涉及JVM类的双亲加载机制）。
//        解决办法有两个，其一是通过JVM参数-Xbootclasspath指定要使用的类为启动类，另外一个办法就是java反射了。
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        System.out.println("获取到unsafe对象" + unsafe);

        Guard guard = new Guard();
        System.out.println("unsafe之前" + guard.giveAccess());
        guard.giveAccess();   // false, no access
        Field f1 = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        unsafe.putInt(guard, unsafe.objectFieldOffset(f1), 42); // memory corruption
        System.out.println("unsafe出来之后" + guard.giveAccess());
    }
}
//(1)绕过类初始化方法。当你想要绕过对象构造方法、安全检查器或者没有public的构造方法时，allocateInstance()方法变得非常有用。
//A o3 = (A) unsafe.allocateInstance(A.class); // unsafe //在单例模式时，我们似乎看到了危机。

//（2）内存修改
class Guard {
    private int ACCESS_ALLOWED = 1;

    public boolean giveAccess() {
        return 42 == ACCESS_ALLOWED;
    }
}
//sizeof 然后可以实现Java浅拷贝

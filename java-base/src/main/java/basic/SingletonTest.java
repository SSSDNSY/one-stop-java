package basic;

/**
 * 单例
 *
 * @since 2020-08-07
 */
public class SingletonTest extends Thread {

    @Override
    public void run() {

    }
}

/**
 * 懒汉式
 */
class LazySinglon {
    private static LazySinglon instance = null;

    private LazySinglon() {
    }

    public static LazySinglon getInstance() {
        if (null == instance) {
            instance = new LazySinglon();
        }
        return instance;
    }
}


/**
 * 饿汉式
 */
class FuckSinglon {
    private static FuckSinglon instance = new FuckSinglon();

    private FuckSinglon() {
    }

    public static FuckSinglon getInstance() {
        return instance;
    }
}

/**
 * 静态内部类
 */

class ScSingleton {
    private ScSingleton() {
    }

    static class innerClass {
        private static final ScSingleton instance = new ScSingleton();
    }

    public static ScSingleton getInstance() {
        return innerClass.instance;
    }
}

/**
 * 双重检验
 */
class DbCheckLazySingleton {
    // volatile 可见性 非一致性，还是得要锁 ，指令重排
    private static volatile DbCheckLazySingleton instance = null;

    private DbCheckLazySingleton() {
    }

    public DbCheckLazySingleton getInstance() {
        // 剔除了不必要的锁竞争
        if (instance == null) {
            synchronized (DbCheckLazySingleton.class) {
                // 下面的3个步骤不是原子的，也就是可能会有多个实例 二次校验就是为了检验单例
                if (instance == null) {
                    //1 声明变量分配内存空间
                    //2 绑定
                    //3 初始化
                    instance = new DbCheckLazySingleton();
                }
            }
        }
        return instance;
    }
}

/**
 * 枚举单例 说是被推荐的
 */

class EnumSingleton {
    private EnumSingleton() {
    }

    static enum EnumSingletons {
        INSTANCE;

        private EnumSingleton instance = null;

        private EnumSingletons() {
            instance = new EnumSingleton();
        }

        private EnumSingleton getInstance() {
            return instance;
        }
    }

    public static EnumSingleton getInstance() {
        return EnumSingletons.INSTANCE.getInstance();
    }
}

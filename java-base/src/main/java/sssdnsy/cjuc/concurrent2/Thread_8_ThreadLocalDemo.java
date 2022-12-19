package sssdnsy.cjuc.concurrent2;

/**
 * @author pengzh
 * @since 2020-07-02
 */
public class Thread_8_ThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        Thread t1  = new Thread(()->{
            threadLocal.set("asdf");
            try {
                Thread.sleep(1234);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());
        });
        Thread t2  = new Thread(()->{
            System.out.println(threadLocal.get());
            threadLocal.set("abc");
            System.out.println(threadLocal.get());

        });
        t1.start();
        t2.start();

    }
}

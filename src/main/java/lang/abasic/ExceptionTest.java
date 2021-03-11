package lang.abasic;

/**
 * 异常性能和耗时
 *
 * @author pengzh
 * @since 2020-06-29
 */
public class ExceptionTest {

    private int testTimes;

    public ExceptionTest(int testTimes) {
        this.testTimes = testTimes;
    }

    public void newObject() {
        long l = System.nanoTime();
        for (int i = 0; i < testTimes; i++) {
            new Object();
        }
        System.out.println("建立对象：" + (System.nanoTime() - l));
    }

    public void newException() {
        long l = System.nanoTime();
        for (int i = 0; i < testTimes; i++) {
            new Exception();
        }
        System.out.println("建立异常对象：" + (System.nanoTime() - l));
    }

    public void catchException() {
        long l = System.nanoTime();
        for (int i = 0; i < testTimes; i++) {
            try {
                throw new Exception();
            } catch (Exception e) {
            }
        }
        System.out.println("建立、抛出并接住异常对象：" + (System.nanoTime() - l));
    }

    public static void main(String[] args) throws Exception {
        ExceptionTest test = new ExceptionTest(1000000);
        test.newObject();
        test.newException();
        test.catchException();
        System.out.println(" main 报错前");
        testMethodException();
        System.out.println(" main 报错后");
    }

    public static void testMethodException() throws Exception {
        System.out.println(" testMethodException 报错前");
        throw new RuntimeException("就是要报错呢！");
    }


}

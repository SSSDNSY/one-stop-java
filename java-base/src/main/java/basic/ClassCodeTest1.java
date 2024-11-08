package basic;

/**
 * @since 2020-06-29
 * 字节码文件常用于分析语义问题
 */
public class ClassCodeTest1 {

    public int foo() {
        int x;
        try {

            x = 1;
            x /= 0;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
            System.out.println(x);
        }
    }

    public static void main(String[] args) {
        System.out.println(new ClassCodeTest1().foo());
    }
}

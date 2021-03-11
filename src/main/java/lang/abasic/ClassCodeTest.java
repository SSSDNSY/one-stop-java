package lang.abasic;

/**
 * @author pengzh
 * @since 2020-06-29
 * 字节码文件常用于分析语义问题
 * javap -v ClassCodeTest.class
 */
public class ClassCodeTest {
    private int m;

    public int inc() {
        return m + 1;
    }
}

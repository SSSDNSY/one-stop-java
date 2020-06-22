package lang;

/**
 * @author pengzh
 * @date 2020-03-12
 * <p>
 * 我一直以为 assert 仅仅是个报错函数，事实上，它居然是个宏，并且作用并非"报错"。
 * <p>
 * 在经过对其进行一定了解之后，对其作用及用法有了一定的了解，assert() 的用法像是一种"契约式编程"，
 * 在我的理解中，其表达的意思就是，程序在我的假设条件下，能够正常良好的运作，其实就相当于一个 if 语句：
 * if(假设成立)
 * {
 * 程序正常运行；
 * }
 * else
 * {
 * 报错&&终止程序！（避免由程序运行引起更大的错误）
 * }
 */
public class AssertTest {
    public static void main(String[] args) {
        System.out.println("".split(","));
    }
}

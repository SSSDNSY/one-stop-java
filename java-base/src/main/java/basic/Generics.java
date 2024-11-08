package basic;

/**
 * @since 2020-06-28
 * <p>
 * Java泛型这个特性是从JDK 1.5才开始加入的，因此为了兼容之前的版本，Java泛型的实现采取了“伪泛型”的策略，即Java在语法上支持泛型，
 * 但是在编译阶段会进行所谓的“类型擦除”（Type Erasure），将所有的泛型表示（尖括号中的内容）都替换为具体的类型（其对应的原生态类型），
 * 就像完全没有泛型一样。本文综合多篇文章后，总结了Java 泛型的相关知识，希望可以提升你对Java中泛型的认知效率。@pdai
 * * 著作权归https://www.pdai.tech所有。
 * * 链接：https://www.pdai.tech/md/java/basic/java-basic-x-generic.html
 * *
 */
public class Generics {

    //不用泛型
    public void add(int a, int b) {

    }

    public void add(float a, float b) {

    }

    //使用泛型 (上限)
    public <T extends Number> void add(T a, T b) {

    }

    //泛型有三种使用方式，分别为：泛型类、泛型接口、泛型方法。

    //泛型方法
    public static <T> T getInstance(Class<T> c) throws InstantiationException, IllegalAccessException {
        return c.newInstance();
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        final Object instance = getInstance(Class.forName("sssdnsy.abasic.ParentClass"));
        main1(args);
    }

    /**
     * 著作权归https://www.pdai.tech所有。
     * 链接：https://www.pdai.tech/md/java/basic/java-basic-x-generic.html
     * <p>
     * <?> 无限制通配符
     * <? extends E> extends 关键字声明了类型的上界，表示参数化的类型可能是所指定的类型，或者是此类型的子类
     * <? super E> super 关键字声明了类型的下界，表示参数化的类型可能是指定的类型，或者是此类型的父类
     * <p>
     * // 使用原则《Effictive Java》
     * // 为了获得最大限度的灵活性，要在表示 生产者或者消费者 的输入参数上使用通配符，使用的规则就是：生产者有上限、消费者有下限
     * 1. 如果参数化类型表示一个 T 的生产者，使用 < ? extends T>;
     * 2. 如果它表示一个 T 的消费者，就使用 < ? super T>；
     * 3. 如果既是生产又是消费，那使用通配符就没什么意义了，因为你需要的是精确的参数类型。
     */


    //    泛型数组

//    List<String>[] list11 = new ArrayList<String>[10]; //编译错误，非法创建
//    List<String>[] list12 = new ArrayList<?>[10]; //编译错误，需要强转类型
//    List<String>[] list13 = (List<String>[]) new ArrayList<?>[10]; //OK，但是会有警告
//    List<?>[] list14 = new ArrayList<String>[10]; //编译错误，非法创建
//    List<?>[] list15 = new ArrayList<?>[10]; //OK
//    List<String>[] list6 = new ArrayList[10]; //OK，但是会有警告
    public static void main1(String args[]) {
        Integer i[] = fun1(1, 2, 3, 4, 5, 6);   // 返回泛型数组
        fun2(i);
    }

    public static <T> T[] fun1(T... arg) {  // 接收可变参数
        return arg;            // 返回泛型数组
    }

    public static <T> void fun2(T param[]) {   // 输出
        System.out.print("接收泛型数组：");
        for (T t : param) {
            System.out.print(t + "、");
        }
    }
}

/**
 * 泛型类
 */
class Point<P> {
    private P x;
    private P y;

    public P getX() {
        return x;
    }

    public P getY() {
        return y;
    }

    public void setX(P x) {
        this.x = x;
    }

    public void setY(P y) {
        this.y = y;
    }

    public static void main(String[] args) {
        final Point<String> p = new Point<>();
        p.setX("SDFSDF");
        System.out.println(p.getX());
    }
}

/**
 * 多元泛型
 */
class NotePad<K, V> {
    private K key;
    private V val;

    public void setKey(K key) {
        this.key = key;
    }

    public void setVal(V val) {
        this.val = val;
    }

    public K getKey() {
        return key;
    }

    public V getVal() {
        return val;
    }

    public static void main(String[] args) {
        final NotePad<Integer, String> pad = new NotePad();
        pad.setKey(1);
        pad.setVal("123");
        System.out.println(pad.getKey());
        System.out.println(pad.getVal());
    }
}

/**
 * 泛型接口
 */
interface Info<T> {
    T getVar();
}

class InfoImpl<T> implements Info {
    private T var;

    public InfoImpl(T t) {
        this.setVar(t);
    }

    @Override
    public T getVar() {
        return this.var;
    }

    public void setVar(T var) {
        this.var = var;
    }

    public static void main(String[] args) {
        final InfoImpl<String> stringInfo = new InfoImpl<>("123");
        System.out.println(stringInfo.getVar());
    }
}


class Inter<T> {
    private T var;        // 定义泛型变量

    public void setVar(T var) {
        this.var = var;
    }

    public T getVar() {
        return this.var;
    }

    public String toString() {    // 直接打印
        return this.var.toString();
    }

    public static void main(String args[]) {
        Inter<String> i1 = new Inter<String>();        // 声明String的泛型对象
        Inter<Object> i2 = new Inter<Object>();        // 声明Object的泛型对象
        i1.setVar("hello");
        i2.setVar(new Object());
        fun(i1);
        fun(i2);
    }

    public static void fun(Inter<? super String> temp) {    // 只能接收String或Object类型的泛型，String类的父类只有Object类
        System.out.print(temp + ", ");
    }

}

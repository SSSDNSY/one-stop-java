package jvm.gc;

/**
 * @author ：pengzh
 * @date ：Created in 2020/2/3 9:47
 * @description： 引用计数法 复制  标记-清除 标记-整理
 * 引用计数法的可达性分析
 * @modified By：
 */
public class G1gcRoot {

    //方法区中类静态属性的对象
    private static G1gcRoot root2;
    //    方法区中常量引用的对象
    private static final String root3 = "root3";

    public static void main(String[] args) {
        methed1();
        //root4 本地方法栈中JNI引用的对象"
        new Thread().start();
    }

    private static void methed1() {
        //虚拟机栈中的对象
        G1gcRoot root1 = new G1gcRoot();
        System.out.println(root3);
    }

    private static void showMsg() {
        System.out.println("java中能作为gcroot对象的对象 4种：");
        System.out.println("虚拟机栈中的对象");
        System.out.println("方法区中类静态属性的对象");
        System.out.println("方法区中常量引用的对象");
        System.out.println("本地方法栈中JNI引用的对象");
    }
}

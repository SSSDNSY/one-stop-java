package lang.abasic;

/**
 * @author pengzh
 * @date 2020-03-05
 * 修饰符        类内部       同包     子类     任何地方
 * private 		yes
 * default      yes			yes
 * protected	yes			yes		yes
 * public		yes			yes		yes		yes
 *
 * 19.代码执行次序 多个静态成员变量, 静态代码块按顺序执行
 * 单个类中: 静态代码 -> main方法 -> 构造块 -> 构造方法
 * 构造块在每一次创建对象时执行
 * 涉及父类和子类的初始化过程
 * a.初始化父类中的静态成员变量和静态代码块
 * b.初始化子类中的静态成员变量和静态代码块
 * c.初始化父类的普通成员变量和构造代码块(按次序)，再执行父类的构造方法(注意父类构造方法中的子类方法覆盖)
 * d.初始化子类的普通成员变量和构造代码块(按次序)，再执行子类的构造方法
 *
 * 加载  连接 验证 准备 解析 初始化 使用 卸载
 *
 */
public class MainClass {
    public static void main(String[] args) throws Exception {
        ChildClass cc = new ChildClass();
        cc.tradeReg();
    }
}

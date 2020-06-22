package lang.features.jdk8;

/**
 * @author pengzh
 * @date 2020-03-03
 * <p>
 * Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。
 * Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。
 * 使用 Lambda 表达式可以使代码变的更加简洁紧凑。
 * <p>
 * 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
 * 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
 * 可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
 * 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。
 */
public class Java81Lambda {
    final static String CLASS_STR ="CLASS_STR";
    public static void main(String[] args) {
        Java81Lambda java81Lambda = new Java81Lambda();
        //类型声明 ,没有大括号和返回语句
        MethodOperation opr1 = (int a, int b) -> a + b;
        //不适用类型声明
        MethodOperation opr2 = (a, b) -> a - b;
        //大括号中的返回语句
        MethodOperation opr3 = (a, b) -> { return a * b; };

        System.out.println("10+5="+java81Lambda.operate(10,5,opr1));
        System.out.println("10-5="+java81Lambda.operate(10,5,opr2));
        System.out.println("10*5="+java81Lambda.operate(10,5,opr3));

        //不适用括号
        GreationService gs1 = s -> System.out.println(""+s);
        //使用括号
        GreationService gs2 = (s) -> System.out.println(""+s);

        gs1.saySomething("asdfasdf");
        gs2.saySomething("asdfasdf123123");

//        lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不能在 lambda 内部修改定义在域外的局部变量，否则会编译错误。
        final  String MAIN_STR ="MAIN_STR";
        GreationService gs3 = (s) -> System.out.println(MAIN_STR+s);
        GreationService gs4 = (s) -> System.out.println(CLASS_STR+s);
        gs3.saySomething("123abc");
        gs4.saySomething("123abc");
    }

    interface MethodOperation {
        int operation(int a, int b);
    }

    interface GreationService {
        void saySomething(String s);
    }

    private int operate(int a, int b, MethodOperation operation) {
        return operation.operation(a, b);
    }
}

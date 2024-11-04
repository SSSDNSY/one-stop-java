package features.jdk21;

/**
 * @Description 记录模式，也就是记录模式匹配，英文为“Record Patterns”，记录模式匹配是指自动匹配Record记录类，从而简化代码。
 * Record记录会自动生成了构造函数、getter、equals、hashCode、toString等方法，简化代码的编写，
 * 类似于lombok插件的@Data注解，但是对象属性只读，只有get方法，没有set方法。
 * <p>
 * 原文链接：https://blog.csdn.net/C_AJing/article/details/136179739
 */
public class RecordTest {

    record User(String name, Integer age) {

    }


    public static void main(String[] args) {

        User user = new User("name", 1);
        System.out.println(user.name);
        System.out.println(user);

    }

}

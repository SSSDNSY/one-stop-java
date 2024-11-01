package features.reflection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pengzh
 * @date 2020-03-10
 * 类 Student
 * 对象 Student类的实例
 * 类对象(Class对象) :类对象，就是用于描述这种类，都有什么属性，什么方法的
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    static String scholl;

    static {
        System.out.println("初始化 scholl");
        scholl = "版权由scholl所有";
    }

    private String name;
    private int age;
    private String sex;
}

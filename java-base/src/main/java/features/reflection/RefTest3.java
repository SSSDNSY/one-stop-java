package features.reflection;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @date 2020-03-10
 */


public class RefTest3 {
    public static void main(String[] args) {
        try {
//            源代码不需要发生任何变化，只需要修改配置文件，就可以导致程序的逻辑发生变化， 这是一种基于配置的编程思想。
            BufferedReader bf = new BufferedReader(new FileReader("W:\\code\\algorithm\\gh\\src\\main\\java\\lang\\reflection\\context.conf"));
            String className = bf.readLine();
            Class s1 = Class.forName(className);
            System.out.println(s1.getConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

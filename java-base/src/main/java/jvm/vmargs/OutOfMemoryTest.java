package jvm.vmargs;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: test for Java heap OutOf memory
 * -Xms8m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError  分析Java内存dump文件使用这个命令

 * @date: 2019/11/17
 */
public class OutOfMemoryTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            String str = "";
            for (int j = 0; j < 100; j++) {
            }
            list.add(str);
        }
        System.out.println("OK");
    }
}

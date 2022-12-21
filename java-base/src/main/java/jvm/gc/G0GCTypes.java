package jvm.gc;

import java.util.Random;

/**
 * @author ：pengzh
 * @date ：Created in 2020/2/5 16:14
 * @description：
 * @modified By：
 *
 * Serial ParNew Parallel
 * CMS  SerialOD ParaOld
 * G1
 *
 * java -server -Xms1024m -Xmx1024m -XX:+UseG1GC -jar  xxxx.war/xxx.jar
 */
public class G0GCTypes {
    public static void main(String[] args) {
        int i=0;
        while (true){
            System.out.println(new Random().nextInt(Integer.MAX_VALUE));
        }
    }
}

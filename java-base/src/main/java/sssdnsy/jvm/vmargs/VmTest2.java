package sssdnsy.jvm.vmargs;

import java.util.concurrent.TimeUnit;

/**
 * @author ：pengzh
 * @date ：Created in 2020/2/3 16:49
 * @description： 查看JVM默认垃圾回收
 * <p>
 * java -XX:+PrintFlagsInitial 查看初始
 * java -XX:+PrintFlagsFinal   查看、修改后的最终值 =没有改过 :=修改过
 * java -XX:+PrintFlagsFinal -Xms128m VmTest2
 * java -XX:+PrintCommandLineFlags -version
 * <p>
 * <p>
 * -XX:PrintCommandLineFlags
 * @modified By：
 */
public class VmTest2 {
    public static void main(String[] args) throws InterruptedException {

//          jinfo -flags 10464
//          -XX:InitialHeapSize=268435456 -XX:MaxHeapSize=4263510016
//          -XX:InitialHeapSize=268435456 -XX:MaxHeapSize=4263510016

        //-Xms
        System.out.println("JVM的内存总量" + Runtime.getRuntime().totalMemory()+" ("
        +(Runtime.getRuntime().totalMemory()/1024/1024)+"MB)");

        System.out.println("JVM空闲的内存" + Runtime.getRuntime().freeMemory());
        //-Xmx
        System.out.println("JVM试图使用的内存总量" + Runtime.getRuntime().maxMemory()+" ("
                +(Runtime.getRuntime().maxMemory()/1024/1024)+"MB)");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);

    }
}

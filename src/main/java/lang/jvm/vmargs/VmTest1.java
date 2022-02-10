package lang.jvm.vmargs;

/**
 *
 * jvm参数分3类：
 * -X参数 -Xms5m
 * 标准参数 java -c -p
 * -XX参数 -XX:+printGC (boolean类型 kv键值对)
 * -D 设置系统参数
 * <p>
 * -XX:+printGC -Xms5m -Xmx20m -XX:+UserSerialGC -XX:+PrintGCdetails
 * -XX:InitialHeapSize=5242880 -XX:MaxHeapSize=20971520 -XX:+PrintCommandLineFlags -XX:+PrintGC -XX:+PrintGCDetails
 * -XX:InitialHeapSize=5242880 -XX:MaxHeapSize=20971520 -XX:+PrintCommandLineFlags -XX:+PrintGC -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseSerialGC
 *
 *
 * jps -l 查看jvm运行中的进程号
 * jinfo -flags 进程号  查看当前进程配置的JVM参数
 *
 * -Xms和Xmx是-XX参数 不过是（-Xms 和-Xmx分别等价于 -XX:IntialHeapSize  -XX:MaxHeapSize） 因为太长了取得的别名
 * @author imi
 */
public class VmTest1 {

    public static void main(String[] args) {
//        System.out.println(args[1]);
        System.out.println("Max Memory     :" + Runtime.getRuntime().maxMemory());
        System.out.println("Free Memory    :" + Runtime.getRuntime().freeMemory());
        System.out.println("Total Memory   :" + Runtime.getRuntime().totalMemory());
        byte[] b1 = new byte[1024 * 1024];
        System.out.println("分配1M");

        System.out.println("Max Memory     :" + Runtime.getRuntime().maxMemory());
        System.out.println("Free Memory    :" + Runtime.getRuntime().freeMemory());
        System.out.println("Total Memory   :" + Runtime.getRuntime().totalMemory());
        byte[] b2 = new byte[4 * 1024 * 1024];
        System.out.println("分配4M");
    }
}

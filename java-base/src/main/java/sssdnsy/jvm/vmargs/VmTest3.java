package sssdnsy.jvm.vmargs;

/**
 * @author ：pengzh
 * @date ：Created in 2020/2/3 19:07
 * @description： -Xms10m -Xmx10m -XX:PrintGCDetails
 * -XX:SurvivorRatio=8  eden:s0:s1
 * -XX:NewRatio=4
 * -XX:MaxTenuringThreshould 15
 *
 * java堆GC角度划分：
 * 新生代 (占堆总内存1/3)：Eden,from,to
 * 老年代 (占堆总内存2/3)：tenured
 * 元空间:Metaspace
 * @modified By：
 */
public class VmTest3 {
    public static void main(String[] args) {
        System.out.println(">>>>>>>>>>>分配50M的byte数组：" + new byte[50 * 1024 * 1025]);
    }
}

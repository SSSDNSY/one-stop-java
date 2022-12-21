package utils.sigar;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;

/**
 * Need sigar-amd64-winnt.dll
 */
public class SigarTest {

    public static void main(String[] args) {

        Sigar sigar = new Sigar();
        try {
            System.out.println(System.getProperty("java.library.path"));
            Mem mem = sigar.getMem();
            CpuPerc cpuCerc = sigar.getCpuPerc();
            System.out.println("*****当前CPU使用情况 ：");
            System.out.println("#总使用率: " + cpuCerc.getCombined() * 100 + "%");
            System.out.println("#用户使用率(user): " + cpuCerc.getUser() * 100 + "%");
            System.out.println("#系统使用率(sys): " + cpuCerc.getSys() * 100 + "%");
            System.out.println("#当前空闲率(idel): " + cpuCerc.getIdle() * 100 + "%");
            System.out.println("\n*****当前内存使用情况 ：");
            System.out.println("#内存总量：" + mem.getTotal() / 1024 / 1024 + "M");
            System.out.println("#已使用内存：" + mem.getUsed() / 1024 / 1024 + "M");
            System.out.println("#剩余内存：" + mem.getFree() / 1024 / 1024 + "M");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

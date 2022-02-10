import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.RandomUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: imi
 * @Date: 2019/4/27 14:59
 * @Description:
 */
public class Test {


    private static Test TEST = null;


    private void doSth() {
        System.out.println("private method print the name " + this.name);
    }

    private String name;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    private Test() {
    }

    public static synchronized Test getTest() {
        if (TEST == null) {
            TEST = new Test();
        }
        return TEST;
    }

    /**
     * [B4, A1, A3, A2, B3, B1]
     * [A1, A2, A3, B1, B3, B4]
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        testBigDecimal();
    }

    public static void testBigDecimal(){
        BigDecimal addAppend = new BigDecimal(2d).add(new BigDecimal(1d)).add(new BigDecimal(8d));
        double score = addAppend.divide(new BigDecimal(3d),2,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(score);
    }

    @Data
    @AllArgsConstructor
    static
    class Obj {
//        private String l1;
//        private String l2;
        private String id;
    }

    static void a(int a, Integer b) {
        b = 3;
        System.out.println(a + " " + b);
    }

    public static void startTomcat() throws IOException {
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k  W:/server/nginx_proxy_tomcat/tomcat_8222/bin/startup.bat");
    }

    public static void stopTomcat() throws IOException {
        Runtime.getRuntime().exec("W:/server/nginx_proxy_tomcat/tomcat_8222/bin/showdown.bat");
    }



}

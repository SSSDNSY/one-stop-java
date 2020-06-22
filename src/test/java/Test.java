import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    ;

    public static synchronized Test getTest() {
        if (TEST == null) {
            TEST = new Test();
        }
        return TEST;
    }

    public static void main(String[] args) throws Exception {

    String String ;
    String = "asdf";

        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;String  "+String);
        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;Date  "+new Date());
        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;Math.round()  "+Math.round(-153.6));
        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;; Double.doubleToRawLongBits(a) "+ Double.doubleToRawLongBits(1));
        int i =Integer.MAX_VALUE;
        long b = i+2l;
        System.out.println(Integer.MAX_VALUE+2L);
        System.out.println(b);
        System.out.println(Integer.MIN_VALUE);
        float f = 1.3f;
        long num = 100;
        int x=10;
        double y =20.2;
        long z=10l;
        System.out.println(""+x+y*z);
        Map map = new HashMap();
        map.put("TOTAL_FEE",9661);
        long a = (long)map.get("TOTAL_FEE");
        System.out.println(a);
//        double f = Double.parseDouble((String) map.get("TOTAL_FEE"));
//        double fees = ( f/100);
//        long fee = 10;
//        System.out.println(fees );
//        double d1 = 1.01d;
//        double d2 = 1.01d;
//        double d3 = 1.00d;
//        double d4 = 0.00d;
//        System.out.println(d1 >= 1.01d);
//        System.out.println(d1 == d2);
//        System.out.println(d3 == d1);
//        System.out.println(0d==0.0d);
//        System.out.println(d4==0d);


//        Class.forName("com.mysql.jdbc.Driver");
//        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my2?useSSL=false", "root", "root");
//
//        String sql =
//                "insert into my2.status(variable_name,variable_value) values('THREADS_CONNECTED',?)";
//        PreparedStatement pspt = con.prepareStatement(sql);
//        Random r = new Random();
//
//        while (true) {
//            Thread.sleep(800);
//            try {
//                pspt.setInt(1, r.nextInt((int) System.currentTimeMillis() / 100));
//            } catch (Exception e) {
//            }
//            pspt.executeUpdate();
//
//        }



    }

    public static void startTomcat() throws IOException {
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k  W:/server/nginx_proxy_tomcat/tomcat_8222/bin/startup.bat");
    }

    public static void stopTomcat() throws IOException {
        Runtime.getRuntime().exec("W:/server/nginx_proxy_tomcat/tomcat_8222/bin/showdown.bat");
    }


}

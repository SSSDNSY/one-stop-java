import java.io.IOException;

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

    public static void main(String[] args) throws Exception {
        try {
            int i = 1;
            return;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally");
        }
    }

    public static void startTomcat() throws IOException {
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k  W:/server/nginx_proxy_tomcat/tomcat_8222/bin/startup.bat");
    }

    public static void stopTomcat() throws IOException {
        Runtime.getRuntime().exec("W:/server/nginx_proxy_tomcat/tomcat_8222/bin/showdown.bat");
    }

}

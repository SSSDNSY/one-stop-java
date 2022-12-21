package features.annotation;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author pengzh
 * @date 2020-03-11
 */
@JDBCconfig(database = "test", charSet = "UTF8", userName = "root", passWord = "123456")
public class DBUtilAnno {


    private DBUtilAnno() {
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;

        JDBCconfig conf = DBUtilAnno.class.getAnnotation(JDBCconfig.class);

        String HOST = conf.host();//default localhost
        int PORT = conf.port();//default 3306
        String CHAR_SET = conf.charSet();//"UTF8";
        String DATABASE = conf.database();//"test";
        String USERNAME = conf.userName();//"root";
        String PASSWORD = conf.passWord();//"123456";

        String urlStr = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s&useSSL=false", HOST, PORT, DATABASE, CHAR_SET);
        try {
            connection = DriverManager.getConnection(urlStr, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        System.out.println(DBUtilAnno.getConnection());
    }
}

package features.annotation;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author pengzh
 * @date 2020-03-11
 */
public class DBUtils {

    static String HOST = "localhost";
    static int PORT = 3306;
    static String CHAR_SET = "UTF8";
    static String DATABASE = "test";
    static String USERNAME = "root";
    static String PASSWORD = "123456";

    private DBUtils() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        String urlStr = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", HOST, PORT, DATABASE, CHAR_SET);
        try {
            connection = DriverManager.getConnection(urlStr, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        System.out.println(DBUtils.getConnection());
    }
}

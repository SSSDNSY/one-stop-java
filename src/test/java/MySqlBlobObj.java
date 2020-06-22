import java.io.InputStream;
import java.sql.*;

/**
 * @description:
 * @author: pengzh
 * @createDate: 2019/6/18$ 7:48$
 */
public class MySqlBlobObj {

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = DriverManager.getConnection("127.0.0.1","root","");

        Statement st = con.createStatement();
        st.execute("");

        ResultSet rs = st.getResultSet();


        Blob blob = con.createBlob();
        InputStream is = blob.getBinaryStream();





    }
}

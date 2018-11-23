
import java.sql.*;

import oracle.jdbc.driver.OracleDriver;

public class jdbcOracleTest {
    public static void testOracle() {

        //the connection of database
        Connection conn = null;

        //compile statement object
        PreparedStatement pre = null;

        //the set of compile result
        ResultSet res = null;

        //the sql will be used to search in database
        String sql;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //inform the user if the driver is run successfully
            System.out.println("starting connecting to database");

            String url = "jdbc:oracle:thin:@localhost:1521:XE";

            //the name and password of user which has been created through Oracle
            String user = "P4545180";
            String password = "ROOT";

            //connect with database
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("successful connection");
            } else {
                System.out.println("connection failed");
            }

            sql = "SELECT * FROM USERINFO";

            //execute the sql query
            pre = conn.prepareStatement(sql);
            res = pre.executeQuery();

            while (res.next()) {

                //output the results of search
                String fname = res.getString(1);
                String sname = res.getString(2);
                String address = res.getString(3);
                System.out.println("FirstName:" + fname + ' ' + "Surname:" + sname + ' '
                        + "StreetAddress:" + address);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (pre != null) {
                    pre.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        testOracle();
    }
}

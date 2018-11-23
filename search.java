package infoSearch;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/search")
public class search extends HttpServlet {


    /**
     * the connection of database
     */
    private static Connection conn = null;

    //compile statement object
    private static PreparedStatement pre = null;

    //the set of compile result
    public ResultSet res = null;

    static {
        try {

            //Run the Oracledriver and inform user if success
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("starting connecting to database");

            String url = "jdbc:oracle:thin:@localhost:1521:XE";

            //the name and password of user has been created through Oracle
            String user = "P4545180";
            String password = "ROOT";

            //connecting with the database
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("successful connection");
            } else {
                System.out.println("connection failed");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * response to request from index.jsp,get the surname entered by user and
     * search in database by surname
     * @param request the surname entered from index.jsp
     * @param response response to call associated method
     * @throws ServletException
     * @throws IOException
     */
    protected  void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //a List to save all information got from searching
        List<Map> list =new ArrayList<Map>();

        //the surname entered by user
        String sur = request.getParameter("sur");
        System.out.println(sur);

        try {

            //use this sql query to search in database
            String sql = "SELECT * FROM USERINFO WHERE SURNAME ='" + sur + "'";

            pre = conn.prepareStatement(sql);
            res = pre.executeQuery();

            while (res.next()) {

                //printed all results to java console and save them into the list
                String fname = res.getString(1);
                String sname = res.getString(2);
                String address = res.getString(3);
                System.out.println("FirstName:" + fname + ' ' + "SurName:" + sname + ' '
                        + "StreetAddress:" + address);

                //use a map to save the result
                Map map = new HashMap();
                map.put("fname",fname);
                map.put("address",address);
                map.put("sname",sname);

                list.add(map);

            }
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

        //transfer the list with all results into query.jsp and display them
        request.setAttribute("info_list" ,list);
        request.getRequestDispatcher("/query.jsp").forward(request, response);
    }
}

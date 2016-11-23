import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by caly on 10/17/2016.
 */
@WebServlet("/register")
public class register extends HttpServlet {
    ArrayList<user> list = new ArrayList<user>();


    public ArrayList<user> createUser(String username, String password) throws ClassNotFoundException, SQLException {
        try {// 1. load driver
            Class.forName("org.postgresql.Driver");

            // 2. define connection params to db
            final String URL = "jdbc:postgresql://54.93.65.5/4_Calin";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 4. create a query statement
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO login (username, password) VALUES (?,?)");
            pSt.setString(1, username);
            pSt.setString(2, password);

            // 5. execute a prepared statement
            int rowsInserted = pSt.executeUpdate();

            // 6. close the objects
            pSt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {


        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String password1 = req.getParameter("retypePass");

        if (password.equals(password1) && (username != "") && (password != "") && (password1 != "")) {
            try {
                createUser(username, password);
                System.out.println("New Account:" + username);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("User sau parola gresita!!");
            RequestDispatcher rd=req.getRequestDispatcher("login.html");
            rd.include(req,resp);
            out.println("<p style=\"text-align:center;margin-top:30px;font-size: 40px;color: #8B0000\"><b>" +
                    "Account succesully created!" + "</b><br>");
            out.close();

        } else {
            System.out.println("User sau parola gresita!!");
            RequestDispatcher rd=req.getRequestDispatcher("register.html");
            rd.include(req,resp);
            out.println("<p style=\"text-align:center;margin-top:30px;font-size: 40px;color: #8B0000\"><b>" +
                    "Account not created!" + "</b><br>");
            out.close();

        }
    }
}
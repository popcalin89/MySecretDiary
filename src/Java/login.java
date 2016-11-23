import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by caly on 10/17/2016.
 */
@WebServlet("/login")
public class login extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // read user and password introduced by the user in the UI
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String id = request.getParameter("id");


        int userid = -1;
        try {
            userid = AccesDB.isUserInDB(username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (userid!=-1) {
            System.out.println("user gasit cu id:"+userid);
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("iduser", userid);
            String success = "/diary.html";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(success);
            dispatcher.forward(request, response);
        }
        else {

            System.out.println("nu exista acest user in db, deci nu fac nimic ");
            String back = "/login.html";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
            dispatcher.forward(request, response);
        }

        System.out.println("login service called...");
    }

}
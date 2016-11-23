import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by caly on 10/19/2016
 */
@WebServlet("/mystory")
public class StoryServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        Integer iduser = (Integer) session.getAttribute("iduser");
        if (iduser == null) {
            System.out.println("nu esti logat");
            //notLoginAction(request, response, true);
        } else {
            System.out.println("bravo, esti un user deja logat");

            try {
                List l = AccesDB.readStory(iduser);

                // put the list in a json
                JsonObjectBuilder jObjBuilder = Json.createObjectBuilder();
                JsonArrayBuilder jArrayBuilder = Json.createArrayBuilder();
                for (ListIterator<StoryBean> iterator = l.listIterator(); iterator.hasNext(); ) {
                    StoryBean element = iterator.next();
                    jArrayBuilder.add(Json.createObjectBuilder()
                            .add("story", element.getStory())
                            .add("insertDate", element.getInsertDate().toString())
                            .add("id", element.getId())
                    );

                }

                jObjBuilder.add("myStory", jArrayBuilder);
                JsonObject jSonFinal = jObjBuilder.build();

                System.out.println("json pe list: " + jSonFinal.toString());

                returnJsonResponse(resp, jSonFinal.toString());


            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**/
    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }
}
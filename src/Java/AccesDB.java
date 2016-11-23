import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caly on 10/17/2016.
 */
public class AccesDB {

    public static int isUserInDB(String username, String password) throws ClassNotFoundException, SQLException {
        // 1. load driver
        Class.forName("org.postgresql.Driver");
        int userid = -1;

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/4_Calin";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT * FROM login where username='" + username + "' and password='" + password + "'");

        // 6. iterate the result set and print the values

        while (rs.next()) {
            userid = rs.getInt("id");
        }

        // 7. close the objects
        rs.close();
        st.close();
        conn.close();

        return userid;
    }

    public static List readStory(Integer iduser) throws ClassNotFoundException, SQLException {


        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/4_Calin";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT id,story,insertdate FROM diary where iduser='" + iduser + "' ORDER BY insertdate DESC");

        // 6. iterate the result set and print the values

        List<StoryBean> listOfStorys = new ArrayList();
        while (rs.next()) {

            // creez un bean de tipul tweetbean , adica un rand din db
            StoryBean task = new StoryBean();

            task.setStory(rs.getString("story").trim());
            task.setId(rs.getLong("id"));
            task.setInsertDate(rs.getDate("insertdate"));


            //scriu obiectul(randul) in lista
            listOfStorys.add(task);

        }

        System.out.println("dimensiunea listei:" + listOfStorys.size());
        // 7. close the objects
        rs.close();
        st.close();
        conn.close();

        return listOfStorys;
    }

    public static void addStory(String textToStory, Integer iduser) throws ClassNotFoundException, SQLException {
        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/4_Calin";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO diary (iduser,story,insertdate) VALUES (?,?, now())");
        pSt.setInt(1, iduser);
        pSt.setString(2, textToStory);
        System.out.println("adus cu succes");

        // 5. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();
    }

}
package dbHandler;

import rssParser.News;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBController {
    private static final String URL= "jdbc:postgresql://localhost:5432/NewsDB";
    private static final String NAME ="postgres";
    private static final String PASSWORD = "111";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL,NAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public List<News>getNewsFromDB(){
        return null;
    }
    public void setNewsToDB(){
        try {
            Statement statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

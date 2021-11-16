package dbHandler;

import rssParser.News;
import java.sql.*;
import java.util.ArrayList;


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
            connection=DriverManager.getConnection(URL,NAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static void save(News news,int index){

        try {

            Statement statement = connection.createStatement();
            String SQL=null;

            if(index==0)
            SQL = "INSERT INTO news VALUES('"+news.getPubDate()+"','"+news.getTitle()+
                    "','"+news.getDescription()+"', '"+news.getLink()+"')";

            if(index==1)
                SQL = "INSERT INTO earningnews VALUES('"+news.getPubDate()+"','"+news.getTitle()+
                        "','"+news.getDescription()+"', '"+news.getLink()+"')";

            if(index==2)
                SQL = "INSERT INTO financenews VALUES('"+news.getPubDate()+"','"+news.getTitle()+
                        "','"+news.getDescription()+"', '"+news.getLink()+"')";
            statement.executeUpdate(SQL);

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public static ArrayList<News> getAll(){
        ArrayList<News>list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM news";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                News news = new News();
                news.setPubDate(resultSet.getString("pubDate"));
                news.setTitle(resultSet.getString("title"));
                news.setLink(resultSet.getString("link"));
                list.add(news);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;

    }



}

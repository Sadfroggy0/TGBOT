package dbHandler;

import mailing.Mailing;
import mailing.Subscription;
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
    public static void saveSubs(String id){


            try {
                Statement statement = connection.createStatement();
                if(getSubs().size()==0) {
                    String SQL = "INSERT INTO subscribers VALUES('" + id + "')";
                    Subscription.subs.add(id);
                    statement.executeUpdate(SQL);
                }
                if(getSubs().size()>0){
                    for (int i =0;i< getSubs().size();i++){
                        if(id.equals(getSubs().get(i)))
                            return;

                        String SQL = "INSERT INTO subscribers VALUES('" + id + "')";
                        Subscription.subs.add(id);
                        statement.executeUpdate(SQL);
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


    }
    public static ArrayList<String> getSubs(){
        ArrayList<String>subs  = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL= "SELECT * FROM  subscribers";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                Subscription.subs.add(resultSet.getString("id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return subs;
    }
    public static void save(News news,int index){

        try {
            Statement statement = connection.createStatement();
            String SQL=null;
            ArrayList<String>temp = getAll(index);
            if(index==0) {
                for (int i =0;i< temp.size();i++){
                    if (temp.get(i).equals(news.getLink()))
                        return;
                }
                SQL = "INSERT INTO news VALUES('" + news.getPubDate() + "','" + news.getTitle() +
                        "','" + news.getDescription() + "', '" + news.getLink() + "')";
            }
            if(index==1) {
                for (int i =0;i< temp.size();i++){
                    if (temp.get(i).equals(news.getLink()))
                        return;
                }

                SQL = "INSERT INTO earningnews VALUES('" + news.getPubDate() + "','" + news.getTitle() +
                        "','" + news.getDescription() + "', '" + news.getLink() + "')";
            }
            if(index==2) {
                for (int i =0;i< temp.size();i++){
                    if (temp.get(i).equals(news.getLink()))
                        return;
                }
                SQL = "INSERT INTO financenews VALUES('" + news.getPubDate() + "','" + news.getTitle() +
                        "','" + news.getDescription() + "', '" + news.getLink() + "')";
            }
                statement.executeUpdate(SQL);

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public static void saveMailingNews(News news,int index){
        try {
            Statement statement = connection.createStatement();
            String SQL=null;
            ArrayList<String>temp = getAll(index);
            if(index==0) {
                for (int i =0;i< temp.size();i++){
                    if (temp.get(i).equals(news.getLink()))
                        return;
                }
                SQL = "INSERT INTO news VALUES('" + news.getPubDate() + "','" + news.getTitle() +
                        "','" + news.getDescription() + "', '" + news.getLink() + "')";
            }
            if(index==1) {
                for (int i =0;i< temp.size();i++){
                    if (temp.get(i).equals(news.getLink()))
                        return;
                }

                SQL = "INSERT INTO earningnews VALUES('" + news.getPubDate() + "','" + news.getTitle() +
                        "','" + news.getDescription() + "', '" + news.getLink() + "')";
            }
            if(index==2) {
                for (int i =0;i< temp.size();i++){
                    if (temp.get(i).equals(news.getLink()))
                        return;
                }
                SQL = "INSERT INTO financenews VALUES('" + news.getPubDate() + "','" + news.getTitle() +
                        "','" + news.getDescription() + "', '" + news.getLink() + "')";
            }
            statement.executeUpdate(SQL);
            Mailing.mailingNews.add(news);

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }


    public static ArrayList<String> getAll(int index){
        ArrayList<String>list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL=null;
            if (index==0)
                SQL = "SELECT link FROM news";
            if (index ==1)
                SQL = "SELECT link FROM earningnews";
            if(index==2)
                SQL = "SELECT link FROM financenews";

            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
               /* News news = new News();
                news.setPubDate(resultSet.getString("pubDate"));
                news.setTitle(resultSet.getString("title"));
                news.setLink(resultSet.getString("link"));
                list.add(news);*/
                list.add(resultSet.getString("link"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;

    }



}

package Data;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static Data.Log.*;


/**
 * Created by Eugen on 6/8/2016.
 */
public class DBCon {
    public static final Sql2o db;
    static {
        db = new Sql2o("jdbc:mysql://localhost:3306/wtodo", "root", "root");
    }

    public static Sql2o getDB(){
        return db;
    }
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = getDB().open();
        } catch(Exception e){
            log.error(e);
        }

        return connection;
    }


    public static boolean isCorrectLogin(String login, String pass){
        boolean checking = false;
        String query = "select pass from user where login = \"" + login + "\"";
        try{
            String dbPass = getConnection().createQuery(query).executeScalar(String.class);
            if(dbPass.equals(pass)){
                checking=true;
            } else {
                checking = false;
            }
        } catch(Exception e){
            log.error(e);
        }

        return checking;
    }
}

package Data;

import Data.Entity.Employe;
import Data.Entity.Project;
import Data.Entity.ToDo;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;

import static Data.Log.*;

/**
 * Created by Eugen on 6/8/2016.
 */
public class ModelDataApp {
    public static List<Project> getProjectList(){
        List<Project> list = new ArrayList<>();
        String query = "select id_proj, title, descr from project";
        try{
            list.addAll(DBCon.getConnection().createQuery(query).addColumnMapping("id_proj", "idProj").addColumnMapping("title", "title").addColumnMapping("descr", "descr").executeAndFetch(Project.class));
        } catch (Exception e){
            log.error("ModelDataApp ProjectList error - " + e);
        }
        return list;
    }
    public static List<ToDo> getToDoList(int id){
        List<ToDo> list = new ArrayList<>();
        String query = "select id_todo, id_proj, id_emp, title, descr, completed from todo where id_proj=" + id;
        try{
            list.addAll(DBCon.getConnection().createQuery(query).addColumnMapping("id_todo", "idToDo").addColumnMapping("id_proj", "idProj").addColumnMapping("id_emp", "idEmp").addColumnMapping("title", "title").addColumnMapping("descr", "descr").executeAndFetch(ToDo.class));
        } catch (Exception e){
            log.error("ModelDataApp ToDoList error - " + e);
        }
        return list;
    }

    public static boolean addTodo(String title, String description, int projectId, int currentUserId){
        //String query = "insert into todo (title, description, id_proj, id_emp, completed) values (" + title +"," + description + "," + projectId + "," + currentUserId + ", 0)";
        String query = "insert into todo (title, descr, id_proj, id_emp, completed) values (:title, :descr, :idPr, :idEmp, 0)";
        try{
            DBCon.getConnection().createQuery(query).addParameter("title", title).addParameter("descr", description).addParameter("idPr", projectId).addParameter("idEmp", currentUserId).executeUpdate();
        } catch (Exception e){
            log.error("insert is not succesful --- " + e);
        }
        return false;
    }

    public static Integer getUserId(String name){
        int id=0;
        String sql = "select id_user from user where login=:logUser";
        try{
            return DBCon.getConnection().createQuery(sql).addParameter("logUser", name).executeScalar(Integer.class);
        } catch(Exception e){
            log.error("User id is not available  --- " + e);
        }

        return id;
    }

    public static String getUserLogin(int id){
        String login ="";
        String sql = "select login from user where id_user=:id";
        try{
            return DBCon.getConnection().createQuery(sql).addParameter("id", id).executeScalar(String.class);
        } catch(Exception e){
            log.error("User id is not available  --- " + e);
        }

        return login;
    }

}

package Data;

import Data.Entity.Employe;
import Data.Entity.Project;
import Data.Entity.ToDo;

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

}

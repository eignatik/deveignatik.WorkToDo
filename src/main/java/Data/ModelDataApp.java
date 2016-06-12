package Data;

import Data.Entity.Employe;
import Data.Entity.Project;

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
            list.addAll(DBCon.getConnection().createQuery(query).addColumnMapping("id_proj", "idProj").addColumnMapping("title", "Title").addColumnMapping("descr", "descr").executeAndFetch(Project.class));
        } catch (Exception e){
            log.error("ModelDataApp ProjectList error - " + e);
        }
        return list;
    }
}

package Data.Entity;

/**
 * Created by Eugen on 6/8/2016.
 */
public class ToDo {
    private int idToDo;
    private int idProj;
    private int idEmp;
    private String title;
    private String descr;
    private boolean completed;

    public int getIdToDo() {
        return idToDo;
    }

    public void setIdToDo(int idToDo) {
        this.idToDo = idToDo;
    }

    public int getIdProj() {
        return idProj;
    }

    public void setIdProj(int idProj) {
        this.idProj = idProj;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

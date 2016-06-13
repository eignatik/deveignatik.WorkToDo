package Data.Controllers;

import Data.Entity.Project;
import Data.Entity.ToDo;
import Data.ModelDataApp;
import Data.Session;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Eugen on 6/12/2016.
 */
public class MainController implements Initializable {
    @FXML
    private Label username;
    @FXML
    private TextArea projDescr;
    @FXML
    private ListView<Project> projList;
    @FXML
    private TextArea todoDescr;
    @FXML
    private ListView<ToDo> todoList;
    private int idProject;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(Session.getSession().getUser());
        ObservableList<Project> pList = FXCollections.observableList(ModelDataApp.getProjectList());
        projList.setItems(pList);
        projList.setCellFactory(new Callback<ListView<Project>, ListCell<Project>>(){
            @Override
            public ListCell<Project> call(ListView<Project> p){
                ListCell<Project> cell = new ListCell<Project>(){
                    @Override
                    protected void updateItem(Project t, boolean bln){
                        super.updateItem(t, bln);
                        if(t != null){
                            setText(t.getIdProj() + "   " + t.getTitle());
                        }
                    }
                };
                return cell;
            }
        });
        projList.getSelectionModel().selectedItemProperty().addListener((new ChangeListener<Project>() {
            @Override
            public void changed(ObservableValue<? extends Project> observable, Project oldValue, Project newValue) {
                if(newValue==null){return;}
                idProject = projList.getSelectionModel().getSelectedItem().getIdProj();
                MainController.this.showProjDescription(projList.getSelectionModel().getSelectedItem().getDescr());
                MainController.this.showToDo(projList.getSelectionModel().getSelectedItem().getIdProj());
            }
        }));
        todoList.getSelectionModel().selectedItemProperty().addListener((new ChangeListener<ToDo>() {
            @Override
            public void changed(ObservableValue<? extends ToDo> observable, ToDo oldValue, ToDo newValue) {
                if(newValue==null){return;}
                MainController.this.showTodoDescription(todoList.getSelectionModel().getSelectedItem().getDescr());
            }
        }));
    }

    public void showProjDescription(String description){
        projDescr.setText(description);
        System.out.println("Selected description: " + description);
    }

    public void showTodoDescription(String description){
        todoDescr.setText(description);
        System.out.println("Selected description: " + description);
    }

    public void showToDo(int id){
        if(idProject==0) {return;}
        ObservableList<ToDo> todoList = FXCollections.observableList(ModelDataApp.getToDoList(idProject));
        this.todoList.setItems(todoList);
//        this.todoList.setCellFactory(CheckBoxListCell.forListView(new Callback<ListView<ToDo>, ObservableValue<Boolean>>(){
//            @Override
//            public ObservableValue<Boolean> call(ListView<ToDo> p){
//                BooleanProperty observ = new SimpleBooleanProperty();
//                observ.addListener(new ChangeListener<Boolean>() {
//                    @Override
//                    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasSelected, Boolean isNowSelected) {
//                        System.out.println("Check box was selected");
//                    }
//                });
//                return observ;
//            }
//
//        }));
        this.todoList.setCellFactory(new Callback<ListView<ToDo>, ListCell<ToDo>>(){
            @Override
            public ListCell<ToDo> call(ListView<ToDo> p){
                ListCell<ToDo> cell = new ListCell<ToDo>(){
                    @Override
                    protected void updateItem(ToDo t, boolean bln){
                        super.updateItem(t, bln);
                        if(t != null){
                            setText(t.isCompleted() + " " + t.getTitle() + "  Employer: " + t.getIdEmp());
                        }
                    }
                };
                return cell;
            }
        });
    }
}

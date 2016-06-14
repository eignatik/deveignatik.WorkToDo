package Data.Controllers;

import Data.Entity.Project;
import Data.Entity.ToDo;
import Data.Log;
import Data.ModelDataApp;
import Data.Session;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

import static Data.Log.log;

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
    @FXML
    private Button addButton;
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
                Session.getSession().setProjectId(idProject);
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

    public void addDialog() {
        String file = "/fxml/addDialog.fxml";
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root;
        try{
            root = (Parent)loader.load(getClass().getResourceAsStream(file));
            stage.setTitle("Add ToDo");
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e){
            log.error("Error of opening fxml file --- " + e);
        }

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
                            setText(t.isCompleted() + " " + t.getTitle() + "  Employer: " + ModelDataApp.getUserLogin(t.getIdEmp()));
                        }
                    }
                };
                return cell;
            }
        });
    }
}

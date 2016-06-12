package Data.Controllers;

import Data.Entity.Project;
import Data.Log;
import Data.ModelDataApp;
import Data.Session;
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
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.Observable;
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
                MainController.this.showDescription(projList.getSelectionModel().getSelectedItem().getDescr());
            }
        }));
    }

    public void showDescription(String description){
        projDescr.setText(description);
        System.out.println("Selected description: " + description);
    }
}

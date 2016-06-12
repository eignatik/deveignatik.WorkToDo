package Data.Controllers;

import Data.DBCon;
import Data.Session;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Eugen on 6/7/2016.
 */
public class LoginController implements Initializable{

    @FXML
    private Button submit;
    @FXML
    private TextField login;
    @FXML
    private PasswordField pass;
    @FXML
    private Label status;

    @FXML
    public void login() throws Exception{
        if(login.getText().isEmpty() || pass.getText().isEmpty()){
            status.setText("Fields can't be empty");
        } else {
            String loginCur = login.getText();
            String passCur = pass.getText();
            if(DBCon.isCorrectLogin(loginCur, passCur)){
                Session.getSession().setUser(loginCur);
                Stage newStage = new Stage();
                newStage = (Stage) submit.getScene().getWindow();
                newStage.close();
                String fxmlFile = "/fxml/main.fxml";
                FXMLLoader loader = new FXMLLoader();
                Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
                newStage.setTitle("Projects. You logged like: " + loginCur + ".");
                newStage.setScene(new Scene(root));
                newStage.show();

            } else {
                status.setText("Login or pass are incorrect");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}

import Data.DBCon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Eugen on 6/7/2016.
 */
public class MainController implements Initializable{

    @FXML
    private Button submit;
    @FXML
    private TextField login;
    @FXML
    private PasswordField pass;
    @FXML
    private Label status;

    @FXML
    public void login(){
        if(login.getText().isEmpty() || pass.getText().isEmpty()){
            status.setText("Fields can't be empty");
        } else {
            String loginCur = login.getText();
            String passCur = pass.getText();
            if(DBCon.isCorrectLogin(loginCur, passCur)){
                status.setText("Login or pass are correct");
            } else {
                status.setText("Login or pass are incorrect");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}

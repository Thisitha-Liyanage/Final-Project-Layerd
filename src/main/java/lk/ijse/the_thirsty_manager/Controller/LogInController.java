package lk.ijse.the_thirsty_manager.Controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.the_thirsty_manager.AppInitializer;
import lk.ijse.the_thirsty_manager.Model.LogInModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private AnchorPane acnLogInPage;

    @FXML
    private Button btnLogIn;

    @FXML
    private Button btnPasswordReset;

    @FXML
    private PasswordField pwfUserPassword;

    @FXML
    private TextField txtUserID;
    LogInModel logInModel = new LogInModel();
    private final ProgressIndicator progressIndicator = new ProgressIndicator();

    @FXML
    void btnLogInOnAction(ActionEvent event) {
        try{
            String inpUserId = txtUserID.getText();
            String inpPass = pwfUserPassword.getText();
            String role = logInModel.logIn(inpUserId , inpPass);

            if(role == null){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR");
                alert.setContentText("Wrong Authentication Data");
                alert.show();
                txtUserID.clear();
                pwfUserPassword.clear();

            }else if(role.equals("Admin")){

                navigatTo("/View/AdminDashBoard.fxml");

            }else {
                navigatTo("/View/SuperDashBoard.fxml");
            }
        } catch (SQLException e ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Database Error Found");
        }
    }

    @FXML
    void btnPasswordResetOnAction(ActionEvent event) {

    }

    public void navigatTo(String path){
        try{
            acnLogInPage.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            acnLogInPage.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnLogInAnActionK(KeyEvent keyEvent) {
        try{
            String inpUserId = txtUserID.getText();
            String inpPass = pwfUserPassword.getText();
            String role = logInModel.logIn(inpUserId , inpPass);

            if(role == null){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR");
                alert.setContentText("Wrong Authentication Data");
                alert.show();
                txtUserID.clear();
                pwfUserPassword.clear();

            }else if(role.equals("Admin")){

                navigatTo("/View/AdminDashBoard.fxml");

            }else {
                navigatTo("/View/SuperDashBoard.fxml");
            }
        } catch (SQLException e ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Database Error Found");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pwfUserPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnLogInAnActionK(event);
            }
        });
    }
}











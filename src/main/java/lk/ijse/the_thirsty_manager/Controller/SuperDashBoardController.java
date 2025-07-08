package lk.ijse.the_thirsty_manager.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SuperDashBoardController implements Initializable {

    @FXML
    private AnchorPane ancPageLoader;

    @FXML
    private AnchorPane ancSuperDash;

    @FXML
    private AnchorPane ancSuperDashBoard;

    @FXML
    private Button btnSupervisor;

    @FXML
    private Button btnSupervisor1;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnOrder;

    @FXML
    private Button btnTable;



    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        ancSuperDashBoard.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/View/LogInPage.fxml"));
        ancSuperDashBoard.getChildren().add(parent);
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        navigatTo("/View/EmployeePage.fxml");
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {
        navigatTo("/View/HomePage.fxml");
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) {
        navigatTo("/View/OrderPage.fxml");
    }

    @FXML
    void btnTableOnAction(ActionEvent event) {

    }
    public void navigatTo(String path){
        try{
            ancPageLoader.getChildren().clear();
           Parent parent = FXMLLoader.load(getClass().getResource(path));
           ancPageLoader.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navigatTo("/View/HomePage.fxml");
    }

}



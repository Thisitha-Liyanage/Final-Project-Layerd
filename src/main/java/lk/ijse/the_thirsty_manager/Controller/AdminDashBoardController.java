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

public class AdminDashBoardController implements Initializable {

    @FXML
    private AnchorPane ancAdminDash;

    @FXML
    private AnchorPane ancAdminDashBoard;

    @FXML
    private AnchorPane ancPageLoader;

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnBooking;

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnEvent;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnInventory;

    @FXML
    private Button btnOrder;

    @FXML
    private Button btnTable;

    @FXML
    void btnAdminOnAction(ActionEvent event) {

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        ancAdminDashBoard.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/View/LogInPage.fxml"));
        ancAdminDashBoard.getChildren().add(parent);
    }

    @FXML
    void btnBookingOnAction(ActionEvent event) {

    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        navigatTo("/View/CustomerPage.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        navigatTo("/View/EmployeePage.fxml");
    }

    @FXML
    void btnEventOnAction(ActionEvent event) {

    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {
        navigatTo("/View/HomePage.fxml");
    }

    @FXML
    void btnInventoryOnAction(ActionEvent event) {
        navigatTo("/View/InventoryPage.fxml");
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



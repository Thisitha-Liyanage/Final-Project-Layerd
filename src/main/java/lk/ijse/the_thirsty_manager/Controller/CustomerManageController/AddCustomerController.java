package lk.ijse.the_thirsty_manager.Controller.CustomerManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Model.CustomerManageModel.AddCustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    private CustomerDto customerDto = new CustomerDto();
    @FXML
    private AnchorPane ancAddCustomer;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblCustomerID;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerAge;

    @FXML
    private TextField txtCustomerContact;

    @FXML
    private TextField txtCustomerName;

    private AddCustomerModel addCustomerModel = new AddCustomerModel();


    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancAddCustomer.getChildren().clear();
        ancAddCustomer.setVisible(false);
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtCustomerAddress.clear();
        txtCustomerAge.clear();
        txtCustomerContact.clear();
        txtCustomerName.clear();

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {

        lblCustomerID.setText(addCustomerModel.getNextId());
        String cusID = lblCustomerID.getText();
        String cusName = txtCustomerName.getText();
        String cusAddress = txtCustomerAddress.getText();
        String cusContact = txtCustomerContact.getText();
        String cusAge = txtCustomerAge.getText();

        if (cusName.isEmpty() || cusAddress.isEmpty() || cusContact.isEmpty() || cusAge.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Fields");
            alert.setContentText("Please fill all fields");
            alert.show();
            return;
        }

        if (cusContact.length() != 10) {
            errorSender("Not Allowed" , null , "Wrong Contact Number");
            txtCustomerContact.clear();
            return;
        }
        int customerAgeInt = 0 ;
        try {
            customerAgeInt = Integer.parseInt(cusAge);
        } catch (NumberFormatException e) {
            errorSender("ERROR" , null , "Customer Age Must Be Numbers");
            txtCustomerContact.clear();
        }
        if (customerAgeInt <= 20) {
            errorSender("Not Allowed" , "Age - below 20" , "Customer Not Allowed");
            txtCustomerAge.clear();
            return;
        }

            customerDto.setCustomerID(cusID);
            customerDto.setCustomerName(cusName);
            customerDto.setAddress(cusAddress);
            customerDto.setContact(cusContact);
            customerDto.setAge(customerAgeInt);

            try {
                boolean isSaved = addCustomerModel.saveCustomer(customerDto);
                if (isSaved) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Saved");
                    alert.setContentText("Customer Saved");
                    alert.show();
                    btnResetOnAction(null);
                    loadCustomerId();
                } else {
                    errorSender("Not Saved" , null , "Customer Not Saved");
                }
            } catch (SQLException e) {
                errorSender("Database Error" , null , "Internal Database Error");
            }

        }
        public void errorSender(String titleTxt , String headerTxt , String contentTxt){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(titleTxt);
            alert.setHeaderText(headerTxt);
            alert.setContentText(contentTxt);
            alert.show();
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCustomerId();
    }

    private void loadCustomerId() {
        try {
            lblCustomerID.setText(addCustomerModel.getNextId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




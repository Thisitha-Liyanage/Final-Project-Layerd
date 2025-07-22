package lk.ijse.the_thirsty_manager.Controller.CustomerManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.Custom.CustomerBO;
import lk.ijse.the_thirsty_manager.BO.Custom.IMPL.CustomerBOIMPL;
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
    void btnSaveOnAction(ActionEvent event) {

        loadCustomerId();
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

        CustomerBO customerBO = new CustomerBOIMPL();
        boolean isSaved = false;
        try {
            isSaved = customerBO.save(customerDto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION , "Customer Saved").show();
            loadCustomerId();
            btnResetOnAction(null);
        }else{

            new Alert(Alert.AlertType.ERROR , "Customer Not Saved").show();
            btnResetOnAction(null);
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
    private CustomerBO customerBO = new CustomerBOIMPL();

    private void loadCustomerId() {
        try {
            lblCustomerID.setText(customerBO.nextID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




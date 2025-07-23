package lk.ijse.the_thirsty_manager.Controller.CustomerManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.CustomerBO;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Model.CustomerManageModel.UpdateCustomerModel;

import java.sql.SQLException;

public class UpdateCustomerController {

    @FXML
    private AnchorPane ancUpdateCustomer;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnFindCustomer;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerAge;

    @FXML
    private TextField txtCustomerContact;

    @FXML
    private TextField txtCustomerID;

    @FXML
    private TextField txtCustomerName;

    private final UpdateCustomerModel updateCustomerModel = new UpdateCustomerModel();
    private CustomerBO customerBO = BOFactory.getInstance().getBO(BOTypes.CUSTOMER);

    String updCusID ;
    String updCusName ;
    String updCusAddress ;
    String updCusContact ;
    String updAge;
    @FXML
    void btnCloseOnAction(ActionEvent event) {

        ancUpdateCustomer.getChildren().clear();
        ancUpdateCustomer.setVisible(false);
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtCustomerID.clear();
        txtCustomerAddress.clear();
        txtCustomerAge.clear();
        txtCustomerContact.clear();
        txtCustomerName.clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        updCusName = txtCustomerName.getText();
        updCusAddress = txtCustomerAddress.getText();
        updCusContact = txtCustomerContact.getText();
        updAge = txtCustomerAge.getText();


        if (updCusName.isEmpty() || updCusAddress.isEmpty() || updCusContact.isEmpty() || updAge.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Fields");
            alert.setContentText("Please fill all fields");
            alert.show();
            return;
        }

        if (updCusContact.length() != 10) {
            errorSender("Not Allowed" , null , "Wrong Contact Number");
            txtCustomerContact.clear();
            return;
        }
        int updAgeInt = 0;
        try {
            updAgeInt = Integer.parseInt(updAge);
        } catch (NumberFormatException e) {
            errorSender("ERROR" , null , "Customer Age Must Be Numbers");
            txtCustomerContact.clear();
        }
        if ( updAgeInt <= 20) {
            errorSender("Not Allowed" , "Age - below 20" , "Customer Not Allowed");
            txtCustomerAge.clear();
            return;
        }
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerID(updCusID);
        customerDto.setCustomerName(updCusName);
        customerDto.setAddress(updCusAddress);
        customerDto.setContact(updCusContact);
        customerDto.setAge(updAgeInt);

        boolean isUpdate = false;
        try {
            System.out.println(isUpdate);
            isUpdate = customerBO.update(customerDto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(isUpdate){
            infoSender("Customer Updated" , null , "Customer Update Success");
            btnResetOnAction(null);
        }else{
            new Alert(Alert.AlertType.ERROR , "Customer Not Updated").show();
            btnResetOnAction(null);
        }
    }

    public void btnFindCustomerOnAction(ActionEvent actionEvent) throws SQLException {
        updCusID = txtCustomerID.getText();
        CustomerDto foundCustomer = customerBO.searchCustomer(updCusID);

        if(foundCustomer != null){
            if (updCusID == null) {
                errorSender("ERROR", null, "Enter Customer ID");
                btnResetOnAction(null);
            } else {
                txtCustomerName.setText(foundCustomer.getCustomerName());
                txtCustomerAddress.setText(foundCustomer.getAddress());
                txtCustomerContact.setText(foundCustomer.getContact());
                txtCustomerAge.setText(String.valueOf(foundCustomer.getAge()));
            }
        }else{
            errorSender("ERROR" , null , "Customer ID not Found");
        }
    }
    public void errorSender(String titleTxt , String headerTxt , String contentTxt){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleTxt);
        alert.setHeaderText(headerTxt);
        alert.setContentText(contentTxt);
        alert.show();
    }
    public void infoSender(String titleTxt , String headerTxt , String contentTxt){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleTxt);
        alert.setHeaderText(headerTxt);
        alert.setContentText(contentTxt);
        alert.show();
    }
}

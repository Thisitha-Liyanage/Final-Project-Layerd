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
import lk.ijse.the_thirsty_manager.Model.CustomerManageModel.DeleteCustomerModel;

import java.sql.SQLException;

public class DeleteCustomerController {

    @FXML
    private AnchorPane ancDeleteCUstomer;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnFind;

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

    String delCusID;


    private final DeleteCustomerModel deleteCustomerModel= new DeleteCustomerModel();
    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancDeleteCUstomer.getChildren().clear();
        ancDeleteCUstomer.setVisible(false);

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtCustomerID.clear();
        txtCustomerAddress.clear();
        txtCustomerAge.clear();
        txtCustomerContact.clear();
        txtCustomerName.clear();
    }
    private CustomerBO customerBO = BOFactory.getInstance().getBO(BOTypes.CUSTOMER);

    @FXML
    void  btnFindOnAction(ActionEvent event) throws SQLException {
        delCusID = txtCustomerID.getText();
        CustomerDto foundCustomer = customerBO.searchCustomer(delCusID);


            if (foundCustomer != null) {
                if (delCusID.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Missing Fields");
                    alert.setContentText("Please fill Customer ID");
                    alert.show();
                    return;
                }
                txtCustomerName.setText(foundCustomer.getCustomerName());
                txtCustomerAddress.setText(foundCustomer.getAddress());
                txtCustomerContact.setText(foundCustomer.getContact());
                txtCustomerAge.setText(String.valueOf(foundCustomer.getAge()));

            } else {
                errorSender("ERROR", null, "Customer ID not Found");
            }
        }


    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        boolean isDelete = customerBO.delete(delCusID) ;

        if (delCusID.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Fields");
            alert.setContentText("Please fill Customer ID");
            alert.show();
            return;
        }

        if(isDelete){
            infoSender("Success" , null , "Customer Deleted");
            btnResetOnAction(null);
        }else{
            errorSender("Not Deleted" , null , "Customer not Delete");
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
    public void infoSender(String titleTxt , String headerTxt , String contentTxt){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleTxt);
        alert.setHeaderText(headerTxt);
        alert.setContentText(contentTxt);
        alert.show();
    }
}

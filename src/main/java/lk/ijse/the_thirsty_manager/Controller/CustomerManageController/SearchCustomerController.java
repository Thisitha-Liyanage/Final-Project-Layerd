package lk.ijse.the_thirsty_manager.Controller.CustomerManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.CustomerBO;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Model.CustomerManageModel.SearchCustomerModel;

import java.sql.SQLException;

public class SearchCustomerController {

    @FXML
    private AnchorPane ancSearchCustomerpage;

    @FXML
    private Button btnClose;

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

    private CustomerBO customerBO = BOFactory.getInstance().getBO(BOTypes.CUSTOMER);
    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancSearchCustomerpage.getChildren().clear();
        ancSearchCustomerpage.setVisible(false);
    }
    private SearchCustomerModel searchCustomerModel = new SearchCustomerModel();
    public void searchCustomer(String cusID){
        try {
            CustomerDto searchCustomerDto = customerBO.searchCustomer(cusID);

            if (searchCustomerDto != null){
                if(cusID.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Missing Fields");
                    alert.setContentText("Please fill Order ID");
                    alert.show();
                    ancSearchCustomerpage.getChildren().clear();
                    ancSearchCustomerpage.setVisible(false);
                    return;
                }


                lblCustomerID.setText(searchCustomerDto.getCustomerID());
                txtCustomerName.setText(searchCustomerDto.getCustomerName());
                txtCustomerAddress.setText(searchCustomerDto.getAddress());
                txtCustomerContact.setText(searchCustomerDto.getContact());
                txtCustomerAge.setText(String.valueOf(searchCustomerDto.getAge()));
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ID not found");
                alert.setContentText("Customer ID not found");
                alert.show();

                ancSearchCustomerpage.setVisible(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

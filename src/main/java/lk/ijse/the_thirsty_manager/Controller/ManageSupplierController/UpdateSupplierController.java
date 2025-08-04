package lk.ijse.the_thirsty_manager.Controller.ManageSupplierController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.SupplierBO;
import lk.ijse.the_thirsty_manager.Dto.SupplierDto;
import lk.ijse.the_thirsty_manager.Model.SupplierManageModel.UpdateSupplierModel;

import java.sql.SQLException;

public class UpdateSupplierController {

    @FXML
    private AnchorPane ancUpdateSUp;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnFind;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSupID;

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancUpdateSUp.getChildren().clear();
        ancUpdateSUp.setVisible(false);
    }

    private final SupplierBO supplierBO = BOFactory.getInstance().getBO(BOTypes.SUPPLIER);
    private SupplierDto supplierDto = new SupplierDto();
    @FXML
    void btnFindOnAction(ActionEvent event) {
        String ID = txtSupID.getText();

        if(ID.isEmpty()){
            errorSender("ID not Found" , null , "Employee ID Not Found");
            btnResetOnAction(null);
            return;
        }

        supplierDto.setSupID(ID);

        try {
            SupplierDto findSup =supplierBO.searchById(ID);

            if(findSup == null){
                errorSender("ID Not Found" , null , "Supplier ID not Found");
                btnResetOnAction(null);
                return;
            }

            txtName.setText(findSup.getName());
            txtAddress.setText(findSup.getAddress());
            txtContact.setText(findSup.getContact());

        } catch (SQLException e) {
            errorSender("ERROR" , null , "Internal Database Error");
            e.printStackTrace();
            btnResetOnAction(null);
        }

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtSupID.clear();
        txtName.clear();
        txtContact.clear();
        txtAddress.clear();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String name = txtName.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();

        if(name.isEmpty() || contact.isEmpty() || address.isEmpty()){
            errorSender("Missing Fields" , null , "Fill All Fields");
            btnResetOnAction(null);
            return;
        }
        if(contact.length() != 10){
            errorSender("Invalid Contact Number" , null , "Enter Valid Contact Number");
            btnResetOnAction(null);
            return;
        }

        supplierDto.setName(name);
        supplierDto.setContact(contact);
        supplierDto.setAddress(address);

        try{
            boolean isUpdate =supplierBO.update(supplierDto);

            if(isUpdate){
              infoSender("Supplier Updated" , null , "Supplier Updated Success");
              btnResetOnAction(null);
            }else{
                errorSender("Not Updated" , null , "Supplier Not Updated");
                btnResetOnAction(null);
            }
        } catch (SQLException e) {
            errorSender("ERROR0 " , null , "Internal Database Error");
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

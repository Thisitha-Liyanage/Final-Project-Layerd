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


import java.sql.SQLException;

public class DeleteSupplierController {

    @FXML
    private AnchorPane ancDeleteSup;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnFind;

    @FXML
    private Button btnReset;

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
        ancDeleteSup.getChildren().clear();
        ancDeleteSup.setVisible(false);
    }
    private final SupplierBO supplierBO = BOFactory.getInstance().getBO(BOTypes.SUPPLIER);
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = supplierBO.delete(txtSupID.getText());

            if(isDeleted){
                infoSender("Supplier Deleted" , null , "Supplier Deleted Success");
                btnResetOnAction(null);
            }else{
                errorSender("Not Deleted" , null , "Supplier Not Deleted");
                btnResetOnAction(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorSender("Not Deleted" , null , "Supplier Not Deleted");
            btnResetOnAction(null);
        }


    }

    private SupplierDto supplierDto = new SupplierDto();

    @FXML
    void btnFindOnAction(ActionEvent event) {
        String supID = txtSupID.getText();

        if(supID.isEmpty()){
            errorSender("ID not Found" , null , "Supplier ID Not Found");
            btnResetOnAction(null);
            return;
        }
        supplierDto.setSupID(supID);

        try {
            SupplierDto findDto = supplierBO.searchById(supID);

            if(findDto == null){
                errorSender("ID not Found" , null , "Supplier ID Not Found");
                btnResetOnAction(null);
            }else{
                txtName.setText(findDto.getName());
                txtName.setEditable(false);
                txtContact.setText(findDto.getContact());
                txtContact.setEditable(false);
                txtAddress.setText(findDto.getAddress());
                txtAddress.setEditable(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorSender("ERROR" , null , "Internal Database Error");
        }

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtSupID.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
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

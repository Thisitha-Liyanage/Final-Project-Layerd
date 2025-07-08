package lk.ijse.the_thirsty_manager.Controller.ManageSupplierController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.Dto.EmployeeDto;
import lk.ijse.the_thirsty_manager.Dto.SupplierDto;
import lk.ijse.the_thirsty_manager.Model.SupplierManageModel.AddSupplierModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddSupplierController implements Initializable {

    @FXML
    private AnchorPane ancAddSupplierPage;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblSupplierID;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancAddSupplierPage.getChildren().clear();
        ancAddSupplierPage.setVisible(false);
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
    }
    private AddSupplierModel addSupplierModel = new AddSupplierModel();

    private SupplierDto supplierDto = new SupplierDto();
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String supID = lblSupplierID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        if(name.isEmpty() || contact.isEmpty() || address.isEmpty()){
            errorSender("Missing Fields " , null , "Fill Missing Fields");
            btnResetOnAction(null);
            return;
        }
        if(contact.length() != 10){
           errorSender("Invalid Contact Number" , null , "Enter Valid Contact Number");
           btnResetOnAction(null);
           return;
        }
        supplierDto.setSupID(supID);
        supplierDto.setContact(contact);
        supplierDto.setAddress(address);
        supplierDto.setName(name);

        try{
          boolean isSaved = addSupplierModel.saveSupplier(supplierDto);

          if(isSaved){
              infoSender("Saved" , null , "Supplier Saved Success");
              lblSupplierID.setText(addSupplierModel.getNextId());
              btnResetOnAction(null);
          }else{
              errorSender("Not Saved", null, "Supplier Not Saved");
              btnResetOnAction(null);
          }


        } catch (SQLException e) {
            errorSender("ERROR" , null , "Internal Database Error");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadID();
    }
    public void loadID(){
        try {
            lblSupplierID.setText(addSupplierModel.getNextId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

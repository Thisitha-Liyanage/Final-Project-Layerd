package lk.ijse.the_thirsty_manager.Controller.ManageSupplierController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.Dto.SupplierDto;
import lk.ijse.the_thirsty_manager.Dto.TM.SupplierTM;
import lk.ijse.the_thirsty_manager.Model.SupplierManageModel.SearchSupplierModel;

import java.sql.SQLException;

public class SearchSupplierCOntroller {

    @FXML
    private AnchorPane ancSearchSupplier;

    @FXML
    private Button btnClose;

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
        ancSearchSupplier.getChildren().clear();
        ancSearchSupplier.setVisible(false);
    }
    private SupplierDto supplierDto = new SupplierDto();
    public void searchSup(String supID){
        if(supID == null){
            errorSender("ID not Found" , null , "Supplier ID Not Found");
            btnCloseOnAction(null);
            return;
        }

        supplierDto.setSupID(supID);

        SearchSupplierModel searchSupplierModel = new SearchSupplierModel();

        try{
            SupplierDto findDto = searchSupplierModel.findSupplier(supplierDto);

            if(findDto == null){
                errorSender("ID not Found" , null , "Supplier ID Not Found");
                btnCloseOnAction(null);
            }else{
                txtSupID.setText(findDto.getSupID());
                txtName.setText(findDto.getName());
                txtAddress.setText(findDto.getAddress());
                txtContact.setText(findDto.getContact());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void errorSender(String titleTxt , String headerTxt , String contentTxt){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleTxt);
        alert.setHeaderText(headerTxt);
        alert.setContentText(contentTxt);
        alert.show();
    }
}

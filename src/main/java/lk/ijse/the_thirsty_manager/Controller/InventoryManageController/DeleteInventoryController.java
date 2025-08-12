package lk.ijse.the_thirsty_manager.Controller.InventoryManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.InventoryBO;
import lk.ijse.the_thirsty_manager.Dto.InventoryDto;

import java.sql.SQLException;

public class DeleteInventoryController {

    @FXML
    private SplitMenuButton SplitMenuUnit;

    @FXML
    private AnchorPane ancDeleteStock;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnFInd;

    @FXML
    private Button btnReset;

    @FXML
    private TextField txtCurrentStock;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtInventoryID;

    @FXML
    private TextField txtInventoryName;

    @FXML
    private TextField txtReorderLevel;

    @FXML
    private TextField txtSupplierID;


    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancDeleteStock.getChildren().clear();
        ancDeleteStock.setVisible(false);
    }
    private final InventoryBO inventoryBO = BOFactory.getInstance().getBO(BOTypes.INVENTORY);
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String invID = txtInventoryID.getText();
        boolean isDeleted;

        try {
            if(invID.isEmpty()){
                errorSender("Missing Field " , null , "Enter Stock ID");
            }else{
                isDeleted = inventoryBO.delete(invID);

                if(isDeleted){
                    infoSender("Deleted" , null , "Stock Delete Success");
                    btnResetOnAction(null);
                }else{
                    errorSender("Not Deleted" , null , "Stock Not Deleted");
                    btnResetOnAction(null);
                }

            }
        } catch (SQLException e) {
            errorSender("ERROR" , null , "Internal Database Error");
            btnResetOnAction(null);
        }
    }


    @FXML
    void btnFindOnAction(ActionEvent event) {
        String invID = txtInventoryID.getText();
        try {
            if(invID.isEmpty()){
                errorSender("Missing Field " , null , "Enter Stock ID");
            }else{
                InventoryDto deleteDto = inventoryBO.searchById(invID);

                if(deleteDto == null){
                    errorSender("ID not Found" , null , "Stock ID not Found");
                }else{
                    txtInventoryName.setText(deleteDto.getInvname());
                    txtInventoryName.setEditable(false);
                    SplitMenuUnit.setText(deleteDto.getUnit());
                    txtCurrentStock.setText(String.valueOf(deleteDto.getCurrentStock()));
                    txtCurrentStock.setEditable(false);
                    txtSupplierID.setText(deleteDto.getSupplierID());
                    txtSupplierID.setEditable(false);
                    txtReorderLevel.setText(String.valueOf(deleteDto.getReOrderLevel()));
                    txtReorderLevel.setEditable(false);
                }
            }
        } catch (SQLException e) {
            errorSender("ERROR" , null , "Internal Database Error");
            btnResetOnAction(null);
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtInventoryID.clear();
        txtInventoryName.clear();
        txtCurrentStock.clear();
        txtDate.clear();
        txtReorderLevel.clear();
        SplitMenuUnit.setText("Choose Unit Type");
        txtSupplierID.clear();
    }

    public void errorSender(String titleTxt, String headerTxt, String contentTxt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleTxt);
        alert.setHeaderText(headerTxt);
        alert.setContentText(contentTxt);
        alert.show();
    }

    public void infoSender(String titleTxt, String headerTxt, String contentTxt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleTxt);
        alert.setHeaderText(headerTxt);
        alert.setContentText(contentTxt);
        alert.show();
    }

}

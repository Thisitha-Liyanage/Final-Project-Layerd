package lk.ijse.the_thirsty_manager.Controller.InventoryManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.InventoryBO;
import lk.ijse.the_thirsty_manager.Dto.InventoryDto;

import java.sql.SQLException;

public class UpdateInventoryController {

    @FXML
    private SplitMenuButton SplitMenuUnit;

    @FXML
    private AnchorPane ancUpdateInventory;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnFInd;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtInventoryID;

    @FXML
    private TextField txtCurrentStock;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtInventoryName;

    @FXML
    private TextField txtReorderLevel;

    @FXML
    private TextField txtSupplierID;

    @FXML
    void SplitMenuKiloGramsOnAction(ActionEvent event) {
        SplitMenuUnit.setText("Kilo Grams");
    }

    @FXML
    void SplitMenuLitersOnAction(ActionEvent event) {
        SplitMenuUnit.setText("Liters");
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancUpdateInventory.getChildren().clear();
        ancUpdateInventory.setVisible(false);
    }

    private final InventoryBO inventoryBO = BOFactory.getInstance().getBO(BOTypes.INVENTORY);
    @FXML
    void btnFindOnAction(ActionEvent event) {
        InventoryDto findInv;
        String invID = txtInventoryID.getText();
        try {
            findInv = inventoryBO.searchById(invID);
            if(findInv == null){
                errorSender("ID not Found" , null , "Inventory ID Not Found");
                btnResetOnAction(null);
            }else{
                txtInventoryName.setText(findInv.getInvname());

                if(findInv.getUnit().equals("Liters")){
                    SplitMenuUnit.setText("Liters");
                }else{
                    SplitMenuUnit.setText("Kilo Grams");
                }

                txtCurrentStock.setText(String.valueOf(findInv.getCurrentStock()));
                txtSupplierID.setText(findInv.getSupplierID());
                txtReorderLevel.setText(String.valueOf(findInv.getReOrderLevel()));
                txtDate.setText(findInv.getExpDate());
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

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        InventoryDto updateDto = new InventoryDto();

        String invID = txtInventoryID.getText();
        String invName = txtInventoryName.getText();
        String invUnit = SplitMenuUnit.getText();
        String stock = txtCurrentStock.getText();
        String supID = txtSupplierID.getText();
        String reOrderLV = txtReorderLevel.getText();
        String expDate = txtDate.getText();
        boolean isUpdate = false;


        try {
            if(invName.isEmpty() || invUnit.isEmpty() || stock.isEmpty() || supID.isEmpty() || reOrderLV.isEmpty() || expDate.isEmpty()){
                errorSender("Missing Fields" , null , "Enter Values to Missing Fields");
                btnResetOnAction(null);
            }else {
                double stockD;
                double reOrderLVD ;
                try{
                    stockD = Double.parseDouble(stock);
                    reOrderLVD = Double.parseDouble(reOrderLV);

                    if(inventoryBO.findSup(supID)){
                        updateDto.setInventoryID(invID);
                        updateDto.setInvname(invName);
                        updateDto.setUnit(invUnit);
                        updateDto.setExpDate(expDate);
                        updateDto.setSupplierID(supID);
                        updateDto.setCurrentStock(stockD);
                        updateDto.setReOrderLevel(reOrderLVD);
                    }else{
                        errorSender("Wrong ID" , null , "Supplier ID not Found");
                    }

                } catch (NumberFormatException e) {
                    errorSender("Wrong Number Format" , null , "Stock values must be numbers");
                    btnResetOnAction(null);
                }

            }

                isUpdate = inventoryBO.update(updateDto);

                if(isUpdate){
                    infoSender("Updated" , null , "Stock Update Success");
                    btnResetOnAction(null);
                }else{
                    errorSender("Not Updated" , null , "Stock Not Updated");
                    btnResetOnAction(null);
                }



        } catch (SQLException e) {
            errorSender("ERROR" , null , "Internal Database Error");
        }
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

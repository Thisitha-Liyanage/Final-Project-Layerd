package lk.ijse.the_thirsty_manager.Controller.InventoryManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.InventoryBO;
import lk.ijse.the_thirsty_manager.Dto.InventoryDto;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddInventoryController implements Initializable {

    @FXML
    private AnchorPane ancIAddInventory;
    @FXML
    private SplitMenuButton SplitMenuUnit;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblInventoryID;

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

    private InventoryDto inventoryDto = new InventoryDto();
    @FXML
    void SplitMenuKiloGramsOnAction(ActionEvent event) {
        SplitMenuUnit.setText("Kilo Grams");
        inventoryDto.setUnit("Kilo Grams");
    }

    @FXML
    void SplitMenuLitersOnAction(ActionEvent event) {
        SplitMenuUnit.setText("Liters");
        inventoryDto.setUnit("Liters");
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancIAddInventory.getChildren().clear();
        ancIAddInventory.setVisible(false);
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtSupplierID.clear();
        txtDate.clear();
        txtReorderLevel.clear();
        txtInventoryName.clear();
        txtCurrentStock.clear();
        SplitMenuUnit.setText("Choose Unit Type");
    }
    private final InventoryBO inventoryBO = BOFactory.getInstance().getBO(BOTypes.INVENTORY);
    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        inventoryDto.setInventoryID(lblInventoryID.getText());
        String inventoryID  = inventoryBO.nextID();
        String invName = txtInventoryName.getText();
        String invUnit = inventoryDto.getUnit();
        String supID = txtSupplierID.getText();
        String expdate = txtDate.getText();
        String reOrderLVS = txtReorderLevel.getText();
        String stockS = txtCurrentStock.getText();


        if(invName.isEmpty() || invUnit.isEmpty() || stockS.isEmpty()|| supID.isEmpty() || expdate.isEmpty() ||  reOrderLVS.isEmpty() ){
            errorSender("Missing Details" , null  , "Empty Fields , Please Fill ");
            btnResetOnAction(null);
            return;
        }else {
            double reOrderLv = 0 , current_stock = 0 ;
            try {
                reOrderLv = Double.parseDouble(txtReorderLevel.getText());
                current_stock = Double.parseDouble(txtCurrentStock.getText());

                if(reOrderLv > current_stock){
                    errorSender("Stock Miss Match" , null , "Stock is less than Reorder level");
                    return;
                }
            } catch (NumberFormatException e) {
                errorSender("Wrong Number Format" , null , "Stock values must be numbers");
                btnResetOnAction(null);
                return;
            }
            if (inventoryBO.findSup(supID)) {
                inventoryDto.setInventoryID(inventoryID);
                inventoryDto.setInvname(invName);
                inventoryDto.setCurrentStock(current_stock);
                inventoryDto.setSupplierID(supID);
                inventoryDto.setExpDate(expdate);
                inventoryDto.setReOrderLevel(reOrderLv);
            }else{
                errorSender("ID not Found" , null , "Supplier ID Not Found");
                btnResetOnAction(null);
                return;
            }
        }
        if(inventoryBO.save(inventoryDto)){
            infoSender("Success" , null , "Stock Saved Success");
            btnResetOnAction(null);
            lblInventoryID.setText(inventoryBO.nextID());
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

    private void loadInvID() {
        try {
            lblInventoryID.setText(inventoryBO.nextID());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadInvID();
    }
}

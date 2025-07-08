package lk.ijse.the_thirsty_manager.Controller.ItemManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;
import lk.ijse.the_thirsty_manager.Model.ItemManageModel.UpdateItemModel;

import java.sql.SQLException;

public class UpdateItemController {


    @FXML
    private AnchorPane ancUpdateItem;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnFind;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private CheckBox checkBoxAvailability;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemID;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtPrice;

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancUpdateItem.getChildren().clear();
        ancUpdateItem.setVisible(false);
    }

    private UpdateItemModel updateItemModel = new UpdateItemModel();
    @FXML
    void btnFind(ActionEvent event) throws SQLException {
        ItemDto findItemDto = updateItemModel.findItem(txtItemID.getText());

        if(findItemDto == null){
            errorSender("ID not Found" , null , "Item ID not Found");
            btnResetOnAction(null);
        }else{

            txtItemName.setText(findItemDto.getItemName());
            txtDescription.setText(findItemDto.getDescription());
            txtPrice.setText(String.valueOf(findItemDto.getPrice()));

            if ("Available".equalsIgnoreCase(findItemDto.getAvailability())) {
                checkBoxAvailability.setSelected(true);
            } else {
                checkBoxAvailability.setSelected(false);
            }
        }


    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtItemName.clear();
        txtItemID.clear();
        txtDescription.clear();
        txtPrice.clear();
        checkBoxAvailability.setSelected(false);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String itemID = txtItemID.getText();
        String itemName = txtItemName.getText();
        String itemDes = txtDescription.getText();
        String price = txtPrice.getText();
        boolean isavailable = checkBoxAvailability.isSelected();
        String available = null;

        if(itemID.isEmpty() || itemName.isEmpty() || itemDes.isEmpty() || price.isEmpty()){
            errorSender("Missing Fields" , null , "Enter Details to Missing Fields");
            btnResetOnAction(null);
            return;
        }
        if(isavailable){
            available = "Available";
        }else{
            available = "Not Available";
        }

        double priceD = 0.00;
        try{
            priceD = Double.parseDouble(price);
        }catch (NumberFormatException e){
            errorSender("Wrong Price Format" , null , "Price Must Be Numbers");
            btnResetOnAction(null);
        }

        boolean isUpdated = false;
        try {
            isUpdated = updateItemModel.updateItem(itemName , itemDes , available , priceD , itemID);
        } catch (SQLException e) {
            errorSender("ERROR" , null , "Internal Database Error");
        }

        if(isUpdated){
            infoSender("Updated" , null , "Item Updated Success");
            btnResetOnAction(null);
        }else{
            errorSender("Not Updated" , null , "Item Not Updated");
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

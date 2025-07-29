package lk.ijse.the_thirsty_manager.Controller.ItemManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.ItemBO;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;
import lk.ijse.the_thirsty_manager.Model.ItemManageModel.DeleteItemModel;

import java.sql.SQLException;

public class DeleteItemController {

    @FXML
    private AnchorPane ancItemDelete;

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
        ancItemDelete.setVisible(false);
        ancItemDelete.getChildren().clear();
    }
    private final ItemBO itemBO = BOFactory.getInstance().getBO(BOTypes.ITEM);
    private DeleteItemModel deleteItemModel = new DeleteItemModel();
    @FXML
    void btnFind(ActionEvent event) {
        try {
            ItemDto findItemDto = itemBO.search(txtItemID.getText());
            if(findItemDto == null){
                errorSender("ID Not Found" ,null , "Item ID not Found");
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
        } catch (SQLException e) {
            errorSender("ERROR" , null , "Internal Database Error");
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtItemID.clear();
        txtItemName.clear();
        txtDescription.clear();
        txtPrice.clear();
        checkBoxAvailability.setSelected(false);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            boolean isDeleted = itemBO.delete(txtItemID.getText());
            if(isDeleted){
                infoSender("Deleted" , null , "Item Delete Success" );
                btnResetOnAction(null);
            }else{
                errorSender("Not Deleted" , null , "Item Not Deleted");
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

}

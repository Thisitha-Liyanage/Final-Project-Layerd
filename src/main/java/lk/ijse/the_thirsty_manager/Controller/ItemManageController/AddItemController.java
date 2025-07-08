package lk.ijse.the_thirsty_manager.Controller.ItemManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;
import lk.ijse.the_thirsty_manager.Model.CustomerManageModel.AddCustomerModel;
import lk.ijse.the_thirsty_manager.Model.ItemManageModel.AddItemModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddItemController implements Initializable {

    @FXML
    private AnchorPane ancAddItem;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private CheckBox ckeckBoxAvailability;

    @FXML
    private Label lblItemID;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtPrice;

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancAddItem.getChildren().clear();
        ancAddItem.setVisible(false);
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtItemName.clear();
        txtDescription.clear();
        txtPrice.clear();
        ckeckBoxAvailability.setSelected(false);
    }

    private ItemDto itemDto = new ItemDto();
    private AddItemModel addItemModel = new AddItemModel();

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        itemDto.setItemID(lblItemID.getText());
        lblItemID.setText(addItemModel.getNextId());
        String itemName = txtItemName.getText();
        String itemDescrip = txtDescription.getText();
        String itemPrice = txtPrice.getText();
        boolean isAvailable = ckeckBoxAvailability.isSelected();

        itemDto.setItemName(itemName);
        itemDto.setDescription(itemDescrip);

        double itemPriceD;
        try {
            itemPriceD = Double.parseDouble(itemPrice);
            itemDto.setPrice(itemPriceD);
        } catch (NumberFormatException e) {
            errorSender("Wrong Price Format", null, "Item Price must be number");
            btnResetOnAction(null);
        }

        if (!isAvailable) {
            itemDto.setAvailability("Not Available");
        } else {
            itemDto.setAvailability("Available");
        }


        try {
            boolean isSaved = addItemModel.saveItem(itemDto);
            if (!isSaved) {
                errorSender("Not Saved", null, "Item Not Saved");
                btnResetOnAction(null);
            } else {
                infoSender("Saved", null, "Item Saved");
                loadItemID();
                btnResetOnAction(null);
            }
        } catch (SQLException e) {
            errorSender("ERROR", null, "Internal Database Error");
            btnResetOnAction(null);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadItemID();
    }

    private void loadItemID() {
        try {
            lblItemID.setText(addItemModel.getNextId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

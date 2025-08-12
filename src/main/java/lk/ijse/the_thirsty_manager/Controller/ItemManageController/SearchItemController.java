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

import java.sql.SQLException;

public class SearchItemController {

    @FXML
    private AnchorPane ancSearchItem;

    @FXML
    private Button btnClose;

    @FXML
    private CheckBox checkBoxAvailability;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancSearchItem.getChildren().clear();
        ancSearchItem.setVisible(false);
    }

    private final ItemBO itemBO = BOFactory.getInstance().getBO(BOTypes.ITEM);

    public void searchItem (String itemID){
        try {
            ItemDto searchItemDto = itemBO.search(itemID);

            if (searchItemDto != null){
                if(itemID.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Missing Fields");
                    alert.setContentText("Please fill Order ID");
                    alert.show();
                    ancSearchItem.getChildren().clear();
                    ancSearchItem.setVisible(false);
                    return ;
                }


                txtItemID.setText(searchItemDto.getItemID());
                txtItemID.setEditable(false);
                txtName.setText(searchItemDto.getItemName());
                txtName.setEditable(false);

                String availability = searchItemDto.getAvailability();

                if(availability.equals("Available")){
                    checkBoxAvailability.setSelected(true);
                }else{
                    checkBoxAvailability.setSelected(false);
                }

                txtPrice.setText(String.valueOf(searchItemDto.getPrice()));
                txtPrice.setEditable(false);
                txtDescription.setText(searchItemDto.getDescription());
                txtDescription.setEditable(false);
                }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ID not found");
                alert.setContentText("Customer ID not found");
                alert.show();

                ancSearchItem.setVisible(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

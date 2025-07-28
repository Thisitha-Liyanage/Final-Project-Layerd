package lk.ijse.the_thirsty_manager.Controller.ManageIngerdientController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.IngredientBO;
import lk.ijse.the_thirsty_manager.Dto.IngredientDto;

import java.sql.SQLException;

public class SearchIngredientController {

    @FXML
    private AnchorPane ancSearchIngPage;

    @FXML
    private Button btnClose;

    @FXML
    private Label lblIngredientID;

    @FXML
    private TextField txtInventoryID;

    @FXML
    private TextField txtItemID;

    @FXML
    private TextField txtStockUse;

    private final IngredientBO ingredientBO = BOFactory.getInstance().getBO(BOTypes.INGREDIENTS);
    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancSearchIngPage.getChildren().clear();
        ancSearchIngPage.setVisible(false);
    }

    public void searchIng(String searchID){
        if(searchID.isEmpty()){
            btnCloseOnAction(null);
            new Alert(Alert.AlertType.ERROR , "Search ID is Empty").show();
            return;
        }

        try {
            IngredientDto ingredientDto = ingredientBO.search(searchID);

            if(ingredientDto == null){
                btnCloseOnAction(null);
                new Alert(Alert.AlertType.ERROR , "Ingredient ID Not Found").show();
            }else{
                txtInventoryID.setText(ingredientDto.getInventoryID());
                txtItemID.setText(ingredientDto.getItemID());
                lblIngredientID.setText(ingredientDto.getIngredientID());
                txtStockUse.setText(String.valueOf(ingredientDto.getQuantity()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

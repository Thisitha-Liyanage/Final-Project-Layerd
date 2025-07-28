package lk.ijse.the_thirsty_manager.Controller.ManageIngerdientController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.IngredientBO;
import lk.ijse.the_thirsty_manager.BO.Exceptions.IDNotFoundException;
import lk.ijse.the_thirsty_manager.Dto.IngredientDto;
import lk.ijse.the_thirsty_manager.Model.IngerdientModel;
import lk.ijse.the_thirsty_manager.Model.IngredientManageModel.AddIngredient;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddIngredientController implements Initializable {
    @FXML
    private AnchorPane ancAddIngredient;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblIngredientID;

    @FXML
    private TextField txtInventoryID;

    @FXML
    private TextField txtItemID;

    @FXML
    private TextField txtStockUse;

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancAddIngredient.getChildren().clear();
        ancAddIngredient.setVisible(false);
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtInventoryID.clear();
        txtItemID.clear();
        txtStockUse.clear();
    }

    private final AddIngredient addIngredientModel = new AddIngredient();
    private final IngredientBO ingredientBO = BOFactory.getInstance().getBO(BOTypes.INGREDIENTS);
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String igreID = lblIngredientID.getText();
        String itemID = txtItemID.getText();
        String invenID = txtInventoryID.getText();
        String stockUse = txtStockUse.getText();

        if(itemID.isEmpty() || invenID.isEmpty() || stockUse.isEmpty()){
            errorSender("Missing Fields" , null , "Fill All Fields");
            btnResetOnAction(null);
            return;
        }

        double stockUseD =0;

        try{
            stockUseD = Double.parseDouble(stockUse);
        } catch (NumberFormatException e) {
            errorSender("ERROR" , null , "Stock Use Must Be Numbers");
        }

        try {
            ingredientBO.findItem(itemID);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IDNotFoundException e){
            errorSender("ID Not Found" , null , "ItemID Not Found");
            btnResetOnAction(null);
            return;
        }

        try {
            ingredientBO.findInvenID(invenID);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IDNotFoundException e){
            errorSender("ID Not Found" , null , "Inventory ID Not Found");
            btnResetOnAction(null);
            return;
        }


        IngredientDto ingredientDto = new IngredientDto(
                igreID,
                itemID,
                invenID,
                stockUseD
        );



        boolean isSaved =false;
        try {
            isSaved = ingredientBO.save(ingredientDto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(isSaved){
            infoSender("Ingredient Saved" , null , "Ingredient Saved Success");
            btnResetOnAction(null);
            nextID();
        }else{
            errorSender("Not Saved" , null , "Ingredient Not Saved");
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
        nextID();
    }

    public void nextID(){
        try {
            lblIngredientID.setText(addIngredientModel.getNextId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

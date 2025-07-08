package lk.ijse.the_thirsty_manager.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.Dto.TM.IngredientTM;

public class IngerdientPageController {

    @FXML
    private AnchorPane ancIngredientManagePageLoader;

    @FXML
    private AnchorPane ancIngredientPage;

    @FXML
    private Button btnAddIngredients;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<?, ?> clnIngredientID;

    @FXML
    private TableColumn<?, ?> clnInventoryID;

    @FXML
    private TableColumn<?, ?> clnItemID;

    @FXML
    private TableColumn<?, ?> clnStockUse;

    @FXML
    private TableView<IngredientTM> tableViewIngredientt;

    @FXML
    private TextField txtSearchCustomer;

    @FXML
    void btnAddIngredientsOnAction(ActionEvent event) {
        managePageLoader("/View/ManageIngredient/AddIngredient.fxml");
    }

    @FXML
    void btnManageIngredientOnAction(ActionEvent event) {
        navigateto("/View/IngredientPage.fxml");
    }

    @FXML
    void btnManageItemOnAction(ActionEvent event) {
        navigateto("/View/ItemPage.fxml");
    }

    @FXML
    void btnManagePaymentOnAction(ActionEvent event) {
        navigateto("/View/PaymentPage.fxml");
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        navigateto("/View/OrderPage.fxml");
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        tableViewIngredientt.refresh();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        managePageLoader("/View/ManageIngredient/SearchIngredient.fxml");
    }

    @FXML
    void btnTableOnActon(ActionEvent event) {
        navigateto("/View/TablePage.fxml");
    }


    public void navigateto(String path) {
        try {
            ancIngredientPage.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancIngredientPage.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void managePageLoader(String path) {
        try {
            ancIngredientManagePageLoader.getChildren().clear();
            ancIngredientManagePageLoader.setVisible(true);
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancIngredientManagePageLoader.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

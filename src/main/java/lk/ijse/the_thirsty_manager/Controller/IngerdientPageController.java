package lk.ijse.the_thirsty_manager.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.IngredientBO;
import lk.ijse.the_thirsty_manager.Controller.AttendanceManage.SearchAttendanceController;
import lk.ijse.the_thirsty_manager.Controller.ManageIngerdientController.SearchIngredientController;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.IngredientDto;
import lk.ijse.the_thirsty_manager.Dto.TM.CustomerTM;
import lk.ijse.the_thirsty_manager.Dto.TM.IngredientTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class IngerdientPageController implements Initializable {

    @FXML
    private AnchorPane ancIngredientManagePageLoader;

    @FXML
    private AnchorPane ancIngredientPage;

    @FXML
    private Button btnAddIngredients;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<IngredientTM, String > clnIngredientID;

    @FXML
    private TableColumn<IngredientTM, String> clnInventoryID;

    @FXML
    private TableColumn<IngredientTM, String> clnItemID;

    @FXML
    private TableColumn<IngredientTM, Double> clnStockUse;

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
        try {
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableViewIngredientt.refresh();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String searchID = txtSearchCustomer.getText();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ManageIngredient/SearchIngredient.fxml"));
            Parent parent = loader.load();
            SearchIngredientController controller = loader.getController();

            ancIngredientManagePageLoader.getChildren().clear();
            ancIngredientManagePageLoader.setVisible(true);
            ancIngredientManagePageLoader.getChildren().add(parent);

            controller.searchIng(searchID);

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private final IngredientBO ingredientBO = BOFactory.getInstance().getBO(BOTypes.INGREDIENTS);
    public void loadTable() throws SQLException {

        List<IngredientDto> ingDtoListList = ingredientBO.getAll();

        ObservableList<IngredientTM> list = FXCollections.observableArrayList();
        for (IngredientDto ingredientDto : ingDtoListList) {
            IngredientTM ingredientTM = new IngredientTM(
                    ingredientDto.getInventoryID(),
                    ingredientDto.getItemID(),
                    ingredientDto.getInventoryID(),
                    ingredientDto.getQuantity()

            );
            list.add(ingredientTM);
        }
        tableViewIngredientt.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clnIngredientID     .setCellValueFactory(new PropertyValueFactory<>("ingredientID"));
        clnItemID   .setCellValueFactory(new PropertyValueFactory<>("itemID"));
        clnInventoryID.setCellValueFactory(new PropertyValueFactory<>("inventoryID"));
        clnStockUse.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        try{
            loadTable();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

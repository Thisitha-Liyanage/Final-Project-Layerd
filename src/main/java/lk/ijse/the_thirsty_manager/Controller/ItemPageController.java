package lk.ijse.the_thirsty_manager.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.ItemBO;
import lk.ijse.the_thirsty_manager.Controller.CustomerManageController.SearchCustomerController;
import lk.ijse.the_thirsty_manager.Controller.ItemManageController.SearchItemController;
import lk.ijse.the_thirsty_manager.Dto.InventoryDto;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;
import lk.ijse.the_thirsty_manager.Dto.TM.InventoryTM;
import lk.ijse.the_thirsty_manager.Dto.TM.ItemTM;
import lk.ijse.the_thirsty_manager.Model.ItemPageModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemPageController implements Initializable {

    public TextField txtSearchItem;
    @FXML
    private AnchorPane ancItemPage;

    @FXML
    private AnchorPane ancitemManagePageOrder;

    @FXML
    private Button btnAddItem;

    @FXML
    private Button btnDeleteItem;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdateItem;

    @FXML
    private TableColumn<ItemTM, String> clnItemAvailability;

    @FXML
    private TableColumn<ItemTM, String> clnItemDescription;

    @FXML
    private TableColumn<ItemTM, String > clnItemID;

    @FXML
    private TableColumn<ItemTM, String> clnItemName;

    @FXML
    private TableColumn<ItemTM, Double> clnItemPrice;

    @FXML
    private TableView<ItemTM> tableViewItem;


    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        manageLoader("/View/ItemManage/AddItem.fxml");
    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) {
        manageLoader("/View/ItemManage/DeleteItem.fxml");
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        loadTable();
        tableViewItem.refresh();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String searchItemID = txtSearchItem.getText();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ItemManage/SearchItem.fxml"));
            Parent parent = loader.load();
            SearchItemController controller = loader.getController();

            ancitemManagePageOrder.getChildren().clear();
            ancitemManagePageOrder.setVisible(true);
            ancitemManagePageOrder.getChildren().add(parent);

            controller.searchItem(searchItemID);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {
        manageLoader("/View/ItemManage/UpdateItem.fxml");
    }


    public void manageLoader(String path) {
        try {
            ancitemManagePageOrder.setVisible(true);
            ancitemManagePageOrder.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancitemManagePageOrder.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigatTo(String path){
        try{
            ancItemPage.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancItemPage.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnPlaceOrderOnAction(ActionEvent event) {
        navigatTo("/View/OrderPage.fxml");
    }


    public void btnTableOnActon(ActionEvent event) {
        navigatTo("/View/TablePage.fxml");
    }

    public void btnManageItemOnAction(ActionEvent event) {
        navigatTo("/View/ItemPage.fxml");
    }

    public void btnManagePaymentOnAction(ActionEvent event) {
        navigatTo("/View/PaymentPage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clnItemID     .setCellValueFactory(new PropertyValueFactory<>("itemID"));
        clnItemName   .setCellValueFactory(new PropertyValueFactory<>("itemName"));
        clnItemAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        clnItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        clnItemPrice    .setCellValueFactory(new PropertyValueFactory<>("price"));


        try {
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private final ItemBO itemBO = BOFactory.getInstance().getBO(BOTypes.ITEM);


    public void loadTable() {
        try {
            List<ItemDto> itemDtoList = itemBO.getAll();
            ObservableList<ItemTM> observableList = FXCollections.observableArrayList();

            for (ItemDto dto : itemDtoList) {
                observableList.add(new ItemTM(
                        dto.getItemID(),
                        dto.getItemName(),
                        dto.getAvailability(),
                        dto.getPrice(),
                        dto.getDescription()
                ));
            }

            tableViewItem.setItems(observableList);

        } catch (SQLException e) {
            errorSender("Database Error", "Failed to load inventory", e.getMessage());
            e.printStackTrace();
        }
    }
    public void errorSender(String titleTxt, String headerTxt, String contentTxt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleTxt);
        alert.setHeaderText(headerTxt);
        alert.setContentText(contentTxt);
        alert.show();
    }

    public void btnManageIngredientOnAction(ActionEvent event) {
        navigatTo("/View/IngredientPage.fxml");
    }
}

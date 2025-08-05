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
import lk.ijse.the_thirsty_manager.BO.Custom.InventoryBO;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.InventoryDto;
import lk.ijse.the_thirsty_manager.Dto.TM.CustomerTM;
import lk.ijse.the_thirsty_manager.Dto.TM.InventoryTM;
import lk.ijse.the_thirsty_manager.Model.InventoryPageModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InventoryPageController implements Initializable {

    @FXML
    private TableColumn<InventoryTM, String> Date;

    @FXML
    private AnchorPane ancInventoryManagePageLoader;

    @FXML
    private AnchorPane ancInventoryPage;

    @FXML
    private Button btnAddInventory;

    @FXML
    private Button btnDeleteInventory;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdateInventory;

    @FXML
    private TableColumn<InventoryTM, Double> clnCurrentStock;

    @FXML
    private TableColumn<InventoryTM, String > clnInventoryID;

    @FXML
    private TableColumn<InventoryTM, String > clnName;

    @FXML
    private TableColumn<InventoryTM, Double> clnReorderLevel;

    @FXML
    private TableColumn<InventoryTM, String > clnSupplierID;

    @FXML
    private TableColumn<InventoryTM, String> clnUnit;


    @FXML
    private TableView<InventoryTM> tableViewInventory;

    @FXML
    private TextField txtSearchCustomer;

    @FXML
    void btnAddInventoryOnAction(ActionEvent event) {
        manageLoader("/View/InventoryManage/AddInventory.fxml");
    }

    @FXML
    void btnDeleteInventoryOnAction(ActionEvent event) {
        manageLoader("/View/InventoryManage/DeleteInventory.fxml");
    }

    @FXML
    void btnInventoryOnAction(ActionEvent event) {
        navigateTo("/View/InventoryPage.fxml");
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        loadTable();
        tableViewInventory.refresh();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        manageLoader("/View/InventoryManage/SearchInventory.fxml");
    }

    @FXML
    void btnSuppierOnAction(ActionEvent event) {
        navigateTo("/View/SupplierPage.fxml");
    }

    @FXML
    void btnUpdateInventoryOnAction(ActionEvent event) {
        manageLoader("/View/InventoryManage/UpdateInventory.fxml");
    }

    public void manageLoader(String path){
        try{
            ancInventoryManagePageLoader.getChildren().clear();
            ancInventoryManagePageLoader.setVisible(true);
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancInventoryManagePageLoader.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigateTo(String path){
        try{
            ancInventoryPage.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancInventoryPage.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final InventoryBO inventoryBO = BOFactory.getInstance().getBO(BOTypes.INVENTORY);

    public void loadTable() {
        try {
            List<InventoryDto> inventoryDtoList = inventoryBO.getAll();
            ObservableList<InventoryTM> observableList = FXCollections.observableArrayList();

            for (InventoryDto dto : inventoryDtoList) {
                observableList.add(new InventoryTM(
                        dto.getInventoryID(),
                        dto.getInvname(),
                        dto.getUnit(),
                        dto.getCurrentStock(),
                        dto.getSupplierID(),
                        dto.getReOrderLevel(),
                        dto.getExpDate()
                ));
            }

            tableViewInventory.setItems(observableList);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clnInventoryID     .setCellValueFactory(new PropertyValueFactory<>("inventoryID"));
        clnName   .setCellValueFactory(new PropertyValueFactory<>("Invname"));
        clnUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        clnCurrentStock.setCellValueFactory(new PropertyValueFactory<>("currentStock"));
        clnSupplierID    .setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        clnReorderLevel.setCellValueFactory(new PropertyValueFactory<>("reOrderLevel"));
        Date.setCellValueFactory(new PropertyValueFactory<>("expDate"));


        try {
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnIngredientsOnAction(ActionEvent event) {

    }
}

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

public class TablePageController {

    public AnchorPane ancTablePage;
    public AnchorPane ancManagePageOrder;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<?, ?> clnstatus;

    @FXML
    private TableColumn<?, ?> clntableIID;

    @FXML
    private TableColumn<?, ?> noFonChairs;

    @FXML
    private TableView<?> tableViewTables;

    @FXML
    private TextField txtSearchTable;

    @FXML
    void btnAddTableOnAction(ActionEvent event) {
        tableManageLoader("/View/TableManage/AddTable.fxml");
    }

    @FXML
    void btnDeleteTableOnAction(ActionEvent event) {
        tableManageLoader("/View/TableManage/DeleteTable.fxml");
    }

    @FXML
    void btnManageItemOnAction(ActionEvent event) {
        navigateTo("/View/ItemPage.fxml");
    }

    @FXML
    void btnManagePaymentOnAction(ActionEvent event) {
        navigateTo("/View/PaymentPage.fxml");
    }


    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        navigateTo("/View/OrderPage.fxml");
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        tableViewTables.refresh();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnTableOnActon(ActionEvent event) {
        navigateTo("/View/TablePage.fxml");
    }

    @FXML
    void btnUpdateTableOnAction(ActionEvent event) {
        tableManageLoader("/View/TableManage/UpdateTable.fxml");
    }
    public void tableManageLoader(String path) {
        try {
            ancManagePageOrder.getChildren().clear();
            ancManagePageOrder.setVisible(true);
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancManagePageOrder.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public void navigateTo(String path) {
            try {
                ancTablePage.getChildren().clear();
                ancTablePage.setVisible(true);
                Parent parent = FXMLLoader.load(getClass().getResource(path));
                ancTablePage.getChildren().add(parent);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void btnManageIngredientOnAction(ActionEvent event) {
        navigateTo("/View/IngredientPage.fxml");
    }
}

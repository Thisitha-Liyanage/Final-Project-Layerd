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
import lk.ijse.the_thirsty_manager.BO.Custom.PaymentBO;
import lk.ijse.the_thirsty_manager.Controller.PaymentManageController.SearchPaymentController;
import lk.ijse.the_thirsty_manager.Dto.PaymentDto;
import lk.ijse.the_thirsty_manager.Dto.TM.PaymentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentPageController implements Initializable {

    public TextField txtSearchPay;
    @FXML
    private AnchorPane ancPaymentManagePageLoader;

    @FXML
    private AnchorPane ancPaymentPage;

    @FXML
    private Button btnAddPayment;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<PaymentTM , String> clnDate;

    @FXML
    private TableColumn<PaymentTM, Double> clnAmount;

    @FXML
    private TableColumn<PaymentTM, String> clnOrderID;

    @FXML
    private TableColumn<PaymentTM, String> clnPaymentID;

    @FXML
    private TableColumn<PaymentTM, String > clnPaymentMethod;

    @FXML
    private TableView<PaymentTM> tableViewePayment;

    @FXML
    private TextField txtSearEmp;

    @FXML
    void btnAddPaymentOnAction(ActionEvent event) {
        manageLoader("/View/PaymentManage/AddPayment.fxml");
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
    void btnRefreshOnAction(ActionEvent event) throws SQLException {
        tableViewePayment.refresh();
        loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String searchPay = txtSearchPay.getText();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PaymentManage/SearchPayment.fxml"));
            Parent parent = loader.load();
            SearchPaymentController controller = loader.getController();

            ancPaymentManagePageLoader.getChildren().clear();
            ancPaymentManagePageLoader.setVisible(true);
            ancPaymentManagePageLoader .getChildren().add(parent);

            controller.searchPayment(searchPay);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnTableOnActon(ActionEvent event) {
        navigateTo("/View/TablePage.fxml");
    }

    public void navigateTo(String path){
        try{
            ancPaymentPage.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancPaymentPage.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void manageLoader(String path){
        try{
            ancPaymentManagePageLoader.getChildren().clear();
            ancPaymentManagePageLoader.setVisible(true);
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancPaymentManagePageLoader.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clnPaymentID     .setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        clnPaymentMethod   .setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        clnAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        clnOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        clnDate    .setCellValueFactory(new PropertyValueFactory<>("date"));


        try {
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private final PaymentBO paymentBO = BOFactory.getInstance().getBO(BOTypes.PAYMENT);
    public void loadTable() throws SQLException {

        List<PaymentDto> paymentDTOArrayList = paymentBO.getAll();

        ObservableList<PaymentTM> list = FXCollections.observableArrayList();
        for (PaymentDto paymentDto : paymentDTOArrayList){
            PaymentTM paymentTM = new PaymentTM(
                    paymentDto.getPaymentID(),
                    paymentDto.getPaymentMethod(),
                    paymentDto.getTotalAmount(),
                    paymentDto.getOrderID(),
                    paymentDto.getDate()
            );
            System.out.println(paymentDto.getDate());
            list.add(paymentTM);
        }
        tableViewePayment.setItems(list);

    }

    public void btnManageIngredientOnAction(ActionEvent event) {
        navigateTo("/View/IngredientPage.fxml");
    }
}

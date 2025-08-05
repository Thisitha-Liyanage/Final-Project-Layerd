package lk.ijse.the_thirsty_manager.Controller;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;
import lk.ijse.the_thirsty_manager.Dto.OrderDto;
import lk.ijse.the_thirsty_manager.Dto.TM.CustomerTM;
import lk.ijse.the_thirsty_manager.Dto.TM.OrderTM;
import lk.ijse.the_thirsty_manager.Model.OrderPageModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderPageController implements Initializable {

    public TextField txtItemName;



    @FXML
    private TextField txtTableID;


    @FXML
    private TableColumn<?, ?> clnTotalPricePlace;

    @FXML
    private TableColumn<?, ?> clnOrderIDPlace;


    @FXML
    private TableColumn<?, ?> clnCustomerIDPlace;

    @FXML
    private TableColumn<?, ?> clnDatePlace;

    @FXML
    private TableView<?> tblPlaceOrder;

    @FXML
    private AnchorPane ancOrder;

    @FXML
    private TableColumn<OrderTM, String> clnItemID;

    @FXML
    private TableColumn<OrderTM, String> clnItemName;

    @FXML
    private TableColumn<OrderTM, String> clnCustomerID;

    @FXML
    private TableColumn<OrderTM, Integer> clnQuantity;

    @FXML
    private TableColumn<OrderTM, Double> clnUnitPrice;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblOrderID;

    @FXML
    private Label lblQOH;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private TableView<OrderTM> tableOrder;

    @FXML
    private TextField txtCustomerID;

    @FXML
    private TextField txtItemID;

    @FXML
    private TextField txtQuantity;

    private OrderPageModel orderPageModel = new OrderPageModel();

    @FXML
    void AddToCartOnAction(ActionEvent event) {
        String quantityS = txtQuantity.getText();

        int qty = 0;
        try{
            qty = Integer.parseInt(quantityS);
        }catch (NumberFormatException e ){
            errorSender("Wrong Quantity" , null , "Quantity Must Be Numbers");
            btnResetOnAction(null);
        }

        OrderDto OrderDTO = new OrderDto();

        orderDto.setCustomerID(txtCustomerID.getText());
        orderDto.setUnitPrice(Double.parseDouble(lblUnitPrice.getText()));
        orderDto.setQuantity(qty);
        orderDto.setItemName(txtItemName.getText());
        orderDto.setItemID(txtItemID.getText());


        try{
            ArrayList<OrderDto> placeOrderLIst = orderPageModel.addToCart(orderDto);

            if(placeOrderLIst != null){
                ObservableList<OrderTM> list = FXCollections.observableArrayList();
                for (OrderDto orderDto1 : placeOrderLIst) {
                    OrderTM OrderTM = new OrderTM(
                            orderDto1.getCustomerID(),
                            orderDto1.getUnitPrice(),
                            orderDto1.getItemName(),
                            orderDto1.getQuantity(),
                            orderDto1.getItemID()
                    );
                    list.add(OrderTM);
                }
                tableOrder.setItems(list);
            }else{
                errorSender("ERROR" , null , "Add To Cart Not Success");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        clnCustomerID     .setCellValueFactory(new PropertyValueFactory<>("customerID"));
        clnItemName   .setCellValueFactory(new PropertyValueFactory<>("itemName"));
        clnItemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        clnQuantity.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        clnUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
    }




    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        tableOrder.refresh();
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtCustomerID.clear();
        txtQuantity.clear();
        txtItemID.clear();
        lblUnitPrice.setText(null);
        txtItemName.clear();
        tableOrder.getItems().clear();
        tblPlaceOrder.getItems().clear();
    }

    @FXML
    void placeOrderOnAction(ActionEvent event) {
    }
    private OrderDto orderDto = new OrderDto();
    @FXML
    void txtItemIDOnAction(ActionEvent event) {
        String customerID = txtCustomerID.getText();
        String itemID = txtItemID.getText();

        if (customerID.isEmpty()) {
            errorSender("Missing Fields", null, "Enter Customer ID");
            btnResetOnAction(null);
            return;
        }


        if (itemID.isEmpty()) {
            errorSender("Missing Fields", null, "Enter Item ID");
            btnResetOnAction(null);
            return;
        }

        //find Item Details
        try {
            ItemDto findItemDto = orderPageModel.findItem(itemID);

            if(findItemDto == null){
                errorSender("ID Not Found" , null , "Item ID Not Found");
            }else{
                txtItemName.setText(findItemDto.getItemName());
                txtItemName.setEditable(false);
                lblUnitPrice.setText(String.valueOf(findItemDto.getPrice()));

                if(findItemDto.getAvailability().equals("Not Available")){
                    errorSender("Not Available" , null , "Item Currently Not Available");
                    btnResetOnAction(null);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Find Customer ID

        try {
            boolean isFound = orderPageModel.findCustomer(customerID);

            if(!isFound){
                errorSender("ID Not Found" , null , "Customer ID Not Found");
                btnResetOnAction(null);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void btnPlaceOrderOnAction(ActionEvent event) {
        navigatTo("/View/OrderPage.fxml");
    }

    public void btnTableOnActon(ActionEvent event) {
        navigatTo("/View/TablePage.fxml");
    }

    public void btnManagePaymentOnAction(ActionEvent event) {
        navigatTo("/View/PaymentPage.fxml");
    }


    public void navigatTo(String path){
        try{
            ancOrder.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancOrder.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void errorSender(String title , String header , String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblDate.setText(String.valueOf(LocalDate.now()));
        nextID();
        tblPlaceOrder.setVisible(false);
    }

    public void nextID(){
        try{
            lblOrderID.setText(orderPageModel.getNextId());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void btnManageItemOnAction(ActionEvent event) {
        navigatTo("/View/ItemPage.fxml");
    }

    public void btnManageIngredientOnAction(ActionEvent event) {
        navigatTo("/View/IngredientPage.fxml");
    }
}

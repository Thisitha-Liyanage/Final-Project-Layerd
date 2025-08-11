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
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.OrderBO;
import lk.ijse.the_thirsty_manager.Dto.*;
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
    private TableColumn<OrderTM, Double> clnTotalPricePlace;

    @FXML
    private TableColumn<OrderTM, String> clnOrderIDPlace;


    @FXML
    private TableColumn<OrderTM, String> clnCustomerIDPlace;

    @FXML
    private TableColumn<OrderTM , String> clnDatePlace;

    @FXML
    private TableView<OrderTM> tblPlaceOrder;

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
    private List<CustomOrderDTO> cart = new ArrayList<>();
    @FXML
    void AddToCartOnAction(ActionEvent event) {
        String quantityS = txtQuantity.getText();
        tblPlaceOrder.setVisible(false);
        tableOrder.setVisible(true);
        int qty = 0;
        try{
            qty = Integer.parseInt(quantityS);
        }catch (NumberFormatException e ){
            errorSender("Wrong Quantity" , null , "Quantity Must Be Numbers");
            btnResetOnAction(null);
        }

        CustomOrderDTO customOrderDTO = new CustomOrderDTO();
        customOrderDTO.setCustomerID(txtCustomerID.getText());
        if(txtQuantity.getText() != null) {
            customOrderDTO.setUnitPrice(Double.parseDouble(lblUnitPrice.getText()));
        }else{
            new Alert(Alert.AlertType.ERROR , "Quantity is Empty").show();
        }
        customOrderDTO.setQty(qty);
        customOrderDTO.setItemName(txtItemName.getText());
        customOrderDTO.setItemID(txtItemID.getText());
        customOrderDTO.setOrderID(lblOrderID.getText());
        customOrderDTO.setDate(lblDate.getText());
        double totalam = customOrderDTO.getUnitPrice()* customOrderDTO.getQty();
        customOrderDTO.setTotalAmount(totalam);
        System.out.println(totalam);
        cart.add(customOrderDTO);

            ObservableList<OrderTM> list = FXCollections.observableArrayList();
            for (CustomOrderDTO customOrderDTO1 : cart) {
                OrderTM OrderTM = new OrderTM(
                        customOrderDTO1.getCustomerID(),
                        customOrderDTO1.getUnitPrice(),
                        customOrderDTO1.getItemName(),
                        customOrderDTO.getQty(),
                        customOrderDTO1.getItemID()
                );
                list.add(OrderTM);
            }
            tableOrder.setItems(list);


        clnCustomerID     .setCellValueFactory(new PropertyValueFactory<>("customerID"));
        clnItemName   .setCellValueFactory(new PropertyValueFactory<>("itemName"));
        clnItemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        clnQuantity.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        clnUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
    }




    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        tblPlaceOrder.refresh();
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

        tblPlaceOrder.setVisible(true);
        tableOrder.setVisible(false);
        try {
            if(orderBO.placeOrder(cart) && orderBO.saveOrderDetails(cart)){
                new Alert(Alert.AlertType.INFORMATION , "Order Placed").show();
                nextID();
                cart.clear();
            }else{
                new Alert(Alert.AlertType.ERROR , "Order Not Saved").show();
                btnResetOnAction(null);
                cart.clear();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private final OrderBO orderBO = BOFactory.getInstance().getBO(BOTypes.ORDER);

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
            ItemDto findItemDto = orderBO.findItem(itemID);

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
            boolean isFound = orderBO.findCustomer(customerID);

            if(!isFound){
                errorSender("ID Not Found" , null , "Customer ID Not Found");
                btnResetOnAction(null);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void loadTable() throws SQLException{
        List<OrderDto> orderDtoList = orderBO.getAll();

        ObservableList<OrderTM> list = FXCollections.observableArrayList();
        for (OrderDto orderDto : orderDtoList){
            OrderTM orderTM = new OrderTM(
                    orderDto.getOrderID(),
                    orderDto.getCustomerID(),
                    orderDto.getTotalAmount(),
                    orderDto.getDate()
            );
            list.add(orderTM);
        }
        tblPlaceOrder.setItems(list);
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
        tableOrder.setVisible(false);
        tblPlaceOrder.setVisible(true);

        clnCustomerIDPlace     .setCellValueFactory(new PropertyValueFactory<>("customerID"));
        clnOrderIDPlace   .setCellValueFactory(new PropertyValueFactory<>("orderID"));
        clnTotalPricePlace.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        clnDatePlace.setCellValueFactory(new PropertyValueFactory<>("date"));

        try{
            loadTable();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void nextID(){
        try{
            lblOrderID.setText(orderBO.nextID());
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

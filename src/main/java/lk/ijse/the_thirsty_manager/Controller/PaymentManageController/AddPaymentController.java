package lk.ijse.the_thirsty_manager.Controller.PaymentManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.PaymentBO;
import lk.ijse.the_thirsty_manager.Dto.PaymentDto;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddPaymentController implements Initializable {

    public TextField txtAmount;
    @FXML
    private SplitMenuButton SplitMenuPaymentMethod;

    @FXML
    private TextField txtDate;

    @FXML
    private AnchorPane ancAddPayment;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblPaymentID;


    @FXML
    private TextField txtOrderID;

    private PaymentDto paymentDto = new PaymentDto();
    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancAddPayment.getChildren().clear();
        ancAddPayment.setVisible(false);
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        SplitMenuPaymentMethod.setText("Payment Method");
        txtOrderID.clear();
    }
    public void byCardOnAction(ActionEvent event) {
        SplitMenuPaymentMethod.setText("By Card");
        paymentDto.setPaymentMethod("Card");
    }

    public void byCashOnAction(ActionEvent event) {
        SplitMenuPaymentMethod.setText("By Cash");
        paymentDto.setPaymentMethod("Cash");
    }
    private final PaymentBO paymentBO = BOFactory.getInstance().getBO(BOTypes.PAYMENT);

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        txtDate.setText(String.valueOf(LocalDate.now()));
        String payID = lblPaymentID.getText();
        String oID = txtOrderID.getText();

        if(oID.isEmpty()){
            errorSender("Missing Fields" , null , "Fill All Missing Fields");
            btnResetOnAction(null);
            return;
        }
        if(paymentDto.getTotalAmount() == null){
            errorSender("Amount Is Empty" , null , "Fill order ID and Press 'Enter");
            btnResetOnAction(null);
            return;
        }
        double amount = paymentDto.getTotalAmount();

        if(amount == 0){
            errorSender("Amount Is Empty" , null , "Fill order ID and Press 'Enter");
            btnResetOnAction(null);
            return;
        }

        paymentDto.setPaymentID(payID);
        paymentDto.setOrderID(oID);
        paymentDto.setDate(txtDate.getText());

        try {
            boolean isSaved = paymentBO.save(paymentDto);

            if(isSaved){
                infoSender("Payment Saved" , null , "Payment Saved Success");
                loadID();
                btnResetOnAction(null);
            }else{
                errorSender("Not Saved" , null , "Payment Not Saved");
                btnResetOnAction(null);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            errorSender("ERROR" , null , "Internal Database Error");
            btnResetOnAction(null);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadID();
        txtDate.setText(String.valueOf(LocalDate.now()));
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

    public void findOrderOnAction(ActionEvent event) {
        String oID = txtOrderID.getText();
        try {
           PaymentDto findOrder = paymentBO.findOrder(oID);

            if(findOrder == null){
                errorSender("ID Not Found" , null , "Order ID Not Found");
                btnResetOnAction(null);
            }else{
                paymentDto.setTotalAmount(findOrder.getTotalAmount());
                txtAmount.setText(String.valueOf(findOrder.getTotalAmount()));
            }
            boolean isDuplicated = paymentBO.duplicate(oID);
            System.out.println(isDuplicated);

            if(isDuplicated) {
                errorSender("Paid", null, "This Order Get Paid");
                btnResetOnAction(null);
                txtAmount.clear();
                return;
            }

            paymentDto.setOrderID(oID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void loadID(){
        try {
            lblPaymentID.setText(paymentBO.nextID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

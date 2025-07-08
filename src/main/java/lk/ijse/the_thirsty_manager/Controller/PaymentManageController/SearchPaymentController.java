package lk.ijse.the_thirsty_manager.Controller.PaymentManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.PaymentDto;
import lk.ijse.the_thirsty_manager.Dto.SalaryDto;
import lk.ijse.the_thirsty_manager.Model.CustomerManageModel.SearchCustomerModel;
import lk.ijse.the_thirsty_manager.Model.PaymentManageModel.SearchPaymentModel;

import java.sql.SQLException;

public class SearchPaymentController {

    @FXML
    private SplitMenuButton SplitMenuPaymentMethod;

    @FXML
    private AnchorPane ancSearchPayment;

    @FXML
    private Button btnClose;

    @FXML
    private Label lblPaymentID;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtOrderID;

    private PaymentDto paymentDto = new PaymentDto();
    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancSearchPayment.getChildren().clear();
        ancSearchPayment.setVisible(false);
    }

    private SearchPaymentModel searchPaymentModel = new SearchPaymentModel();
    public void searchPayment(String payID){
        paymentDto.setPaymentID(payID);
        try {
            PaymentDto searchPaymentDto = searchPaymentModel.findPayment(paymentDto);

            if (searchPaymentDto != null){
                if(payID.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Missing Fields");
                    alert.setContentText("Please fill Order ID");
                    alert.show();
                    ancSearchPayment.getChildren().clear();
                    ancSearchPayment.setVisible(false);
                    return;
                }


                lblPaymentID.setText(searchPaymentDto.getPaymentID());
                if(searchPaymentDto.getPaymentMethod().equals("By Card")) {
                    SplitMenuPaymentMethod.setText("By Card");
                }else {
                    SplitMenuPaymentMethod.setText("By Cash");
                }


                txtAmount.setText(String.valueOf(searchPaymentDto.getTotalAmount()));
                txtAmount.setEditable(false);
                txtOrderID.setText(searchPaymentDto.getOrderID());
                txtOrderID.setEditable(false);
                txtDate.setText(searchPaymentDto.getDate());
                txtDate.setEditable(false);
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ID not found");
                alert.setContentText("Customer ID not found");
                alert.show();
                ancSearchPayment.setVisible(false);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


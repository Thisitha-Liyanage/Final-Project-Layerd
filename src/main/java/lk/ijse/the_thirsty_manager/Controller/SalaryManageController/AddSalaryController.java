package lk.ijse.the_thirsty_manager.Controller.SalaryManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.SalaryBO;
import lk.ijse.the_thirsty_manager.Dto.SalaryDto;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddSalaryController implements Initializable {

    @FXML
    private AnchorPane ancAddSalary;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblSalaryID;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmployeeID;

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancAddSalary.getChildren().clear();
        ancAddSalary.setVisible(false);
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtEmployeeID.clear();
        txtAmount.clear();
    }
    private SalaryBO salaryBO = BOFactory.getInstance().getBO(BOTypes.SALARY);

    private SalaryDto salaryDto = new SalaryDto();

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String empID = txtEmployeeID.getText();
        String salID = lblSalaryID.getText();
        String date = txtDate.getText();
        String amount = txtAmount.getText();


        if(empID.isEmpty()){
            errorSender("ID Not Found" , null , "Employee ID Not Found");
            btnResetOnAction(null);
            return;
        }

        if (amount.isEmpty()){
            errorSender("Empty Salary" , null , "Fill Employee ID and Press 'Enter'");
            btnResetOnAction(null);
            return;
        }

        salaryDto.setSalaryID(salID);
        salaryDto.setEmpID(empID);
        try {
            salaryDto.setAmount(Double.valueOf(amount));
        } catch (NumberFormatException e) {
            errorSender("Empty Salary" , null , "Fill Employee ID and Press 'Enter'");
            e.printStackTrace();
        }
        salaryDto.setDate(date);

        try {

            boolean isSaved = salaryBO.save(salaryDto);

            if(isSaved){
                infoSender("Salary Paid" , null , "Salary Paid Success");
                loadID();
                btnResetOnAction(null);
            }else{
                errorSender("Not Saved" , null , "Salary Not Paid");
                btnResetOnAction(null);
            }
        } catch (SQLException e) {
            errorSender("ERROR" , null , "Internal Database Error");
            e.printStackTrace();
        }
    }
    public void errorSender(String titleTxt , String headerTxt , String contentTxt){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleTxt);
        alert.setHeaderText(headerTxt);
        alert.setContentText(contentTxt);
        alert.show();
    }

    public void infoSender(String titleTxt , String headerTxt , String contentTxt){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleTxt);
        alert.setHeaderText(headerTxt);
        alert.setContentText(contentTxt);
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtDate.setText(String.valueOf(LocalDate.now()));
        loadID();
    }

    public void loadID(){
        try {
            lblSalaryID.setText(salaryBO.getNextID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salaryGetOnAction(ActionEvent event) {
        try {
            SalaryDto empDtl = salaryBO.findEmp(txtEmployeeID.getText());
            if (empDtl == null) {
                errorSender("ID Not Found" , null , "Employee ID Not Found");
                btnResetOnAction(null);
            }else{
                txtAmount.setText(String.valueOf(empDtl.getAmount()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package lk.ijse.the_thirsty_manager.Controller.EmployeeManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.EmployeeBO;
import lk.ijse.the_thirsty_manager.Dto.EmployeeDto;
import lk.ijse.the_thirsty_manager.Model.EmployeeManageModel.DeleteEmployeeModel;

import java.sql.SQLException;

public class DeleteEmployeeController {

    @FXML
    private AnchorPane ancDeleteEmployee;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnFind;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtEmployeeContact;

    @FXML
    private TextField txtEmployeeID;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtEmployeeRole;

    @FXML
    private TextField txtEmployeeSPD;

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancDeleteEmployee.getChildren().clear();
        ancDeleteEmployee.setVisible(false);
    }

    private final EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);

    @FXML
    void btnFindOnActon(ActionEvent event) {
        String empID = txtEmployeeID.getText();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeID(empID);

        try {
            EmployeeDto findEmp = employeeBO.search(empID);
            if(findEmp == null){
                errorSender("ID Not Found" , null ," Employee ID Not Found");
                btnResetOnAction(null);
                return;
            }

            txtEmployeeName.setText(findEmp.getName());
            txtEmployeeName.setEditable(false);
            txtEmployeeContact.setText(findEmp.getContact());
            txtEmployeeRole.setEditable(false);
            txtEmployeeRole.setText(findEmp.getRole());
            txtEmployeeContact.setEditable(false);
            txtEmployeeSPD.setText(String.valueOf(findEmp.getSPD()));
            txtEmployeeSPD.setEditable(false);

        } catch (SQLException e) {
            errorSender("ERROR" , null , "Internal Database Error" );
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtEmployeeID.clear();
        txtEmployeeName.clear();
        txtEmployeeContact.clear();
        txtEmployeeSPD.clear();
        txtEmployeeRole.clear();
    }


    public void btnDeleteOnAction(ActionEvent event) {
        String empID = txtEmployeeID.getText();
        try {
            boolean isDelete = employeeBO.delete(empID);

            if(isDelete){
                infoSender("Employee Deleted" , null , "Employee Delete Success");
                btnResetOnAction(null);
            }else{
                errorSender("Not Deleted" , null , "Employee Not Delete");
            }

        } catch (SQLException e) {
            errorSender("ERROR" , null , "Internal Database Error");
            btnResetOnAction(null);
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
}

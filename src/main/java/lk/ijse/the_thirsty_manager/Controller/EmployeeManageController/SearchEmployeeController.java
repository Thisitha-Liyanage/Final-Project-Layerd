package lk.ijse.the_thirsty_manager.Controller.EmployeeManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.EmployeeBO;
import lk.ijse.the_thirsty_manager.Dto.EmployeeDto;

import java.sql.SQLException;

public class SearchEmployeeController {

    @FXML
    private AnchorPane ancSearchEmployee;

    @FXML
    private Button btnClose;

    @FXML
    private Label lblEmployeeID;

    @FXML
    private TextField txtEmployeeContact;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtEmployeeRole;

    @FXML
    private TextField txtEmployeeSPD;

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancSearchEmployee.getChildren().clear();
        ancSearchEmployee.setVisible(false);
    }

    private final EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);

    public void searchEmployee(String empID){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeID(empID);

        try {
            EmployeeDto searchEmpDto = employeeBO.search(empID);

            if(searchEmpDto == null){
                errorSender("ID Not Found" , null , "Employee ID Not Found");
                btnCloseOnAction(null);
            }else{
                lblEmployeeID.setText(employeeDto.getEmployeeID());
                txtEmployeeName.setText(searchEmpDto.getName());
                txtEmployeeName.setEditable(false);
                txtEmployeeRole.setText(searchEmpDto.getRole());
                txtEmployeeRole.setEditable(false);
                txtEmployeeContact.setText(searchEmpDto.getContact());
                txtEmployeeContact.setEditable(false);
                txtEmployeeSPD.setText(String.valueOf(searchEmpDto.getSPD()));
                txtEmployeeSPD.setEditable(false);
            }
        } catch (SQLException e) {
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

}

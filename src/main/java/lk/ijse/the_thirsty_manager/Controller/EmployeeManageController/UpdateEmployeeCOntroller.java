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
import lk.ijse.the_thirsty_manager.Model.EmployeeManageModel.UpdateEmployeeModel;

import java.sql.SQLException;

public class UpdateEmployeeCOntroller {
    private final EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    @FXML
    private AnchorPane ancUpdateEmployee;

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
        ancUpdateEmployee.getChildren().clear();
        ancUpdateEmployee.setVisible(false);
    }


    @FXML
    void btnFindOnActon(ActionEvent event) {
        String empID = txtEmployeeID.getText();
        if (empID.isEmpty()) {
            errorSender("ERROR" , null , "Enter Employee ID");
            return;
        }

        try {
            EmployeeDto findEmpDto = employeeBO.search(empID);
            if(findEmpDto == null){
                errorSender("ID Not Found" , null , "Employee ID Not Found");
                btnResetOnAction(null);
            }else{
                txtEmployeeRole.setText(findEmpDto.getRole());
                txtEmployeeName.setText(findEmpDto.getName());
                txtEmployeeContact.setText(findEmpDto.getContact());
                txtEmployeeSPD.setText(String.valueOf(findEmpDto.getSPD()));
            }

        } catch (SQLException e) {
            errorSender("ERROR" , null , "Internal Database Error");
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

    public void btnUpdateOnAction(ActionEvent event) {
        EmployeeDto employeeDto = new EmployeeDto();
        String name = txtEmployeeName.getText();
        String role = txtEmployeeRole.getText();
        String contact = txtEmployeeContact.getText();
        String sPD = txtEmployeeSPD.getText();

        if(name.isEmpty() || role.isEmpty() || contact.isEmpty() || sPD.isEmpty()){
            errorSender("Missing Fields" , null , "Please Fill All Fields");
            return;
        }
        double sPDD;
        try {
                sPDD = Double.parseDouble(sPD);
            System.out.println(sPDD);
        }catch (NumberFormatException e){
            errorSender("Wrong Value" , null , "Salary Per Day Must Be Numbers");
            btnResetOnAction(null);
            return;
        }
        employeeDto.setEmployeeID(txtEmployeeID.getText());
        employeeDto.setName(name);
        employeeDto.setRole(role);
        employeeDto.setContact(contact);
        employeeDto.setSPD(sPDD);

        try{
            boolean isUpdate = employeeBO.update(employeeDto);

            if(isUpdate){
                infoSender("Employee Updated" , null , "Employee Updated Success");
                btnResetOnAction(null);
            }else{
                errorSender("Not Updated" , null , "Employee Not Updated");
                btnResetOnAction(null);
            }
        }catch (SQLException e){
            errorSender("ERROR" , null , "Internal Database Error");
            btnResetOnAction(null);
        }
    }
}

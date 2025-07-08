package lk.ijse.the_thirsty_manager.Controller.EmployeeManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.Dto.EmployeeDto;
import lk.ijse.the_thirsty_manager.Model.EmployeeManageModel.AddEmployeeModel;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {

    @FXML
    private AnchorPane ancAddEmployee;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

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
        ancAddEmployee.getChildren().clear();
        ancAddEmployee.setVisible(false);
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtEmployeeName.clear();
        txtEmployeeContact.clear();
        txtEmployeeRole.clear();
        txtEmployeeSPD.clear();
    }

    private AddEmployeeModel addEmployeeModel = new AddEmployeeModel();
    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {

        String name = txtEmployeeName.getText();
        String contact = txtEmployeeContact.getText();
        String role = txtEmployeeRole.getText();
        String sPD = txtEmployeeSPD.getText();


        if(name.isEmpty() || contact.isEmpty() || role.isEmpty() || sPD.isEmpty()){
            errorSender("Missing Fields" , null , "Fill All Fields");
            btnResetOnAction(null);
            return;
        }

            if(contact.length() != 10){
                errorSender("ERROR" , null , "Wrong Contact Number");
                btnResetOnAction(null);
                return;
            }

            double sPDD;
            try{
                sPDD = Double.parseDouble(sPD);
            }catch (NumberFormatException e){
                errorSender("ERROR" , null , "Salary Per Day Must Be Numbers" );
                btnResetOnAction(null);
                return;
            }



            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setEmployeeID(lblEmployeeID.getText());
            employeeDto.setName(txtEmployeeName.getText());
            employeeDto.setRole(txtEmployeeRole.getText());
            employeeDto.setContact(txtEmployeeContact.getText());
            employeeDto.setSPD(sPDD);

        try {
            boolean isSaved = addEmployeeModel.saveEmployee(employeeDto);

            if(isSaved){
                infoSender("Saved" , null , "Customer Saved Success");
                lblEmployeeID.setText(addEmployeeModel.getNextId());
                btnResetOnAction(null);
            }else{
                errorSender("Not Saved" , null , "Employee Not Saved");
                btnResetOnAction(null);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadNextID();
    }

    public void loadNextID(){
        try {
            lblEmployeeID.setText(addEmployeeModel.getNextId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


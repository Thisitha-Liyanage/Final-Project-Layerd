package lk.ijse.the_thirsty_manager.Controller.SalaryManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.Dto.SalaryDto;
import lk.ijse.the_thirsty_manager.Model.SalaryManageModel.SearchSalaryModel;

import java.sql.SQLException;

public class SearchSalaryController {

    @FXML
    private AnchorPane ancSearchSalary;

    @FXML
    private Button btnClose;

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
        ancSearchSalary.getChildren().clear();
        ancSearchSalary.setVisible(false);
    }
    public void findSalary(String salaryID){
        if(salaryID.isEmpty()){
           errorSender("ID Not Found" , null , "Salary ID Not Found");
           ancSearchSalary.getChildren().clear();
           ancSearchSalary.setVisible(false);
           return;
        }

        SalaryDto salaryDto = new SalaryDto();

        salaryDto.setSalaryID(salaryID);

        SearchSalaryModel searchSalaryModel = new SearchSalaryModel();
        try {
            SalaryDto findSal = searchSalaryModel.findSalary(salaryDto);

            if(findSal == null){
                errorSender("ID Not Found" , null , "Salary ID Not Found");
                ancSearchSalary.getChildren().clear();
                ancSearchSalary.setVisible(false);
            }else{
                lblSalaryID.setText(findSal.getSalaryID());
                txtDate.setText(findSal.getDate());
                txtAmount.setText(String.valueOf(findSal.getAmount()));
                txtEmployeeID.setText(findSal.getEmpID());
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

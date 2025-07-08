package lk.ijse.the_thirsty_manager.Controller.AttendanceManage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.Dto.AttendanceDto;
import lk.ijse.the_thirsty_manager.Model.AttendanceManage.AddAttendanceModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddAttendanceController implements Initializable {

    public AnchorPane ancReportAttendance;
    @FXML
    private Button btnClose;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private CheckBox checkBoxAttendance;

    @FXML
    private Label lblAttendanceID;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmployeeID;

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancReportAttendance.getChildren().clear();
        ancReportAttendance.setVisible(false);
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtEmployeeID.clear();
        checkBoxAttendance.setSelected(false);
    }

    private AttendanceDto attendanceDto  = new AttendanceDto();
    private AddAttendanceModel addAttendanceModel = new AddAttendanceModel();
    @FXML
    void btnSaveOnAction(ActionEvent event)  {
        txtDate.setText(String.valueOf(LocalDate.now()));
        String attID = lblAttendanceID.getText();

        attendanceDto.setAttenID(attID);
        attendanceDto.setDate(txtDate.getText());

        String empID = txtEmployeeID.getText();

        if(empID.isEmpty()){
            errorSender("Missing Fields" , null , "Fill All Missing Fields");
            btnResetOnAction(null);
            return;
        }

        if(checkBoxAttendance.isSelected()){
            attendanceDto.setStatus("Working");
        }else{
            attendanceDto.setStatus("Not Working");
        }

        try {
            boolean isFound = addAttendanceModel.findEmployee(empID);

            if(isFound){
                attendanceDto.setEmpID(empID);
            }else{
                errorSender("ID Not Found" , null , "Employee ID Not Found");
                btnResetOnAction(null);
                return;
            }

            boolean isSaved = addAttendanceModel.saveAttendance(attendanceDto);

            if(isSaved){
                infoSender("Attendance Reported" , null , "Attendance Report Success");
                loadID();
                btnResetOnAction(null);
            }else{
                errorSender("Not Reported" , null , "Attendance Not Reported");
                loadID();
                btnResetOnAction(null);
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
            lblAttendanceID.setText(addAttendanceModel.getNextId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

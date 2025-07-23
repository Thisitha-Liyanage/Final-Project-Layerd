package lk.ijse.the_thirsty_manager.Controller.AttendanceManage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.AttendanceBO;
import lk.ijse.the_thirsty_manager.Dto.AttendanceDto;
import lk.ijse.the_thirsty_manager.Model.AttendanceManage.SearchAttendanceModel;

import java.sql.SQLException;

public class SearchAttendanceController {

    @FXML
    private AnchorPane ancAttendanceManage;

    @FXML
    private Button btnClose;

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
        ancAttendanceManage.getChildren().clear();
        ancAttendanceManage.setVisible(false);
    }

    private AttendanceBO attendanceBO = BOFactory.getInstance().getBO(BOTypes.ATTENDANCE);

    public void searchAttend(String attID){
        try {

            AttendanceDto attendanceDto = attendanceBO.search(attID);
            if(attendanceDto == null){
                errorSender("ID Not Found" , null , "Attendance ID Not Found");
                btnCloseOnAction(null);
            }else{
                lblAttendanceID.setText(attendanceDto.getAttenID());
                txtEmployeeID.setText(attendanceDto.getEmpID());
                txtDate.setText(attendanceDto.getDate());

                if(attendanceDto.getStatus().equals("Working")){
                    checkBoxAttendance.setSelected(true);
                }else{
                    checkBoxAttendance.setSelected(false);
                }
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

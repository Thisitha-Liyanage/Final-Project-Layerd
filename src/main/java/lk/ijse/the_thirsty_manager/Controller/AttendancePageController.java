package lk.ijse.the_thirsty_manager.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.AttendanceBO;
import lk.ijse.the_thirsty_manager.Controller.AttendanceManage.SearchAttendanceController;
import lk.ijse.the_thirsty_manager.Dto.AttendanceDto;
import lk.ijse.the_thirsty_manager.Dto.TM.AttendanceTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AttendancePageController implements Initializable {

    @FXML
    private AnchorPane ancAttendancePage;

    @FXML
    private AnchorPane ancPaymentManagePageLoader;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<AttendanceTM, String > clnAttendanceID;

    @FXML
    private TableColumn<AttendanceTM, String > clnDate;

    @FXML
    private TableColumn<AttendanceTM, String > clnEmployeeID;

    @FXML
    private TableColumn<AttendanceTM, String> clnStatus;

    @FXML
    private TableView<AttendanceTM> tableViewePayment;

    @FXML
    private TextField txtSearchPay;

    @FXML
    void btnAttendanceOnAction(ActionEvent event) {
        navigatTo("/View/AttendancePage.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        navigatTo("/View/EmployeePage.fxml");
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) throws SQLException {
        loadTable();
        tableViewePayment.refresh();
    }

    @FXML
    void btnSalaryOnAction(ActionEvent event) {
        navigatTo("/View/SalaryPage.fxml");
    }
    public void navigatTo(String path){
        try{
            ancAttendancePage.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancAttendancePage.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void manageLoad(String path){
        try{
            ancPaymentManagePageLoader.getChildren().clear();
            ancPaymentManagePageLoader.setVisible(true);
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancPaymentManagePageLoader.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String searchattID = txtSearchPay.getText();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AttendanceManage/SearchAttendace.fxml"));
            Parent parent = loader.load();
            SearchAttendanceController controller = loader.getController();

            ancPaymentManagePageLoader.getChildren().clear();
            ancPaymentManagePageLoader.setVisible(true);
            ancPaymentManagePageLoader.getChildren().add(parent);

            controller.searchAttend(searchattID);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnreportAttendanceOnAction(ActionEvent event) {
        manageLoad("/View/AttendanceManage/AddAttendance.fxml");
    }

    private final AttendanceBO attendanceBO = BOFactory.getInstance().getBO(BOTypes.ATTENDANCE);
    public void loadTable() throws SQLException {
        List<AttendanceDto> attendanceDTOArrayList = attendanceBO.getAll();

        ObservableList<AttendanceTM> list = FXCollections.observableArrayList();
        for (AttendanceDto attendanceDto : attendanceDTOArrayList){
            AttendanceTM attendanceTM = new AttendanceTM(
                    attendanceDto.getAttenID(),
                    attendanceDto.getDate(),
                    attendanceDto.getEmpID(),
                    attendanceDto.getStatus()
            );
            list.add(attendanceTM);
        }
        tableViewePayment.setItems(list);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clnAttendanceID     .setCellValueFactory(new PropertyValueFactory<>("attenID"));
        clnDate   .setCellValueFactory(new PropertyValueFactory<>("date"));
        clnEmployeeID.setCellValueFactory(new PropertyValueFactory<>("empID"));
        clnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

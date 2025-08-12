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
import lk.ijse.the_thirsty_manager.BO.Custom.EmployeeBO;
import lk.ijse.the_thirsty_manager.Controller.EmployeeManageController.SearchEmployeeController;
import lk.ijse.the_thirsty_manager.Dto.EmployeeDto;
import lk.ijse.the_thirsty_manager.Dto.TM.EmployeeTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeePageController implements Initializable {

    public AnchorPane ancEmployeeManagePageLoader;
    public TextField txtSearEmp;
    @FXML
    private AnchorPane ancEmployeePage;

    @FXML
    private Button btnAddEmployee;

    @FXML
    private Button btnDeleteEmployee;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdateEmployee;

    @FXML
    private TableColumn<EmployeeTM , String > clnContact;

    @FXML
    private TableColumn<EmployeeTM , String > clnEmployeeID;

    @FXML
    private TableColumn<EmployeeTM , String > clnName;

    @FXML
    private TableColumn<EmployeeTM , String > clnRole;

    @FXML
    private TableColumn<EmployeeTM , Double> clnSallaryPerDay;

    @FXML
    private TableView<EmployeeTM> tableVieweEmployee;

    private final EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) {
        manageLoader("/View/EmployeeManage/AddEmployee.fxml");
    }

    @FXML
    void btnAttendanceOnAction(ActionEvent event) {
        navigateto("/View/AttendancePage.fxml");
    }

    @FXML
    void btnDeleteEmployeeOnAction(ActionEvent event) {
        manageLoader("/View/EmployeeManage/DeleteEmployee.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        navigateto("/View/EmployeePage.fxml");
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) throws SQLException {
        loadTable();
        tableVieweEmployee.refresh();
    }

    @FXML
    void btnSalaryOnAction(ActionEvent event) {
        navigateto("/View/SalaryPage.fxml");
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String searchEmpID = txtSearEmp.getText();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EmployeeManage/SearchEmployee.fxml"));
            Parent parent = loader.load();
            SearchEmployeeController controller = loader.getController();

            ancEmployeeManagePageLoader.getChildren().clear();
            ancEmployeeManagePageLoader.setVisible(true);
            ancEmployeeManagePageLoader.getChildren().add(parent);

            controller.searchEmployee(searchEmpID);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateEmployeeOnAction(ActionEvent event) {
        manageLoader("/View/EmployeeManage/UpdateEmployee.fxml");
    }

    public void manageLoader(String path) {
        try {
            ancEmployeeManagePageLoader.getChildren().clear();
            ancEmployeeManagePageLoader.setVisible(true);
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancEmployeeManagePageLoader.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void navigateto(String path) {
        try {
            ancEmployeePage.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancEmployeePage.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        public void loadTable() throws SQLException {

        List<EmployeeDto> employeeDTOArrayList = employeeBO.getAll();
        ObservableList<EmployeeTM> list = FXCollections.observableArrayList();
        for (EmployeeDto employeeDto : employeeDTOArrayList) {
            EmployeeTM employeeTM = new EmployeeTM(
                    employeeDto.getEmployeeID(),
                    employeeDto.getName(),
                    employeeDto.getRole(),
                    employeeDto.getContact(),
                    employeeDto.getSPD()
            );

            list.add(employeeTM);
        }

        tableVieweEmployee.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clnEmployeeID .setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        clnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clnRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        clnContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clnSallaryPerDay .setCellValueFactory(new PropertyValueFactory<>("SPD"));


        try {
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

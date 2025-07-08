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
import lk.ijse.the_thirsty_manager.Controller.CustomerManageController.SearchCustomerController;
import lk.ijse.the_thirsty_manager.Controller.SalaryManageController.AddSalaryController;
import lk.ijse.the_thirsty_manager.Controller.SalaryManageController.SearchSalaryController;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.SalaryDto;
import lk.ijse.the_thirsty_manager.Dto.TM.CustomerTM;
import lk.ijse.the_thirsty_manager.Dto.TM.SalaryTM;
import lk.ijse.the_thirsty_manager.Model.SalaryManageModel.AddSalaryModel;
import lk.ijse.the_thirsty_manager.Model.SalaryManageModel.SearchSalaryModel;
import lk.ijse.the_thirsty_manager.Model.SalaryPageModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SalaryPageController implements Initializable {

    @FXML
    private AnchorPane ancSalaryManagePageLoader;

    @FXML
    private AnchorPane ancSalaryPage;

    @FXML
    private Button btnAddSalary;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdateSalary;

    @FXML
    private TableColumn<SalaryTM, Double> clnAmount;

    @FXML
    private TableColumn<SalaryTM, String> clnDate;

    @FXML
    private TableColumn<SalaryTM, String> clnEmployeeID;

    @FXML
    private TableColumn<SalaryTM, String > clnSalaryID;

    @FXML
    private TableView<SalaryTM> tableVieweSalary;

    @FXML
    private TextField txtSearchSalary;

    @FXML
    void btnAddSalaryOnAction(ActionEvent event) {
        salaryManageLoad("/View/SalaryManage/AddSalary.fxml");
    }

    @FXML
    void btnAttendanceOnAction(ActionEvent event) {
        navigateTo("/View/AttendancePage.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        navigateTo("/View/EmployeePage.fxml");
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) throws SQLException {
        loadTable();
    }

    @FXML
    void btnSalaryOnAction(ActionEvent event) {
        navigateTo("/View/SalaryPage.fxml");
    }

    private SearchSalaryController searchSalaryController = new SearchSalaryController();
    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String searchSalID = txtSearchSalary.getText();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SalaryManage/SearchSalary.fxml"));
            Parent parent = loader.load();
            SearchSalaryController controller = loader.getController();

            ancSalaryManagePageLoader.getChildren().clear();
            ancSalaryManagePageLoader.setVisible(true);
            ancSalaryManagePageLoader.getChildren().add(parent);

            controller.findSalary(searchSalID);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salaryManageLoad(String path) {
        try {
            ancSalaryManagePageLoader.getChildren().clear();
            ancSalaryManagePageLoader.setVisible(true);
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancSalaryManagePageLoader.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void navigateTo(String path) {
        try {
            ancSalaryPage.getChildren().clear();
            ancSalaryPage.setVisible(true);
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancSalaryPage.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private SalaryPageModel salaryPageModel = new SalaryPageModel();
    public void loadTable() throws SQLException {

        ArrayList<SalaryDto> salaryDTOArrayList = salaryPageModel.getAllSalary();

        ObservableList<SalaryTM> list = FXCollections.observableArrayList();
        for (SalaryDto salaryDto : salaryDTOArrayList){
            SalaryTM salaryTM = new SalaryTM(
                    salaryDto.getSalaryID(),
                    salaryDto.getEmpID(),
                    salaryDto.getAmount(),
                    salaryDto.getDate()
            );
            list.add(salaryTM);
        }
        tableVieweSalary.setItems(list);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clnSalaryID     .setCellValueFactory(new PropertyValueFactory<>("salaryID"));
        clnEmployeeID   .setCellValueFactory(new PropertyValueFactory<>("empID"));
        clnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        clnDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

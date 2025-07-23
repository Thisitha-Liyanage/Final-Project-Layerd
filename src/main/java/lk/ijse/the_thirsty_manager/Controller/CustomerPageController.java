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
import lk.ijse.the_thirsty_manager.BO.Custom.CustomerBO;
import lk.ijse.the_thirsty_manager.Controller.CustomerManageController.SearchCustomerController;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.TM.CustomerTM;
import lombok.Getter;
import lombok.Setter;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerPageController implements Initializable {

    @FXML
    private AnchorPane ancCustomerPage;

    @FXML
    private AnchorPane ancCustomerManagePageLoader;

    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnDeleteCustomer;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdateCustomer;

    @FXML
    private TableColumn<CustomerTM, String> clnCustomerAddress;

    @FXML
    private TableColumn<CustomerTM, Integer> clnCustomerAge;

    @FXML
    private TableColumn<CustomerTM, String> clnCustomerContact;

    @FXML
    private TableColumn<CustomerTM, String> clnCustomerID;

    @FXML
    private TableColumn<CustomerTM, String> clnCustomerName;

    @FXML
    private TableView<CustomerTM> tableViewCustomer;


    @FXML
    private TextField txtSearchCustomer;

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
        tableViewCustomer.refresh();
        customerManageLoad("/View/CustomerManage/AddCUstomer.fxml");
    }


    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {
        tableViewCustomer.refresh();
        customerManageLoad("/View/CustomerManage/DeleteCustomer.fxml");
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String searchCusID = txtSearchCustomer.getText();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CustomerManage/SearchCustomer.fxml"));
            Parent parent = loader.load();
            SearchCustomerController controller = loader.getController();

            ancCustomerManagePageLoader.getChildren().clear();
            ancCustomerManagePageLoader.setVisible(true);
            ancCustomerManagePageLoader.getChildren().add(parent);

            controller.searchCustomer(searchCusID);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {
        tableViewCustomer.refresh();
        customerManageLoad("/View/CustomerManage/UpdateCustomer.fxml");
    }


    public void customerManageLoad(String path) {
        try {
            ancCustomerManagePageLoader.getChildren().clear();
            ancCustomerManagePageLoader.setVisible(true);
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancCustomerManagePageLoader.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private CustomerBO customerBO = BOFactory.getInstance().getBO(BOTypes.CUSTOMER);
    @Setter
    @Getter
    private CustomerTM customerTM = new CustomerTM();

    public void loadTable() throws SQLException {

        List<CustomerDto> customerDtoListList = customerBO.getAll();

        ObservableList<CustomerTM> list = FXCollections.observableArrayList();
        for (CustomerDto customerDTO : customerDtoListList){
            CustomerTM customerTM = new CustomerTM(
                    customerDTO.getCustomerID(),
                    customerDTO.getCustomerName(),
                    customerDTO.getAddress(),
                    customerDTO.getContact(),
                    customerDTO.getAge()
            );
            list.add(customerTM);
        }
        tableViewCustomer.setItems(list);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clnCustomerID     .setCellValueFactory(new PropertyValueFactory<>("cusID"));
        clnCustomerName   .setCellValueFactory(new PropertyValueFactory<>("name"));
        clnCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clnCustomerContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clnCustomerAge    .setCellValueFactory(new PropertyValueFactory<>("age"));

        try {
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnRefreshOnAction(ActionEvent event)  {
        try {
            loadTable();
            tableViewCustomer.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




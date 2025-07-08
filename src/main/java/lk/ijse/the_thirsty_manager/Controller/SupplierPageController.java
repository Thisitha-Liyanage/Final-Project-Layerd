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
import lk.ijse.the_thirsty_manager.Controller.EmployeeManageController.SearchEmployeeController;
import lk.ijse.the_thirsty_manager.Controller.ManageSupplierController.SearchSupplierCOntroller;
import lk.ijse.the_thirsty_manager.Dto.EmployeeDto;
import lk.ijse.the_thirsty_manager.Dto.SupplierDto;
import lk.ijse.the_thirsty_manager.Dto.TM.EmployeeTM;
import lk.ijse.the_thirsty_manager.Dto.TM.SupplierTM;
import lk.ijse.the_thirsty_manager.Model.SUpplierPageModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierPageController implements Initializable {

    public TextField txtSearchSup;
    @FXML
    private AnchorPane ancSupplierMnagePageLoader;

    @FXML
    private AnchorPane ancSupplierPage;

    @FXML
    private Button btnAddSupplier;

    @FXML
    private Button btnDeleteSupplier;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdateSupplier;

    @FXML
    private TableColumn<SupplierTM, String > clnAddress;

    @FXML
    private TableColumn<SupplierTM, String> clnContact;

    @FXML
    private TableColumn<SupplierTM, String> clnName;

    @FXML
    private TableColumn<SupplierTM, String > clnSupplierID;

    @FXML
    private TableView<SupplierTM> tableVieweSupplier;

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {
        manageLoader("/View/SupplierManage/AddSupplier.fxml");
    }

    @FXML
    void btnDeleteSupplierOnAction(ActionEvent event) {
        manageLoader("/View/SupplierManage/DeleteSupplier.fxml");
    }

    @FXML
    void btnIngredientsOnAction(ActionEvent event) {
        navigateTo("/View/IngredIentPage.fxml");
    }

    @FXML
    void btnInventoryOnAction(ActionEvent event) {
        navigateTo("/View/InventoryPage.fxml");
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) throws SQLException {
        loadTable();
        tableVieweSupplier.refresh();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String searchSupID = txtSearchSup.getText();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SupplierManage/SearchSupplier.fxml"));
            Parent parent = loader.load();
            SearchSupplierCOntroller controller = loader.getController();

            ancSupplierMnagePageLoader.getChildren().clear();
            ancSupplierMnagePageLoader.setVisible(true);
            ancSupplierMnagePageLoader.getChildren().add(parent);

            controller.searchSup(searchSupID);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSuppierOnAction(ActionEvent event) {
        navigateTo("/View/SupplierPage.fxml");
    }

    @FXML
    void btnUpdateSupplierOnAction(ActionEvent event) {
        manageLoader("/View/SupplierManage/UpdateSupplier.fxml");
    }

    public void manageLoader(String path){
        try{
            ancSupplierMnagePageLoader.getChildren().clear();
            ancSupplierMnagePageLoader.setVisible(true);
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancSupplierMnagePageLoader.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigateTo(String path){
        try{
            ancSupplierPage.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            ancSupplierPage.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private SUpplierPageModel sUpplierPageModel = new SUpplierPageModel();
    private SupplierTM supplierTM = new SupplierTM();

    public void loadTable() throws SQLException {

        ArrayList<SupplierDto> supplierDTOArrayList = sUpplierPageModel.getAllSuppliers();

        ObservableList<SupplierTM> list = FXCollections.observableArrayList();
        for (SupplierDto supplierDto : supplierDTOArrayList) {
            SupplierTM supplierTM = new SupplierTM(
                    supplierDto.getSupID(),
                    supplierDto.getName(),
                    supplierDto.getContact(),
                    supplierDto.getAddress()
            );
            list.add(supplierTM);
        }
        tableVieweSupplier.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clnSupplierID .setCellValueFactory(new PropertyValueFactory<>("supID"));
        clnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clnAddress.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clnContact.setCellValueFactory(new PropertyValueFactory<>("address"));

        try {
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

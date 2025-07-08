package lk.ijse.the_thirsty_manager.Controller.TableManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.Dto.TableDto;
import lk.ijse.the_thirsty_manager.Model.TableManageMoled.DeleteTableModel;

import java.sql.SQLException;

public class DeleteTableController {

    @FXML
    private SplitMenuButton SplitMenuStatus;

    @FXML
    private AnchorPane ancDeletePage;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnReset;

    @FXML
    private TextField txtNoOfSeat;

    @FXML
    private TextField txtTableID;

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancDeletePage.getChildren().clear();
        ancDeletePage.setVisible(false);
    }

    private DeleteTableModel deleteTableModel = new DeleteTableModel();

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String tID = txtTableID.getText();
        try {
            boolean isDeleted = deleteTableModel.DeleteTable(tID);

            if(isDeleted){
                infoSender("Table Deleted" , null , "Table Delete Success");
                btnResetOnAction(null);
            }else{
                errorSender("Not Deleted" , null , "Table Not Deleted");
                btnResetOnAction(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnFindOnAction(ActionEvent event) {
        String tID = txtTableID.getText();

        if(tID.isEmpty()){
            errorSender("ERROR" , null , "Table ID Not Found");
            btnResetOnAction(null);
            return;
        }
        try {
            TableDto tableDto = deleteTableModel.findTable(tID);

            if(tableDto == null){
                errorSender("ERROR", null, "Table ID Not Found");
                btnResetOnAction(null);
            }else{
                txtNoOfSeat.setText(String.valueOf(tableDto.getNoOfSeat()));
                txtNoOfSeat.setEditable(false);
                SplitMenuStatus.setText(tableDto.getStatus());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtTableID.clear();
        txtNoOfSeat.clear();
        SplitMenuStatus.setText("Select Status");
    }

    @FXML
    void menuStatusBooked(ActionEvent event) {

    }

    @FXML
    void menuStatusFree(ActionEvent event) {

    }

    @FXML
    void menuStatusNotAvailable(ActionEvent event) {

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

}

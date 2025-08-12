package lk.ijse.the_thirsty_manager.Controller.TableManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.TableBO;
import lk.ijse.the_thirsty_manager.Dto.TableDto;

import java.sql.SQLException;

public class UpdateTableController {

    @FXML
    private SplitMenuButton SplitMenuStatus;

    @FXML
    private AnchorPane ancUpdateTable;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnReset;

    @FXML
    private TextField txtNoOfSeat;

    @FXML
    private TextField txtTableID;

    private TableDto tableDto = new TableDto();
    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancUpdateTable.getChildren().clear();
        ancUpdateTable.setVisible(false);
    }

    private final TableBO tableBO = BOFactory.getInstance().getBO(BOTypes.TABLE);
    @FXML
    void btnFindOnAction(ActionEvent event) {
        String tID = txtTableID.getText();

        if(tID.isEmpty()){
            errorSender("ID Not Found" , null , "Table ID Not Found");
            btnResetOnAction(null);
            return;
        }
        try {
            TableDto findDto = tableBO.searchByID(tID);

            if (findDto == null) {
                errorSender("ID Not Found" , null , "Table ID Not Found");
                btnResetOnAction(null);
            }else{
                txtNoOfSeat.setText(String.valueOf(findDto.getNoOfSeat()));
                SplitMenuStatus.setText(findDto.getStatus());
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
    void btnUpdateOnAction(ActionEvent event) {
        tableDto.setTableID(txtTableID.getText());
        tableDto.setStatus(SplitMenuStatus.getText());
        String noOfSeat = txtNoOfSeat.getText();

        int noOfSeatInt = 0;

        try{
            noOfSeatInt= Integer.parseInt(noOfSeat);

            if(noOfSeatInt <= 0){
                errorSender("ERROR" , null , "Enter Value For No of Seats");
                return;
            }
            tableDto.setNoOfSeat(noOfSeatInt);
            boolean isUpdated = tableBO.update(tableDto);

            if(isUpdated){
                infoSender("Updated" , null , "Table Update Success");
                btnResetOnAction(null);
            }else{
                errorSender("Not Updated" , null , "Table Not Updated");
                btnResetOnAction(null);
            }
        }catch (NumberFormatException e){

            errorSender("ERROR" , null , "No Of Seats Must Be Number");
            btnResetOnAction(null);

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    @FXML
    void menuStatusBooked(ActionEvent event) {
        SplitMenuStatus.setText("Booked");
        tableDto.setStatus("Booked");
    }

    @FXML
    void menuStatusFree(ActionEvent event) {
        SplitMenuStatus.setText("Free");
        tableDto.setStatus("Free");
    }

    @FXML
    void menuStatusNotAvailable(ActionEvent event) {
        SplitMenuStatus.setText("Not Available");
        tableDto.setStatus("Not Available");
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

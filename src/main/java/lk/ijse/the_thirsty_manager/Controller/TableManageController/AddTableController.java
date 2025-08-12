package lk.ijse.the_thirsty_manager.Controller.TableManageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.TableBO;
import lk.ijse.the_thirsty_manager.Dto.TableDto;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddTableController implements Initializable {

    @FXML
    private SplitMenuButton SplitMenuStatus;

    @FXML
    private AnchorPane ancAddTable;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblTableID;

    @FXML
    private TextField txtNoOfSeat;

    private TableDto tableDto = new TableDto();
    @FXML
    void btnCloseOnAction(ActionEvent event) {
        ancAddTable.getChildren().clear();
        ancAddTable.setVisible(false);
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtNoOfSeat.clear();
        SplitMenuStatus.setText("Select Status");
    }
    private final TableBO tableBO = BOFactory.getInstance().getBO(BOTypes.TABLE);
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String tableID = lblTableID.getText();
        String noOfSeat = txtNoOfSeat.getText();

        int noOfSeatInt = 0;
        try{
            noOfSeatInt = Integer.parseInt(noOfSeat);
        }catch (NumberFormatException e){
            errorSender("Wrong No of Seat" , null , "No Of Seat Must Be Numberes");
            btnResetOnAction(null);
        }

        if(noOfSeatInt <= 0) {
            errorSender("ERROR", null, "Enter Number of Seats");
            btnResetOnAction(null);
            return;
        }
        tableDto.setTableID(tableID);
        tableDto.setNoOfSeat(noOfSeatInt);

        if(tableDto.getStatus() == null){
            errorSender("ERROR" , null , "Table Status Is Empty");
            btnResetOnAction(null);
            return;
        }

        try{
            boolean isSaved = tableBO.save(tableDto);

            if(isSaved){
                infoSender("Table Saved" , null , "Table Saved Success");
                loadID();
                btnResetOnAction(null);
            }else{
                errorSender("Not Saved" , null , "Table Not Saved");
                btnResetOnAction(null);
            }

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadID();
    }

    public void loadID(){
        try {
            lblTableID.setText(tableBO.nextID());
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
}

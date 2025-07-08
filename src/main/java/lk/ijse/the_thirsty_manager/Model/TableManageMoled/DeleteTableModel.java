package lk.ijse.the_thirsty_manager.Model.TableManageMoled;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.TableDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteTableModel {
    public TableDto findTable(String tabID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from tables where table_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, tabID);

        ResultSet resultSet = statement.executeQuery();

        TableDto tableDto = new TableDto();
        if(resultSet.next()){
            tableDto.setNoOfSeat(resultSet.getInt(2));
            tableDto.setStatus(resultSet.getString(3));
            return tableDto;
        }else{
            return null;
        }
    }

    public boolean DeleteTable(String tbleID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "delete from tables where table_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , tbleID);
         int rawEffected = statement.executeUpdate();
         return rawEffected > 0;
        }
}

package lk.ijse.the_thirsty_manager.Model.TableManageMoled;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.TableDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateTableModel {
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

    public boolean updateTable(TableDto tableDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "update tables set no_of_chairs = ? , status = ? where table_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1 , tableDto.getNoOfSeat());
        statement.setString(2, tableDto.getStatus());
        statement.setString(3 , tableDto.getTableID());

        int rawEffected = statement.executeUpdate();

        return rawEffected > 0;
    }
}

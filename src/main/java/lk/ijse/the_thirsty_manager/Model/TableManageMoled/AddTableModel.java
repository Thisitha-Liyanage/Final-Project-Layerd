package lk.ijse.the_thirsty_manager.Model.TableManageMoled;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.AttendanceDto;
import lk.ijse.the_thirsty_manager.Dto.TableDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddTableModel {
    public boolean saveTable(TableDto tableDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into tables (table_id, no_of_chairs, status) values (? , ? , ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, tableDto.getTableID());
        statement.setInt(2, tableDto.getNoOfSeat());
        statement.setString(3, tableDto.getStatus());

        int rowAffected = statement.executeUpdate();
        return rowAffected > 0;
    }
    public String getNextId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select table_id from tables order by table_id desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        char tableChar = 'T';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNUmberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNUmberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableChar + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableChar + "001";
    }
}

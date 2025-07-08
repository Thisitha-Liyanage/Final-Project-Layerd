package lk.ijse.the_thirsty_manager.Model.ItemManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddItemModel {
    public boolean saveItem(ItemDto itemDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into menu_items (item_id, name, availability, description, price) value (? , ? , ? , ?  , ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, itemDto.getItemID());
        statement.setString(2, itemDto.getItemName());
        statement.setString(3, itemDto.getAvailability());
        statement.setString(4, itemDto.getDescription());
        statement.setDouble(5, itemDto.getPrice());

        int rowAffected = statement.executeUpdate();
        return rowAffected > 0;
    }

    public String getNextId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select item_id from menu_items order by item_id desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        char tableChar = 'I';
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

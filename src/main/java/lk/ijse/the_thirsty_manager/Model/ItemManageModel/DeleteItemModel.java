package lk.ijse.the_thirsty_manager.Model.ItemManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DeleteItemModel {
    private ItemDto itemDto = new ItemDto();
    public ItemDto findItem(String itemID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String que = "select * from menu_items where item_id = ?";
        PreparedStatement statement = connection.prepareStatement(que);
        statement.setString(1, itemID);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            itemDto.setItemName(resultSet.getString(2));
            itemDto.setDescription(resultSet.getString(4));
            itemDto.setAvailability(resultSet.getString(3));
            itemDto.setPrice(resultSet.getDouble(5));
            return itemDto;

        } else {
            return null;
        }
    }

    public boolean deleteItem(String itemID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "delete  from menu_items where item_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , itemID);

        int rawEffected = statement.executeUpdate();
        return rawEffected > 0;
    }
}

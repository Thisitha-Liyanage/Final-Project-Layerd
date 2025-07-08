package lk.ijse.the_thirsty_manager.Model.ItemManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateItemModel {

    private ItemDto itemDto = new ItemDto();
    public boolean updateItem(String itemName , String itemDes , String availability , double price , String itemID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "update menu_items set name = ? , description = ? , availability = ? , price = ? where item_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , itemName);
        statement.setString(2 , itemDes);
        statement.setString(3 , availability);
        statement.setDouble(4 , price);
        statement.setString(5 , itemID);

        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    }

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
}

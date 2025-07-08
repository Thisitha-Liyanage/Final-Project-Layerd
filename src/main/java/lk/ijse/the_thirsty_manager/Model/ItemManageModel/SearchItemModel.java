package lk.ijse.the_thirsty_manager.Model.ItemManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchItemModel {
    public ItemDto searchItem(String itemID) throws SQLException {
            ItemDto itemDto = new ItemDto();
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "select * from menu_items where item_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1 , itemID);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                itemDto.setItemID(resultSet.getString(1));
                itemDto.setItemName(resultSet.getString(2));
                itemDto.setAvailability(resultSet.getString(3));
                itemDto.setPrice((resultSet.getDouble(5)));
                itemDto.setDescription(resultSet.getString(4));
                return itemDto;

            } else {
                return null;
            }
    }
}

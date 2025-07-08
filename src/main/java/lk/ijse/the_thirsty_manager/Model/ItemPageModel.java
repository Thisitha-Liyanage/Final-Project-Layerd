package lk.ijse.the_thirsty_manager.Model;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemPageModel {
    public ArrayList<ItemDto> getAllItems() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from menu_items");
        ResultSet resultSet = pst.executeQuery();

        ArrayList<ItemDto> list = new ArrayList<>();

        while (resultSet.next()) {
            ItemDto itemDto = new ItemDto(
                    resultSet.getString("item_id"),
                    resultSet.getString("name"),
                    resultSet.getString("availability"),
                    resultSet.getDouble("price"),
                    resultSet.getString("description")
            );

            list.add(itemDto);
        }
        return list;
    }
}

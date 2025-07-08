package lk.ijse.the_thirsty_manager.Model;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.InventoryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryPageModel {
    public ArrayList<InventoryDto> getAllStock() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM inventory";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet resultSet = pst.executeQuery();

        ArrayList<InventoryDto> list = new ArrayList<>();

        while (resultSet.next()) {
            InventoryDto inventoryDto = new InventoryDto(
                    resultSet.getString("inventory_id"),
                    resultSet.getString("name"),
                    resultSet.getString("unit"),
                    resultSet.getDouble("current_stock"),
                    resultSet.getString("supplier_id"),
                    resultSet.getDouble("reorder_level"),
                    resultSet.getString("expiration_date")
            );
            list.add(inventoryDto);
        }

        return list;
    }
}

package lk.ijse.the_thirsty_manager.Model.InventoryManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.InventoryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteInventoryModel {
    private InventoryDto inventoryDto = new InventoryDto();
    public InventoryDto findInv(String inventoryID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String que = "select * from inventory where inventory_id = ?";
        PreparedStatement statement = connection.prepareStatement(que);
        statement.setString(1, inventoryID);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            inventoryDto.setInvname(resultSet.getString(2));
            inventoryDto.setUnit(resultSet.getString(3));
            inventoryDto.setCurrentStock((resultSet.getDouble(4)));
            inventoryDto.setSupplierID(resultSet.getString(5));
            inventoryDto.setReOrderLevel(resultSet.getDouble(6));
            inventoryDto.setExpDate(resultSet.getString(7));
            return inventoryDto;

        } else {
            return null;
        }
    }

    public boolean deleteInv(String invID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "delete  from inventory where inventory_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 ,invID);

        int rawEffected = statement.executeUpdate();
        return rawEffected > 0;
    }
}

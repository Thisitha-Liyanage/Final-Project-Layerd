package lk.ijse.the_thirsty_manager.Model.InventoryManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.InventoryDto;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddInventoryModel {
    public boolean findSupplier(String supID) throws SQLException {
        boolean isFound = false;
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select supplier.supplier_id from supplier where supplier_id = ?";
        PreparedStatement statement  = connection.prepareStatement(sql);
        statement.setString(1 , supID);

        ResultSet result = statement.executeQuery();

        String supIDF = null;
        while (result.next()){
            supIDF = result.getString("supplier_id");
        }

        if(supID.equals(supIDF) ){
            isFound = true;
        }

        return isFound;
    }


    public boolean addInventory(InventoryDto inventoryDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into inventory (inventory_id, name, unit, current_stock, supplier_id, reorder_level, expiration_date) value (? , ? , ? , ? , ? , ? , ? )";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, inventoryDto.getInventoryID());
        statement.setString(2, inventoryDto.getInvname());
        statement.setString(3, inventoryDto.getUnit());
        statement.setDouble(4, inventoryDto.getCurrentStock());
        statement.setString(5, inventoryDto.getSupplierID());
        statement.setDouble(6 , inventoryDto.getReOrderLevel());
        statement.setString(7 , inventoryDto.getExpDate());

        int rowAffected = statement.executeUpdate();
        return rowAffected > 0;
    }

    public String getNextId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select inventory_id from inventory order by inventory_id desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        String tableString  = "IN";
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNUmberString = lastId.substring(2);
            int lastIdNumber = Integer.parseInt(lastIdNUmberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableString + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableString + "001";
    }
}

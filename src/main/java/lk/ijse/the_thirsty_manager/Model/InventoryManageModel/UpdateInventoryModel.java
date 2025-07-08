package lk.ijse.the_thirsty_manager.Model.InventoryManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.InventoryDto;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateInventoryModel {
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

    public boolean updateInv(InventoryDto updateDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "update inventory set name = ? , unit = ? , current_stock  = ? , supplier_id = ? , reorder_level = ? , expiration_date = ? where inventory_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , updateDto.getInvname());
        statement.setString(2 , updateDto.getUnit());
        statement.setDouble(3 , updateDto.getCurrentStock());
        statement.setString(4 , updateDto.getSupplierID());
        statement.setDouble(5 , updateDto.getReOrderLevel());
        statement.setString(6 , updateDto.getExpDate());
        statement.setString(7 , updateDto.getInventoryID());


        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    }

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
}

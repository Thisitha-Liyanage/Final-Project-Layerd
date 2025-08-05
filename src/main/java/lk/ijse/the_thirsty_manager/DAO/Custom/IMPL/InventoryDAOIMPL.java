package lk.ijse.the_thirsty_manager.DAO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.DAO.Custom.InventoryDAO;
import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Entity.InventoryEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventoryDAOIMPL implements InventoryDAO {
    @Override
    public boolean update(InventoryEntity inventoryEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "update inventory set name = ? , unit = ? , current_stock  = ? , supplier_id = ? , reorder_level = ? , expiration_date = ? where inventory_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , inventoryEntity.getInvname());
        statement.setString(2 , inventoryEntity.getUnit());
        statement.setDouble(3 , inventoryEntity.getCurrentStock());
        statement.setString(4 , inventoryEntity.getSupplierID());
        statement.setDouble(5 , inventoryEntity.getReOrderLevel());
        statement.setString(6 , inventoryEntity.getExpDate());
        statement.setString(7 , inventoryEntity.getInventoryID());


        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    }

    @Override
    public boolean delete(String ID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "delete  from inventory where inventory_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 ,ID);

        int rawEffected = statement.executeUpdate();
        return rawEffected > 0;
    }

    @Override
    public String findSup(String ID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select supplier.supplier_id from supplier where supplier_id = ?";
        PreparedStatement statement  = connection.prepareStatement(sql);
        statement.setString(1 , ID);

        ResultSet result = statement.executeQuery();

        if (result.next()){
            return result.getString(1);
        }
        return null;
    }

    @Override
    public boolean save(InventoryEntity inventoryEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into inventory (inventory_id, name, unit, current_stock, supplier_id, reorder_level, expiration_date) value (? , ? , ? , ? , ? , ? , ? )";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, inventoryEntity.getInventoryID());
        statement.setString(2, inventoryEntity.getInvname());
        statement.setString(3, inventoryEntity.getUnit());
        statement.setDouble(4, inventoryEntity.getCurrentStock());
        statement.setString(5, inventoryEntity.getSupplierID());
        statement.setDouble(6 , inventoryEntity.getReOrderLevel());
        statement.setString(7 , inventoryEntity.getExpDate());

        int rowAffected = statement.executeUpdate();
        return rowAffected > 0;
    }

    @Override
    public Optional<InventoryEntity> searchByID(String s) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String que = "select * from inventory where inventory_id = ?";
        PreparedStatement statement = connection.prepareStatement(que);
        statement.setString(1, s);
        InventoryEntity inventoryEntity = new InventoryEntity();
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            inventoryEntity.setInvname(resultSet.getString(2));
            inventoryEntity.setUnit(resultSet.getString(3));
            inventoryEntity.setCurrentStock((resultSet.getDouble(4)));
            inventoryEntity.setSupplierID(resultSet.getString(5));
            inventoryEntity.setReOrderLevel(resultSet.getDouble(6));
            inventoryEntity.setExpDate(resultSet.getString(7));
            return Optional.of(inventoryEntity);

        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<InventoryEntity> getAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM inventory";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet resultSet = pst.executeQuery();

        List<InventoryEntity> list = new ArrayList<>();

        while (resultSet.next()) {
            InventoryEntity inventoryEntity = new InventoryEntity(
                    resultSet.getString("inventory_id"),
                    resultSet.getString("name"),
                    resultSet.getString("unit"),
                    resultSet.getDouble("current_stock"),
                    resultSet.getString("supplier_id"),
                    resultSet.getDouble("reorder_level"),
                    resultSet.getString("expiration_date")
            );
            list.add(inventoryEntity);
        }

        return list;
    }

    @Override
    public String getNextID() throws SQLException {
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
            return String.format(tableString + "%03d", nextIdNumber);
        }
        return tableString + "001";
    }
}

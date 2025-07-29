package lk.ijse.the_thirsty_manager.DAO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.DAO.Custom.ItemDAO;
import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;
import lk.ijse.the_thirsty_manager.Entity.AttendanceEntity;
import lk.ijse.the_thirsty_manager.Entity.ItemEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDAOIMPL implements ItemDAO {
    @Override
    public boolean update(ItemEntity itemEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "update menu_items set name = ? , description = ? , availability = ? , price = ? where item_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , itemEntity.getItemName());
        statement.setString(2 , itemEntity.getDescription());
        statement.setString(3 , itemEntity.getAvailability());
        statement.setDouble(4 , itemEntity.getPrice());
        statement.setString(5 , itemEntity.getItemID());

        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    }

    @Override
    public boolean delete(String ID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "delete  from menu_items where item_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , ID);

        int rawEffected = statement.executeUpdate();
        return rawEffected > 0;
    }

    @Override
    public boolean save(ItemEntity itemEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into menu_items (item_id, name, availability, description, price) value (? , ? , ? , ?  , ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, itemEntity.getItemID());
        statement.setString(2, itemEntity.getItemName());
        statement.setString(3, itemEntity.getAvailability());
        statement.setString(4, itemEntity.getDescription());
        statement.setDouble(5, itemEntity.getPrice());

        int rowAffected = statement.executeUpdate();
        return rowAffected > 0;
    }

    @Override
    public Optional<ItemEntity> searchByID(String s) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String que = "select * from menu_items where item_id = ?";
        PreparedStatement statement = connection.prepareStatement(que);
        statement.setString(1, s);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(new ItemEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(5),
                    resultSet.getString(4)
            ));
        }
        return Optional.empty();
    }

    @Override
    public List<ItemEntity> getAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from menu_items");
        ResultSet resultSet = pst.executeQuery();

        List<ItemEntity> list = new ArrayList<>();

        while (resultSet.next()) {
            ItemEntity itemEntity = new ItemEntity(
                    resultSet.getString("item_id"),
                    resultSet.getString("name"),
                    resultSet.getString("availability"),
                    resultSet.getDouble("price"),
                    resultSet.getString("description")
            );

            list.add(itemEntity);
        }
        return list;
    }

    @Override
    public String getNextID() throws SQLException {
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


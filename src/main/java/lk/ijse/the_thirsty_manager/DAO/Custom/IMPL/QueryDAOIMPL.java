package lk.ijse.the_thirsty_manager.DAO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.DAO.Custom.QueryDAO;
import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Entity.CustomOrderEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class QueryDAOIMPL implements QueryDAO {
    @Override
    public boolean updateStock(CustomOrderEntity customOrderEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql =
                "UPDATE inventory i " +
                        "JOIN ingredients ing ON i.inventory_id = ing.inventory_id " +
                        "SET i.current_stock = i.current_stock - (ing.`liquid-quantity` * ?) " +
                        "WHERE ing.menu_item_id = ?";


        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1 ,customOrderEntity.getQuantity());
        stm.setString(2,customOrderEntity.getItemID());

        int rawEffected = stm.executeUpdate();
        return rawEffected > 0;
    }

    @Override
    public boolean save(CustomOrderEntity customOrderEntity) throws SQLException {
        return false;
    }

    @Override
    public Optional<CustomOrderEntity> searchByID(String s) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<CustomOrderEntity> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String getNextID() throws SQLException {
        return "";
    }
}

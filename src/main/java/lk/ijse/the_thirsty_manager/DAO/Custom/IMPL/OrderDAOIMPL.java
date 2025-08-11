package lk.ijse.the_thirsty_manager.DAO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.DAO.Custom.OrderDAO;
import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;
import lk.ijse.the_thirsty_manager.Dto.OrderDto;
import lk.ijse.the_thirsty_manager.Entity.CustomOrderEntity;
import lk.ijse.the_thirsty_manager.Entity.EmployeeEntity;
import lk.ijse.the_thirsty_manager.Entity.ItemEntity;
import lk.ijse.the_thirsty_manager.Entity.OrderEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SimpleTimeZone;

public class OrderDAOIMPL implements OrderDAO {
    @Override
    public ItemEntity findItem(String ID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String foundItem = "select menu_items.name , menu_items.availability , price from menu_items where item_id = ?";
        PreparedStatement stm = connection.prepareStatement(foundItem);
        stm.setString(1 , ID);
        ResultSet resultSet = stm.executeQuery();

        ItemEntity itemEntity = new ItemEntity();
        if(resultSet.next()){
            itemEntity.setItemName(resultSet.getString(1));
            itemEntity.setAvailability(resultSet.getString(2));
            itemEntity.setPrice(resultSet.getDouble(3));
            return itemEntity;
        }

        return null;
    }

    @Override
    public String findCustomer(String ID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select customer.customer_id from customer where customer_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);

        stm.setString(1 , ID);

        ResultSet resultSet = stm.executeQuery();


        if(resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean placeOrder(CustomOrderEntity customOrderEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into orders (order_id, customer_id, total_amount, date) values (?, ?, ?, ?)";
        PreparedStatement stm = connection.prepareStatement(sql);

        stm.setString(1 ,customOrderEntity.getOrderID());
        stm.setString(2, customOrderEntity.getCustomerID());
        stm.setDouble(3,(customOrderEntity.getTotalAmount()));
        stm.setString(4,customOrderEntity.getDate());

        int rawEffected = stm.executeUpdate();
        return rawEffected > 0;
    }

    @Override
    public boolean save(OrderEntity orderEntity) throws SQLException {
        return false;
    }

    @Override
    public Optional<OrderEntity> searchByID(String s) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<OrderEntity> getAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from orders");
        ResultSet resultSet = pst.executeQuery();

        List<OrderEntity> list = new ArrayList<>();

        while (resultSet.next()) {
            OrderEntity orderEntity= new OrderEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );

            list.add(orderEntity);
        }

        return list;
    }

    @Override
    public String getNextID() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select order_id from orders order by order_id desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        char tableChar = 'O';
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

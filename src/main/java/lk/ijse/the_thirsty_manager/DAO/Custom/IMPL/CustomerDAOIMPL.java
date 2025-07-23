package lk.ijse.the_thirsty_manager.DAO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.DAO.Custom.CustomerDAO;
import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOIMPL implements CustomerDAO{
    @Override
    public boolean save(CustomerEntity customerEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO customer(customer_id, customer_name, customer_address, customer_contact, customer_age) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, customerEntity.getCustomerID());
        statement.setString(2, customerEntity.getCustomerName());
        statement.setString(3, customerEntity.getAddress());
        statement.setString(4, customerEntity.getContact());
        statement.setInt(5, customerEntity.getAge());

        int rowAffected = statement.executeUpdate();
        System.out.println(rowAffected);
        return rowAffected > 0;
    }

    @Override
    public boolean update(CustomerEntity customerEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "update customer set customer_name = ? , customer_address = ? , customer_contact = ? , customer_age = ? where customer_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , customerEntity.getCustomerName());
        statement.setString(2 , customerEntity.getAddress());
        statement.setString(3 , customerEntity.getContact());
        statement.setInt(4 , customerEntity.getAge());
        statement.setString(5 , customerEntity.getCustomerID());

        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    }

    @Override
    public boolean delete(String ID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "delete from customer where customer_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , ID);

        int rawEffected = statement.executeUpdate();
        return rawEffected > 0;
    }

    @Override
    public Optional<CustomerEntity> searchByID(String ID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from customer where customer_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , ID);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(new CustomerEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            ));
        }
        return Optional.empty();

    }

    @Override
    public List<CustomerEntity> getAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from customer");
        ResultSet resultSet = pst.executeQuery();

        List<CustomerEntity> list = new ArrayList<>();

        while (resultSet.next()) {
            CustomerEntity customerEntity = new CustomerEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );

            list.add(customerEntity);
        }
        return list;
    }

    @Override
    public String getNextID() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select customer_id from customer order by customer_id desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        char tableChar = 'C';
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

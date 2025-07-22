package lk.ijse.the_thirsty_manager.DAO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.DAO.Custom.CustomerDAO;
import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return rowAffected > 0;
    }

    @Override
    public boolean update(CustomerEntity customerEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String ID) throws SQLException {
        return false;
    }

    @Override
    public Optional<CustomerEntity> searchByID(String s) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<CustomerEntity> getAll() throws SQLException {
        return List.of();
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

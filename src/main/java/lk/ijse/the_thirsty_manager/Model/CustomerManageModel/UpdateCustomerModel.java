package lk.ijse.the_thirsty_manager.Model.CustomerManageModel;


import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateCustomerModel {
    private final CustomerDto customerDto = new CustomerDto();

    public boolean updateCustomer(String cusName , String address , String contact , int age , String cusID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "update customer set customer_name = ? , customer_address = ? , customer_contact = ? , customer_age = ? where customer_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , cusName);
        statement.setString(2 , address);
        statement.setString(3 , contact);
        statement.setInt(4 , age);
        statement.setString(5 , cusID);

        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    }


    public CustomerDto  findCustomer(String cusID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String que = "SELECT * FROM customer WHERE customer_id = ?";
        PreparedStatement statement = connection.prepareStatement(que);
        statement.setString(1, cusID);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            customerDto.setCustomerName(resultSet.getString(2));
            customerDto.setAddress(resultSet.getString(3));
            customerDto.setContact(resultSet.getString(4));
            customerDto.setAge(resultSet.getInt(5));
            return customerDto;

        } else {
            return null;
        }
    }
}

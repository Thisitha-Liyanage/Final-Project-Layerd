package lk.ijse.the_thirsty_manager.Model.CustomerManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchCustomerModel {
    private CustomerDto customerDto = new CustomerDto();
    public CustomerDto searchCustomer(String cusID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from customer where customer_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , cusID);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            customerDto.setCustomerID(resultSet.getString(1));
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

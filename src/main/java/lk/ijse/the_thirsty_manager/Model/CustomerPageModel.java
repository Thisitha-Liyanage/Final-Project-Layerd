package lk.ijse.the_thirsty_manager.Model;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerPageModel {
    public ArrayList<CustomerDto> getAllCustomer() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from customer");
        ResultSet resultSet = pst.executeQuery();

        ArrayList<CustomerDto> list = new ArrayList<>();

        while (resultSet.next()) {
            CustomerDto customerDTO = new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );

            list.add(customerDTO);
        }
        return list;
    }
}

package lk.ijse.the_thirsty_manager.Model;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.PaymentDto;
import lk.ijse.the_thirsty_manager.Dto.SalaryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentModel {

    public ArrayList<PaymentDto> getAllPayments() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from payment");
        ResultSet resultSet = pst.executeQuery();

        ArrayList<PaymentDto> list = new ArrayList<>();

        while (resultSet.next()) {
            PaymentDto paymentDto = new PaymentDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );

            list.add(paymentDto);
        }
        return list;
    }
}

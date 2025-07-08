package lk.ijse.the_thirsty_manager.Model.PaymentManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.PaymentDto;
import lk.ijse.the_thirsty_manager.Dto.SalaryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchPaymentModel {
    public PaymentDto findPayment(PaymentDto paymentDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String que = "select * from payment where payment_id = ?";
        PreparedStatement statement = connection.prepareStatement(que);
        statement.setString(1, paymentDto.getPaymentID());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            paymentDto.setPaymentMethod(resultSet.getString(2));
            paymentDto.setTotalAmount(resultSet.getDouble(3));
            paymentDto.setOrderID(resultSet.getString(4));
            paymentDto.setDate(resultSet.getString(5));

            return paymentDto;

        } else {
            return null;
        }
    }
}

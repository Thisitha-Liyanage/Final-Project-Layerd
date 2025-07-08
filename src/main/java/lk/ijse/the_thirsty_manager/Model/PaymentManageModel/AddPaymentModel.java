package lk.ijse.the_thirsty_manager.Model.PaymentManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;
import lk.ijse.the_thirsty_manager.Dto.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddPaymentModel {

    public boolean savePayment(PaymentDto paymentDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into payment (payment_id, payment_method, total_amount, order_id,date) values ( ? , ? , ? , ? , ? )";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, paymentDto.getPaymentID());
        statement.setString(2, paymentDto.getPaymentMethod());
        statement.setDouble(3, paymentDto.getTotalAmount());
        statement.setString(4, paymentDto.getOrderID());
        statement.setString(5 , paymentDto.getDate());

        int rowAffected = statement.executeUpdate();
        return rowAffected > 0;
    }

    public String getNextId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select payment_id from payment order by payment_id desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        char tableChar = 'P';
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
    private PaymentDto paymentDto = new PaymentDto();
    public PaymentDto findOrder(String orderID) throws SQLException {
        String sql = "select orders.order_id , total_amount from orders where order_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , orderID);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
           paymentDto.setOrderID(resultSet.getString("order_id"));
           paymentDto.setTotalAmount(resultSet.getDouble("total_amount"));
        }


        if(orderID.equals(paymentDto.getOrderID())){
            return paymentDto;
        }
        return null;
    }

    public boolean checkDuplicateOrder(String oID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select payment.order_id from payment where order_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1 , oID);
        ResultSet resultSet = statement.executeQuery();
        String findOid;

        if(resultSet.next()){
            findOid = resultSet.getString("order_id");
            System.out.println(findOid);
            return findOid.equals(oID);

        }
        return false;
    }
}

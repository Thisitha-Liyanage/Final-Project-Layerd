package lk.ijse.the_thirsty_manager.DAO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.DAO.Custom.PaymentDAO;
import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.PaymentDto;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;
import lk.ijse.the_thirsty_manager.Entity.PaymentEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDAOIMPL implements PaymentDAO {
    @Override
    public boolean save(PaymentEntity paymentEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into payment (payment_id, payment_method, total_amount, order_id,date) values ( ? , ? , ? , ? , ? )";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, paymentEntity.getPaymentID());
        statement.setString(2, paymentEntity.getPaymentMethod());
        statement.setDouble(3, paymentEntity.getTotalAmount());
        statement.setString(4, paymentEntity.getOrderID());
        statement.setString(5 ,paymentEntity.getDate());

        int rowAffected = statement.executeUpdate();
        return rowAffected > 0;
    }

    @Override
    public Optional<PaymentEntity> searchByID(String s) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String que = "select * from payment where payment_id = ?";
        PreparedStatement statement = connection.prepareStatement(que);
        statement.setString(1, s);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(new PaymentEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return Optional.empty();
    }

    @Override
    public List<PaymentEntity> getAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from payment");
        ResultSet resultSet = pst.executeQuery();

        List<PaymentEntity> list = new ArrayList<>();

        while (resultSet.next()) {
            PaymentEntity paymentEntity = new PaymentEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );

            list.add(paymentEntity);
        }
        return list;
    }

    @Override
    public String getNextID() throws SQLException {
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

    @Override
    public PaymentEntity findOrder(String ID) throws SQLException {
        String sql = "select orders.order_id , total_amount from orders where order_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , ID);

        ResultSet resultSet = statement.executeQuery();
        PaymentEntity paymentEntity = new PaymentEntity();
        while (resultSet.next()){
            paymentEntity.setOrderID(resultSet.getString("order_id"));
            paymentEntity.setTotalAmount(resultSet.getDouble("total_amount"));
            return paymentEntity;
        }

        return null;
    }

    @Override
    public String duplicate(String ID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select payment.order_id from payment where order_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1 , ID);
        ResultSet resultSet = statement.executeQuery();
        String findOid;

        if(resultSet.next()){
            findOid = resultSet.getString("order_id");
            System.out.println(findOid);
            return findOid;

        }
        return null;

    }
}

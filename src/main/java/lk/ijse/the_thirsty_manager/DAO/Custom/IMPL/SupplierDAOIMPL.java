package lk.ijse.the_thirsty_manager.DAO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.DAO.Custom.SupplierDAO;
import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.SupplierDto;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;
import lk.ijse.the_thirsty_manager.Entity.SupplierEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierDAOIMPL implements SupplierDAO {
    @Override
    public boolean update(SupplierEntity supplierEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "update supplier set name = ?, address = ? , contact = ? where supplier_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , supplierEntity.getName());
        statement.setString(3, supplierEntity.getContact());
        statement.setString(2 , supplierEntity.getAddress());
        statement.setString(4 , supplierEntity.getSupID());

        int rawEffected = statement.executeUpdate();

        return rawEffected > 0;
    }

    @Override
    public boolean delete(String ID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "delete  from supplier where supplier_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , ID);

        int rawEffected = statement.executeUpdate();
        return rawEffected > 0;
    }

    @Override
    public boolean save(SupplierEntity supplierEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into supplier (supplier_id, name, contact, address) values (? , ? , ? , ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, supplierEntity.getSupID());
        statement.setString(2 , supplierEntity.getName());
        statement.setString(3 , supplierEntity.getContact());
        statement.setString(4 , supplierEntity.getAddress());

        int rawEffected = statement.executeUpdate();
        return rawEffected > 0;
    }

    @Override
    public Optional<SupplierEntity> searchByID(String s) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from supplier where supplier_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, s);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return Optional.of(new SupplierEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return Optional.empty();
    }

    @Override
    public List<SupplierEntity> getAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from supplier");
        ResultSet resultSet = pst.executeQuery();

        List<SupplierEntity> list = new ArrayList<>();

        while (resultSet.next()) {
            SupplierEntity supplierEntity = new SupplierEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );

            list.add(supplierEntity);
        }
        return list;
    }

    @Override
    public String getNextID() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select supplier_id from supplier order by supplier_id desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        char tableChar = 'S';
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

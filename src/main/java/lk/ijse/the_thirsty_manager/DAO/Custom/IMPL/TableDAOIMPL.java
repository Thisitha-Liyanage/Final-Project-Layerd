package lk.ijse.the_thirsty_manager.DAO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.DAO.Custom.TableDAO;
import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.TableDto;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;
import lk.ijse.the_thirsty_manager.Entity.TableEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TableDAOIMPL implements TableDAO {
    @Override
    public boolean update(TableEntity tableEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "update tables set no_of_chairs = ? , status = ? where table_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1 , tableEntity.getNoOfSeat());
        statement.setString(2, tableEntity.getStatus());
        statement.setString(3 , tableEntity.getTableID());

        int rawEffected = statement.executeUpdate();

        return rawEffected > 0;
    }

    @Override
    public boolean delete(String ID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "delete from tables where table_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , ID);
        int rawEffected = statement.executeUpdate();
        return rawEffected > 0;
    }

    @Override
    public boolean save(TableEntity tableEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into tables (table_id, no_of_chairs, status) values (? , ? , ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, tableEntity.getTableID());
        statement.setInt(2, tableEntity.getNoOfSeat());
        statement.setString(3, tableEntity.getStatus());

        int rowAffected = statement.executeUpdate();
        return rowAffected > 0;
    }

    @Override
    public Optional<TableEntity> searchByID(String s) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from tables where table_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, s);

        ResultSet resultSet = statement.executeQuery();

        TableEntity tableEntity = new TableEntity();
        if(resultSet.next()){
            tableEntity.setNoOfSeat(resultSet.getInt(2));
            tableEntity.setStatus(resultSet.getString(3));
            return Optional.of(tableEntity);
        }
        return Optional.empty();
    }

    @Override
    public List<TableEntity> getAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from tables");
        ResultSet resultSet = pst.executeQuery();

        List<TableEntity> list = new ArrayList<>();

        while (resultSet.next()) {
            TableEntity tableEntity = new TableEntity(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getString(3)
            );

            list.add(tableEntity);
        }
        return list;
    }

    @Override
    public String getNextID() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select table_id from tables order by table_id desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        char tableChar = 'T';
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

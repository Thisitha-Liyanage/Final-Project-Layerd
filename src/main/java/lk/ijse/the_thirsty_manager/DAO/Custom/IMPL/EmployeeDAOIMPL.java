package lk.ijse.the_thirsty_manager.DAO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.DAO.Custom.EmployeeDAO;
import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.EmployeeDto;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;
import lk.ijse.the_thirsty_manager.Entity.EmployeeEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOIMPL implements EmployeeDAO {
    @Override
    public boolean update(EmployeeEntity employeeEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "update employee set employee_name = ? , contact = ? , role = ? , salary_per_day = ? where employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , employeeEntity.getName());
        statement.setString(2, employeeEntity.getContact());
        statement.setString(3 , employeeEntity.getRole());
        statement.setDouble(4 , employeeEntity.getSPD());
        statement.setString(5 , employeeEntity.getEmployeeID());

        int rawEffected = statement.executeUpdate();

        return rawEffected > 0;
    }

    @Override
    public boolean delete(String ID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "delete  from employee where employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , ID);

        int rawEffected = statement.executeUpdate();
        return rawEffected > 0;
    }

    @Override
    public boolean save(EmployeeEntity employeeEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into employee values (? , ? , ? , ? , ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , employeeEntity.getEmployeeID());
        statement.setString(2 , employeeEntity.getName());
        statement.setString(3 , employeeEntity.getRole());
        statement.setString(4 , employeeEntity.getContact());
        statement.setDouble(5 , employeeEntity.getSPD());

        int rawEffected = statement.executeUpdate();
        return rawEffected > 0;
    }

    @Override
    public Optional<EmployeeEntity> searchByID(String ID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from employee where employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, ID);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return Optional.of(new EmployeeEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            ));
        }
        return Optional.empty();

    }

    @Override
    public List<EmployeeEntity> getAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from employee");
        ResultSet resultSet = pst.executeQuery();

        List<EmployeeEntity> list = new ArrayList<>();

        while (resultSet.next()) {
            EmployeeEntity employeeEntity= new EmployeeEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            );

            list.add(employeeEntity);
        }

        return list;
    }

    @Override
    public String getNextID() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select employee_id from employee order by employee_id desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        char tableChar = 'E';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNUmberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNUmberString);
            int nextIdNumber = lastIdNumber + 1;
            return String.format(tableChar + "%03d", nextIdNumber);
        }
        return tableChar + "001";
    }
}


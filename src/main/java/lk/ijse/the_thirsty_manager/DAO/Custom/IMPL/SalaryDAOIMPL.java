package lk.ijse.the_thirsty_manager.DAO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.DAO.Custom.SalaryDAO;
import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.SalaryDto;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;
import lk.ijse.the_thirsty_manager.Entity.SalaryEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalaryDAOIMPL implements SalaryDAO {
    @Override
    public boolean save(SalaryEntity salaryEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into salary (salary_id, employee_id, amount, date) VALUES (? , ? , ? , ? )";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, salaryEntity.getSalaryID());
        statement.setString(2 , salaryEntity.getEmpID());
        statement.setDouble(3 , salaryEntity.getAmount());
        statement.setString(4 , salaryEntity.getDate());

        int rawEffected = statement.executeUpdate();
        return rawEffected > 0;
    }

    @Override
    public Optional<SalaryEntity> searchByID(String s) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String que = "select * from salary where salary_id = ?";
        PreparedStatement statement = connection.prepareStatement(que);
        statement.setString(1, s);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return Optional.of(new SalaryEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4)
            ));
        }
        return Optional.empty();
    }


    @Override
    public List<SalaryEntity> getAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from salary");
        ResultSet resultSet = pst.executeQuery();

        List<SalaryEntity> list = new ArrayList<>();

        while (resultSet.next()) {
            SalaryEntity salaryEntity = new SalaryEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4)
            );

            list.add(salaryEntity);
        }
        return list;
    }

    @Override
    public String getNextID() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT salary_id FROM salary ORDER BY salary_id DESC LIMIT 1";
        PreparedStatement pst = connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        String tableChar = "SL";

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);

            if (lastId.startsWith(tableChar)) {
                String numericPart = lastId.substring(tableChar.length());
                int lastIdNumber = Integer.parseInt(numericPart);
                int nextIdNumber = lastIdNumber + 1;
                return String.format(tableChar + "%03d", nextIdNumber);
            } else {
                return tableChar + "001";
            }
        }
        return tableChar + "001";
    }

    @Override
    public SalaryEntity findEmp(String ID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT salary_per_day FROM employee WHERE employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, ID);

        ResultSet resultSet = statement.executeQuery();
        SalaryEntity salaryEntity = new SalaryEntity();
        if (resultSet.next()) {
            double amount = resultSet.getDouble("salary_per_day");
            salaryEntity.setAmount(amount);
            return salaryEntity;
        }
        return null;
    }
}

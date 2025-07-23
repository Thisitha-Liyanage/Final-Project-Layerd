package lk.ijse.the_thirsty_manager.DAO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.DAO.CrudDAO;
import lk.ijse.the_thirsty_manager.DAO.Custom.AttendanceDAO;
import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.AttendanceDto;
import lk.ijse.the_thirsty_manager.Entity.AttendanceEntity;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AttendanceDAOIMPL implements AttendanceDAO {
    @Override
    public boolean save(AttendanceEntity attendanceEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into attendance (attendance_id, date, employee_id, status) values (? , ? ,? , ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, attendanceEntity.getAttenID());
        statement.setString(2, attendanceEntity.getDate());
        statement.setString(3, attendanceEntity.getEmpID());
        statement.setString(4, attendanceEntity.getStatus());

        int rowAffected = statement.executeUpdate();
        return rowAffected > 0;
    }

    @Override
    public Optional<AttendanceEntity> searchByID(String ID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from attendance where attendance_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , ID);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(new AttendanceEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return Optional.empty();
    }

    @Override
    public List getAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from attendance");
        ResultSet resultSet = pst.executeQuery();

        List<AttendanceEntity> list = new ArrayList<>();

        while (resultSet.next()) {
            AttendanceEntity attendanceEntity = new AttendanceEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );

            list.add(attendanceEntity);
        }
        return list;
    }

    @Override
    public String getNextID() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select attendance_id from attendance order by attendance_id desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        char tableChar = 'A';
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
    public String findEmployee(String empID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select employee_id from employee where employee_id = ?";
        PreparedStatement statement  = connection.prepareStatement(sql);
        statement.setString(1 , empID);

        ResultSet result = statement.executeQuery();

        String empIDF = null;
        while (result.next()){
            empIDF = result.getString("employee_id");
        }

        return empIDF;
    }
}


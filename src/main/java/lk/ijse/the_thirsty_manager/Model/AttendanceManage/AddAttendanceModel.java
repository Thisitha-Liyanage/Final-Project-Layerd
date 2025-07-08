package lk.ijse.the_thirsty_manager.Model.AttendanceManage;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.AttendanceDto;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddAttendanceModel {
    public boolean saveAttendance(AttendanceDto attendanceDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into attendance (attendance_id, date, employee_id, status) values (? , ? ,? , ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, attendanceDto.getAttenID());
        statement.setString(2, attendanceDto.getDate());
        statement.setString(3, attendanceDto.getEmpID());
        statement.setString(4, attendanceDto.getStatus());

        int rowAffected = statement.executeUpdate();
        return rowAffected > 0;
    }

    public String getNextId() throws SQLException {
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

    public boolean findEmployee(String empID) throws SQLException {
        boolean isFound = false;
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select employee_id from employee where employee_id = ?";
        PreparedStatement statement  = connection.prepareStatement(sql);
        statement.setString(1 , empID);

        ResultSet result = statement.executeQuery();

        String empIDF = null;
        while (result.next()){
            empIDF = result.getString("employee_id");
        }

        if(empID.equals(empIDF) ){
            isFound = true;
        }

        return isFound;
    }
}

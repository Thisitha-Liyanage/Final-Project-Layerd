package lk.ijse.the_thirsty_manager.Model.AttendanceManage;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.AttendanceDto;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchAttendanceModel {
    private AttendanceDto attendanceDto = new AttendanceDto();
    public AttendanceDto searchAttend(String attID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from attendance where attendance_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , attID);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            attendanceDto.setAttenID(resultSet.getString(1));
            attendanceDto.setDate(resultSet.getString(2));
            attendanceDto.setEmpID(resultSet.getString(3));
            attendanceDto.setStatus(resultSet.getString(4));
            return attendanceDto;

        } else {
            return null;
        }
    }
}

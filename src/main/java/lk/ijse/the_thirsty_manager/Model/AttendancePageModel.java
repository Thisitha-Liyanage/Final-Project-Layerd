package lk.ijse.the_thirsty_manager.Model;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.AttendanceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendancePageModel {
    public ArrayList<AttendanceDto> getAllAttendance() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from attendance");
        ResultSet resultSet = pst.executeQuery();

        ArrayList<AttendanceDto> list = new ArrayList<>();

        while (resultSet.next()) {
            AttendanceDto attendanceDto = new AttendanceDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );

            list.add(attendanceDto);
        }
        return list;
    }
}

package lk.ijse.the_thirsty_manager.BO.Custom;

import lk.ijse.the_thirsty_manager.BO.SuperBO;
import lk.ijse.the_thirsty_manager.Dto.AttendanceDto;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface AttendanceBO extends SuperBO {
    boolean save(AttendanceDto attendanceDto) throws SQLException;
    AttendanceDto search(String ID) throws SQLException ;
    List<AttendanceDto> getAll()throws SQLException;
    String nextID() throws SQLException;
    boolean searchEmpID(String ID) throws SQLException;

}

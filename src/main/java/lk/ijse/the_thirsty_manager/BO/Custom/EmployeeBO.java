package lk.ijse.the_thirsty_manager.BO.Custom;

import lk.ijse.the_thirsty_manager.BO.SuperBO;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    boolean save(EmployeeDto employeeDto) throws SQLException;
    boolean update(EmployeeDto employeeDto) throws SQLException;
    boolean delete(String ID) throws SQLException;
    List<EmployeeDto> getAll()throws SQLException;
    String nextID() throws SQLException;
    EmployeeDto search(String ID) throws SQLException;
}

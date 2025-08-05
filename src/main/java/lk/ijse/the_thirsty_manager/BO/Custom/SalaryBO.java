package lk.ijse.the_thirsty_manager.BO.Custom;

import lk.ijse.the_thirsty_manager.BO.SuperBO;
import lk.ijse.the_thirsty_manager.Dto.SalaryDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SalaryBO extends SuperBO {
    boolean save(SalaryDto salaryDto) throws SQLException;
    SalaryDto searchByID(String s) throws SQLException;
    List<SalaryDto> getAll() throws SQLException;
    String getNextID() throws SQLException;
    SalaryDto findEmp(String ID) throws SQLException;
}

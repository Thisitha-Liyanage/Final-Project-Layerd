package lk.ijse.the_thirsty_manager.DAO.Custom;

import lk.ijse.the_thirsty_manager.DAO.CrudDAO;
import lk.ijse.the_thirsty_manager.Entity.AttendanceEntity;

import java.sql.SQLException;

public interface AttendanceDAO extends CrudDAO<AttendanceEntity> {
    String findEmployee(String empID) throws SQLException;
}

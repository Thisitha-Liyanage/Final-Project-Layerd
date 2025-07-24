package lk.ijse.the_thirsty_manager.DAO.Custom;

import lk.ijse.the_thirsty_manager.DAO.CrudDAO;
import lk.ijse.the_thirsty_manager.DAO.SuperDAO;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;
import lk.ijse.the_thirsty_manager.Entity.EmployeeEntity;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<EmployeeEntity> {
    boolean update (EmployeeEntity employeeEntity) throws SQLException;
    boolean delete(String ID) throws SQLException;
    String getNextID() throws SQLException;
}

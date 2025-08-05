package lk.ijse.the_thirsty_manager.DAO.Custom;

import lk.ijse.the_thirsty_manager.DAO.CrudDAO;
import lk.ijse.the_thirsty_manager.DAO.SuperDAO;
import lk.ijse.the_thirsty_manager.Entity.SalaryEntity;

import java.sql.SQLException;

public interface SalaryDAO extends CrudDAO<SalaryEntity> {
    SalaryEntity findEmp(String ID) throws SQLException;
}

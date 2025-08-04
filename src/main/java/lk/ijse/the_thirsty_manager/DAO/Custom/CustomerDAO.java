package lk.ijse.the_thirsty_manager.DAO.Custom;

import lk.ijse.the_thirsty_manager.DAO.CrudDAO;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;

import java.sql.SQLException;
import java.util.Optional;

public interface CustomerDAO extends CrudDAO<CustomerEntity> {
    boolean update (CustomerEntity customerEntity) throws SQLException;
    boolean delete(String ID) throws SQLException;

}

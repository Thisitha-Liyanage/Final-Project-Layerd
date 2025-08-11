package lk.ijse.the_thirsty_manager.DAO.Custom;

import lk.ijse.the_thirsty_manager.DAO.CrudDAO;
import lk.ijse.the_thirsty_manager.Entity.TableEntity;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;

public interface TableDAO extends CrudDAO<TableEntity> {
    boolean update(TableEntity tableEntity) throws SQLException;
    boolean delete(String ID) throws SQLException;
}

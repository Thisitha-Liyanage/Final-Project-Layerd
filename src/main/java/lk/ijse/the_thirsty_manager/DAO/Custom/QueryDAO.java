package lk.ijse.the_thirsty_manager.DAO.Custom;

import lk.ijse.the_thirsty_manager.DAO.CrudDAO;
import lk.ijse.the_thirsty_manager.Entity.CustomOrderEntity;

import java.sql.SQLException;

public interface QueryDAO extends CrudDAO<CustomOrderEntity> {
    boolean updateStock(CustomOrderEntity customOrderEntity) throws SQLException;
}

package lk.ijse.the_thirsty_manager.DAO.Custom;

import lk.ijse.the_thirsty_manager.DAO.CrudDAO;
import lk.ijse.the_thirsty_manager.DAO.Custom.IMPL.ItemDAOIMPL;
import lk.ijse.the_thirsty_manager.Entity.ItemEntity;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<ItemEntity> {
    boolean update(ItemEntity itemEntity)throws SQLException;
    boolean delete(String ID)throws SQLException;
}

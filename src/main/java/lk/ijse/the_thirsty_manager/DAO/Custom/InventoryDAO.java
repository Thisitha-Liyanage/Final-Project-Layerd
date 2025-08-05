package lk.ijse.the_thirsty_manager.DAO.Custom;

import lk.ijse.the_thirsty_manager.DAO.CrudDAO;
import lk.ijse.the_thirsty_manager.Entity.InventoryEntity;

import java.sql.SQLException;

public interface InventoryDAO extends CrudDAO<InventoryEntity> {
    boolean update(InventoryEntity inventoryEntity) throws SQLException;
    boolean delete(String ID) throws SQLException;
    String findSup(String ID) throws SQLException;
}

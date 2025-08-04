package lk.ijse.the_thirsty_manager.DAO.Custom;

import lk.ijse.the_thirsty_manager.DAO.CrudDAO;
import lk.ijse.the_thirsty_manager.Dto.SupplierDto;
import lk.ijse.the_thirsty_manager.Entity.SupplierEntity;

import java.sql.SQLException;

public interface SupplierDAO extends CrudDAO<SupplierEntity> {
    boolean update(SupplierEntity supplierEntity)throws SQLException;
    boolean delete(String ID) throws SQLException;
}

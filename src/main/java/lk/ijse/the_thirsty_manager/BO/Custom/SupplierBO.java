package lk.ijse.the_thirsty_manager.BO.Custom;

import lk.ijse.the_thirsty_manager.BO.SuperBO;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.SupplierDto;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {
    boolean save(SupplierDto supplierDto) throws SQLException;
    boolean update(SupplierDto supplierDto)throws SQLException;
    boolean delete(String ID) throws SQLException;
    String nextID()throws SQLException;
    SupplierDto searchById(String ID) throws SQLException;
    List<SupplierDto> getAll() throws SQLException;
}

package lk.ijse.the_thirsty_manager.BO.Custom;

import lk.ijse.the_thirsty_manager.BO.SuperBO;
import lk.ijse.the_thirsty_manager.Dto.InventoryDto;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.List;

public interface InventoryBO extends SuperBO {
    boolean save(InventoryDto inventoryDto) throws SQLException;
    boolean update(InventoryDto inventoryDto ) throws SQLException;
    boolean delete(String ID) throws SQLException;
    InventoryDto searchById(String ID) throws SQLException;
    List<InventoryDto> getAll() throws SQLException;
    String nextID() throws SQLException;
    boolean findSup(String ID) throws SQLException;
}

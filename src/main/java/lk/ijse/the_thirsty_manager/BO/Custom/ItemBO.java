package lk.ijse.the_thirsty_manager.BO.Custom;

import lk.ijse.the_thirsty_manager.BO.SuperBO;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    boolean save(ItemDto itemDto) throws SQLException;
    boolean update(ItemDto itemDto) throws SQLException;
    boolean delete(String ID) throws SQLException;
    ItemDto search(String ID) throws SQLException;
    List<ItemDto> getAll()throws SQLException;
    String nextID()throws SQLException;
}

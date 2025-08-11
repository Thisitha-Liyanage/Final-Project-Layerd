package lk.ijse.the_thirsty_manager.BO.Custom;

import lk.ijse.the_thirsty_manager.BO.SuperBO;
import lk.ijse.the_thirsty_manager.Dto.TableDto;

import java.sql.SQLException;
import java.util.List;

public interface TableBO extends SuperBO {
    boolean save(TableDto tableDto) throws SQLException;
    boolean update(TableDto tableDto) throws SQLException;
    boolean delete(String ID) throws SQLException;
    String nextID()throws SQLException;
    List<TableDto> getAll() throws SQLException;
    TableDto searchByID(String ID) throws SQLException;
}

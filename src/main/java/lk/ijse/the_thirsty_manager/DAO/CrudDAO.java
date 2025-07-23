package lk.ijse.the_thirsty_manager.DAO;

import lk.ijse.the_thirsty_manager.Dto.AttendanceDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T t) throws SQLException;
    Optional<T> searchByID(String s) throws SQLException;
    List<T> getAll() throws SQLException;
    String getNextID() throws SQLException;
}

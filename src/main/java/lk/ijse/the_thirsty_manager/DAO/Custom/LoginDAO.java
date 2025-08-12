package lk.ijse.the_thirsty_manager.DAO.Custom;

import lk.ijse.the_thirsty_manager.DAO.CrudDAO;
import lk.ijse.the_thirsty_manager.DAO.SuperDAO;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;

public interface LoginDAO extends SuperDAO {
    String login(String username , String password) throws SQLException;
}

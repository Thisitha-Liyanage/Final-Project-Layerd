package lk.ijse.the_thirsty_manager.BO.Custom;

import lk.ijse.the_thirsty_manager.BO.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    String login(String username , String password)throws SQLException;

}

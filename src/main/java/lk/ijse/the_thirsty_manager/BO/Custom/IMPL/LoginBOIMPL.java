package lk.ijse.the_thirsty_manager.BO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.BO.Custom.LoginBO;
import lk.ijse.the_thirsty_manager.BO.SuperBO;
import lk.ijse.the_thirsty_manager.DAO.Custom.LoginDAO;
import lk.ijse.the_thirsty_manager.DAO.DAOFactory;
import lk.ijse.the_thirsty_manager.DAO.DAOTypes;

import java.sql.SQLException;

public class LoginBOIMPL implements LoginBO {
    private final LoginDAO loginDAO = DAOFactory.getInstance().getDAO(DAOTypes.LOGIN);
    @Override
    public String login(String username, String password) throws SQLException {
        return loginDAO.login(username, password);
    }
}

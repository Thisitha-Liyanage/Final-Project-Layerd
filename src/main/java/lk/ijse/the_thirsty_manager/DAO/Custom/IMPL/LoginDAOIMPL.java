package lk.ijse.the_thirsty_manager.DAO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.DAO.Custom.LoginDAO;
import lk.ijse.the_thirsty_manager.DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOIMPL implements LoginDAO {
    @Override
    public String login(String username, String password) throws SQLException {
        String role = null;
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from user where user_id = ? and user_password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet rst = statement.executeQuery();
        if (rst.next()){
            role =  rst.getString("user_role");
        }
        return role ;
    }
    }


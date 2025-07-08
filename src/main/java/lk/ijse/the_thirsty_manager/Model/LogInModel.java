package lk.ijse.the_thirsty_manager.Model;

import lk.ijse.the_thirsty_manager.DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInModel {
    public String logIn(String inpUserID , String inpPassword) throws SQLException {
        String role = null;
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from user where user_id = ? and user_password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, inpUserID);
        statement.setString(2, inpPassword);

        ResultSet rst = statement.executeQuery();
        if (rst.next()){
            role =  rst.getString("user_role");
        }
        return role ;
    }

}

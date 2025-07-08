package lk.ijse.the_thirsty_manager.Model.EmployeeManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchEmployeeModel {
    public EmployeeDto findEmployee(EmployeeDto employeeDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from employee where employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employeeDto.getEmployeeID());

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            employeeDto.setName(resultSet.getString(2));
            employeeDto.setRole(resultSet.getString(3));
            employeeDto.setContact(resultSet.getString(4));
            employeeDto.setSPD(resultSet.getDouble(5));
            return employeeDto;
        }else{
            return null;
        }
    }
}

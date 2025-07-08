package lk.ijse.the_thirsty_manager.Model.EmployeeManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateEmployeeModel {
    public boolean updateEmployee(EmployeeDto employeeDto) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "update employee set employee_name = ? , contact = ? , role = ? , salary_per_day = ? where employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , employeeDto.getName());
        statement.setString(2, employeeDto.getContact());
        statement.setString(3 , employeeDto.getRole());
        statement.setDouble(4 , employeeDto.getSPD());
        statement.setString(5 , employeeDto.getEmployeeID());

        int rawEffected = statement.executeUpdate();

        return rawEffected > 0;
    }

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

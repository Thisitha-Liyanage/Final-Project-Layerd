package lk.ijse.the_thirsty_manager.Model.EmployeeManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddEmployeeModel {
    public boolean saveEmployee(EmployeeDto employeeDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into employee values (? , ? , ? , ? , ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , employeeDto.getEmployeeID());
        statement.setString(2 , employeeDto.getName());
        statement.setString(3 , employeeDto.getRole());
        statement.setString(4 , employeeDto.getContact());
        statement.setDouble(5 , employeeDto.getSPD());

        int rawEffected = statement.executeUpdate();
        return rawEffected > 0;
    }

    public String getNextId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select employee_id from employee order by employee_id desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        char tableChar = 'E';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNUmberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNUmberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableChar + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableChar + "001";
    }
}

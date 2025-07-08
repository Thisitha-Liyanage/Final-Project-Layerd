package lk.ijse.the_thirsty_manager.Model.SalaryManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.SalaryDto;
import lk.ijse.the_thirsty_manager.Dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddSalaryModel {
    public boolean saveSupplier(SalaryDto salaryDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into salary (salary_id, employee_id, amount, date) VALUES (? , ? , ? , ? )";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, salaryDto.getSalaryID());
        statement.setString(2 , salaryDto.getEmpID());
        statement.setDouble(3 , salaryDto.getAmount());
        statement.setString(4 , salaryDto.getDate());

        int rawEffected = statement.executeUpdate();
        return rawEffected > 0;
    }
    public String getNextId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT salary_id FROM salary ORDER BY salary_id DESC LIMIT 1";
        PreparedStatement pst = connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        String tableChar = "SL";

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);

            if (lastId.startsWith(tableChar)) {
                String numericPart = lastId.substring(tableChar.length());
                int lastIdNumber = Integer.parseInt(numericPart);
                int nextIdNumber = lastIdNumber + 1;
                return String.format(tableChar + "%03d", nextIdNumber);
            } else {
                return tableChar + "001";
            }
        }
        return tableChar + "001";
    }

    public SalaryDto empDetail(SalaryDto salaryDto) throws SQLException {
        String empID = salaryDto.getEmpID();
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT salary_per_day FROM employee WHERE employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, empID);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            double amount = resultSet.getDouble("salary_per_day");
            salaryDto.setAmount(amount);
            return salaryDto;
        } else {
            System.out.println("No employee found for ID: " + empID);
        }

        return null;
    }

}

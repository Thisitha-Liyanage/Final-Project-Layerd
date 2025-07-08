package lk.ijse.the_thirsty_manager.Model.SalaryManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;
import lk.ijse.the_thirsty_manager.Dto.SalaryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchSalaryModel {
    public SalaryDto findSalary(SalaryDto salaryDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String que = "select * from salary where salary_id = ?";
        PreparedStatement statement = connection.prepareStatement(que);
        statement.setString(1, salaryDto.getSalaryID());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            salaryDto.setEmpID(resultSet.getString(2));
            salaryDto.setAmount(resultSet.getDouble(3));
            salaryDto.setDate(resultSet.getString(4));

            return salaryDto;

        } else {
            return null;
        }
    }
}

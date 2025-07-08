package lk.ijse.the_thirsty_manager.Model;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.SalaryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryPageModel {
    public ArrayList<SalaryDto> getAllSalary() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from salary");
        ResultSet resultSet = pst.executeQuery();

        ArrayList<SalaryDto> list = new ArrayList<>();

        while (resultSet.next()) {
            SalaryDto salaryDto = new SalaryDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4)
            );

            list.add(salaryDto);
        }
        return list;
    }
}

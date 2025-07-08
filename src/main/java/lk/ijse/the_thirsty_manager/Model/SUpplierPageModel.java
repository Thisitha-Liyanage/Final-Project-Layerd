package lk.ijse.the_thirsty_manager.Model;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.EmployeeDto;
import lk.ijse.the_thirsty_manager.Dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SUpplierPageModel {
    public ArrayList<SupplierDto> getAllSuppliers() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from supplier");
        ResultSet resultSet = pst.executeQuery();

        ArrayList<SupplierDto> list = new ArrayList<>();

        while (resultSet.next()) {
           SupplierDto supplierDto = new SupplierDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );

            list.add(supplierDto);
        }
        return list;
    }
}

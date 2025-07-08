package lk.ijse.the_thirsty_manager.Model.SupplierManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddSupplierModel {
    public boolean saveSupplier(SupplierDto supplierDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into supplier (supplier_id, name, contact, address) values (? , ? , ? , ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, supplierDto.getSupID());
        statement.setString(2 , supplierDto.getName());
        statement.setString(3 , supplierDto.getContact());
        statement.setString(4 , supplierDto.getAddress());

        int rawEffected = statement.executeUpdate();
        return rawEffected > 0;
    }
    public String getNextId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select supplier_id from supplier order by supplier_id desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        char tableChar = 'S';
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

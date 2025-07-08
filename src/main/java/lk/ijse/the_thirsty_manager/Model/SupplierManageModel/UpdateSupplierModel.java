package lk.ijse.the_thirsty_manager.Model.SupplierManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateSupplierModel {
    public SupplierDto findSupplier(SupplierDto supplierDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from supplier where supplier_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, supplierDto.getSupID());

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            supplierDto.setName(resultSet.getString(2));
            supplierDto.setContact(resultSet.getString(3));
            supplierDto.setAddress(resultSet.getString(4));
            return supplierDto;
        }else{
            return null;
        }
    }

    public boolean updateSup(SupplierDto supplierDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "update supplier set name = ?, address = ? , contact = ? where supplier_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1 , supplierDto.getName());
        statement.setString(3, supplierDto.getContact());
        statement.setString(2 , supplierDto.getAddress());
        statement.setString(4 , supplierDto.getSupID());

        int rawEffected = statement.executeUpdate();

        return rawEffected > 0;
    }
}

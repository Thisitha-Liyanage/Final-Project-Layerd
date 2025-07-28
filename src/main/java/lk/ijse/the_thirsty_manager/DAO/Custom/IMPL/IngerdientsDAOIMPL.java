package lk.ijse.the_thirsty_manager.DAO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.DAO.Custom.IngredientDAO;
import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Entity.EmployeeEntity;
import lk.ijse.the_thirsty_manager.Entity.IngerdientEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IngerdientsDAOIMPL implements IngredientDAO {
    @Override
    public String findItem(String itemID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select menu_items.item_id from menu_items where item_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);

        stm.setString(1 , itemID);

        ResultSet resultSet = stm.executeQuery();

        String iID = null ;
        if(resultSet.next()){
            iID = resultSet.getString(1);
        }

        if (iID != null) {
            return iID;
        }else{
            return null;
        }
    }

    @Override
    public String findInvenID(String invID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select inventory_id from inventory where inventory_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);

        stm.setString(1 , invID);

        ResultSet resultSet = stm.executeQuery();

        String iID= null;
        if(resultSet.next()){
           iID = resultSet.getString(1);
        }
        if(iID != null){
            return iID;
        }else{
            return null;
        }
    }

    @Override
    public boolean save(IngerdientEntity ingerdientEntity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into ingredients (ingredient_id, inventory_id, menu_item_id, liquid_quantity) values (? , ? , ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, ingerdientEntity.getIngredientID());
        statement.setString(2, ingerdientEntity.getInventoryID());
        statement.setString(3, ingerdientEntity.getItemID());
        statement.setDouble(4, ingerdientEntity.getQuantity());

        int rowAffected = statement.executeUpdate();
        return rowAffected > 0;
    }

    @Override
    public Optional<IngerdientEntity> searchByID(String s) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from ingredients where ingredient_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, s);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return Optional.of(new IngerdientEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)

            ));
        }
        return Optional.empty();
    }

    @Override
    public List<IngerdientEntity> getAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from ingredients");
        ResultSet resultSet = pst.executeQuery();

        List<IngerdientEntity> list = new ArrayList<>();

        while (resultSet.next()) {
            IngerdientEntity ingerdientEntity= new IngerdientEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );

            list.add(ingerdientEntity);
        }

        return list;
    }

    @Override
    public String getNextID() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select ingredient_id from ingredients order by ingredient_id desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        String tableString = "IG";
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNUmberString = lastId.substring(2);
            int lastIdNumber = Integer.parseInt(lastIdNUmberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableString + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableString + "001";
    }
}

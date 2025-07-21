package lk.ijse.the_thirsty_manager.Model.IngredientManageModel;

import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.AttendanceDto;
import lk.ijse.the_thirsty_manager.Dto.IngredientDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddIngredient {
    public boolean saveIngerdients(IngredientDto ingredientDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into ingredients (ingredient_id, inventory_id, menu_item_id, liquid_quantity) values (? , ? , ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, ingredientDto.getIngredientID());
        statement.setString(2, ingredientDto.getInventoryID());
        statement.setString(3, ingredientDto.getItemID());
        statement.setDouble(4, ingredientDto.getQuantity());

        int rowAffected = statement.executeUpdate();
        return rowAffected > 0;
    }

    public String getNextId() throws SQLException {
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

    public boolean findItemID(String itemID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select menu_items.item_id from menu_items where item_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);

        stm.setString(1 , itemID);

        ResultSet resultSet = stm.executeQuery();

        if(resultSet.next()){
            if(resultSet.getString(1).equals(itemID)){
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean findInvenID(String inventoryID) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select inventory_id from inventory where inventory_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);

        stm.setString(1 , inventoryID);

        ResultSet resultSet = stm.executeQuery();

        if(resultSet.next()){
            if(resultSet.getString(1).equals(inventoryID)){
                return true;
            }
            return false;
        }
        return false;
    }
}

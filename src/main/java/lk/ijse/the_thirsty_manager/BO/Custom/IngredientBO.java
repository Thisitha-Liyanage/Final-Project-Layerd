package lk.ijse.the_thirsty_manager.BO.Custom;

import lk.ijse.the_thirsty_manager.BO.SuperBO;
import lk.ijse.the_thirsty_manager.Dto.IngredientDto;

import java.sql.SQLException;
import java.util.List;

public interface IngredientBO extends SuperBO {
    boolean save(IngredientDto ingredientDto) throws SQLException;
    boolean findItem(String itemID) throws SQLException;
    boolean findInvenID(String invID) throws SQLException;
    IngredientDto search(String ID) throws SQLException;
    List<IngredientDto> getAll()throws SQLException;
    String nextID() throws SQLException;
}

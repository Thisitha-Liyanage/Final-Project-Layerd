package lk.ijse.the_thirsty_manager.DAO.Custom;

import lk.ijse.the_thirsty_manager.DAO.CrudDAO;
import lk.ijse.the_thirsty_manager.Dto.IngredientDto;
import lk.ijse.the_thirsty_manager.Entity.IngerdientEntity;

import java.sql.SQLException;

public interface IngredientDAO extends CrudDAO<IngerdientEntity> {
    String findItem(String itemID) throws SQLException;
    String findInvenID(String invID) throws SQLException;
}

package lk.ijse.the_thirsty_manager.BO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.BO.Custom.IngredientBO;
import lk.ijse.the_thirsty_manager.BO.Exceptions.IDNotFoundException;
import lk.ijse.the_thirsty_manager.DAO.Custom.IngredientDAO;
import lk.ijse.the_thirsty_manager.DAO.DAOFactory;
import lk.ijse.the_thirsty_manager.DAO.DAOTypes;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.IngredientDto;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;
import lk.ijse.the_thirsty_manager.Entity.IngerdientEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class IngredeintBOIMPL implements IngredientBO {
    private final IngredientDAO ingredientDAO = DAOFactory.getInstance().getDAO(DAOTypes.INGREDIENTS);
    @Override
    public boolean save(IngredientDto ingredientDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        IngerdientEntity entity = modelMapper.map(ingredientDto, IngerdientEntity.class);
        return ingredientDAO.save(entity);
    }

    @Override
    public boolean findItem(String itemID) throws SQLException {

            String iID = ingredientDAO.findItem(itemID);

            if(iID != null && iID.equals(itemID)){
                return true;
            }else{
                throw  new IDNotFoundException("Item ID Not Found");
            }
    }

    @Override
    public boolean findInvenID(String invID) throws SQLException {

            String inID = ingredientDAO.findInvenID(invID);

            if (inID.equals(invID)) {
                return true;
            } else {
                throw new IDNotFoundException("Inventory ID Not Found");
            }
        }

    @Override
    public IngredientDto search(String ID) throws SQLException {
        Optional<IngerdientEntity> ingerdientEntity = ingredientDAO.searchByID(ID);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(ingerdientEntity, IngredientDto.class);
    }

    @Override
    public List<IngredientDto> getAll() throws SQLException {
        List<IngerdientEntity> ingredientDAOList = ingredientDAO.getAll();
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<IngredientDto>>() {}.getType();
        return modelMapper.map(ingredientDAOList, listType);
    }

    @Override
    public String nextID() throws SQLException {
        return ingredientDAO.getNextID();
    }
}

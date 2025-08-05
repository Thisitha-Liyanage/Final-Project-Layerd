package lk.ijse.the_thirsty_manager.BO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.InventoryBO;
import lk.ijse.the_thirsty_manager.DAO.Custom.InventoryDAO;
import lk.ijse.the_thirsty_manager.DAO.DAOFactory;
import lk.ijse.the_thirsty_manager.DAO.DAOTypes;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.InventoryDto;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;
import lk.ijse.the_thirsty_manager.Entity.InventoryEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class InventoryBOIMPL implements InventoryBO {
    private final InventoryDAO inventoryDAO = DAOFactory.getInstance().getDAO(DAOTypes.INVENTORY);
    @Override

    public boolean save(InventoryDto inventoryDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        InventoryEntity inventoryEntity = modelMapper.map(inventoryDto , InventoryEntity.class);
        return inventoryDAO.save(inventoryEntity);
    }

    @Override
    public boolean update(InventoryDto inventoryDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        InventoryEntity inventoryEntity = modelMapper.map(inventoryDto , InventoryEntity.class);
        return inventoryDAO.update(inventoryEntity);
    }

    @Override
    public boolean delete(String ID) throws SQLException {
        return inventoryDAO.delete(ID);
    }

    @Override
    public InventoryDto searchById(String ID) throws SQLException {
        Optional<InventoryEntity> inventoryEntity = inventoryDAO.searchByID(ID);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(inventoryEntity, InventoryDto.class);
    }

    @Override
    public List<InventoryDto> getAll() throws SQLException {
        List<InventoryEntity> inventoryEntityList = inventoryDAO.getAll();
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<InventoryDto>>() {}.getType();
        return modelMapper.map(inventoryEntityList, listType);
    }

    @Override
    public String nextID() throws SQLException {
        return inventoryDAO.getNextID();
    }

    @Override
    public boolean findSup(String ID) throws SQLException {
        if(ID != null) {
            return inventoryDAO.findSup(ID).equals(ID);
        }
        return false;
    }
}

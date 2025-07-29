package lk.ijse.the_thirsty_manager.BO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.BO.Custom.ItemBO;
import lk.ijse.the_thirsty_manager.DAO.Custom.ItemDAO;
import lk.ijse.the_thirsty_manager.DAO.DAOFactory;
import lk.ijse.the_thirsty_manager.DAO.DAOTypes;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;
import lk.ijse.the_thirsty_manager.Entity.ItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ItemBOIMPL implements ItemBO {
    private final ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);

    @Override
    public boolean save(ItemDto itemDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        return itemDAO.save(modelMapper.map(itemDto, ItemEntity.class));
    }

    @Override
    public boolean update(ItemDto itemDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        return itemDAO.update(modelMapper.map(itemDto, ItemEntity.class));
    }

    @Override
    public boolean delete(String ID) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        return itemDAO.delete(ID);
    }

    @Override
    public ItemDto search(String ID) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        Optional<ItemEntity> itemEntity = itemDAO.searchByID(ID);

        System.out.println(itemEntity);
        return modelMapper.map(itemEntity, ItemDto.class);
    }

    @Override
    public List<ItemDto> getAll() throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        List<ItemEntity> itemEntityList = itemDAO.getAll();
        Type listType = new TypeToken<List<ItemDto>>() {}.getType();
        return modelMapper.map(itemEntityList, listType);
    }

    @Override
    public String nextID() throws SQLException {
        return itemDAO.getNextID();
    }
}

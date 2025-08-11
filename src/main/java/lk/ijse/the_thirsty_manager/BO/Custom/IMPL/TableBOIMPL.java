package lk.ijse.the_thirsty_manager.BO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.BO.Custom.TableBO;
import lk.ijse.the_thirsty_manager.DAO.Custom.TableDAO;
import lk.ijse.the_thirsty_manager.DAO.DAOFactory;
import lk.ijse.the_thirsty_manager.DAO.DAOTypes;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.TableDto;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;
import lk.ijse.the_thirsty_manager.Entity.TableEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TableBOIMPL implements TableBO {
    private final TableDAO tableDAO = DAOFactory.getInstance().getDAO(DAOTypes.TABLE);
    @Override
    public boolean save(TableDto tableDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        TableEntity entity = modelMapper.map(tableDto, TableEntity.class);

        return tableDAO.save(entity);
    }

    @Override
    public boolean update(TableDto tableDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        TableEntity entity = modelMapper.map(tableDto, TableEntity.class);

        return tableDAO.update(entity);
    }

    @Override
    public boolean delete(String ID) throws SQLException {
        return tableDAO.delete(ID);
    }

    @Override
    public String nextID() throws SQLException {
        return tableDAO.getNextID();
    }

    @Override
    public List<TableDto> getAll() throws SQLException {
        List<TableEntity> tableEntityList = tableDAO.getAll();
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<TableDto>>() {}.getType();
        return modelMapper.map(tableEntityList, listType);
    }

    @Override
    public TableDto searchByID(String ID) throws SQLException {
        Optional<TableEntity> tableEntity = tableDAO.searchByID(ID);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(tableEntity, TableDto.class);
    }
}

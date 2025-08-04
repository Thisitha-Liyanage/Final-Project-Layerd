package lk.ijse.the_thirsty_manager.BO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.BO.Custom.SupplierBO;
import lk.ijse.the_thirsty_manager.DAO.Custom.SupplierDAO;
import lk.ijse.the_thirsty_manager.DAO.DAOFactory;
import lk.ijse.the_thirsty_manager.DAO.DAOTypes;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.SupplierDto;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;
import lk.ijse.the_thirsty_manager.Entity.SupplierEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SupplierBOIMPL implements SupplierBO {
    private final SupplierDAO supplierDAO = DAOFactory.getInstance().getDAO(DAOTypes.SUPPLIER);
    @Override
    public boolean save(SupplierDto supplierDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        SupplierEntity entity = modelMapper.map(supplierDto, SupplierEntity.class);
        return supplierDAO.save(entity);
    }

    @Override
    public boolean update(SupplierDto supplierDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        SupplierEntity entity = modelMapper.map(supplierDto, SupplierEntity.class);
        return supplierDAO.update(entity);
    }

    @Override
    public boolean delete(String ID) throws SQLException {
        return supplierDAO.delete(ID);
    }

    @Override
    public String nextID() throws SQLException {
        return supplierDAO.getNextID();
    }

    @Override
    public SupplierDto searchById(String ID) throws SQLException {
        Optional<SupplierEntity> supplierEntity = supplierDAO.searchByID(ID);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(supplierEntity, SupplierDto.class);
    }

    @Override
    public List<SupplierDto> getAll() throws SQLException {
        List<SupplierEntity> supEntityList = supplierDAO.getAll();
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<SupplierDto>>() {}.getType();
        return modelMapper.map(supEntityList, listType);
    }
}

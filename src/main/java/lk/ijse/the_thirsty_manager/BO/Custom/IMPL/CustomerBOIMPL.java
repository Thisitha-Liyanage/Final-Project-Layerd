package lk.ijse.the_thirsty_manager.BO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.Custom.CustomerBO;
import lk.ijse.the_thirsty_manager.DAO.Custom.CustomerDAO;
import lk.ijse.the_thirsty_manager.DAO.Custom.IMPL.CustomerDAOIMPL;
import lk.ijse.the_thirsty_manager.DAO.DAOFactory;
import lk.ijse.the_thirsty_manager.DAO.DAOTypes;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class CustomerBOIMPL implements CustomerBO {
    private CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);
    @Override
    public boolean save(CustomerDto customerDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        CustomerEntity entity = modelMapper.map(customerDto, CustomerEntity.class);

        return customerDAO.save(entity);
    }


    @Override
    public boolean update(CustomerDto customerDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);
        return customerDAO.update(customerEntity);
    }

    @Override
    public boolean delete(String ID) throws SQLException {
        return customerDAO.delete(ID);
    }

    @Override
    public List<CustomerDto> getAll() throws SQLException {
        List<CustomerEntity> customerEntityList = customerDAO.getAll();
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<CustomerDto>>() {}.getType();
        List<CustomerDto> customerDtoList = modelMapper.map(customerEntityList, listType);
        return customerDtoList;
    }

    @Override
    public String nextID() throws SQLException {
        String nextID = customerDAO.getNextID();
        return nextID;
    }

    @Override
    public CustomerDto searchCustomer(String ID) throws SQLException {
        Optional<CustomerEntity> customerEntity = customerDAO.searchByID(ID);
        ModelMapper modelMapper = new ModelMapper();
        CustomerDto customerDto = modelMapper.map(customerEntity, CustomerDto.class);
        return customerDto;
    }
}

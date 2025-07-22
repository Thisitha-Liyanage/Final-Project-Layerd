package lk.ijse.the_thirsty_manager.BO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.BO.Custom.CustomerBO;
import lk.ijse.the_thirsty_manager.DAO.Custom.CustomerDAO;
import lk.ijse.the_thirsty_manager.DAO.Custom.IMPL.CustomerDAOIMPL;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;

public class CustomerBOIMPL implements CustomerBO {
    @Override
    public boolean save(CustomerDto customerDto) throws SQLException {
        CustomerDAO customerDAO = new CustomerDAOIMPL();
        ModelMapper modelMapper = new ModelMapper();
        CustomerEntity entity = modelMapper.map(customerDto, CustomerEntity.class);

        return customerDAO.save(entity);
    }

    @Override
    public void update(CustomerDto customerDto) throws SQLException {

    }

    @Override
    public boolean delete(String ID) throws SQLException {
        return false;
    }

    @Override
    public List<CustomerDto> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String nextID() throws SQLException {
        CustomerDAO customerDAO = new CustomerDAOIMPL();
        String nextID = customerDAO.getNextID();
        return nextID;
    }
}

package lk.ijse.the_thirsty_manager.DAO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.DAO.Custom.CustomerDAO;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CustomerDAOIMPL implements CustomerDAO<CustomerEntity> {

    @Override
    public boolean save(CustomerEntity customerEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(CustomerEntity customerEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String ID) throws SQLException {
        return false;
    }

    @Override
    public Optional<CustomerEntity> searchByID(String s) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<CustomerEntity> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String getNextID() throws SQLException {
        return "";
    }

}

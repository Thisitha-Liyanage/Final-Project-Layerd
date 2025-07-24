package lk.ijse.the_thirsty_manager.BO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.BO.Custom.EmployeeBO;
import lk.ijse.the_thirsty_manager.DAO.Custom.EmployeeDAO;
import lk.ijse.the_thirsty_manager.DAO.DAOFactory;
import lk.ijse.the_thirsty_manager.DAO.DAOTypes;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.EmployeeDto;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;
import lk.ijse.the_thirsty_manager.Entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeBOIMPL implements EmployeeBO {
    private final EmployeeDAO employeeDAO = DAOFactory.getInstance().getDAO(DAOTypes.EMPLOYEE);

    @Override
    public boolean save(EmployeeDto employeeDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        EmployeeEntity entity = modelMapper.map(employeeDto, EmployeeEntity.class);
        return employeeDAO.save(entity);
    }

    @Override
    public boolean update(EmployeeDto employeeDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        EmployeeEntity entity = modelMapper.map(employeeDto, EmployeeEntity.class);
        return employeeDAO.update(entity);
    }

    @Override
    public boolean delete(String ID) throws SQLException {
        return employeeDAO.delete(ID);
    }

    @Override
    public List<EmployeeDto> getAll() throws SQLException {
        List<EmployeeEntity> employeeEntityList = employeeDAO.getAll();
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<EmployeeDto>>() {}.getType();
        return modelMapper.map(employeeEntityList, listType);
    }

    @Override
    public String nextID() throws SQLException {
        return employeeDAO.getNextID();
    }

    @Override
    public EmployeeDto search(String ID) throws SQLException {
        Optional<EmployeeEntity> employeeEntity = employeeDAO.searchByID(ID);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employeeEntity, EmployeeDto.class);
    }
}

package lk.ijse.the_thirsty_manager.BO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.BO.Custom.SalaryBO;
import lk.ijse.the_thirsty_manager.DAO.Custom.SalaryDAO;
import lk.ijse.the_thirsty_manager.DAO.DAOFactory;
import lk.ijse.the_thirsty_manager.DAO.DAOTypes;
import lk.ijse.the_thirsty_manager.Dto.EmployeeDto;
import lk.ijse.the_thirsty_manager.Dto.SalaryDto;
import lk.ijse.the_thirsty_manager.Entity.EmployeeEntity;
import lk.ijse.the_thirsty_manager.Entity.SalaryEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SalaryBOIMPL implements SalaryBO {
    private final SalaryDAO salaryDAO = DAOFactory.getInstance().getDAO(DAOTypes.SALARY);
    @Override
    public boolean save(SalaryDto salaryDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        SalaryEntity entity = modelMapper.map(salaryDto, SalaryEntity.class);
        return salaryDAO.save(entity);
    }

    @Override
    public SalaryDto searchByID(String s) throws SQLException {
        Optional<SalaryEntity> salaryEntity = salaryDAO.searchByID(s);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(salaryEntity, SalaryDto.class);
    }

    @Override
    public List<SalaryDto> getAll() throws SQLException {
        List<SalaryEntity> salaryEntityList = salaryDAO.getAll();
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<SalaryDto>>() {}.getType();
        return modelMapper.map(salaryEntityList, listType);
    }

    @Override
    public String getNextID() throws SQLException {
        return salaryDAO.getNextID();
    }

    @Override
    public SalaryDto findEmp(String ID) throws SQLException {
        SalaryEntity salaryEntity = salaryDAO.findEmp(ID);
        if(salaryEntity == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(salaryEntity , SalaryDto.class);
    }
}

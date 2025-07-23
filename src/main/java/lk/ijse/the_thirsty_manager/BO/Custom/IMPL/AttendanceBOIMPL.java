package lk.ijse.the_thirsty_manager.BO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.BO.BOFactory;
import lk.ijse.the_thirsty_manager.BO.BOTypes;
import lk.ijse.the_thirsty_manager.BO.Custom.AttendanceBO;
import lk.ijse.the_thirsty_manager.DAO.Custom.AttendanceDAO;
import lk.ijse.the_thirsty_manager.DAO.DAOFactory;
import lk.ijse.the_thirsty_manager.DAO.DAOTypes;
import lk.ijse.the_thirsty_manager.Dto.AttendanceDto;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Entity.AttendanceEntity;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class AttendanceBOIMPL implements AttendanceBO {
    private AttendanceDAO attendanceDAO = DAOFactory.getInstance().getDAO(DAOTypes.ATTENDANCE);
    @Override
    public boolean save(AttendanceDto attendanceDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        AttendanceEntity entity = modelMapper.map(attendanceDto, AttendanceEntity.class);

        return attendanceDAO.save(entity);
    }

    @Override
    public AttendanceDto search(String ID) throws SQLException {
        Optional<AttendanceEntity> attendanceEntity = attendanceDAO.searchByID(ID);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(attendanceEntity, AttendanceDto.class);
    }

    @Override
    public List<AttendanceDto> getAll() throws SQLException {
        List<AttendanceEntity> attendanceEntityList = attendanceDAO.getAll();
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<AttendanceDto>>() {}.getType();
        return modelMapper.map(attendanceEntityList, listType);
    }

    @Override
    public String nextID() throws SQLException {
        return attendanceDAO.getNextID();
    }

    @Override
    public boolean searchEmpID(String ID) throws SQLException {
        String empIDQ = attendanceDAO.findEmployee(ID);

        if (empIDQ != null) {
            return empIDQ.equals(ID);
        }else{
            return false;
        }
    }
}

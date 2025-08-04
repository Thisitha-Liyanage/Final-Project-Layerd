package lk.ijse.the_thirsty_manager.BO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.BO.Custom.PaymentBO;
import lk.ijse.the_thirsty_manager.DAO.Custom.PaymentDAO;
import lk.ijse.the_thirsty_manager.DAO.DAOFactory;
import lk.ijse.the_thirsty_manager.DAO.DAOTypes;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;
import lk.ijse.the_thirsty_manager.Dto.PaymentDto;
import lk.ijse.the_thirsty_manager.Entity.CustomerEntity;
import lk.ijse.the_thirsty_manager.Entity.PaymentEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PaymentBOIMPL implements PaymentBO {
    private final PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOTypes.PAYMENT);
    @Override
    public boolean save(PaymentDto paymentDto) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        PaymentEntity entity = modelMapper.map(paymentDto, PaymentEntity.class);

        return paymentDAO.save(entity);
    }

    @Override
    public PaymentDto findOrder(String ID) throws SQLException {
        PaymentEntity paymentEntity = paymentDAO.findOrder(ID);

        if (paymentEntity != null) {
            if(paymentEntity.getOrderID().equals(ID)){
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(paymentEntity, PaymentDto.class);
            }
            return null;
        }else{
            return null;
        }
    }

    @Override
    public String nextID() throws SQLException {
        return paymentDAO.getNextID();
    }

    @Override
    public boolean duplicate(String oID) throws SQLException {
        String dupID = paymentDAO.duplicate(oID);
        if(dupID == null){
            return false;
        }else{
            return dupID.equals(oID);
        }
    }

    @Override
    public List<PaymentDto> getAll() throws SQLException {
        List<PaymentEntity> paymentEntityList = paymentDAO.getAll();
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<PaymentDto>>() {}.getType();
        return modelMapper.map(paymentEntityList, listType);
    }

    @Override
    public PaymentDto searchByID(String iD) throws SQLException {
        Optional<PaymentEntity> paymentEntity = paymentDAO.searchByID(iD);
        ModelMapper modelMapper = new ModelMapper();
        PaymentDto paymentDto = modelMapper.map(paymentEntity, PaymentDto.class);
        return paymentDto;

    }
}

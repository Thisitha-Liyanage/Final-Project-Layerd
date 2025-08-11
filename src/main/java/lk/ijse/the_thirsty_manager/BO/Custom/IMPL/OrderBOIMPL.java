package lk.ijse.the_thirsty_manager.BO.Custom.IMPL;

import lk.ijse.the_thirsty_manager.BO.Custom.OrderBO;
import lk.ijse.the_thirsty_manager.DAO.Custom.OrderDetailsDAO;
import lk.ijse.the_thirsty_manager.DAO.Custom.OrderDAO;
import lk.ijse.the_thirsty_manager.DAO.Custom.QueryDAO;
import lk.ijse.the_thirsty_manager.DAO.DAOFactory;
import lk.ijse.the_thirsty_manager.DAO.DAOTypes;
import lk.ijse.the_thirsty_manager.DB.DBConnection;
import lk.ijse.the_thirsty_manager.Dto.CustomOrderDTO;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;
import lk.ijse.the_thirsty_manager.Dto.OrderDto;
import lk.ijse.the_thirsty_manager.Entity.CustomOrderEntity;
import lk.ijse.the_thirsty_manager.Entity.ItemEntity;
import lk.ijse.the_thirsty_manager.Entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderBOIMPL implements OrderBO {
    private final OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER);
    @Override
    public String nextID() throws SQLException {
       return orderDAO.getNextID();
    }

    @Override
    public boolean findCustomer(String ID) throws SQLException {
        return orderDAO.findCustomer(ID) != null;
    }

    @Override
    public ItemDto findItem(String ID) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        ItemEntity itemEntity = orderDAO.findItem(ID);
        if(itemEntity != null) {
            return modelMapper.map(itemEntity, ItemDto.class);
        }else{
            return null;
        }
    }

    private final OrderDetailsDAO orderDetailsDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER_DETAILS);
    @Override
    public boolean saveOrderDetails(List<CustomOrderDTO> customOrderDTOS) throws SQLException {
        ModelMapper modelMapper  = new ModelMapper();
        List<CustomOrderEntity> customOrderEntities = customOrderDTOS.stream()
        .map(dto -> modelMapper.map(dto, CustomOrderEntity.class))
                .collect(Collectors.toList());
        for(CustomOrderEntity customOrderEntity : customOrderEntities){
            if(! orderDetailsDAO.save(customOrderEntity)){
                return false;
            }
        }
        return true;
    }
    private final QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERY);
    @Override
    public boolean updateStock(List<CustomOrderDTO> customOrderDTOS) throws SQLException {
        ModelMapper modelMapper  = new ModelMapper();
        List<CustomOrderEntity> customOrderEntities = customOrderDTOS.stream()
                .map(dto -> modelMapper.map(dto, CustomOrderEntity.class))
                .collect(Collectors.toList());
        for(CustomOrderEntity customOrderEntity : customOrderEntities){
            if( !queryDAO.save(customOrderEntity)){
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean placeOrder(List<CustomOrderDTO> customOrderDTOS) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);


        try {
            ModelMapper modelMapper  = new ModelMapper();
            List<CustomOrderEntity> customOrderEntities = customOrderDTOS.stream()
                    .map(dto -> modelMapper.map(dto, CustomOrderEntity.class))
                    .collect(Collectors.toList());
            for(CustomOrderEntity customOrderEntity : customOrderEntities)
                if (!orderDAO.placeOrder(customOrderEntity)) {
                    connection.rollback();
                    return false;
                }
            else {
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
           connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public List<OrderDto> getAll() throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        List<OrderEntity> orderEntityList = orderDAO.getAll();
        Type listType = new TypeToken<List<OrderDto>>() {}.getType();
        return modelMapper.map(orderEntityList, listType);
    }
}

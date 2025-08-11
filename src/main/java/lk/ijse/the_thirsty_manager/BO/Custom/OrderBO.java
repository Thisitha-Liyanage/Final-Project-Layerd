package lk.ijse.the_thirsty_manager.BO.Custom;

import lk.ijse.the_thirsty_manager.BO.SuperBO;
import lk.ijse.the_thirsty_manager.Dto.CustomOrderDTO;
import lk.ijse.the_thirsty_manager.Dto.ItemDto;
import lk.ijse.the_thirsty_manager.Dto.OrderDetailsDto;
import lk.ijse.the_thirsty_manager.Dto.OrderDto;
import lk.ijse.the_thirsty_manager.Entity.OrderEntity;

import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {
    String nextID() throws SQLException;
    boolean findCustomer(String ID) throws SQLException;
    ItemDto findItem(String ID) throws SQLException;
    boolean saveOrderDetails(List<CustomOrderDTO> customOrderDTOS) throws SQLException;
    boolean updateStock(List<CustomOrderDTO> customOrderDTOS) throws SQLException;
    boolean placeOrder(List<CustomOrderDTO> customOrderDTOS) throws SQLException;
    List<OrderDto> getAll()throws SQLException;
}

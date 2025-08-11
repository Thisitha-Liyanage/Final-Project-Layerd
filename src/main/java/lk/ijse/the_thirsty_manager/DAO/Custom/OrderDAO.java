package lk.ijse.the_thirsty_manager.DAO.Custom;

import lk.ijse.the_thirsty_manager.DAO.CrudDAO;
import lk.ijse.the_thirsty_manager.Dto.OrderDto;
import lk.ijse.the_thirsty_manager.Entity.CustomOrderEntity;
import lk.ijse.the_thirsty_manager.Entity.ItemEntity;
import lk.ijse.the_thirsty_manager.Entity.OrderEntity;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends CrudDAO<OrderEntity> {
    ItemEntity findItem(String ID) throws SQLException;
    String findCustomer(String ID) throws SQLException;
    boolean placeOrder(CustomOrderEntity customOrderEntity) throws SQLException;

}

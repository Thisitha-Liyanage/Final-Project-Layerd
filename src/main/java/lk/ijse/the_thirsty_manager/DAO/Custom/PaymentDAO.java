package lk.ijse.the_thirsty_manager.DAO.Custom;

import lk.ijse.the_thirsty_manager.DAO.CrudDAO;
import lk.ijse.the_thirsty_manager.Entity.PaymentEntity;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<PaymentEntity> {
    PaymentEntity findOrder(String ID) throws SQLException;
    String duplicate(String ID) throws SQLException;
}

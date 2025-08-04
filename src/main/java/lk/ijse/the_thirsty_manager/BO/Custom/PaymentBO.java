package lk.ijse.the_thirsty_manager.BO.Custom;

import lk.ijse.the_thirsty_manager.BO.Custom.IMPL.PaymentBOIMPL;
import lk.ijse.the_thirsty_manager.BO.SuperBO;
import lk.ijse.the_thirsty_manager.Dto.PaymentDto;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {
    boolean save(PaymentDto paymentDto) throws SQLException;
    PaymentDto findOrder(String ID) throws SQLException;
    String nextID() throws SQLException;
    boolean duplicate(String oID) throws SQLException;
    List<PaymentDto> getAll() throws SQLException;
    PaymentDto searchByID(String iD) throws SQLException;
}

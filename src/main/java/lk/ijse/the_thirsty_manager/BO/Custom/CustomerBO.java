package lk.ijse.the_thirsty_manager.BO.Custom;

import lk.ijse.the_thirsty_manager.BO.SuperBO;
import lk.ijse.the_thirsty_manager.Dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean save(CustomerDto customerDto) throws SQLException;
    boolean update(CustomerDto customerDto) throws SQLException;
    boolean delete(String ID) throws SQLException;
    List<CustomerDto> getAll()throws SQLException;
    String nextID() throws SQLException;
    CustomerDto searchCustomer(String ID) throws SQLException;
}

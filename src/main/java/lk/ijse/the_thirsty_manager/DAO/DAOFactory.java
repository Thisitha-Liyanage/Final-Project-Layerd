package lk.ijse.the_thirsty_manager.DAO;

import lk.ijse.the_thirsty_manager.BO.Custom.IMPL.ItemBOIMPL;
import lk.ijse.the_thirsty_manager.DAO.Custom.IMPL.*;
import lk.ijse.the_thirsty_manager.DAO.Custom.IMPL.OrderDetailsDAOIMPL;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getInstance(){
        return daoFactory == null ? (daoFactory = new DAOFactory() ): daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes) {
        return switch (daoTypes) {
            case CUSTOMER -> (T) new CustomerDAOIMPL();
            case ORDER -> (T) new OrderDAOIMPL();
            case ITEM -> (T) new ItemDAOIMPL();
            case INVENTORY -> (T) new InventoryDAOIMPL();
            case SUPPLIER -> (T) new SupplierDAOIMPL();
            case INGREDIENTS -> (T) new IngerdientsDAOIMPL();
            case EMPLOYEE -> (T) new EmployeeDAOIMPL();
            case PAYMENT -> (T) new PaymentDAOIMPL ();
            case TABLE -> (T) new TableDAOIMPL();
            case SALARY -> (T) new SalaryDAOIMPL();
            case ATTENDANCE -> (T) new AttendanceDAOIMPL();
            case ORDER_DETAILS -> (T) new OrderDetailsDAOIMPL();
            case QUERY -> (T) new QueryDAOIMPL();
            case LOGIN -> (T) new LoginDAOIMPL();
        };

    }

}

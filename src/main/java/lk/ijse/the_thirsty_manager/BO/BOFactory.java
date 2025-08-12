package lk.ijse.the_thirsty_manager.BO;

import lk.ijse.the_thirsty_manager.BO.Custom.IMPL.*;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory(){}

    public static BOFactory getInstance(){
        return boFactory == null ? (boFactory = new BOFactory()) : boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boType) {
        return switch (boType) {
            case CUSTOMER -> (T) new CustomerBOIMPL();
            case ORDER -> (T) new OrderBOIMPL();
            case ITEM -> (T) new ItemBOIMPL();
            case INVENTORY -> (T) new InventoryBOIMPL();
            case SUPPLIER -> (T) new SupplierBOIMPL();
            case INGREDIENTS -> (T) new IngredeintBOIMPL();
            case EMPLOYEE -> (T) new EmployeeBOIMPL();
            case PAYMENT -> (T) new PaymentBOIMPL();
            case TABLE -> (T) new TableBOIMPL();
            case SALARY -> (T) new SalaryBOIMPL();
            case ATTENDANCE -> (T) new AttendanceBOIMPL();
            case LOGIN -> (T) new LoginBOIMPL();
        };

    }
}

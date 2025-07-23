package lk.ijse.the_thirsty_manager.BO;

import lk.ijse.the_thirsty_manager.BO.Custom.IMPL.CustomerBOIMPL;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory(){}

    public static BOFactory getInstance(){
        return boFactory == null ? (boFactory = new BOFactory()) : boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boType) {
        return (T) new CustomerBOIMPL();

    }
}

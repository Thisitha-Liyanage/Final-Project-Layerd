package lk.ijse.the_thirsty_manager.DAO;

import lk.ijse.the_thirsty_manager.DAO.Custom.IMPL.CustomerDAOIMPL;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getInstance(){
        return daoFactory == null ? (daoFactory = new DAOFactory() ): daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes) {
        return (T) new CustomerDAOIMPL();

    }

}

package lk.ijse.the_thirsty_manager.DAO;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public DAOFactory getInstance(){
        return daoFactory == null ? (daoFactory = new DAOFactory() ): daoFactory;
    }
}

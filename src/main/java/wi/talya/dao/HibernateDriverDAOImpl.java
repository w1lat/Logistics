package wi.talya.dao;

import org.apache.log4j.Logger;
import wi.talya.model.Driver;
import wi.talya.utils.ConnectionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Таня on 23.07.2015.
 */
public class HibernateDriverDAOImpl implements DriverDAO {

    private final static Logger LOG =  Logger.getLogger(CargoDAO.class);
    private EntityManagerFactory factory;

    public HibernateDriverDAOImpl() {
        this.factory = ConnectionFactory.getConnection();
    }

    public Driver getFreeDriver() {
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Driver> query = manager.createQuery("SELECT d FROM Driver d WHERE busy = false", Driver.class);
        List<Driver> list = query.getResultList();
        return list.get(0);
    }

    public Driver addDriver(Driver driver) {
        return null;
    }

    public void updateDriver(Driver driver) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.merge(driver);
            transaction.commit();
        }catch (Exception e){
            LOG.error(e);
            transaction.rollback();
        }
        manager.close();
    }
}

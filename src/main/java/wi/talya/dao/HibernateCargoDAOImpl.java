package wi.talya.dao;

import org.apache.log4j.Logger;
import wi.talya.model.Cargo;
import wi.talya.model.CargoState;
import wi.talya.utils.ConnectionFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Таня on 14.07.2015.
 */
public class HibernateCargoDAOImpl implements CargoDAO {

    private static final Logger LOG = Logger.getLogger(CargoDAO.class);
    private EntityManagerFactory factory;

    public HibernateCargoDAOImpl() {
        this.factory = ConnectionFactory.getConnection();
    }

    public Cargo addCargo(Cargo cargo) {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            cargo.setState(CargoState.OFSTOCK);
            entityManager.persist(cargo);
            transaction.commit();
            return cargo;
        }catch (Exception e){
            LOG.error(e);
            transaction.rollback();
        }
        entityManager.close();
        return null;
    }

    public void updateCargo(Cargo cargo) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try{
            transaction.begin();
            manager.refresh(cargo);
            transaction.commit();
        }catch (Exception e){
            LOG.error(e);
            transaction.rollback();
        }
        manager.close();
    }

    public Cargo removeCargo(long id) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        Cargo found = manager.find(Cargo.class, id);
        try {
            transaction.begin();
            manager.remove(found);
            transaction.commit();
        }catch (Exception e){
            LOG.error(e);
            transaction.rollback();
            found = null;
        }
        manager.close();
        return found;
    }

    public Cargo getById(long id) {
        EntityManager entityManager = factory.createEntityManager();
        return entityManager.find(Cargo.class, id);
    }

    public List<Cargo> getAll() {
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Cargo> query = manager.createQuery("SELECT c FROM Cargo c", Cargo.class);
        List<Cargo> all = query.getResultList();
        manager.close();
        return all;
    }

    public List<Cargo> getAllToDeliver() {
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Cargo> query = manager.createQuery("SELECT c FROM Cargo c WHERE state = 'OFSTOCK'", Cargo.class);
        List<Cargo> all = query.getResultList();
        manager.close();
        return all;
    }
}

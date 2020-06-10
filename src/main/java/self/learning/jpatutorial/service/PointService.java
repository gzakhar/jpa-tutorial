package self.learning.jpatutorial.service;

import org.springframework.stereotype.Service;
import self.learning.jpatutorial.dao.Point;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.mchange.v2.c3p0.*;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

@Service
public class PointService {

    @PostConstruct
    public void initialized() throws PropertyVetoException, SQLException {
        System.out.println("Service initialized");

        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.cj.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/hibernate_db" );
        cpds.setUser("root");
    }

    public int addPoint(Point point) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PERSISTENCE");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(point);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return 1;
    }

    public int removePoint(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PERSISTENCE");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.remove(entityManager.find(Point.class, id));
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return 1;
    }

    public Point getPoint(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PERSISTENCE");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Point point = entityManager.find(Point.class, id);
        entityManager.close();
        entityManagerFactory.close();
        return point;
    }
}

package com.artherus.carshop.DAO;

import com.artherus.carshop.HibernateDatabaseConfig;
import com.artherus.carshop.models.CarModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
class CarModelDAOTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CarModelDAO carModelDAO;

    @Test
    void getCarModelsByName() {
        List<CarModel> models = carModelDAO.getCarModelsByName("X5");
        assertEquals(1, models.size());
        assertEquals("X5", models.get(0).getModel());
    }

    @Test
    void getCarModelsByManufacturer() {
        List<CarModel> models = carModelDAO.getCarModelsByManufacturer("Mercedes");
        assertEquals(2, models.size());
        assertEquals("Mercedes", models.get(0).getManufacturer());
    }

    @BeforeEach
    void beforeEach() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query0 = session.createQuery("DELETE FROM Order");
        query0.executeUpdate();
        Query query1 = session.createQuery("DELETE FROM Client");
        query1.executeUpdate();
        Query query2 = session.createQuery("DELETE FROM Car");
        query2.executeUpdate();
        Query query3 = session.createQuery("DELETE FROM CarModel");
        query3.executeUpdate();
        session.createSQLQuery("ALTER SEQUENCE ORDER_SEQ RESTART WITH 1;").executeUpdate();
        session.createSQLQuery("ALTER SEQUENCE CAR_MODEL_SEQ RESTART WITH 1;").executeUpdate();
        session.createSQLQuery("ALTER SEQUENCE CLIENT_SEQ RESTART WITH 1;").executeUpdate();
        session.createSQLQuery("ALTER SEQUENCE CAR_SEQ RESTART WITH 1;").executeUpdate();
        session.getTransaction().commit();
        List<CarModel> models = new ArrayList<>();
        models.add(new CarModel(null, "X5", "BMW"));
        models.add(new CarModel(null, "X3", "BMW"));
        models.add(new CarModel(null, "M3", "BMW"));
        models.add(new CarModel(null, "C-Class", "Mercedes"));
        models.add(new CarModel(null, "E-Class", "Mercedes"));
        for (CarModel model: models) {
            carModelDAO.addEntity(model);
        }
    }
}
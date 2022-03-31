package com.artherus.carshop.DAO;

import com.artherus.carshop.HibernateDatabaseConfig;
import com.artherus.carshop.models.CarModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
@ContextConfiguration(classes = HibernateDatabaseConfig.class)
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
    }

    @BeforeEach
    void beforeEach() {
        List<CarModel> models = new ArrayList<CarModel>();
        models.add(new CarModel(null, "X5", "BMW"));
        models.add(new CarModel(null, "X3", "BMW"));
        models.add(new CarModel(null, "M3", "BMW"));
        models.add(new CarModel(null, "C-Class", "Mercedes"));
        models.add(new CarModel(null, "E-Class", "Mercedes"));
        for (CarModel model: models) {
            carModelDAO.addEntity(model);
        }
    }

    @BeforeAll
    @AfterEach
    void restart() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE carshop_db.public.car_models RESTART IDENTITY CASCADE;").executeUpdate();
            session.createSQLQuery("ALTER SEQUENCE car_models_model_id_seq RESTART WITH 1;").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
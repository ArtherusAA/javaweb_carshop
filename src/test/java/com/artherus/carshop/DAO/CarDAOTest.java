package com.artherus.carshop.DAO;

import com.artherus.carshop.models.Car;
import com.artherus.carshop.models.CarModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
class CarDAOTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CarModelDAO carModelDAO;

    @Autowired
    private CarDAO carDAO;

    @Test
    void getCarByModel() {
        List<Car> cars = carDAO.getCarByModel("X5");
        assertEquals(1, cars.size());
    }

    @Test
    void getCarByManufacturer() {
        List<Car> cars = carDAO.getCarByManufacturer("BMW");
        assertEquals(2, cars.size());
    }

    @Test
    void getCarById() {
        Car car = carDAO.getCarById(1);
        assertEquals("X5", car.getModel_id().getModel());
    }

    @Test
    void getCarByIdNull() {
        Car car = carDAO.getCarById(100);
        assertNull(car);
    }

    @Test
    void getCarByColor() {
        List<Car> cars = carDAO.getCarByColor("red");
        assertEquals(2, cars.size());
    }

    @Test
    void getCarInPriceRange() {
        List<Car> cars = carDAO.getCarInPriceRange(new BigDecimal(500), new BigDecimal(1500));
        assertEquals(2, cars.size());
    }

    @BeforeEach
    void restart() {
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
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(null, carModelDAO.getCarModelsByName("X5").get(0), "embed1",
                "uphols1", "red", "seat_type1",
                new Timestamp(1001000),
                100, new BigDecimal(1000)));
        cars.add(new Car(null, carModelDAO.getCarModelsByName("X3").get(0), "embed2",
                "uphols2", "blue", "seat_type2",
                new Timestamp(1000000),
                100, new BigDecimal(2000)));
        cars.add(new Car(null, carModelDAO.getCarModelsByName("C-Class").get(0), "embed1",
                "uphols2", "red", "seat_type2",
                new Timestamp(1001000),
                100, new BigDecimal(1000)));
        for (Car car: cars) {
            carDAO.addEntity(car);
        }
    }
}
package com.artherus.carshop.DAO;

import com.artherus.carshop.models.Car;
import com.artherus.carshop.models.CarModel;
import com.artherus.carshop.models.Client;
import com.artherus.carshop.models.Order;
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
class ClientDAOTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private CarModelDAO carModelDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private CarDAO carDAO;

    @Test
    void getClientByOrderId() {
        Client res = clientDAO.getClientByOrderId(1);
        assertEquals("Artur A", res.getName());
    }

    @Test
    void getClientByOrderIdNull() {
        Client res = clientDAO.getClientByOrderId(1000);
        assertNull(res);
    }

    @Test
    void getClientsByOrderDateRange() {
        List<Client> res = clientDAO.getClientsByOrderDateRange(new Timestamp(1000000), new Timestamp(2000000));
        assertEquals(1, res.size());
    }

    @Test
    void getClientsByOrderDateRangeNull() {
        List<Client> res = clientDAO.getClientsByOrderDateRange(new Timestamp(1), new Timestamp(20));
        assertNull(res);
    }

    @Test
    void getClientsByOrderCar() {
        List<Client> res = clientDAO.getClientsByOrderCar(carDAO.getCarById(1));
        assertEquals(1, res.size());
    }

    @Test
    void getClientsByOrderCarNull() {
        List<Client> res = clientDAO.getClientsByOrderCar(carDAO.getCarById(2));
        assertNull(res);
    }

    @Test
    void getClientById() {
        Client res = clientDAO.getClientById(1);
        assertEquals("Artur A", res.getName());
    }

    @Test
    void getClientByIdNull() {
        Client res = clientDAO.getClientById(100);
        assertNull(res);
    }

    @Test
    void getClientsByName() {
        List<Client> res = clientDAO.getClientsByName("Artur");
        assertEquals(2, res.size());
    }

    @Test
    void getClientsByPhone() {
        List<Client> res = clientDAO.getClientsByPhone("7377");
        assertEquals(1, res.size());
    }

    @Test
    void getClientsByEmail() {
        List<Client> res = clientDAO.getClientsByEmail("ande@mail.ru");
        assertEquals(1, res.size());
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
        cars.add(new Car(null, carModelDAO.getCarModelsByName("X5").get(0), "embed",
                "uphols", "red", "seat_type",
                new Timestamp(1000000),
                100, new BigDecimal(1000)));
        cars.add(new Car(null, carModelDAO.getCarModelsByName("X3").get(0), "embed",
                "uphols", "red", "seat_type",
                new Timestamp(1000000),
                100, new BigDecimal(1000)));
        for (Car car: cars) {
            carDAO.addEntity(car);
        }
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(null, "Artur A", "and st, 12", "+7977", "and@mail.ru"));
        clients.add(new Client(null, "Andrew A", "aasdfsadd st, 14", "+7877", "andi@mail.ru"));
        clients.add(new Client(null, "John B", "anasdfd st, 11", "+7277", "ande@mail.ru"));
        clients.add(new Client(null, "Artur C", "abasfdnd st, 13", "+7377", "bnd@mail.ru"));
        for (Client client: clients) {
            clientDAO.addEntity(client);
        }
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(null, new Timestamp(1001000),
                clientDAO.getClientsByName("Artur A").get(0), carDAO.getCarById(1),
                false, "DONE"));
        for (Order order: orders) {
            orderDAO.addEntity(order);
        }
    }
}
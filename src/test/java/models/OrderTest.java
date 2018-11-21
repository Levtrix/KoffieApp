package models;

import KoffieAppRESTServer.models.Drink;
import KoffieAppRESTServer.models.Employee;
import KoffieAppRESTServer.models.Order;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {
    private Employee employee;
    private Drink drink;

    @Before
    public void TestInitialize() {
        employee = new Employee(1, "John", "Doe");
        drink = new Drink(1, "Koffie");
    }

    @Test
    public void TestConstructor() {
        Order order = new Order(1, employee, drink, 2, 1);

        assertEquals(order.getEmployee(), employee);
        assertEquals(order.getDrink(), drink);
        assertEquals(order.getSugarAmount(), 2);
        assertEquals(order.getMilkAmount(), 1);
    }

}
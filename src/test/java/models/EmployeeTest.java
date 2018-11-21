package models;

import KoffieAppRESTServer.models.Employee;
import org.junit.*;

import static org.junit.Assert.*;

public class EmployeeTest {

    @Test
    public void TestConstructor() {
        Employee employee = new Employee(1, "John", "Doe");

        assertEquals(employee.getFirstName(), "John");
        assertEquals(employee.getLastName(), "Doe");
    }

}
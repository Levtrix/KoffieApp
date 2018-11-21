package KoffieAppRESTServer.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {

    @Test
    public void TestConstructor() {
        Employee employee = new Employee(1, "John", "Doe");

        assertEquals(employee.getFirstName(), "John");
        assertEquals(employee.getLastName(), "Doe");
    }

}
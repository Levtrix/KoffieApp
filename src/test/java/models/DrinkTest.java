package models;

import org.junit.*;

import static org.junit.Assert.*;

public class DrinkTest {

    @Test
    public void TestConstructor() {
        Drink drink = new Drink("Koffie");

        assertEquals(drink.getName(), "Koffie");
    }

}
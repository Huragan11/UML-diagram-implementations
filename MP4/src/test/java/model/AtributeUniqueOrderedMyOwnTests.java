package model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import AtributeUniqueOrderedMyOwn.Car;

import java.util.Date;

public class AtributeUniqueOrderedMyOwnTests {

    Car c1;

    @BeforeEach
    void setup() {
        c1 = new Car("Toyota", "Black", 2000, "12345678912345678", new Date(2005-1900,0,1));
    }

    @Test
    void checkBrandTest() {
        assertThrows(IllegalArgumentException.class, () -> new Car(null, "Black", 2000, "12345678912345678", new Date(2005-1900,0,1)));
        assertThrows(IllegalArgumentException.class, () -> new Car("", "Black", 2000, "12345678912345678", new Date(2005-1900,0,1)));
    }

    @Test
    void checkColorTest() {
        assertThrows(IllegalArgumentException.class, () -> c1.setColor(null));
        assertThrows(IllegalArgumentException.class, () -> c1.setColor(""));
    }

    @Test
    void checkWeightTest() {
        assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", "Black", 2999, "12345678912345678", new Date(2005-1900,0,1)));
        assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", "Black", 3001, "12345678912345678", new Date(2005-1900,0,1)));
    }

    @Test
    void checkVINTest() {
        assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", "Black", 2000, null, new Date(2005-1900,0,1)));
        assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", "Black", 2000, "", new Date(2005-1900,0,1)));
    }

    @Test
    void checkIfCarExistsTest() {
        assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", "Black", 2000, "12345678912345678", new Date(2005-1900,0,1)));
    }

    @Test
    void checkDateOfProductionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", "Black", 2000, "12345678912345678", new Date(2000-1900,0,1)));
    }

    @Test
    void getBrandTest() {
        assertEquals("Toyota", c1.getBrand());
    }

    @Test
    void getColorTest() {
        assertEquals("Black", c1.getColor());
    }

    @Test
    void getWeightTest() {
        assertEquals(2000, c1.getWeight());
    }

    @Test
    void getMaxWeightTest() {
        assertEquals(3000, Car.getMaxWeight());
    }

    @Test
    void setAndGetColorTest() {
        c1.setColor("Red");
        assertEquals("Red", c1.getColor());
    }

    @Test
    void getVINTest() {
        assertEquals("12345678912345678", c1.getVIN());
    }

    @Test
    void getDateOfProductionTest() {
        assertEquals(new Date(2005-1900,0,1), c1.getDateOfProduction());
    }

    @Test
    void getCarsTest() {
        assertEquals(1, Car.getCars().size());
    }

    @Test
    void getMinDateOfProductionTest() {
        assertEquals(new Date(2001-1900,0,1), Car.getMinDateOfProduction());
    }


}

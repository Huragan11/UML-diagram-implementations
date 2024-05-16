package model;
import org.example.composition.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompositionTest {

    Car c1;
    Car c2;

    @BeforeEach
    void setup() {
        Accessory.resetExtent();
        c1 = new Car("Volvo");
        c2 = new Car("BMW");
    }

    @Test
    void createAccessoryTest() {
        //you cannot create an accessory without a car
        assertThrows(IllegalArgumentException.class, () -> { new Accessory("name", 10,null); });
        Accessory a1 = new Accessory("name", 10, c1);
        assertEquals(c1, a1.getCar());
        assertTrue(c1.getAccessories().contains(a1));
    }

    @Test
    void removeAccessoryFromCarTest() {
        Accessory a1 = new Accessory("name", 10, c1);
        assertEquals(c1, a1.getCar());
        assertTrue(c1.getAccessories().contains(a1));
        assertTrue(Accessory.getExtent().contains(a1));

        //remove from car
        c1.removeAccessory(a1);
        assertEquals(null, a1.getCar());
        assertFalse(c1.getAccessories().contains(a1));

        //accessory should not exists anymore
        assertFalse(Accessory.getExtent().contains(a1));
    }

    @Test
    void switchAccessoryOwner() {
        Accessory a1 = new Accessory("name", 10, c1);
        assertEquals(c1, a1.getCar());
        assertTrue(c1.getAccessories().contains(a1));

        //its not possible to change an owner of a composed object
        assertThrows(IllegalArgumentException.class,
            () -> {
                c2.addAccessory(a1);
            }
        );
    }
}

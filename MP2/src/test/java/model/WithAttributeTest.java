package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.withAttribute.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class WithAttributeTest {

    Car c1;
    Car c2;
    Garage g1;
    Garage g2;

    @BeforeEach
    void setup() {
        c1 = new Car("Audi");
        c2 = new Car("Volvo");
        g1 = new Garage("RickysGarage");
        g2 = new Garage("MikesGarage");
    }

    @Test
    void createSuccessfully() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Service(LocalDate.now(), null, c1);
                },
                "you should not allow to create a association object with null garage");
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Service(LocalDate.now(), g1, null);
                },
                "you should not allow to create a association object with null car");

        Service s1 = new Service(LocalDate.now(), g1, c1);

        //all references should be set
        assertEquals(g1, s1.getGarage());
        assertEquals(c1, s1.getCar());
        assertTrue(g1.getServices().contains(s1));
        assertTrue(c1.getServices().contains(s1));

        //attempt to use add this service to unrelated Garage
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    g2.addService(s1);
                }
        );

        //attempt to use add this service to unrelated Car
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    c2.addService(s1);
                }
        );

        //attempt to use add this service to the same Garage and Car
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    g2.addService(s1);
                }
        );

        c1.removeCoupling(s1);

        //now all 4 references should be removed
        assertNull(s1.getGarage());
        assertNull(s1.getCar());
        assertFalse(g1.getServices().contains(s1));
        assertFalse(c1.getServices().contains(s1));

    }

}

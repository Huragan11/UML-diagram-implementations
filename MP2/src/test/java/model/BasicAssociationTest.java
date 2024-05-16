package model;

import org.example.basic.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class BasicAssociationTest {

    Part p1;
    Garage g1;
    Garage g2;

    @BeforeEach
    void setup() {
        p1 = new Part("1385967492");
        g1 = new Garage("TommysGarage");
        g2 = new Garage("MikesGarage");
    }

    @Test
    void setGarageTest() {
        p1.setGarage(g1);
        assertEquals(g1, p1.getGarage());
        assertTrue(g1.getParts().contains(p1));
    }

    @Test
    void addPartTest() {
        //should not allow to add relation with a null for a collection
        assertThrows(IllegalArgumentException.class, () -> g1.addPart(null));
        g1.addPart(p1);
        assertEquals(g1, p1.getGarage());
        assertTrue(g1.getParts().contains(p1));
    }

    @Test
    void addAndRemoveFromPart() {
        p1.setGarage(g1);
        assertEquals(g1, p1.getGarage());
        assertTrue(g1.getParts().contains(p1));

        p1.removeGarage();
        assertEquals(null, p1.getGarage());
        assertFalse(g1.getParts().contains(p1));

    }

    @Test
    void addAndRemoveFromGarage() {
        p1.setGarage(g1);
        assertEquals(g1, p1.getGarage());
        assertTrue(g1.getParts().contains(p1));

        g1.removePart(p1);
        assertEquals(null, p1.getGarage());
        assertFalse(g1.getParts().contains(p1));

    }

}
package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.qualified.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class QualifiedTest {

    Car c1;
    Part p1;

    @BeforeEach
    void setup() {
        c1 = new Car("Toyota");
        p1 = new Part("1628574934", "1Q2W3E4R5T6Y7U8I9");
    }

    @Test
    void createTest() {
        c1.addPart(p1);
        assertTrue( c1.getParts().get(p1.getVIN()) == p1);
        assertTrue( p1.getCar() == c1);

        //check if  works properly
        assertEquals(p1, c1.getPartByVIN(p1.getVIN()));
    }

    @Test
    void createTest2() {
        p1.setCar(c1);
        assertTrue( c1.getParts().get(p1.getVIN()) == p1);
        assertTrue( p1.getCar() == c1);

        //check if  works properly
        assertEquals(p1, c1.getPartByVIN(p1.getVIN()));
    }

    @Test
    void removeTest() {
        c1.addPart(p1);
        assertTrue( c1.getParts().get(p1.getVIN()) == p1 );
        assertTrue( p1.getCar() == c1);

        c1.removePart(p1);
        assertTrue(p1.getCar() == null);
        assertFalse(c1.getParts().containsKey(p1.getVIN()));
    }

    @Test
    void removeTest2() {
        c1.addPart(p1);
        assertTrue( c1.getParts().get(p1.getVIN()) == p1 );
        assertTrue( p1.getCar() == c1);

        p1.removeCar();
        assertTrue(p1.getCar() == null);
        assertFalse(c1.getParts().containsKey(p1.getVIN()));
    }
}

package model;

import Bag.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class BagTests {

    Repair r1;
    Mechanic m1;
    RepairAssignment ra1;

    @BeforeEach
    void setup() {
        r1 = new Repair("Fix the engine");
        m1 = new Mechanic("John");
        ra1 = new RepairAssignment(m1, r1, new Date());
    }

    @Test
    void addRepairAssignmentTest() {
        assertEquals(m1, ra1.getMechanic());
        assertEquals(r1, ra1.getRepair());
        assertTrue(m1.getRepairAssignments().contains(ra1));
        assertTrue(r1.getRepairAssignments().contains(ra1));
    }

    @Test
    void removeRepairAssignmentTest() {
        ra1.removeAssignment();
        assertFalse(m1.getRepairAssignments().contains(ra1));
        assertFalse(r1.getRepairAssignments().contains(ra1));
    }

    @Test
    void setNullMechanicTest() {
        assertThrows(IllegalArgumentException.class, () -> new RepairAssignment(null, r1, new Date()));
    }

    @Test
    void setNullRepairTest() {
        assertThrows(IllegalArgumentException.class, () -> new RepairAssignment(m1, null, new Date()));
    }

    @Test
    void setNullDateTest() {
        assertThrows(IllegalArgumentException.class, () -> new RepairAssignment(m1, r1, null));
    }

    @Test
    void setNullRepairAssignmentTest() {
        assertThrows(IllegalArgumentException.class, () -> m1.addRepairAssignment(null));
    }

    @Test
    void setNullRepairAssignmentTest2() {
        assertThrows(IllegalArgumentException.class, () -> m1.removeRepairAssignment(null));
    }

    @Test
    void setWrongRepairAssignmentTest() {
        Repair r2 = new Repair("Fix the brakes");
        Mechanic m2 = new Mechanic("Mike");
        RepairAssignment ra2 = new RepairAssignment(m2, r2, new Date());
        assertThrows(IllegalArgumentException.class, () -> m1.removeRepairAssignment(ra2));
    }




}

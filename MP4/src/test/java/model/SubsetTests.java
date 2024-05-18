package model;

import Subset.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubsetTests {

    Mechanic m1;
    Repair r1;

    @BeforeEach
    void setup() {
        m1 = new Mechanic("John");
        r1 = new Repair("Fix the engine");
    }

    @Test
    void testMechanicName() {
        assertEquals("John", m1.getName());
    }

    @Test
    void testRepairDescription() {
        assertEquals("Fix the engine", r1.getDescription());
    }

    @Test
    void testAssignRepair() {
        m1.assignRepair(r1);
        assertTrue(m1.getAssignedRepairs().contains(r1));
        assertTrue(r1.getMechanics().contains(m1));
    }

    @Test
    void testUnassignRepair() {
        m1.assignRepair(r1);
        m1.unassignRepair(r1);
        assertFalse(m1.getAssignedRepairs().contains(r1));
        assertFalse(r1.getMechanics().contains(m1));
    }

    @Test
    void testLeadRepair() {
        m1.assignRepair(r1);
        m1.leadRepair(r1);
        assertTrue(m1.getLeadingRepairs().contains(r1));
        assertEquals(m1, r1.getLeaders());
    }

    @Test
    void testRemoveMechanic() {
        m1.assignRepair(r1);
        m1.leadRepair(r1);
        r1.removeMechanic(m1);
        assertFalse(m1.getAssignedRepairs().contains(r1));
        assertFalse(m1.getLeadingRepairs().contains(r1));
        assertNull(r1.getLeaders());
    }

    @Test
    void testStopLeadingRepair() {
        m1.assignRepair(r1);
        m1.leadRepair(r1);
        m1.stopLeadingRepair(r1);
        assertFalse(m1.getLeadingRepairs().contains(r1));
        assertNull(r1.getLeaders());
    }

    @Test
    void testAddLeader() {
        Mechanic m2 = new Mechanic("Jane");
        m2.assignRepair(r1);
        r1.addLeader(m2);
        assertEquals(m2, r1.getLeaders());
    }

    @Test
    void testRemoveLeader() {
        Mechanic m2 = new Mechanic("Jane");
        m2.assignRepair(r1);
        r1.addLeader(m2);
        r1.removeLeader(m2);
        assertNull(r1.getLeaders());
    }

}

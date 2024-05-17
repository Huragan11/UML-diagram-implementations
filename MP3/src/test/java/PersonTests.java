import CarTypes.CarType;
import CarTypes.FuelType;
import PersonTypes.Person;
import PersonTypes.PersonType;
import PersonTypes.EmployeeType;
import CarTypes.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTests {

    private Person client;
    private Person employee;

    @BeforeEach
    public void setUp() {
        Set<PersonType> roles = new HashSet<>();
        roles.add(PersonType.CLIENT);
        roles.add(PersonType.OWNER);
        List<Car> cars = List.of(new Car(FuelType.PETROL, CarType.PASSENGER_CAR, "BMW", "E31", 2005, 4, 0), new Car(FuelType.ELECTRIC, CarType.FREIGHT_CAR, "Audi", "A6", 2010, 0, 4000));
        employee = new Person("Jane Doe", "09876543210", "987654321", Collections.singleton(PersonType.EMPLOYEE), EmployeeType.MECHANIC, 6000.0, LocalDate.now(), 1, 0, "Mechanic", cars);
        client = new Person("John Doe", "12345678901", "123456789", roles, null, 0, null, 2, 0, "", cars);
    }


    @Test
    public void testGetName() {
        assertEquals("John Doe", client.getName());
        assertEquals("Jane Doe", employee.getName());
    }

    @Test
    public void testGetPesel() {
        assertEquals("12345678901", client.getPesel());
        assertEquals("09876543210", employee.getPesel());
    }

    @Test
    public void testGetPhoneNumber() {
        assertEquals("123456789", client.getPhoneNumber());
        assertEquals("987654321", employee.getPhoneNumber());
    }

    @Test
    public void testGetEmployeeType() {
        assertEquals(EmployeeType.ASSISTENT, client.getEmployeeType());
        assertEquals(EmployeeType.MECHANIC, employee.getEmployeeType());
    }

    @Test
    public void testGetMonthsOfWork() {
        assertEquals(12, client.getMonthsOfWork());
        assertEquals(24, employee.getMonthsOfWork());
    }

    @Test
    public void testGetSalary() {
        assertEquals(5000.0, client.getSalary());
        assertEquals(6000.0, employee.getSalary());
    }

    @Test
    public void testGetHireDate() {
        assertEquals(LocalDate.now(), client.getHireDate());
        assertEquals(LocalDate.now(), employee.getHireDate());
    }

    @Test
    public void testGetSpecialization() {
        assertEquals("Mechanic", client.getSpecialization());
        assertEquals("Engineer", employee.getSpecialization());
    }

    @Test
    public void testGetNumberOfGarages() {
        assertEquals(2, client.getNumberOfGarages());
        assertEquals(3, employee.getNumberOfGarages());
    }

    @Test
    public void testChangeRoleToMechanic() {
        client.changeRoleToMechanic("Engineer");
        assertEquals("Engineer", client.getSpecialization());
    }
}
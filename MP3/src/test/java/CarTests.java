import CarTypes.Car;
import CarTypes.CarType;
import CarTypes.FuelType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTests {

    private Car passengerCar;
    private Car freightCar;

    @BeforeEach
    public void setUp() {
        passengerCar = new Car(FuelType.PETROL, CarType.PASSENGER_CAR, "BMW", "E31", 2005, 4, 0);
        freightCar = new Car(FuelType.ELECTRIC, CarType.FREIGHT_CAR, "Audi", "A6", 2010, 0, 4000);
    }

    @Test
    public void testGetBrand() {
        assertEquals("Brand", passengerCar.getBrand());
        assertEquals("Brand", freightCar.getBrand());
    }

    @Test
    public void testGetModel() {
        assertEquals("Model", passengerCar.getModel());
        assertEquals("Model", freightCar.getModel());
    }

    @Test
    public void testGetProductionYear() {
        assertEquals(2005, passengerCar.getProductionYear());
        assertEquals(2010, freightCar.getProductionYear());
    }

    @Test
    public void testGetLoad() {
        assertEquals(0, freightCar.getLoad());
    }

    @Test
    public void testGetFreeSeats() {
        assertEquals(4, passengerCar.getFreeSeats());
    }

    @Test
    public void testGetFuelType() {
        assertEquals(FuelType.PETROL, passengerCar.getFuelType());
        assertEquals(FuelType.ELECTRIC, freightCar.getFuelType());
    }

    @Test
    public void testGetCarType() {
        assertEquals(CarType.PASSENGER_CAR, passengerCar.getCarType());
        assertEquals(CarType.FREIGHT_CAR, freightCar.getCarType());
    }

    @Test
    public void testAddCargo() {
        freightCar.addCargo(1000);
        assertEquals(1000, freightCar.getLoad());
    }

    @Test
    public void testRemoveCargo() {
        freightCar.addCargo(2000);
        freightCar.removeCargo(1000);
        assertEquals(1000, freightCar.getLoad());
    }

    @Test
    public void testAddPassenger() {
        passengerCar.addPassanger();
        assertEquals(3, passengerCar.getFreeSeats());
    }

    @Test
    public void testRemovePassenger() {
        passengerCar.addPassanger();
        passengerCar.removePassanger();
        assertEquals(4, passengerCar.getFreeSeats());
    }
}

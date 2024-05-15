package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.color.ICC_ColorSpace;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarTest {
    @Test
    void createWithValue() {
        Car car = new Car("Toyota", "Corolla", 100, "Diesel");
        assertEquals("Toyota", car.getBrand());
        assertEquals("Corolla", car.getModel());
        assertEquals(100, car.getEngine().getHorsePower());
        assertEquals("Diesel", car.getEngine().getFuelType());
    }

    @Test
    void createWithValueAndColor() {
        Car car = new Car("Toyota", "Corolla", 100, "Diesel", "Red");
        assertEquals("Toyota", car.getBrand());
        assertEquals("Corolla", car.getModel());
        assertEquals(100, car.getEngine().getHorsePower());
        assertEquals("Diesel", car.getEngine().getFuelType());
        assertEquals("Red", car.getColor());
    }

    @Test
    void createWithValueColorAndServices() {
        Car car = new Car("Toyota", "Corolla", 100, "Diesel", "Red", List.of("Oil change", "Tire change"));
        assertEquals("Toyota", car.getBrand());
        assertEquals("Corolla", car.getModel());
        assertEquals(100, car.getEngine().getHorsePower());
        assertEquals("Diesel", car.getEngine().getFuelType());
        assertEquals("Red", car.getColor());
        assertEquals(List.of("Oil change", "Tire change"), car.getServices());
    }

    @Test
    void createWithNullBrand() {
        assertThrows(IllegalArgumentException.class, () -> new Car(null, "Corolla", 100, "Diesel"));
    }

    @Test
    void createWithEmptyBrand() {
        assertThrows(IllegalArgumentException.class, () -> new Car("", "Corolla", 100, "Diesel"));
    }
    @Test
    void createWithNullModel() {
        assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", null, 100, "Diesel"));
    }
    @Test
    void createWithEmptyModel() {
        assertThrows(IllegalArgumentException.class, () -> new Car("Toyota", "", 100, "Diesel"));
    }
    @Test
    void createWithNullBrandAndModel() {
        assertThrows(IllegalArgumentException.class, () -> new Car(null, null, 100, "Diesel"));
    }
    @Test
    void createWithEmptyBrandAndModel() {
        assertThrows(IllegalArgumentException.class, () -> new Car("", "", 100, "Diesel"));
    }
    @Test
    void modifyAddService() {
        Car car = new Car("Toyota", "Corolla", 100, "Diesel");
        car.addService("Oil change");
        assertEquals(1, car.getServices().size());
    }
    @Test
    void modifyAddServiceWithNull() {
        Car car = new Car("Toyota", "Corolla", 100, "Diesel");
        assertThrows(NullPointerException.class, () -> car.addService(null));
    }
    @Test
    void modifyAddServiceWithEmpty() {
        Car car = new Car("Toyota", "Corolla", 100, "Diesel");
        assertThrows(IllegalArgumentException.class, () -> car.addService(""));
    }
    @Test
    void modifyRemoveService() {
        Car car = new Car("Toyota", "Corolla", 100, "Diesel");
        car.removeService("Oil change");
        assertEquals(0, car.getServices().size());
    }
    @Test
    void modifyRemoveServiceWithNull() {
        Car car = new Car("Toyota", "Corolla", 100, "Diesel");
        assertThrows(NullPointerException.class, () -> car.removeService(null));
    }
    @Test
    void modifyRemoveServiceWithEmpty() {
        Car car = new Car("Toyota", "Corolla", 100, "Diesel");
        assertThrows(IllegalArgumentException.class, () -> car.removeService(""));
    }

    @Test
    void modifySetColor() {
        Car car = new Car("Toyota", "Corolla", 100, "Diesel");
        car.setColor("Red");
        assertEquals("Red", car.getColor());
    }

    @Test
    void saveAndReadFromFileSerialization(){
        Car originalCar = new Car("Toyota", "Corolla", 100, "Diesel", "Red", List.of("Oil change", "Tire change"));

        String filename = "car.ser";
        clearFile(filename);
        Car.saveToFile(filename);

        Car.clearCarCollection();
        Car.loadFromFile(filename);

        Car deserializedCar = Car.getCarCollection().get(0);

        assertEquals(originalCar.getBrand(), deserializedCar.getBrand());
        assertEquals(originalCar.getModel(), deserializedCar.getModel());
        assertEquals(originalCar.getEngine().getHorsePower(), deserializedCar.getEngine().getHorsePower());
        assertEquals(originalCar.getEngine().getFuelType(), deserializedCar.getEngine().getFuelType());
        assertEquals(originalCar.getColor(), deserializedCar.getColor());
        assertEquals(originalCar.getServices(), deserializedCar.getServices());
    }

    @BeforeEach
    void setUp() {
        Car.clearCarCollection();
    }

    public void clearFile(String filename){
        try {
            new FileWriter(filename,false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

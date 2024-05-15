package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Car implements Serializable {
    private String brand;
    private String model;
    private String color;
    private Engine engine;
    private List<String> Services;
    private static double standardRepairCost = 250.0;
    private double fullServiceCost;
    private static List<Car> carCollection = new ArrayList<>();

    public Car(String brand, String model, int horsePower, String fuelType) {
        checkIfCoreParametersAreValid(brand, model, horsePower, fuelType);
        this.brand = brand;
        this.model = model;
        this.engine = new Engine(horsePower, fuelType);
        this.color = null;
        this.Services = new ArrayList<>();
        carCollection.add(this);
    }

    public Car(String brand, String model, int horsePower, String fuelType, String color) {
        checkIfCoreParametersAreValid(brand, model, horsePower, fuelType);
        this.brand = brand;
        this.model = model;
        this.engine = new Engine(horsePower, fuelType);
        setColor(color);
        this.Services = new ArrayList<>();
        carCollection.add(this);
    }

    public Car(String brand, String model, int horsePower, String fuelType, String color, List<String> services) {
        checkIfCoreParametersAreValid(brand, model, horsePower, fuelType);
        this.brand = brand;
        this.model = model;
        this.engine = new Engine(horsePower, fuelType);
        setColor(color);
        this.Services = services;
        carCollection.add(this);
    }

    private void checkIfCoreParametersAreValid(String brand, String model, int horsePower, String fuelType) {

        if (brand == null || brand.isEmpty() || model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Brand and model cannot be null or empty.");
        }
        if (horsePower <= 0) {
            throw new IllegalArgumentException("Horse power must be greater than 0.");
        }
        if (fuelType == null || fuelType.isEmpty()) {
            throw new IllegalArgumentException("Fuel type cannot be null or empty.");
        }
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public String getColor() {
        return color;
    }

    public List<String> getServices() {
        return Collections.unmodifiableList(Services);
    }

    public void removeService(String service) {
        if (service == null) {
            throw new NullPointerException("Service cannot be null");
        }
        if (service.isEmpty()) {
            throw new IllegalArgumentException("Service cannot be empty.");
        }
        Services.remove(service);
        fullServiceCost = calculateServiceCost();
    }

    public void addService(String service) {
        if (service == null) {
            throw new NullPointerException("Service cannot be null");
        }
        if (service.isEmpty()) {
            throw new IllegalArgumentException("Service cannot be empty.");
        }
        Services.add(service);
        fullServiceCost = calculateServiceCost();
    }

    public void setColor(String color) {
        if (color == null) {
            throw new NullPointerException("Color cannot be null");
        }
        if (color.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Color cannot contain numbers.");
        }
        this.color = color;
    }

    public static double getStandardRepairCost() {
        return standardRepairCost;
    }

    public static void setStandardRepairCost(double newStandardRepairCost) {
        standardRepairCost = newStandardRepairCost;
    }

    public double calculateServiceCost() {
        return Services.size() * standardRepairCost;
    }

    public static double calculateAverageServiceCost() {
        if (carCollection.isEmpty()) {
            return 0;
        }

        double totalCost = 0;
        for (Car car : carCollection) {
            totalCost += car.calculateServiceCost();
        }
        return totalCost / carCollection.size();
    }

    public static List<Car> getCarCollection() {
        return Collections.unmodifiableList(carCollection);
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", engine=" + engine +
                ", Services=" + Services +
                '}';
    }

    public static void clearCarCollection() {
        carCollection.clear();
    }

    public static void saveToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(carCollection);
            System.out.println("Cars saved to file");
        } catch (IOException e) {
            System.out.println("Error saving cars to file: " + e.getMessage());
        }
    }

    public static void loadFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            carCollection = (List<Car>) inputStream.readObject();
            System.out.println("Cars loaded from file");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading cars from file: " + e.getMessage());
        }

    }
}

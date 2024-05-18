package AtributeUniqueOrderedMyOwn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Car {

    private String brand;
    private String color;
    private int weight;
    private String VIN;
    private static int maxWeight = 3000;
    private Date dateOfProduction;
    private static Date minDateOfProduction = new Date(2001 - 1900, 0, 1);
    private static List<Car> cars = new ArrayList<>();

    public Car(String brand, String color, int weight, String VIN, Date dateOfProduction) {

        checkBrand(brand);
        checkColor(color);
        checkWeight(weight);
        checkVIN(VIN);
        checkIfCarExists(VIN);
        checkDateOfProduction(dateOfProduction);

        this.brand = brand;
        this.color = color;
        this.weight = weight;
        this.VIN = VIN;
        this.dateOfProduction = dateOfProduction;
        cars.add(this);
    }

    private void checkDateOfProduction(Date dateOfProduction) {
        if (dateOfProduction.before(minDateOfProduction)) {
            throw new IllegalArgumentException("Date cannot be before 2001-01-01");
        }
    }


    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        checkColor(color);
        this.color = color;
    }

    public String getVIN() {
        return VIN;
    }

    public Date getDateOfProduction() {
        return dateOfProduction;
    }

    public static Date getMinDateOfProduction() {
        return minDateOfProduction;
    }

    public int getWeight() {
        return weight;
    }

    public static int getMaxWeight() {
        return maxWeight;
    }

    public static List<Car> getCars() {
        cars.sort(Comparator.comparing(c -> c.brand));
        return new ArrayList<>(cars);
    }

    private void checkIfCarExists(String VIN) {
        for (Car car : cars) {
            if (car.VIN.equals(VIN)) {
                throw new IllegalArgumentException("AtributeUniqueOrdered.Car with VIN " + VIN + " already exists.");
            }
        }
    }

    private static void checkBrand(String brand) {
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be null or empty.");
        }
    }

    private static void checkColor(String color) {
        if (color == null || color.isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty.");
        }
        if (color.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Color cannot contain numbers.");
        }
    }

    private static void checkWeight(int weight) {
        if (weight > maxWeight) {
            throw new IllegalArgumentException("Weight cannot be more than " + maxWeight + " kg.");
        }
    }

    private void checkVIN(String VIN) {
        if (!VIN.matches("^[a-zA-Z0-9]{17}$")) {
            throw new IllegalArgumentException("Invalid VIN. It must be exactly 17 characters long and contain only letters and digits.");
        }
    }
}
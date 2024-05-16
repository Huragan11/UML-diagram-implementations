package org.example.composition;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Car {
    String brand;
    Set<Accessory> accessories = new HashSet<>();

    public Car(String brand) {
        checkBrand(brand);
    }

    private void checkBrand(String brand) {
        if (!brand.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Invalid brand. It must contain only letters.");
        }
        this.brand = brand;
    }

    public void setBrand(String brand) {
        checkBrand(brand);
    }

    public String getBrand() {
        return brand;
    }

    public void addAccessory(Accessory a) {
        if (a == null) {
            throw new IllegalArgumentException("Accessory cannot be null.");
        }
        if (a.getCar() == this) {
            accessories.add(a);
            return;
        }
        if (a.car != null) {
            throw new IllegalArgumentException("Accessory already has a car.");
        }

    }

    public void removeAccessory(Accessory a) {
        accessories.remove(a);
        a.removeAccessoryData();
    }
    public void removeCarData() {
        for (Accessory a : new HashSet<>(accessories)) {
            removeAccessory(a);

        }
    }

    public Set<Accessory> getAccessories() {
        return Collections.unmodifiableSet(accessories);
    }
}

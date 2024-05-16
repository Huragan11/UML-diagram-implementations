package org.example.composition;

import java.util.HashSet;
import java.util.Set;

public class Accessory {
    static Set<Accessory> extent = new HashSet<>();
    String name;
    int weight;
    Car car;

    public Accessory(String name,int weight, Car car) {
        checkArguments(name,weight);
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null");
        }
        this.car = car;
        extent.add(this);
        car.addAccessory(this);
    }

    private void checkArguments(String name, int weight) {
        if ( name == null || name.isBlank() || weight < 0) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        this.name = name;
        this.weight = weight;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        checkArguments(name,weight);
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        checkArguments(name,weight);
    }

    public Car getCar() {
        return car;
    }

    public void removeAccessoryData() {
        car = null;
        extent.remove(this);
    }
    public static Set<Accessory> getExtent() {
        return new HashSet<>(extent);
    }

    public static void resetExtent() {
        extent.clear();
    }
}

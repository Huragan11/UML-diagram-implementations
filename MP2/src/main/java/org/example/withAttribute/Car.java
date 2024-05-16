package org.example.withAttribute;

import java.util.HashSet;
import java.util.Set;

public class Car {
    private String brand;
    private Set<Service> services = new HashSet<>();

    public Car(String brand) {
        checkBrand(brand);
    }

    private void checkBrand(String brand) {
        if (!brand.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Invalid brand. It must contain only letters.");
        }
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        checkBrand(brand);
    }

    public Set<Service> getServices() {
        return new HashSet<>(services);
    }

    public void addService(Service service) {
        if (service.getCar() != this) {
            throw new IllegalArgumentException("Service is not for this car");
        }
        if (services.contains(service)) {
            throw new IllegalArgumentException("Service already added");
        }
        services.add(service);
    }

    public void removeService(Service service) {
        services.remove(service);
    }

    public void removeCoupling(Service service) {
        service.removeCoupling();
    }
}
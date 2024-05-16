package org.example.withAttribute;

import java.util.HashSet;
import java.util.Set;

public class Garage {
    private String name;
    private Set<Service> services = new HashSet<>();

    public Garage(String name) {
        checkName(name);
    }

    private void checkName(String name) {
        if (!name.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Invalid name. It must contain only letters.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkName(name);
    }

    public Set<Service> getServices() {
        return new HashSet<>(services);
    }

    public boolean checkIsFull() {
        return services.size() == 7;
    }
    public void addService(Service service) {
        if (service.getGarage() != this) {
            throw new IllegalArgumentException("Service is not for this garage");
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
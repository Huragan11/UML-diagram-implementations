package org.example.qualified;

import java.util.HashMap;
import java.util.Map;

public class Car {
    String brand;
    private Map<String, Part> parts = new HashMap<>();

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

    public Map<String, Part> getParts() {
        return new HashMap<>(parts);
    }

    public Part getPartByVIN(String VIN) {
        return parts.get(VIN);
    }

    public void addPart(Part p) {
        if (parts.containsKey(p.getVIN())) {
            return;
        }
        parts.put(p.getVIN(), p);
        p.setCar(this);

    }

    public void removePart(Part p) {
        if (!parts.containsKey(p.getVIN())) {
            return;
        }
        parts.remove(p.getVIN());
        p.removeCar();

    }

}

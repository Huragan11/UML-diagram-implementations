package org.example.basic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Garage {

    private String name;

    private Set<Part> parts = new HashSet<>();

    public Garage(String name) {
        checkName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkName(name);
    }

    private void checkName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public Set<Part> getParts() {
        return Collections.unmodifiableSet(parts);
    }

    public Part getPart(String serialNumber) {
        checkSerialNumber(serialNumber);
        for (Part p : parts) {
            if (p.getSerialNumber().equals(serialNumber)) {
                return p;
            }
        }
        return null;
    }

    private static void checkSerialNumber(String serialNumber) {
        if (!serialNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid serial number. It must be exactly 10 digits.");
        }
    }

    public void addPart(Part p) {
        if (p == null) {
            throw new IllegalArgumentException("Part cannot be null.");
        }
        this.parts.add(p);
        p.setGarage(this);
    }

    public void removePart(Part p) {
        this.parts.remove(p);
        p.removeGarage();
    }

}

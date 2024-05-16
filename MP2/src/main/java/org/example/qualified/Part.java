package org.example.qualified;

public class Part {
    String serialNumber;
    String VIN;
    Car car;

    public Part(String serialNumber, String VIN) {
        checkSerialNumber(serialNumber);
        checkVIN(VIN);
    }

    private void checkVIN(String VIN) {
        if (!VIN.matches("^[a-zA-Z0-9]{17}$")) {
            throw new IllegalArgumentException("Invalid VIN. It must be exactly 17 characters long and contain only letters and digits.");
        }
        this.VIN = VIN;
    }

    private void checkSerialNumber(String serialNumber) {
        if (!serialNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid serial number. It must be exactly 10 digits.");
        }
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        checkSerialNumber(serialNumber);
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        checkVIN(VIN);
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        if (this.car == car || car == null) {
            return;
        }
        car.addPart(this);
        this.car = car;
    }
    public void removeCar() {
        if (car == null) {
            return;
        }
        car.removePart(this);
        car = null;
    }

}

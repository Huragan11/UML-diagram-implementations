package org.example.basic;

public class Part {

    String serialNumber;
    Garage garage;

    public Part(String serialNumber) {
        checkSerialNumber(serialNumber);
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
    public Garage getGarage() {
        return garage;
    }
    public void setGarage(Garage garage) {
        if(this.garage == garage) {
            return;
        }
        if(garage != null) {
            removeGarage();
            this.garage = garage;
            garage.addPart(this);
        }
    }
    public void removeGarage() {
        if(this.garage != null) {
            Garage tmp = this.garage;
            this.garage = null;
            tmp.removePart(this);
        }
    }
    

}

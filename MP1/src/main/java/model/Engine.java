package model;

import java.io.Serializable;

public class Engine implements Serializable {
    private int horsePower;
    private String fuelType;

    public Engine(int horsePower, String fuelType) {
        this.horsePower = horsePower;
        this.fuelType = fuelType;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public String getFuelType() {
        return fuelType;
    }
    @Override
    public String toString() {
        return "Engine{" +
                "horsePower=" + horsePower +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }

}

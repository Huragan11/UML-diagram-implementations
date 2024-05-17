package CarTypes;

public class ElectricCar extends Car{
    private int rangePerCharge;
    private int batteryCapacity;
    private int maxBatteryCapacity;

    public ElectricCar(CarType carType, String brand, String model, int productionYear, int passengerSeats, int maxLoad, int rangePerCharge, int batteryCapacity, int maxBatteryCapacity) {
        super(FuelType.ELECTRIC, carType, brand, model, productionYear, passengerSeats, maxLoad);
        CheckElectricCarArguments(rangePerCharge, batteryCapacity, maxBatteryCapacity);
        this.rangePerCharge = rangePerCharge;
        this.batteryCapacity = batteryCapacity;
        this.maxBatteryCapacity = maxBatteryCapacity;
    }

    private void CheckElectricCarArguments(int rangePerCharge, int batteryCapacity, int maxBatteryCapacity) {
        if (rangePerCharge < 0) {
            throw new IllegalArgumentException("Range per charge cannot be lesser than 0.");
        }
        if (batteryCapacity < 0) {
            throw new IllegalArgumentException("Battery capacity cannot be lesser than 0.");
        }
        if (maxBatteryCapacity < 0) {
            throw new IllegalArgumentException("Max battery capacity cannot be lesser than 0.");
        }
    }

    protected void rechargeBattery() {
        batteryCapacity = maxBatteryCapacity;
    }
}

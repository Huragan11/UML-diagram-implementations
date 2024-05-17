package CarTypes;

public class HybridCar extends ElectricCar implements IPetrolCar{

    private int litersPer100Km;
    private int litersInTank;
    private int maxLitersInTank;

    public HybridCar(CarType carType, String brand, String model, int productionYear, int passengerSeats, int maxLoad, int rangePerCharge, int batteryCapacity, int maxBatteryCapacity, int litersPer100Km, int litersInTank, int maxLitersInTank) {
        super(carType, brand, model, productionYear, passengerSeats, maxLoad, rangePerCharge, batteryCapacity, maxBatteryCapacity);
        CheckHybridCarArguments(litersPer100Km, litersInTank, maxLitersInTank);
        this.litersPer100Km = litersPer100Km;
        this.litersInTank = litersInTank;
        this.maxLitersInTank = maxLitersInTank;
    }

    private void CheckHybridCarArguments(int litersPer100Km, int litersInTank, int maxLitersInTank) {
        if (litersPer100Km < 0) {
            throw new IllegalArgumentException("Liters per 100 km cannot be lesser than 0.");
        }
        if (maxLitersInTank < 0) {
            throw new IllegalArgumentException("Max liters in tank cannot be lesser than 0.");
        }
        if (litersInTank < 0) {
            throw new IllegalArgumentException("Liters in tank cannot be lesser than 0.");
        }
    }
    @Override
    public void refillFuel() {
        litersInTank = maxLitersInTank;
    }
    @Override
    public void burnFuel() {
        if (litersInTank > 0) {
            litersInTank--;
        }
    }
}

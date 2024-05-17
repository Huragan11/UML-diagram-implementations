package CarTypes;

public class PetrolCar extends Car implements IPetrolCar {

    private int litersPer100Km;
    private int litersInTank;
    private int maxLitersInTank;

    public PetrolCar(CarType carType, String brand, String model, int productionYear, int passengerSeats, int maxLoad, int litersPer100Km, int maxLitersInTank, int litersInTank) {
        super(FuelType.PETROL, carType, brand, model, productionYear, passengerSeats, maxLoad);
        CheckPetrolCarArguments(litersPer100Km, maxLitersInTank, litersInTank);
        this.litersPer100Km = litersPer100Km;
        this.maxLitersInTank = maxLitersInTank;
        this.litersInTank = litersInTank;
    }

    private void CheckPetrolCarArguments(int litersPer100Km, int maxLitersInTank, int litersInTank) {
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

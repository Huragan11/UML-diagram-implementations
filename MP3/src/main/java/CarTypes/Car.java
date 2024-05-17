package CarTypes;

public class Car implements IPassangerCar, IFreightCar {

    private String brand;
    private String model;
    private int productionYear;

    private static final int minProductionYear = 2000;

    private int possibleLoad;
    private int load = 0;
    private int minPossibleLoad = 3000;

    private int freeSeats;
    private int passengerSeats;
    private int minNumberOfSeats = 2;

    private FuelType fuelType;
    private CarType carType;

    public Car(FuelType fuelType, CarType carType, String brand, String model, int productionYear, int passengerSeats, int possibleLoad) {
        CheckCarArguments(carType, brand, model, productionYear);
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.carType = carType;

        switch (carType) {
            case PASSENGER_CAR:
                CheckPassengerCarArguments(passengerSeats);
                this.passengerSeats = passengerSeats;
                this.freeSeats = passengerSeats;
                this.carType = carType;
                break;
            case FREIGHT_CAR:
                CheckFreightCarArguments(possibleLoad);
                this.possibleLoad = possibleLoad;
                this.carType = carType;
                break;
        }
    }

    private static void CheckCarArguments(CarType carType, String brand, String model, int productionYear) {
        if (brand == null || model == null) {
            throw new IllegalArgumentException("Brand or model cannot be null.");
        }
        if (brand.isBlank() || model.isBlank()) {
            throw new IllegalArgumentException("Brand or model cannot be empty.");
        }
        if (productionYear < 2000) {
            throw new IllegalArgumentException("Production year cannot be lesser than 2000.");
        }
        if (carType == null) {
            throw new IllegalArgumentException("Car type cannot be null.");
        }
    }

    private void CheckPassengerCarArguments(int numberOfSeats) {
        if (numberOfSeats < minNumberOfSeats) {
            throw new IllegalArgumentException("Number of seats cannot be lesser than 2.");
        }
    }

    private void CheckFreightCarArguments(int possibleLoad) {
        if (possibleLoad < minPossibleLoad) {
            throw new IllegalArgumentException("Max load cannot be lesser than 3000.");
        }
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public int getLoad() {
        return load;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public CarType getCarType() {
        return carType;
    }

    @Override
    public void addCargo(int weight) {
        if (carType == CarType.FREIGHT_CAR) {
            if (load < possibleLoad) {
                if (load + weight > possibleLoad) {
                    throw new IllegalArgumentException("Cannot load more than max load value");
                } else {
                    load += weight;
                }
            } else {
                throw new IllegalArgumentException("Max load reached.");
            }
        } else {
            throw new IllegalArgumentException("This car is not a freight car.");
        }
    }

    @Override
    public void removeCargo(int weight) {
        if (carType == CarType.FREIGHT_CAR) {
            if (load > 0) {
                if (load - weight < 0) {
                    throw new IllegalArgumentException("Cannot remove more than the current load value.");
                } else {
                    load -= weight;
                }
            } else {
                throw new IllegalArgumentException("No cargo to remove.");
            }
        } else {
            throw new IllegalArgumentException("This car is not a freight car.");
        }
    }

    @Override
    public void addPassanger() {
        if (carType == CarType.PASSENGER_CAR) {
            if (freeSeats > 0) {
                freeSeats--;
            } else {
                throw new IllegalArgumentException("No free seats.");
            }
        } else {
            throw new IllegalArgumentException("This car is not a passenger car.");
        }
    }

    @Override
    public void removePassanger() {
        if (carType == CarType.PASSENGER_CAR) {
            if (freeSeats < passengerSeats) {
                freeSeats++;
            } else {
                throw new IllegalArgumentException("No passengers to remove.");
            }
        } else {
            throw new IllegalArgumentException("This car is not a passenger car.");
        }
    }
}

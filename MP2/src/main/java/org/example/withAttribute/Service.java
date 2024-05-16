package org.example.withAttribute;

import java.time.LocalDate;

public class Service {
    private LocalDate beginService;
    private Garage garage;
    private Car car;

    public Service(LocalDate beginService, Garage garage, Car car) {
        checkBeginService(beginService);
        setGarage(garage);
        setCar(car);
    }
    private void checkBeginService(LocalDate beginService) {
        if (beginService == null) {
            throw new IllegalArgumentException("Begin service cannot be null");
        }
        this.beginService = beginService;
    }
    public LocalDate getBeginService() {
        return beginService;
    }

    public Garage getGarage() {
        return garage;
    }

    public Car getCar() {
        return car;
    }

    private void setGarage(Garage garage) {
        if (garage == null) {
            throw new IllegalArgumentException("Garage cannot be null");
        }
        if (garage.checkIsFull()) {
            throw new IllegalArgumentException("Garage is full");
        }
        if (this.garage == garage) {
            throw new IllegalArgumentException("Service already added to this garage");
        }

        this.garage = garage;
        garage.addService(this);
    }

    private void setCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null");
        }
        if (this.car == car) {
            throw new IllegalArgumentException("Service already added to this car");
        }
        this.car = car;
        car.addService(this);
    }

    public void removeCoupling() {
        removeGarage();
        removeCar();
    }
    public void removeGarage() {
        garage.removeService(this);
        garage = null;
    }

    public void removeCar() {
        car.removeService(this);
        car = null;
    }


}
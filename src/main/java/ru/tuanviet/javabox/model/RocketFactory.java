package ru.tuanviet.javabox.model;

public class RocketFactory {

    private FuelProvider fuelProvider;

    public RocketFactory() {
        this.fuelProvider = new FuelProvider();
    }

    public RocketFactory(FuelProvider fuelProvider) {
        this.fuelProvider = fuelProvider;
    }

    public Rocket makeRocket() {
        Rocket newRocket = new Rocket();

        FuelContainer acquiredFuelContainer = fuelProvider.transportSomeFuel();
        newRocket.addFuelFrom(acquiredFuelContainer);

        return newRocket;
    }

}

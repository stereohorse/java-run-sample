package ru.tuanviet.javabox.model;

public class FuelContainer {
    private double fuelAmount;

    /**
     * Creates a fuel container with random non-zero amount of fuel.
     * 
     * @return {@link FuelContainer} with fuel
     */
    public static FuelContainer withRandomFuelAmount() {
        return new FuelContainer(Math.random() * 100 + 1);
    }

    private FuelContainer(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    /**
     * Returns all available fuel from the container.
     * Container becomes empty after this operation.
     */
    public double extractAllFuel() {
        double availableFuel = fuelAmount;
        fuelAmount = 0;

        return availableFuel;
    }
}

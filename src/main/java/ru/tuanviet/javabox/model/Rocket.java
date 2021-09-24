package ru.tuanviet.javabox.model;

public class Rocket {

    private double fuelAmount;
    private boolean launched;

    /**
     * Launches the rocket.
     * Uses all available fuel.
     * 
     * @throws {@link IllegalStateException} if the rocket has no fuel at all 
     */
    public void launch() {
        if (fuelAmount == 0) {
            throw new IllegalStateException("Need more vespene gas");
        }

        fuelAmount = 0;
        launched = true;
    }

    public void addFuelFrom(FuelContainer fuelContainer) {
        this.fuelAmount += fuelContainer.extractAllFuel();
    }

    public boolean isLaunched() {
        return launched;
    }
}

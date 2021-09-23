package ru.tuanviet.javabox.model;

public class FuelProvider {

    public FuelContainer transportSomeFuel() {
        try {
            // simulate hard work here!
            Thread.sleep(60_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return FuelContainer.withRandomFuelAmount();
    }
}

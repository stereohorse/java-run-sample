package ru.tuanviet.javabox;

import ru.tuanviet.javabox.model.Rocket;
import ru.tuanviet.javabox.model.RocketFactory;

public class App {
    public static void main(String[] args) {
        RocketFactory mainRocketBuilder = new RocketFactory();

        Rocket firstRocket = mainRocketBuilder.makeRocket();
        firstRocket.launch();
    }
}

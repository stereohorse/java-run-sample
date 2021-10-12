package ru.tuanviet.javabox;

public class App {
    public static void main(String[] args) {

    }

    public String generateRandomName() {
        Person person = new RandomNameGenerator().generatePerson(
            RandomNameGenerator.GeneratePersonParams.builder()
//                .firstParts()
//                .secondParts()
                .build()
        );

        return person.toString();
    }
}

package ru.tuanviet.javabox;

import com.fasterxml.jackson.core.type.TypeReference;
import ru.tuanviet.javabox.generator.RandomNameGenerator;
import ru.tuanviet.javabox.generator.RandomNameGenerator.GeneratePersonParams;

import java.util.ArrayList;
import java.util.List;

public class App {

    private final String namesUri;
    private final String nounsUri;
    private final Integer nounsServiceTimeout;
    private final Integer namesToGenerateCount;

    public App(String namesUri, String nounsUri, Integer nounsServiceTimeout, Integer namesToGenerateCount) {
        this.namesUri = namesUri;
        this.nounsUri = nounsUri;
        this.nounsServiceTimeout = nounsServiceTimeout;
        this.namesToGenerateCount = namesToGenerateCount;
    }

    public static void main(String[] args) {
        List<String> personNames = new App(
            "https://api.npoint.io/f744aa71f0b7c142f0fd",
            "https://api.npoint.io/a742b65192a1e1e22858",
            40_000,
            3
        ).generateRandomName();

        for (final String name : personNames) {
            System.out.println(name);
        }
    }

    public List<String> generateRandomName() {
        HttpClient namesClient = new HttpClient();
        NamesResponse namesResponse = namesClient.fetch(
            namesUri,
            NamesResponse.class
        );

        HttpClient nounsClient = HttpClient.withTimeout(nounsServiceTimeout);
        List<String> nounsResponse = nounsClient.fetch(
            nounsUri,
            new TypeReference<List<String>>() {
            }
        );

        RandomNameGenerator nameGenerator = new RandomNameGenerator();
        List<String> names = new ArrayList<>(namesToGenerateCount);
        for (int i = 0; i < namesToGenerateCount; i++) {
            Person person = nameGenerator.generatePerson(
                GeneratePersonParams.builder()
                    .firstParts(namesResponse.getNames())
                    .secondParts(nounsResponse)
                    .build()
            );
            names.add(person.toString());
        }

        return names;
    }
}

class NamesResponse {

    List<String> names;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}

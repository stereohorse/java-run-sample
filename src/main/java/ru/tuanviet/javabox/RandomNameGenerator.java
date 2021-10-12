package ru.tuanviet.javabox;

import java.util.List;

public class RandomNameGenerator {

    private RandomListElementChooser randomChooser;


    public RandomNameGenerator() {
        randomChooser = new JavaRandomListChooser();
    }

    public RandomNameGenerator(RandomListElementChooser randomChooser) {
        this.randomChooser = randomChooser;
    }


    public Person generatePerson(GeneratePersonParams params) {
        if (params.getFirstParts() == null || params.getFirstParts().isEmpty()) {
            throw new IllegalArgumentException("first parts is null or empty");
        }

        if (params.getSecondParts() == null || params.getSecondParts().isEmpty()) {
            throw new IllegalArgumentException("second parts is null or null");
        }

        final String firstName = randomChooser.nextFrom(params.getFirstParts());
        final String secondName = randomChooser.nextFrom(params.getSecondParts());

        return new Person(firstName, secondName);
    }

    public void setRandomChooser(RandomListElementChooser randomChooser) {
        this.randomChooser = randomChooser;
    }

    static class GeneratePersonParams {
        private final List<String> firstParts;
        private final List<String> secondParts;

        private GeneratePersonParams(List<String> firstParts, List<String> secondParts) {
            this.firstParts = firstParts;
            this.secondParts = secondParts;
        }

        public static GeneratePersonParamsBuilder builder() {
            return new GeneratePersonParamsBuilder();
        }

        public List<String> getFirstParts() {
            return firstParts;
        }

        public List<String> getSecondParts() {
            return secondParts;
        }


        public static class GeneratePersonParamsBuilder {
            private List<String> firstParts;
            private List<String> secondParts;

            private GeneratePersonParamsBuilder() {
            }

            public GeneratePersonParamsBuilder firstParts(List<String> names) {
                this.firstParts = names;
                return this;
            }

            public GeneratePersonParamsBuilder secondParts(List<String> names) {
                this.secondParts = names;
                return this;
            }

            public GeneratePersonParams build() {
                return new GeneratePersonParams(firstParts, secondParts);
            }
        }
    }
}

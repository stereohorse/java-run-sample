package ru.tuanviet.javabox;

import org.junit.Before;
import org.junit.Test;
import ru.tuanviet.javabox.RandomNameGenerator.GeneratePersonParams;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public class RandomNameGeneratorTest {
    private RandomNameGenerator sutRandomGenerator;

    @Before
    public void setupRandomGenerator() {
        sutRandomGenerator = new RandomNameGenerator();
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void shouldThrowIfFirstNamesAreNull() {
//        new RandomNameGenerator().generatePerson(
//                new GeneratePersonParams(null, asList("name1", "name2"))
//        );
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void shouldThrowIfFirstNamesAreEmpty() {
//        new RandomNameGenerator().generatePerson(
//                new GeneratePersonParams(emptyList(), asList("name1", "name2")));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void shouldThrowIfSecondNamesAreNull() {
//        new RandomNameGenerator().generatePerson(
//                new GeneratePersonParams(asList("name1", "name2"), null));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void shouldThrowIfSecondNamesAreEmpty() {
//        new RandomNameGenerator().generatePerson(
//                new GeneratePersonParams(asList("name1", "name2"), emptyList()));
//    }


    @Test
    public void shouldReturnFromSingularInputs() {
        // when
        Person person = sutRandomGenerator.generatePerson(
                GeneratePersonParams.builder()
                        .firstParts(asList("fname"))
                        .secondParts(asList("sname"))
                        .build()
        );

        // then
        assertThat(person.toString()).isEqualTo("fname sname");
    }

    @Test
    public void shouldReceiveGenerateNameParams() {
        sutRandomGenerator.generatePerson(
                GeneratePersonParams.builder()
                        .firstParts(asList("name1", "name2"))
                        .secondParts(asList("sname1", "sname2"))
                        .build()
        );
    }

    @Test
    public void shouldGenerateWithMultipleNamesInArgs() {
        // given
        randomChooserWillReturnOnNextCalls(0, 2);

        // when
        Person person = sutRandomGenerator.generatePerson(
                GeneratePersonParams.builder()
                        .firstParts(asList("fname1", "fname2"))
                        .secondParts(asList("sname1", "sname2", "sname3"))
                        .build()
        );

        // then
        assertThat(person.toString()).isEqualTo("fname1 sname3");
    }

    @Test
    public void shouldReturnCapitalizedName() {

    }

    private void randomChooserWillReturnOnNextCalls(int... indices) {
        sutRandomGenerator.setRandomChooser(
                new PredictableRandomGenerator(indices)
        );
    }
}

class PredictableRandomGenerator implements RandomListElementChooser {

    private final int[] indices;
    private int indicesCursor;

    public PredictableRandomGenerator(int... indices) {
        this.indices = indices;
    }

    @Override
    public String nextFrom(List<String> firstParts) {
        String nextVal = firstParts.get(indices[indicesCursor]);
        indicesCursor += 1;

        return nextVal;
    }
}
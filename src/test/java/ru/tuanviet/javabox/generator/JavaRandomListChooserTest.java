package ru.tuanviet.javabox.generator;

import org.junit.Before;
import org.junit.Test;
import ru.tuanviet.javabox.generator.JavaRandomListChooser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaRandomListChooserTest {

    private JavaRandomListChooser sutRandomChooser;

    @Before
    public void setup() {
        sutRandomChooser = new JavaRandomListChooser();
    }

    @Test
    public void shouldReturnRandomValues() {
        // given
        List<String> strings = Arrays.asList("a", "b", "c", "d", "e");

        // when
        Set<String> generatedStrings = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            generatedStrings.add(sutRandomChooser.nextFrom(strings));
        }

        // then
        assertThat(generatedStrings.size()).isGreaterThan(2);
    }
}

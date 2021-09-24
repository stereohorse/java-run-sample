package ru.tuanviet.javabox;


import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class AppTest {

    @Test
    public void shouldProcessSingleValueList() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        Integer max = App.max(numbers);

        assertThat(max).isEqualTo(10);
    }

    @Test
    public void shouldProcessMultipleValueList() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(16);
        Integer max = App.max(numbers);

        assertThat(max).isEqualTo(16);
    }

    @Test
    public void shouldProcessUnsortedList() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(27);
        numbers.add(10);
        numbers.add(16);
        Integer max = App.max(numbers);

        assertThat(max).isEqualTo(27);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowOnNullInputList() {
        App.max(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowOnEmptyInputList(){
        App.max(Collections.emptyList());
    }
}

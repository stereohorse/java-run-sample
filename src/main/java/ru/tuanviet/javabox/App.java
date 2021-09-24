package ru.tuanviet.javabox;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // search max value from list
    }

    public static Integer max(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Input list is null");
        }

        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("Imput list is empty");
        }

        int max = Integer.MIN_VALUE;

        for (Integer num : numbers) {
            if (max < num) {
                max = num;
            }
        }

        return max;
    }
}

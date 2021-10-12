package ru.tuanviet.javabox;

import java.util.List;
import java.util.Random;

public class JavaRandomListChooser implements RandomListElementChooser {

    private final Random random = new Random();

    @Override
    public String nextFrom(List<String> list) {
        return list.get(random.nextInt(list.size()));
    }
}

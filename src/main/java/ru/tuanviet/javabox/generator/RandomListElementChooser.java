package ru.tuanviet.javabox.generator;

import java.util.List;

public interface RandomListElementChooser {
    String nextFrom(List<String> firstParts);
}

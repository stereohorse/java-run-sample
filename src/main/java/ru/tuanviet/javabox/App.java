package ru.tuanviet.javabox;

public class App {
    public static void main(String[] args) {

    }
}

class Shawarma<T> {
    private T value;

    public Shawarma(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    // TODO: input 2 args: source Shawarma, dest Shawarma
    // TODO: dest should contain source value
    // TODO: name: copy
}

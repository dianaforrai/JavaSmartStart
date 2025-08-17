package com.zoo.animals;

public class Monkey extends Animal {
    public Monkey(String name) {
        super(name, "Monkey");
    }

    @Override
    public String sound() {
        return "Oook ook!";
    }
}
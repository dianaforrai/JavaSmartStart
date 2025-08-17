package com.zoo.animals;

public class Elephant extends Animal {
    public Elephant(String name) {
        super(name, "Elephant");
    }

    @Override
    public String sound() {
        return "Trumpet!";
    }
}



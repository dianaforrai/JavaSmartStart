package com.zoo.animals;

public class Lion extends Animal {
    public Lion(String name) {
        super(name, "Lion");
    }

    @Override
    public String sound() {
        return "Roar!";
    }
}


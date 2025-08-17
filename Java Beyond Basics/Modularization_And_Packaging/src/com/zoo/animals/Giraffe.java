package com.zoo.animals;

public class Giraffe extends Animal {
    public Giraffe(String name) {
        super(name, "Giraffe");
    }

    @Override
    public String sound() {
        return "Bleat!";
    }
}